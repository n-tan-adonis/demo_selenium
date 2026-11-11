package com.demo.selenium;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FindElementsTest extends BaseTest {

    @BeforeMethod
    public void openHomePage() {
        driver().get("https://www.selenium.dev/");
    }

    @Test
    public void findById() {
        WebElement navbar = driver().findElement(By.id("main_navbar"));
        Assert.assertTrue(navbar.isDisplayed(), "Navbar #main_navbar should be visible");
    }

    @Test
    public void findByNameSendKeys() {
        driver().get("https://the-internet.herokuapp.com/login");
        WebElement username = driver().findElement(By.name("username"));
        WebElement password = driver().findElement(By.name("password"));
        username.sendKeys("tomsmith");
        password.sendKeys("SuperSecretPassword!");
        Assert.assertEquals(username.getDomProperty("value"), "tomsmith");
        driver().findElement(By.cssSelector("button[type='submit']")).click();
        WebElement flash = driver().findElement(By.id("flash"));
        Assert.assertTrue(flash.getText().contains("You logged into a secure area!"));
    }

    @Test
    public void findByClassName() {
        WebElement navbar = driver().findElement(By.className("navbar"));
        Assert.assertTrue(navbar.isDisplayed(), "Navbar should be visible");
    }

    @Test
    public void findByTagName() {
        List<WebElement> links = driver().findElements(By.tagName("a"));
        Assert.assertFalse(links.isEmpty(), "Homepage should contain <a> tags");
        Assert.assertNotNull(links.get(0).getText());
    }

    @Test
    public void findByLinkText() {
        WebElement docs = driver().findElement(By.linkText("Documentation"));
        Assert.assertTrue(docs.isDisplayed());
        Assert.assertEquals(docs.getTagName(), "a");
    }

    @Test
    public void findByPartialLinkText() {
        WebElement downloads = driver().findElement(By.partialLinkText("Download"));
        Assert.assertTrue(downloads.isDisplayed());
    }

    @Test
    public void findByCssSelector() {
        WebElement heading = driver().findElement(By.cssSelector("h1"));
        Assert.assertTrue(heading.isDisplayed());
        Assert.assertFalse(heading.getText().isBlank(), "h1 should have text");
    }

    @Test
    public void findByXpathSameHeadingAsCss() {
        WebElement byCss = driver().findElement(By.cssSelector("h1"));
        WebElement byXpath = driver().findElement(By.xpath("//h1"));
        Assert.assertEquals(byXpath.getText(), byCss.getText());
        Assert.assertTrue(byXpath.isDisplayed());
    }

    @Test
    public void findElementsReturnsEmptyWhenMissing() {
        List<WebElement> missing = driver().findElements(By.id("this-id-does-not-exist-on-selenium-dev"));
        Assert.assertTrue(missing.isEmpty(), "findElements should return empty list, not throw");
    }
}
