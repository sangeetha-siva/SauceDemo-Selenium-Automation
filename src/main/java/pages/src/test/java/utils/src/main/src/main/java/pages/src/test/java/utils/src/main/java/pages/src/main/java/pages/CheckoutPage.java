package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    WebDriver driver;

    By firstNameField  = By.id("first-name");
    By lastNameField   = By.id("last-name");
    By postalCodeField = By.id("postal-code");
    By continueButton  = By.id("continue");
    By finishButton    = By.id("finish");
    By confirmHeader   = By.cssSelector(".complete-header");
    By errorMessage    = By.cssSelector("[data-test='error']");
    By pageTitle       = By.cssSelector(".title");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public void clickFinish() {
        driver.findElement(finishButton).click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmHeader).getText();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isOrderConfirmed() {
        try {
            return driver.findElement(confirmHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
