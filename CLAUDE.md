# CLAUDE.md — demo_selenium

## Overview

Repo demo/học Selenium WebDriver trên Java: thiết lập môi trường, TestNG, và các kỹ thuật tìm web element. Mục tiêu là có một project Maven chạy được test trên Chrome, với test class minh họa locator và assertion TestNG.

Stack mục tiêu:

- Java JDK 25
- Maven
- Selenium WebDriver
- TestNG
- WebDriverManager (tự tải ChromeDriver)
- Chrome

Cấu trúc thư mục mục tiêu:

```
demo_selenium/
├── pom.xml
├── CLAUDE.md
├── src/
│   ├── main/java/          # (có thể trống; không bắt buộc business code)
│   └── test/java/
│       └── com/demo/selenium/
│           ├── BaseTest.java
│           ├── SetupSmokeTest.java
│           └── FindElementsTest.java
└── testng.xml
```

---

## 1. TestNG Testing Framework

Mục tiêu: TestNG là test runner (không dùng JUnit). Test chạy bằng Maven Surefire + `testng.xml`.

### Việc cần làm

1. Thêm dependency `org.testng:testng` (scope `test`) vào `pom.xml`.
2. Cấu hình `maven-surefire-plugin` để chạy `testng.xml` (không dựa vào naming convention của JUnit).
3. Tạo `testng.xml`: suite, test, class list.
4. Viết `BaseTest` với `@BeforeMethod` / `@AfterMethod` (mở/đóng browser).
5. Viết ít nhất 1 test smoke dùng `@Test`, `Assert.assertTrue` / `Assert.assertEquals`.
6. Chạy thành công: `mvn test`.

### Checklist xác nhận

- [x] `pom.xml` có TestNG
- [x] `testng.xml` tồn tại và được Surefire trỏ tới
- [x] Có `@BeforeMethod` / `@AfterMethod` / `@Test`
- [x] `mvn test` chạy được và report TestNG

---

## 2. Introduction Selenium and Set up environment

Mục tiêu: môi trường local chạy được 1 script Selenium tối thiểu (mở Chrome, vào 1 URL, đóng browser).

### Việc cần làm — môi trường máy

1. Cài JDK 25 (`java -version`).
2. Cài Maven (`mvn -version`).
3. Cài Google Chrome.
4. (Khuyến nghị) dùng WebDriverManager để không phải tải ChromeDriver thủ công.

### Việc cần làm — project

1. Tạo Maven project (`pom.xml`) với:
   - `org.seleniumhq.selenium:selenium-java`
   - `io.github.bonigarcia:webdrivermanager`
   - TestNG (mục 1)
   - compiler plugin: Java 25
2. `BaseTest`:
   - `WebDriverManager.chromedriver().setup()`
   - `new ChromeDriver()`
   - `driver.manage().window().maximize()`
   - `driver.quit()` trong teardown (luôn chạy, kể cả test fail)
3. Smoke test: `driver.get("https://www.selenium.dev/")` rồi assert title chứa `"Selenium"`.

### Checklist xác nhận

- [x] JDK 25 và Maven đã cài đặt (chọn `JAVA_HOME`/`PATH` phù hợp khi build)
- [x] Chrome đã cài
- [x] `pom.xml` có selenium-java + webdrivermanager
- [x] Chrome tự mở, vào đúng URL, rồi đóng
- [x] `mvn test` pass phần smoke

---

## 3. Finding Web Elements

Mục tiêu: nắm các locator Selenium và dùng được `findElement` / `findElements`.

### Locator cần cover

| Cách | `By.*` | Khi nào dùng |
|------|--------|----------------|
| ID | `By.id` | Ưu tiên nếu element có `id` ổn định |
| Name | `By.name` | Form input (`name` attribute) |
| Class name | `By.className` | Class đơn, không khoảng trắng |
| Tag name | `By.tagName` | Tập element cùng tag (`a`, `input`) |
| Link text | `By.linkText` | Link `<a>` đúng nguyên văn |
| Partial link text | `By.partialLinkText` | Link `<a>` khớp một phần |
| CSS selector | `By.cssSelector` | Nhanh, linh hoạt |
| XPath | `By.xpath` | DOM phức tạp, text, axis |

### Việc cần làm

1. Chọn 1 trang ổn định để practice (khuyến nghị: `https://www.selenium.dev/` hoặc trang demo có form).
2. Viết `FindElementsTest` với từng locator ở bảng trên — mỗi cái 1 `@Test` (hoặc group rõ ràng).
3. Phân biệt:
   - `findElement` → 1 element; không thấy thì `NoSuchElementException`
   - `findElements` → `List<WebElement>`; không thấy thì list rỗng
4. Thao tác cơ bản trên element: `click()`, `sendKeys()`, `getText()`, `isDisplayed()`.
5. (Tối thiểu) 1 test CSS và 1 test XPath cho cùng một element để so sánh.

### Checklist xác nhận

- [x] Cover đủ 8 kiểu locator ở trên
- [x] Có ví dụ `findElement` và `findElements`
- [x] Có assert `isDisplayed()` / `getText()`
- [x] Test fail rõ ràng khi locator sai (không `quit()` sớm làm mất log)

---

## Thứ tự thực hiện

Làm theo thứ tự này; bước sau phụ thuộc bước trước.

1. Cài JDK 25, Maven, Chrome trên máy.
2. Tạo `pom.xml` (compiler 25, selenium-java, webdrivermanager, testng, surefire + `testng.xml`).
3. Tạo `testng.xml` + `BaseTest` (setup/teardown driver).
4. Viết `SetupSmokeTest` — mở Selenium.dev, assert title.
5. `mvn test` pass smoke.
6. Viết `FindElementsTest` theo bảng locator.
7. `mvn test` pass toàn bộ suite.

Không thêm Page Object, parallel, Grid, hay CI cho đến khi 3 mục trên xong.

---

## Skill bắt buộc: session-memory

Luôn dùng skill `.claude/skills/session-memory/SKILL.md`.

1. **Đầu lượt chat:** đọc `memory.md` rồi tiếp tục từ mục `Next`. Không làm lại việc đã `Done`.
2. **Cuối mỗi lượt đã làm việc** (sửa file, cài env, chạy test, hoặc có quyết định cần nhớ): ghi đè `memory.md` theo template trong skill, trước khi kết thúc câu trả lời.
3. `memory.md` là tiến độ runtime. `CLAUDE.md` là kế hoạch. Nếu hai bên lệch, tin file trên đĩa rồi sửa `memory.md`.
