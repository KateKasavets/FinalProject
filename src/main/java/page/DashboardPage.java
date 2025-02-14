package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import lombok.Getter;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
       super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[normalize-space()='Time at Work']")
    @Getter
    private WebElement timeAtWorkTab;

    @FindBy(xpath = "//p[normalize-space()='My Actions']")
    @Getter
    private WebElement myActionsTab;

    @FindBy(xpath = "//p[normalize-space()='Quick Launch']")
    @Getter
    private WebElement quickLaunchTab;

    @FindBy(xpath = "//p[normalize-space()='Buzz Latest Posts']")
    @Getter
    private WebElement buzzLatestPostTab;

    @FindBy(xpath = "//p[normalize-space()='Employees on Leave Today']")
    @Getter
    private WebElement employeesLeaveTodayTab;

    @FindBy(xpath = "//p[normalize-space()='Employee Distribution by Sub Unit']")
    @Getter
    private WebElement employeeDistributionSubUnitTab;

    @FindBy(xpath = "//p[normalize-space()='Employee Distribution by Location']")
    @Getter
    private WebElement employeeDistributionByLocationTab;

    @FindBy(xpath = "//i[@class='oxd-icon bi-clock-fill orangehrm-dashboard-widget-icon']")
    private WebElement timeIcon;

    @FindBy(xpath = "//i[@class='oxd-icon bi-list-check orangehrm-dashboard-widget-icon']")
    private WebElement myActionIcon;

    @FindBy(xpath = "//i[@class='oxd-icon bi-lightning-charge-fill orangehrm-dashboard-widget-icon']")
    private WebElement quickLaunchIcon;

    @FindBy(xpath = "//i[@class='oxd-icon bi-camera-fill orangehrm-dashboard-widget-icon']")
    private WebElement buzzPostsIcon;

    @FindBy(xpath = "//*[name()='path' and contains(@d,'M35,-60.08')]")
    private WebElement employeeLeaveTodayIcon;

    @FindBy(xpath = "(//i[@class='oxd-icon bi-pie-chart-fill orangehrm-dashboard-widget-icon'])[1]")
    private WebElement employeeDistSubUnitIcon;

    @FindBy(xpath = "(//i[@class='oxd-icon bi-pie-chart-fill orangehrm-dashboard-widget-icon'])[2]")
    private WebElement employeeDistLocationIcon;

    @FindBy(xpath = "//i[@class='oxd-icon bi-question-lg']")
    private WebElement questionIcon;

    @FindBy(xpath = "//i[@class='oxd-icon bi-gear-fill orangehrm-leave-card-icon']")
    private WebElement gearIcon;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-attendance-card-state']")
    private WebElement punchedOutSection;

    @FindBy(xpath = "//p[normalize-space()='(1) Pending Self Review']")
    private WebElement pendingSelfReview;

    @FindBy(xpath = "//p[normalize-space()='(1) Candidate to Interview']")
    private WebElement candidateToInterview;

    @FindBy(xpath = "//button[@title='Assign Leave']//*[name()='svg']")
    private WebElement assignLeave;

    @FindBy(xpath = "//button[@title='Leave List']//*[name()='svg']")
    private WebElement leaveList;

    @FindBy(xpath = "//button[@title='Timesheets']//*[name()='svg']")
    private WebElement timeSheetsButton;

    @FindBy(xpath = "//button[@title='Apply Leave']//*[name()='svg']")
    private WebElement applyLeaveButton;

    @FindBy(xpath = "//button[@title='My Leave']//*[name()='svg']")
    private WebElement myLeaveButton;

    @FindBy(xpath = "//button[@title='My Timesheet']//*[name()='svg']")
    private WebElement myTimeSheet;

    @FindBy(xpath = "(//div[@class=\"oxd-pie-chart\"])[1]")
    private WebElement diagramEmpSubUnit;

    @FindBy(xpath = "(//div[@class=\"emp-distrib-chart\"])[2]")
    private WebElement diagramEmpLocation;

    @FindBy(xpath = "//i[@class='oxd-icon bi-stopwatch']")
    private WebElement watchIcon;

    @FindBy(xpath = "//span[normalize-space()='Attendance']")
    private WebElement attendanceTab;

    @FindBy(xpath = "//span[normalize-space()='Manage Reviews']")
    private WebElement manageReviewsTab;

    @FindBy(xpath = "//a[normalize-space()='Candidates']")
    private WebElement candidatesTab;

    @FindBy(xpath = "//a[normalize-space()='Assign Leave']")
    private WebElement assignLeaveTab;

    @FindBy(xpath = "//a[normalize-space()='Leave List']")
    private WebElement leaveListTab;

    @FindBy(xpath = "//span[normalize-space()='Timesheets']")
    private WebElement timeSheetsPage;

    @FindBy(xpath = "//a[normalize-space()='Apply']")
    private WebElement applyPage;

    @FindBy(xpath = "//a[normalize-space()='My Leave']")
    private WebElement myLeavePage;

    @FindBy(xpath = "//h6[normalize-space()='My Timesheet']")
    private WebElement myTimeSheetPage;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='Dashboard']")
    private WebElement homeButton;

    @FindBy(xpath = "//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")
    private WebElement dropDownIcon;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    private WebElement logOutButton;

    @FindBy(xpath = "//h5[normalize-space()='Login']")
    private WebElement loginTitlePage;


    public boolean areAllElementsDisplayed() {
        return timeAtWorkTab.isDisplayed() &&
                myActionsTab.isDisplayed() &&
                quickLaunchTab.isDisplayed() &&
                buzzLatestPostTab.isDisplayed() &&
                employeesLeaveTodayTab.isDisplayed() &&
                employeeDistributionSubUnitTab.isDisplayed() &&
                employeeDistributionByLocationTab.isDisplayed();
    }

    public boolean isTextDisplayedCorrectly(WebElement element, String expectedText) {
        return element.getText().equals(expectedText);
    }

    public boolean areIconsDisplayed() {
        return timeIcon.isDisplayed() &&
                myActionIcon.isDisplayed() &&
                quickLaunchIcon.isDisplayed() &&
                buzzPostsIcon.isDisplayed() &&
                employeeLeaveTodayIcon.isDisplayed() &&
                employeeDistSubUnitIcon.isDisplayed() &&
                questionIcon.isDisplayed() &&
                gearIcon.isDisplayed() &&
                employeeDistLocationIcon.isDisplayed();
    }

    public boolean areAllElementsInSectionDisplayed() {
        return punchedOutSection.isDisplayed() &&
                pendingSelfReview.isDisplayed() &&
                candidateToInterview.isDisplayed() &&
                assignLeave.isDisplayed() &&
                leaveList.isDisplayed() &&
                timeSheetsButton.isDisplayed() &&
                applyLeaveButton.isDisplayed() &&
                myLeaveButton.isDisplayed() &&
                myTimeSheet.isDisplayed() &&
                diagramEmpSubUnit.isDisplayed() &&
                diagramEmpLocation.isDisplayed();
    }

    public void clickWatchIcon() {
        watchIcon.click();
    }

    public boolean isAttendanceTabDisplayed() {
        return attendanceTab.isDisplayed();
    }

    public void clickPendinfSelfReview() {
        pendingSelfReview.click();
    }

    public boolean isManageReviewsTabDisplayed() {
        return manageReviewsTab.isDisplayed();
    }

    public void clickCandidateToInterview() {
        candidateToInterview.click();
    }

    public boolean isCandidatesTab() {
        return candidatesTab.isDisplayed();
    }

    public void clickAssignLeave() {
        assignLeave.click();
    }

    public boolean isAssignLeaveTab() {
        return assignLeaveTab.isDisplayed();
    }

    public void clickLeaveList() {
        leaveList.click();
    }

    public boolean isLeaveListTab() {
        return leaveListTab.isDisplayed();
    }

    public void clickTimeSheetsButton() {
        timeSheetsButton.click();
    }

    public boolean isTimeSheetsPage() {
        return timeSheetsPage.isDisplayed();
    }

    public void clickApplyLeaveButton() {
        applyLeaveButton.click();
    }

    public boolean isApplyPage() {
        return applyPage.isDisplayed();
    }

    public void clickMyLeaveButton() {
        myLeaveButton.click();
    }

    public boolean isMyLeavePage() {
        return myLeavePage.isDisplayed();
    }

    public void clickMyTimeSheet() {
        myTimeSheet.click();
    }

    public boolean isMyTimeSheetPage() {
        return myTimeSheetPage.isDisplayed();
    }

    public void navigateToDashboard() {
        homeButton.click();
    }

    public void clickDropDownButton() {
        dropDownIcon.click();
    }

    public void clickLogOutButton() {
        logOutButton.click();
    }

    public boolean isLoginPageTitle() {
        return loginTitlePage.isDisplayed();
    }
}
