package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.AddUserPage;
import valueObjects.AddUserVO;

import java.io.File;

import static org.testng.Assert.assertTrue;

public class AddUserFormCheckingTest extends BaseTest {

    private AddUserPage addUserPage;

    @BeforeMethod
    private void navigateToAddUserForm() {
        addUserPage = new AddUserPage(driver);
        addUserPage.clickPimMenu();
        addUserPage.clickAddButton();
    }

    @Test
    public void testSaveButtonWithoutFillingForm() {
        addUserPage.saveEmployee();

        String errMessageForRequiredFields = addUserPage.getRequiredMessage();
        assertTrue(errMessageForRequiredFields.contains("Required"),
                "Ошибка в обязательных полях не соответствует ожиданиям: " + errMessageForRequiredFields);
    }

    @Test
    public void testWithLongEmployeeId() {
        addUserPage.fillEmployeeId();

        String errorMessage = addUserPage.getErrorIDMessage();
        assertTrue(errorMessage.contains("Should not exceed 10 characters"),
                "Ошибка в поле Employee ID не соответствует ожиданиям: " + errorMessage);
    }

    @Test
    public void testWithLongFirstMiddleLastName() {
        AddUserVO user = new AddUserVO();
        String longName = "A".repeat(31);
        user.setFirstName(longName);
        user.setMiddleName(longName);
        user.setLastName(longName);

        addUserPage.fillEmployeeForm(user);

        String errorMessage = addUserPage.getErrMaxLength();
        assertTrue(errorMessage.contains("Should not exceed 30 characters"),
                "Сообщение об ошибке для First Name не соответствует ожиданиям: " + errorMessage);
    }

    @Test
    public void testWithInvalidFileTypeAvatar() {
        String filePath = new File("src/test/resources/images/invalidFilePDF.pdf").getAbsolutePath();
        addUserPage.uploadProfilePicture(filePath);

        String errorMessage = addUserPage.getErrFileTypeMessage();
        assertTrue(errorMessage.contains("File type not allowed"),
                "Сообщение об ошибке для аватара с неправильным типом не соответствует ожиданиям: " + errorMessage);
    }

    @Test
    public void testWithInvalidSizeAvatar() {
        String filePath = new File("src/test/resources/images/invalidSize.jpg").getAbsolutePath();
        addUserPage.uploadProfilePicture(filePath);

        String errorMessage = addUserPage.getErrFileTypeMessage();
        assertTrue(errorMessage.contains("Attachment Size Exceeded"),
                "Сообщение об ошибке для аватара с превышенным размером не соответствует ожиданиям: " + errorMessage);
    }

    @Test
    public void testWithCancelAddingUser() {
        AddUserVO user = new AddUserVO();
        addUserPage.fillEmployeeForm(user);
        addUserPage.clickCancelButton();

        String currentTabTitle = addUserPage.getEmployeeListTab();
        assertTrue(currentTabTitle.contains("Employee List"),
                "Название текущей вкладки не соответствует ожиданиям: " + currentTabTitle);
    }
}
