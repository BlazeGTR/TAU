package pl.pjwstk.steps;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

public class TestSteps {
    WebDriver driver;

    // SCENARIUSZ 1: GITHUB (Firefox)
    @Given("Uzytkownik otwiera strone logowania GitHub w przegladarce Firefox")
    public void openGitHub() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://github.com/login");
    }

    @When("Uzytkownik wpisuje bledny login {string} i haslo {string}")
    public void fillLoginForm(String user, String pass) {
        driver.findElement(By.id("login_field")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(pass);
    }

    @And("Klika przycisk logowania")
    public void clickLogin() {
        driver.findElement(By.name("commit")).click();
    }

    @Then("Powinien zobaczyc komunikat o bledzie {string}")
    public void verifyError(String expectedError) {
        String actualError = driver.findElement(By.cssSelector(".js-flash-alert")).getText();
        Assertions.assertTrue(actualError.contains(expectedError));
        driver.quit();
    }

    // SCENARIUSZ 2: SAUCEDEMO (Chrome)
    @Given("Uzytkownik jest zalogowany na stronie SauceDemo w przegladarce Chrome")
    public void loginToSauceDemo() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @When("Uzytkownik klika przycisk Add to Cart przy produkcie Backpack")
    public void addItemToCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    @Then("Ikona koszyka powinna wyswietlac liczbe {string}")
    public void verifyCart(String count) {
        String actualCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assertions.assertEquals(count, actualCount);
        driver.quit();
    }
}