package utils;
 
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
 
public class BaseTest {
 
    public WebDriver driver;
 
    @BeforeMethod
    public void setUp() {
        // WebDriverManager automatically downloads the correct ChromeDriver
        WebDriverManager.chromedriver().setup();
 
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
 
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com");
        driver.manage().timeouts().implicitlyWait(
            java.time.Duration.ofSeconds(10)
        );
    }
 
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
 


