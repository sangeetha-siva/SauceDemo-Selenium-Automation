package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.BaseTest;

public class ProductTest extends BaseTest {

    @Test(priority = 1, description = "Verify products are displayed on inventory page")
    public void testProductsPageLoads() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        Assert.assertEquals(productsPage.getPageTitle(), "Products",
            "Page title should be Products");
        Assert.assertTrue(productsPage.isProductsPageDisplayed(),
            "Products page should be displayed");

        System.out.println("TC_007 PASSED: Products page loaded successfully");
    }

    @Test(priority = 2, description = "Verify single product can be added to cart")
    public void testAddSingleItemToCart() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        productsPage.addFirstItemToCart();

        Assert.assertEquals(productsPage.getCartCount(), "1",
            "Cart count should be 1 after adding one item");

        System.out.println("TC_008 PASSED: Single item added to cart successfully");
    }

    @Test(priority = 3, description = "Verify multiple products can be added to cart")
    public void testAddMultipleItemsToCart() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        productsPage.addAllItemsToCart();

        Assert.assertEquals(productsPage.getCartCount(), "6",
            "Cart count should be 6 after adding all items");

        System.out.println("TC_009 PASSED: All items added to cart successfully");
    }

    @Test(priority = 4, description = "Verify products can be sorted by name A to Z")
    public void testSortProductsAtoZ() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        productsPage.sortProducts("Name (A to Z)");

        String firstProductName = productsPage.getFirstProductName();
        Assert.assertEquals(firstProductName, "Sauce Labs Backpack",
            "First product should be Sauce Labs Backpack when sorted A to Z");

        System.out.println("TC_010 PASSED: Products sorted A to Z correctly");
    }

    @Test(priority = 5, description = "Verify products can be sorted by name Z to A")
    public void testSortProductsZtoA() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        productsPage.sortProducts("Name (Z to A)");

        String firstProductName = productsPage.getFirstProductName();
        Assert.assertEquals(firstProductName, "Test.allTheThings() T-Shirt (Red)",
            "First product should be T-Shirt when sorted Z to A");

        System.out.println("TC_011 PASSED: Products sorted Z to A correctly");
    }
}
