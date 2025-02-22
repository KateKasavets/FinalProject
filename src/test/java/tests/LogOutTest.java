package tests;

import org.testng.annotations.Test;
import page.DashboardPage;

import static org.testng.Assert.assertTrue;

public class LogOutTest extends BaseTest {

    private DashboardPage dashboardPage;

    @Test
    public void logOutTest() {
        dashboardPage = new DashboardPage(driver);
        dashboardPage.clickDropDownButton();
        dashboardPage.clickLogOutButton();
        assertTrue(dashboardPage.isLoginPageTitle(), "Название страницы авторизации не отображается");
    }
}
