package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.AddUserPage;
import pageObject.PiMFormPage;
import valueObjects.AddUserVO;

public class PiMFormTest extends BaseTest {

    private AddUserPage addUserPage;
    private PiMFormPage pimFormPage;
    private AddUserVO user;

    @BeforeMethod
    public void navigateToPiM() {
        addUserPage = new AddUserPage(driver);
        pimFormPage = new PiMFormPage(driver);
        user = new AddUserVO();
    }

    @Test
    public void createEmployeeWithSalesRole() {
        addUserPage.clickPimMenu();
        addUserPage.clickAddButton();
        addUserPage.fillEmployeeForm(user);
        addUserPage.saveEmployee();
        addUserPage.waitForPopupToDisappear();
        pimFormPage.selectSalesUnit();
        pimFormPage.selectJobTitle();
        pimFormPage.clickAddButton();
        pimFormPage.uploadImage();
        pimFormPage.clickSaveButton2();
        pimFormPage.clickSaveButton();
        pimFormPage.waitForElementToBeVisible();
        addUserPage.clickPimMenu();
        pimFormPage.searchForSalesSubUnit();

        Assert.assertTrue(pimFormPage.isFoundUser(), "User with Sales role is not found");
    }

    @Test
    public void verifyJobDetailsOfSalesEmployee() {
        addUserPage.clickPimMenu();
        pimFormPage.searchForSalesSubUnit();
        pimFormPage.clickPencilIcon();
        pimFormPage.clickJobTab();

        Assert.assertTrue(pimFormPage.isSalesUnitDisplayed(), "Sales unit is not displayed");

        Assert.assertTrue(pimFormPage.isJobTitleDisplayed(), "Job title 'VP - Sales & Marketing' is not displayed!");

        Assert.assertTrue(pimFormPage.isFoundRecorder(), "Attachment is not displayed");
    }
}

