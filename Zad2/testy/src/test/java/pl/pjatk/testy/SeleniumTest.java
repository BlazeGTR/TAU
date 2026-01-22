package pl.pjatk.testy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.jupiter.api.Test;


public class SeleniumTest {

    @Test
    public void testChrome() {
        WebDriverManager.chromedriver().setup(); // Automatyczna konfiguracja
        WebDriver driver = new ChromeDriver();
        driver.get("https://github.com/login");
        // ... logika testu
        driver.quit();
    }

    @Test
    public void testFirefox() {
        WebDriverManager.firefoxdriver().setup(); // Automatyczna konfiguracja
        WebDriver driver = new FirefoxDriver();
        driver.get("https://allegro.pl");
        // ... logika testu
        driver.quit();
    }
}