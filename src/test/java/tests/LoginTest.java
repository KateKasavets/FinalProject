package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import utils.ConfProperties;
import utils.WebDriverSingleton;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setup() {
        driver = WebDriverSingleton.getDriver();
        driver.get(ConfProperties.getBasePage() + ConfProperties.getLoginPageUrl());
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginTest() {
        SoftAssert softAssert = new SoftAssert();

        loginPage.login(ConfProperties.getLogin(), ConfProperties.getPassword());

        softAssert.assertTrue(loginPage.isPageTitleDisplayed(), "Название страницы не отображается");

        softAssert.assertEquals(loginPage.getPageTitleText(), "Dashboard", "Текст заголовка не соответствует ожидаемому");

        softAssert.assertTrue(loginPage.isProfileButtonDisplayed(), "Кнопка профиля не отображается");

        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }
}
