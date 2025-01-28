package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.AddUserPage;
import pageObject.AssignLeavePage;
import valueObjects.AddUserVO;

import static org.testng.AssertJUnit.assertTrue;

public class AssignLeaveTest extends BaseTest {
    private AddUserPage addUserPage;
    private AssignLeavePage assignLeavePage;
    private AddUserVO user;
    private String employeeName;

    @BeforeMethod
    public void navigateToAssignPage() {
        addUserPage = new AddUserPage(driver);
        assignLeavePage = new AssignLeavePage(driver);
        user = new AddUserVO();
    }

    @Test
    public void createEmployee() {
        addUserPage.clickPimMenu();
        addUserPage.clickAddButton();
        addUserPage.fillEmployeeForm(user);
        addUserPage.saveEmployee();
        addUserPage.waitForPopupToDisappear();

        employeeName = user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName();
    }

    @Test(dependsOnMethods = {"createEmployee"})
    public void assignLeaveTest() {
        String fromDate = "2025-02-01";
        String toDate = "2025-02-10";

        assignLeavePage.clickLeaveMenu();
        assignLeavePage.clickAssingLeaveTab();

        assignLeavePage.enterEmployeeName(employeeName);
        assignLeavePage.selectLeaveType();
        assignLeavePage.enterDates(fromDate, toDate);
        assignLeavePage.clickSubmitButton();
        assignLeavePage.clickConfirmLeaveAssignButton();

        boolean isSuccess = assignLeavePage.isLeaveCreatedSuccessfully();
        assertTrue("Leave was not created successfully.", isSuccess);
    }
}

