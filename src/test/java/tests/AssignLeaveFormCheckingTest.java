package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.AssignLeavePage;
import valueObjects.AddUserVO;

import static org.testng.Assert.assertTrue;

public class AssignLeaveFormCheckingTest extends BaseTest {

    private AssignLeavePage assignLeavePage;

    @BeforeMethod
    private void navigateToAssignLeaveForm() {
        assignLeavePage = new AssignLeavePage(driver);
        assignLeavePage.clickLeaveMenu();
        assignLeavePage.clickAssingLeaveTab();
    }

    @Test
    public void testAllElementsAreDisplayed() {
        assertTrue(assignLeavePage.areAllElementsDisplayed(),
                "Не все элементы на странице 'Assign Leave' отображаются.");
    }

    @Test
    public void testWithEmptyFields() {
        assignLeavePage.clickSubmitButton();
        String actualErrorMessage = assignLeavePage.getRequiredErrMessage();
        assertTrue(actualErrorMessage.contains("Required"),
                "Фактическое сообщение: " + actualErrorMessage);
    }

    @Test
    public void testWithInvalidEmployeeName() {
        AddUserVO addUserVO = new AddUserVO();
        String invalidEmployeeName = addUserVO.getFirstName();

        assignLeavePage.enterInvalidEmployeeName(invalidEmployeeName);
        assignLeavePage.clickSubmitButton();

        String errorInvNameMessage = assignLeavePage.getInvalidNameErrMessage();
        assertTrue(errorInvNameMessage.contains("Invalid"), "Ошибка не была показана");
    }

    @Test
    public void testWithToDateEarlierThanFromDate() {
        String fromDate = "2025-01-10";
        String toDate = "2025-01-05";

        assignLeavePage.enterDates(fromDate, toDate);
        assignLeavePage.clickSubmitButton();

        String errorDateMessage = assignLeavePage.getDateErrMessage();

        assertTrue(errorDateMessage.contains("To date should be after from date"),
                "Ошибка не была показана или сообщение не соответствует ожиданиям: " + errorDateMessage);
    }
}
