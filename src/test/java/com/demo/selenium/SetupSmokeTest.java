package com.demo.selenium;

import com.demo.selenium.pages.SeleniumHomePage;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SetupSmokeTest extends BaseTest {

    @Test
    public void seleniumDevTitleContainsSelenium() {
        SeleniumHomePage home = new SeleniumHomePage(driver()).open();
        new WebDriverWait(driver(), Duration.ofSeconds(20))
                .until(ExpectedConditions.titleContains("Selenium"));
        Assert.assertTrue(
                home.title().contains("Selenium"),
                "Expected page title to contain 'Selenium' but was: " + home.title());
    }
}
