package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.DashboardPage;

import static org.testng.AssertJUnit.assertTrue;

public class DashboardTest extends BaseTest {
    private DashboardPage dashboardPage;

    @BeforeMethod
    private void navigateDashboardPage() {
        dashboardPage = new DashboardPage(driver);

    }

    @Test
    public void testAllElementsAreDisplayed() {
        assertTrue("Не все элементы на странице 'Dashboard' отображаются.",
                dashboardPage.areAllElementsDisplayed());
    }

    @Test
    public void testCheckTextAllElements() {
        boolean result = dashboardPage.isTextDisplayedCorrectly(dashboardPage.getTimeAtWorkTab(), "Time at Work") &&
                dashboardPage.isTextDisplayedCorrectly(dashboardPage.getMyActionsTab(), "My Actions") &&
                dashboardPage.isTextDisplayedCorrectly(dashboardPage.getQuickLaunchTab(), "Quick Launch") &&
                dashboardPage.isTextDisplayedCorrectly(dashboardPage.getBuzzLatestPostTab(), "Buzz Latest Posts") &&
                dashboardPage.isTextDisplayedCorrectly(dashboardPage.getEmployeesLeaveTodayTab(), "Employees on Leave Today") &&
                dashboardPage.isTextDisplayedCorrectly(dashboardPage.getEmployeeDistributionSubUnitTab(), "Employee Distribution by Sub Unit") &&
                dashboardPage.isTextDisplayedCorrectly(dashboardPage.getEmployeeDistributionByLocationTab(), "Employee Distribution by Location");

        assertTrue("Не все элементы имеют корректный текст ", result);
    }

    @Test
    public void testAllIconsAreDisplayed() {
        assertTrue("Не все иконки на странице 'Dashboard' отображаются.",
                dashboardPage.areIconsDisplayed());
    }

    @Test
    public void testCheckDataInsideSections() {
        assertTrue("Не все элементы секций отображаются", dashboardPage.areAllElementsInSectionDisplayed());
    }

    @Test
    public void testCheckLinksAndNavigation() {
        dashboardPage.clickWatchIcon();
        assertTrue("Вкладка 'Attendance' не отображается!",
                dashboardPage.isAttendanceTabDisplayed());

        dashboardPage.navigateToDashboard();
        dashboardPage.clickPendinfSelfReview();
        assertTrue("Вкладка 'Manage Reviews' не отображается!",
                dashboardPage.isManageReviewsTabDisplayed());
        dashboardPage.navigateToDashboard();

        dashboardPage.clickCandidateToInterview();
        assertTrue("Вкладка 'Candidates' не отображается!",
                dashboardPage.isCandidatesTab());
        dashboardPage.navigateToDashboard();

        dashboardPage.clickAssignLeave();
        assertTrue("Вкладка 'Assign Leave' не отображается!",
                dashboardPage.isAssignLeaveTab());
        dashboardPage.navigateToDashboard();

        dashboardPage.clickLeaveList();
        assertTrue("Вкладка 'Leave List' не отображается!",
                dashboardPage.isLeaveListTab());
        dashboardPage.navigateToDashboard();

        dashboardPage.clickTimeSheetsButton();
        assertTrue("Страница 'Time Sheets' не отображается!",
                dashboardPage.isTimeSheetsPage());

        dashboardPage.navigateToDashboard();
        dashboardPage.clickApplyLeaveButton();
        assertTrue("Страница 'Apply Leave' не отображается!",
                dashboardPage.isApplyPage());
        dashboardPage.navigateToDashboard();

        dashboardPage.clickMyLeaveButton();
        assertTrue("Страница 'My Leave' не отображается!",
                dashboardPage.isMyLeavePage());
        dashboardPage.navigateToDashboard();

        dashboardPage.clickMyTimeSheet();
        assertTrue("Страница 'My TimeSheet' не отображается!",
                dashboardPage.isMyTimeSheetPage());
        dashboardPage.navigateToDashboard();

    }
}
