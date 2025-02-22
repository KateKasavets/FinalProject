package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.AddCandidatePage;
import entities.AddCandidateVO;

import java.io.File;

import static org.testng.Assert.assertTrue;

public class AddCandidateFormCheckingTest extends BaseTest {

    private AddCandidatePage addCandidatePage;

    @BeforeMethod
    private void navigateToAddCandidateForm() {
        addCandidatePage = new AddCandidatePage(driver);
        addCandidatePage.clickRecruitmentMenu();
        addCandidatePage.clickAddButton();
    }

    @Test
    public void testSaveButtonWithoutFillingForm() {
        addCandidatePage.saveCandidate();

        String errMessageForRequiredFields = addCandidatePage.getRequiredMessage();
        assertTrue(errMessageForRequiredFields.contains("Required"),
                "Ошибка в обязательных полях не соответствует ожиданиям: " + errMessageForRequiredFields);

    }

    @Test
    public void testSaveWithFullNameOnly() {
        AddCandidateVO candidate = new AddCandidateVO();

        addCandidatePage.fillFirstAndLastName(candidate);

        addCandidatePage.saveCandidate();

        String errMessageForRequiredFields = addCandidatePage.getRequiredMessage();
        assertTrue(errMessageForRequiredFields.contains("Required"),
                "Ошибка в обязательных полях не соответствует ожиданиям: " + errMessageForRequiredFields);

    }

    @Test
    public void testSaveWithEmailOnly() {

        AddCandidateVO candidate = new AddCandidateVO();

        addCandidatePage.fillEmail(candidate);
        addCandidatePage.saveCandidate();

        String errMessageForRequiredFields = addCandidatePage.getRequiredMessage();
        assertTrue(errMessageForRequiredFields.contains("Required"),
                "Ошибка в обязательных полях не соответствует ожиданиям: " + errMessageForRequiredFields);

    }

    @Test
    public void testWithLongFirsLastName() {
        AddCandidateVO candidate = new AddCandidateVO();

        String longFirstName = "A".repeat(31);
        String longLastName = "C".repeat(31);

        candidate.setFirstName(longFirstName);
        candidate.setLastName(longLastName);

        addCandidatePage.fillFirstAndLastName(candidate);

        addCandidatePage.saveCandidate();

        String errorMaxLengthMessage = addCandidatePage.getErrMaxLengthMessage();
        assertTrue(errorMaxLengthMessage.contains("Should not exceed 30 characters"),
                "Сообщение об ошибке для превышения длины не соответствует ожиданиям: " + errorMaxLengthMessage);

    }

    @Test
    public void testWithInvalidEmail() {
        AddCandidateVO candidate = new AddCandidateVO();

        String longEmailField = "A".repeat(8);
        candidate.setEmail(longEmailField);

        addCandidatePage.fillEmail(candidate);
        String errorInvalidEmail = addCandidatePage.getErrEmailField();
        assertTrue(errorInvalidEmail.contains("Expected format: admin@example.com"),
                "Сообщение для невалидно email не соответствует ожиданиям: " + errorInvalidEmail);

    }

    @Test
    public void testWithInvalidContactNumberField() {
        AddCandidateVO candidate = new AddCandidateVO();

        String invalidContactNumber = "invalidоо";
        candidate.setContactNumber(invalidContactNumber);

        addCandidatePage.contactNumberFieldCheck(candidate);

        String errorInvalidContactNumber = addCandidatePage.getErrContactNumberMessage();
        assertTrue(errorInvalidContactNumber.contains("Allows numbers and only + - / ( )"),
                "Сообщение для невалидного номера телефона не соответствует ожиданиям: " + errorInvalidContactNumber);

    }

    @Test
    public void testWithInvalidSizeResume() {

        String filePath = new File("src/test/resources/images/invalidSize.jpg").getAbsolutePath();
        addCandidatePage.uploadResume(filePath);

        String errorAttachSizeMessage = addCandidatePage.getErrAttachSize();
        assertTrue(errorAttachSizeMessage.contains("Attachment Size Exceeded"),
                "Сообщение об ошибке для резюме с превышением размера файла не соответствует ожиданиям : " + errorAttachSizeMessage);
    }

    @Test
    public void testWithInvalidFormatResume() {

        String filePath = new File("src/test/resources/images/InvalidResume.gif").getAbsolutePath();
        addCandidatePage.uploadResume(filePath);

        String errorAttachFormatMessage = addCandidatePage.getErrAttachFormat();
        assertTrue(errorAttachFormatMessage.contains("File type not allowed"),
                "Сообщение об ошибке для резюме с невадидным форматом файла не соответствует ожиданиям: " + errorAttachFormatMessage);
    }

    @Test
    public void testWithCancelAddingCandidate() {
        AddCandidateVO candidate = new AddCandidateVO();
        addCandidatePage.fillCandidateFormWithAllFields(candidate);
        addCandidatePage.clickCancelButton();

        String currentTabTitle = addCandidatePage.getCandidateTab();
        assertTrue(currentTabTitle.contains("Candidate"),
                "Название текущей вкладки не соответствует ожиданиям: " + currentTabTitle);
    }
}


