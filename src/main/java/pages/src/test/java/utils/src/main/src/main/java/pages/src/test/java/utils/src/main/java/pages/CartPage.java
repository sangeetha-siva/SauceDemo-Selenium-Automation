package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CartPage {

    WebDriver driver;

    By pageTitle       = By.cssSelector(".title");
    By cartItems       = By.cssSelector(".cart_item");
    By checkoutButton  = By.id("checkout");
    By continueShopBtn = By.id("continue-shopping");
    By removeButtons   = By.cssSelector("[data-test^='remove']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public boolean isCartPageDisplayed() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public void clickContinueShopping() {
        driver.findElement(continueShopBtn).click();
    }

    public void removeFirstItem() {
        List<WebElement> removeBtn = driver.findElements(removeButtons);
        if (!removeBtn.isEmpty()) {
            removeBtn.get(0).click();
        }
    }
}
