package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class ProductsPage {

    WebDriver driver;

    By pageTitle        = By.cssSelector(".title");
    By addToCartButtons = By.cssSelector("[data-test^='add-to-cart']");
    By cartIcon         = By.cssSelector(".shopping_cart_link");
    By cartBadge        = By.cssSelector(".shopping_cart_badge");
    By sortDropdown     = By.cssSelector("[data-test='product-sort-container']");
    By productNames     = By.cssSelector(".inventory_item_name");
    By menuButton       = By.id("react-burger-menu-btn");
    By logoutLink       = By.id("logout_sidebar_link");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public boolean isProductsPageDisplayed() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    public void addFirstItemToCart() {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }

    public void addAllItemsToCart() {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        for (WebElement button : buttons) {
            button.click();
        }
    }

    public String getCartCount() {
        try {
            return driver.findElement(cartBadge).getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public void clickCartIcon() {
        driver.findElement(cartIcon).click();
    }

    public void sortProducts(String sortOption) {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByVisibleText(sortOption);
    }

    public String getFirstProductName() {
        List<WebElement> names = driver.findElements(productNames);
        return names.isEmpty() ? "" : names.get(0).getText();
    }

    public void logout() {
        driver.findElement(menuButton).click();
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
        driver.findElement(logoutLink).click();
    }
}
