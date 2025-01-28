package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pageObject.LoginPage;
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

        loginPage.login(ConfProperties.getLogin(), ConfProperties.getPassword());

        assertTrue(loginPage.isPageTitleDisplayed(), "Название страницы не отображается");

        assertEquals(loginPage.getPageTitleText(), "Dashboard", "Текст заголовка не соответствует ожидаемому");

        assertTrue(loginPage.isProfileButtonDisplayed(), "Кнопка профиля не отображается");
    }

    @AfterClass
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }
}
