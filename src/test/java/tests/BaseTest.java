package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import page.BasePage;
import utils.CookieAuth;
import utils.WebDriverSingleton;


public class BaseTest {
    protected WebDriver driver;
    private BasePage basePage;

    @BeforeClass
    public void setup() {
        driver = WebDriverSingleton.getDriver();
        basePage = new BasePage(driver);

        CookieAuth.setCookies(driver);

        basePage.waitForUrlToContain("/dashboard/index");

        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard/index"),
                "Не удалось попасть на страницу /dashboard/index после авторизации!");
    }

    @AfterClass
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }
}
