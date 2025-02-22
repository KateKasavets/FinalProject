package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.AddCandidatePage;
import entities.AddCandidateVO;

import static org.testng.Assert.assertTrue;

public class AddCandidateTest extends BaseTest {
    private AddCandidatePage addCandidatePage;

    @BeforeMethod
    public void setup() {
        super.setup();
        addCandidatePage = new AddCandidatePage(driver);
        addCandidatePage.clickRecruitmentMenu();
    }

    @Test
    public void testAddCandidateWithMandatoryFields() {

        addCandidatePage.clickAddButton();
        AddCandidateVO candidate = new AddCandidateVO();
        addCandidatePage.fillCandidateForm(candidate);
        addCandidatePage.saveCandidate();
        addCandidatePage.waitForPopupToDisappear();

        String actualText = addCandidatePage.getCandidateProfileTitle();
        assertTrue(actualText.contains("Candidate Profile"), "Текст не соответствует ожиданиям!");
    }

    @Test
    public void testAddCandidateWithAllFields() {

        addCandidatePage.clickAddButton();
        AddCandidateVO candidate = new AddCandidateVO();
        addCandidatePage.fillCandidateFormWithAllFields(candidate);
        addCandidatePage.saveCandidate();
        addCandidatePage.waitForPopupToDisappear();

        String actualText = addCandidatePage.getCandidateProfileTitle();
        assertTrue(actualText.contains("Candidate Profile"), "Текст не соответствует ожиданиям!");
    }
}
