package com.demo.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumHomePage {

    private static final By NAVBAR = By.id("main_navbar");

    private final WebDriver driver;

    public SeleniumHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public SeleniumHomePage open() {
        driver.get("https://www.selenium.dev/");
        return this;
    }

    public String title() {
        return driver.getTitle();
    }

    public WebElement navbar() {
        return driver.findElement(NAVBAR);
    }
}
