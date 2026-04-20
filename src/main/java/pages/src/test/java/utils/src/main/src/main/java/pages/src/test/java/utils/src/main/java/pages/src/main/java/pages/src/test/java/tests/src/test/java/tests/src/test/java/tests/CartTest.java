package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.BaseTest;

public class CartTest extends BaseTest {

    @Test(priority = 1, description = "Verify cart page is accessible after adding items")
    public void testCartPageOpens() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        productsPage.addFirstItemToCart();
        productsPage.clickCartIcon();

        Assert.assertEquals(cartPage.getPageTitle(), "Your Cart",
            "Cart page title should be 'Your Cart'");

        System.out.println("TC_012 PASSED: Cart page opened successfully");
    }

    @Test(priority = 2, description = "Verify added items appear in cart")
    public void testCartItemCount() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        productsPage.addFirstItemToCart();
        productsPage.clickCartIcon();

        Assert.assertEquals(cartPage.getCartItemCount(), 1,
            "Cart should have 1 item");

        System.out.println("TC_013 PASSED: Cart item count is correct");
    }

    @Test(priority = 3, description = "Verify item can be removed from cart")
    public void testRemoveItemFromCart() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        productsPage.addFirstItemToCart();
        productsPage.clickCartIcon();

        Assert.assertEquals(cartPage.getCartItemCount(), 1,
            "Cart should have 1 item before remove");

        cartPage.removeFirstItem();

        Assert.assertEquals(cartPage.getCartItemCount(), 0,
            "Cart should be empty after removing the item");

        System.out.println("TC_014 PASSED: Item removed from cart successfully");
    }

    @Test(priority = 4, description = "Verify continue shopping button works from cart")
    public void testContinueShopping() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        productsPage.addFirstItemToCart();
        productsPage.clickCartIcon();
        cartPage.clickContinueShopping();

        Assert.assertEquals(productsPage.getPageTitle(), "Products",
            "Should navigate back to products page");

        System.out.println("TC_015 PASSED: Continue shopping works correctly");
    }
}
