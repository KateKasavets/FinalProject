package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.CookieAuth;
import utils.WebDriverSingleton;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = WebDriverSingleton.getDriver();

        CookieAuth.setCookies(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/dashboard/index"));

        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard/index"),
                "Не удалось попасть на страницу /dashboard/index после авторизации!");
    }

    @AfterClass
    public void tearDown() {
      WebDriverSingleton.quitDriver();
    }
}
