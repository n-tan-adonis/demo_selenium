package com.demo.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    private static final String CFT_MIRROR =
            "https://cdn.npmmirror.com/binaries/chrome-for-testing/%s/%s/chromedriver-%s.zip";
    private static final Path LOCAL_DRIVER = Path.of(
            System.getProperty("user.home"),
            "chromedriver-157",
            "chromedriver-win64",
            "chromedriver.exe");
    private static final Object DRIVER_LOCK = new Object();
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static volatile boolean localDriverResolved;

    protected WebDriver driver() {
        return DRIVER.get();
    }

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        if (headless()) {
            options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        }

        String gridUrl = gridUrl();
        WebDriver driver;
        if (gridUrl != null) {
            try {
                driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), options);
            } catch (Exception e) {
                throw new IllegalStateException("Invalid grid.url: " + gridUrl, e);
            }
        } else {
            resolveChromeDriver();
            driver = new ChromeDriver(options);
        }
        driver.manage().window().maximize();
        DRIVER.set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
        }
        DRIVER.remove();
    }

    private static String gridUrl() {
        String url = System.getProperty("grid.url");
        if (url == null || url.isBlank()) {
            url = System.getenv("GRID_URL");
        }
        if (url == null || url.isBlank()) {
            return null;
        }
        return url;
    }

    private static boolean headless() {
        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            return true;
        }
        return "true".equalsIgnoreCase(System.getenv("HEADLESS"));
    }

    private void resolveChromeDriver() {
        if (localDriverResolved) {
            return;
        }
        synchronized (DRIVER_LOCK) {
            if (localDriverResolved) {
                return;
            }
            try {
                WebDriverManager manager = WebDriverManager.chromedriver();
                manager.config().setChromeDownloadUrlPattern(CFT_MIRROR);
                manager.useMirror().setup();
                localDriverResolved = true;
                return;
            } catch (Exception ignored) {
                // Google Storage is blocked; mirror or cache may still fail
            }
            if (Files.isRegularFile(LOCAL_DRIVER)) {
                System.setProperty("webdriver.chrome.driver", LOCAL_DRIVER.toString());
                localDriverResolved = true;
                return;
            }
            throw new IllegalStateException(
                    "ChromeDriver not resolved: WebDriverManager failed and "
                            + LOCAL_DRIVER
                            + " is missing");
        }
    }
}
