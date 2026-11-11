package com.demo.selenium;

import com.demo.selenium.pages.LoginPage;
import com.demo.selenium.pages.SeleniumHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginWithPageObjectTest extends BaseTest {

    @Test
    public void homeNavbarIsVisible() {
        SeleniumHomePage home = new SeleniumHomePage(driver()).open();
        Assert.assertTrue(home.navbar().isDisplayed(), "Navbar #main_navbar should be visible");
    }

    @Test
    public void loginWithValidCredentials() {
        LoginPage page = new LoginPage(driver()).open();
        page.login("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(page.flashMessage().contains("You logged into a secure area!"));
    }
}
