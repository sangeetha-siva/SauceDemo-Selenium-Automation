package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.BaseTest;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify successful login with valid credentials")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(productsPage.isProductsPageDisplayed(),
            "Products page should be displayed after valid login");
        Assert.assertEquals(productsPage.getPageTitle(), "Products",
            "Page title should be 'Products'");

        System.out.println("TC_001 PASSED: Valid login successful");
    }

    @Test(priority = 2, description = "Verify error message on invalid password")
    public void testInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "wrongpassword");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
            "Error message should be displayed for invalid password");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"),
            "Correct error message should appear");

        System.out.println("TC_002 PASSED: Invalid password error displayed correctly");
    }

    @Test(priority = 3, description = "Verify error when username is empty")
    public void testEmptyUsername() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("", "secret_sauce");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
            "Error message should be displayed for empty username");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"),
            "Username required error message should appear");

        System.out.println("TC_003 PASSED: Empty username error displayed correctly");
    }

    @Test(priority = 4, description = "Verify error when password is empty")
    public void testEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
            "Error message should be displayed for empty password");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Password is required"),
            "Password required error message should appear");

        System.out.println("TC_004 PASSED: Empty password error displayed correctly");
    }

    @Test(priority = 5, description = "Verify locked out user cannot login")
    public void testLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
            "Error message should be displayed for locked out user");
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"),
            "Locked out error message should appear");

        System.out.println("TC_005 PASSED: Locked out user error displayed correctly");
    }

    @Test(priority = 6, description = "Verify user can logout successfully")
    public void testLogout() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Should be on products page");

        productsPage.logout();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/",
            "Should be redirected to login page after logout");

        System.out.println("TC_006 PASSED: Logout successful");
    }
}
