package pl.pjwstk.testy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class GitHubLoginTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testFailedLogin() {
        // GIVEN: Użytkownik jest na stronie logowania
        driver.get("https://github.com/login");

        // WHEN: Wprowadza niepoprawne dane
        driver.findElement(By.id("login_field")).sendKeys("nieistniejacy_user_pjwstk@wp.pl");
        driver.findElement(By.id("password")).sendKeys("BledneHaslo123!");
        driver.findElement(By.name("commit")).click();

        // THEN: Powinien wyświetlić się komunikat o błędzie
        WebElement alert = driver.findElement(By.cssSelector(".js-flash-alert"));
        String alertText = alert.getText();

        Assertions.assertTrue(alertText.contains("Incorrect username or password"),
                "Komunikat o błędzie nie pojawił się lub ma inną treść!");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}