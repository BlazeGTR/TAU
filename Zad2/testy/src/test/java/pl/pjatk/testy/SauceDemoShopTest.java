package pl.pjatk.testy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class SauceDemoShopTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        //WebDriverManager.edgedriver().setup();

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Nie musimy już podawać .setBinary() - Edge jest w standardowej ścieżce Windowsa
        driver = new EdgeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testAddToCart() {
        driver.get("https://www.saucedemo.com/");

        // Logowanie
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Akcja: Dodanie do koszyka
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // Asercja: Czy w koszyku jest 1 przedmiot
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        Assertions.assertEquals("1", cartBadge.getText());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}