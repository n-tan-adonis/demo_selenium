package com.demo.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private static final By USERNAME = By.name("username");
    private static final By PASSWORD = By.name("password");
    private static final By SUBMIT = By.cssSelector("button[type='submit']");
    private static final By FLASH = By.id("flash");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage open() {
        driver.get("https://the-internet.herokuapp.com/login");
        return this;
    }

    public LoginPage login(String username, String password) {
        driver.findElement(USERNAME).sendKeys(username);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(SUBMIT).click();
        return this;
    }

    public String flashMessage() {
        return driver.findElement(FLASH).getText();
    }
}
