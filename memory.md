# Session memory — demo_selenium

## Current
- **Date:** 2026-11-11
- **Phase:** done
- **Status:** done

## Done
- 3 mục CLAUDE.md (TestNG, Selenium env, Finding Web Elements).
- Java 25 LTS: `pom.xml` `maven.compiler.release` 25; User `JAVA_HOME` = `C:\Users\Lenovo\.jdk\temurin-25.0.5\jdk-25.0.5+8`.
- Page Object: `pages/SeleniumHomePage`, `pages/LoginPage`, `LoginWithPageObjectTest`.
- Parallel: `ThreadLocal` WebDriver + `testng.xml` `parallel="classes"` `thread-count="2"`.
- Grid: `docker-compose.yml` `selenium/standalone-chrome:4.27.0`; `BaseTest` dùng `RemoteWebDriver` khi `-Dgrid.url` / `GRID_URL`. `mvn test -Dgrid.url=http://localhost:4444` 12/12 pass (2026-11-11 14:44 ICT). Container `demo_selenium-chrome-1` đang chạy, `:4444` + noVNC `:7900`.
- CI file: `.github/workflows/maven-test.yml` (headless Chrome, ubuntu-latest).

## In progress
- (trống)

## Next
- CI chưa chạy trên GitHub (repo chưa có remote).
- CDP warning Chrome 157 vs Selenium 4.27 — không fail test.

## Blockers
- `storage.googleapis.com` vẫn reset — WDM dùng npmmirror.
- Shell tool hiện tại có thể thấy JDK 21 trên PATH; User JAVA_HOME đã là JDK 25 — mở terminal mới.
- CI workflow chưa execute.

## Last session
- 2026-11-11: Thêm PO, parallel, Grid (Docker compose), CI yaml. Grid 12/12. Giữ compiler 25 theo upgrade JDK.

## History
- 2026-11-11: Nâng project/runtime lên Java 25 LTS (Temurin 25.0.5).
- 2026-11-11: WDM npmmirror + fallback local; `mvn test` 10/10.
- 2026-11-11: Gắn JDK + Maven vào JAVA_HOME/PATH User.
- 2026-11-10: Scaffold Maven/TestNG/Selenium; CLAUDE.md + skill session-memory.
