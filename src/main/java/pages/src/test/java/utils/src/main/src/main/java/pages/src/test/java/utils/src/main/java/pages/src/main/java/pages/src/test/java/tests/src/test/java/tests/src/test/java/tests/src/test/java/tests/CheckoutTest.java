package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.BaseTest;

public class CheckoutTest extends BaseTest {

    @Test(priority = 1, description = "Verify complete checkout flow from login to order confirmation")
    public void testCompleteCheckoutFlow() {
        LoginPage loginPage       = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage         = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Should be on products page");

        productsPage.addFirstItemToCart();
        Assert.assertEquals(productsPage.getCartCount(), "1", "Cart should have 1 item");

        productsPage.clickCartIcon();
        Assert.assertEquals(cartPage.getPageTitle(), "Your Cart", "Should be on cart page");

        cartPage.clickCheckout();

        checkoutPage.fillCheckoutForm("Sangeetha", "S", "641001");
        checkoutPage.clickContinue();

        checkoutPage.clickFinish();

        Assert.assertTrue(checkoutPage.isOrderConfirmed(),
            "Order confirmation should be displayed");
        Assert.assertEquals(checkoutPage.getConfirmationMessage(), "Thank you for your order!",
            "Confirmation message should match");

        System.out.println("TC_016 PASSED: Complete checkout flow successful");
    }

    @Test(priority = 2, description = "Verify error when first name is empty at checkout")
    public void testCheckoutWithEmptyFirstName() {
        LoginPage loginPage       = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage         = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        productsPage.addFirstItemToCart();
        productsPage.clickCartIcon();
        cartPage.clickCheckout();

        checkoutPage.fillCheckoutForm("", "S", "641001");
        checkoutPage.clickContinue();

        Assert.assertTrue(checkoutPage.getErrorMessage().contains("First Name is required"),
            "Error should show First Name is required");

        System.out.println("TC_017 PASSED: Empty first name error shown correctly");
    }

    @Test(priority = 3, description = "Verify error when postal code is empty at checkout")
    public void testCheckoutWithEmptyPostalCode() {
        LoginPage loginPage       = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage         = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        productsPage.addFirstItemToCart();
        productsPage.clickCartIcon();
        cartPage.clickCheckout();

        checkoutPage.fillCheckoutForm("Sangeetha", "S", "");
        checkoutPage.clickContinue();

        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Postal Code is required"),
            "Error should show Postal Code is required");

        System.out.println("TC_018 PASSED: Empty postal code error shown correctly");
    }
}
