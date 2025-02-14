package page;

import entities.AddCandidateVO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCandidatePage extends BasePage {

    public AddCandidatePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(@class, 'oxd-main-menu-item') and .//span[text()='Recruitment']]")
    private WebElement recruitmentMenuButton;

    @FindBy(xpath = "//i[@class=\"oxd-icon bi-plus oxd-button-icon\"]")
    private WebElement addButton;

    @FindBy(xpath = "//input[@name=\"firstName\"]")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@name=\"middleName\"]")
    private WebElement middleNameField;

    @FindBy(xpath = "//input[@name=\"lastName\"]")
    private WebElement lastNameField;

    @FindBy(xpath = "//div[contains(@class, 'oxd-input-group__label-wrapper')]//label[text()='Email']/parent::div/following-sibling::div//input")
    private WebElement emailField;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement saveButton;

    @FindBy(xpath = "//h6[text()='Candidate Profile']")
    private WebElement candidateProfilePage;

    @FindBy(xpath = "//div[@id='oxd-toaster_1']")
    private WebElement popupWindow;

    @FindBy(xpath = "//i[@class=\"oxd-icon bi-caret-down-fill oxd-select-text--arrow\"]")
    private WebElement vacancyDropdown;

    @FindBy(xpath = "//label[text()='Contact Number']/parent::div/following-sibling::div//input[@placeholder='Type here']")
    private WebElement contactNumberField;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement uploadResume;

    @FindBy(xpath = "//input[@placeholder='Enter comma seperated words...']")
    private WebElement keywordsField;

    @FindBy(xpath = "//i[@class=\"oxd-icon bi-calendar oxd-date-input-icon\"]")
    private WebElement dateOfApplicationField;

    @FindBy(xpath = "//textarea[@placeholder='Type here']")
    private WebElement notesField;

    @FindBy(xpath = "//i[@class=\"oxd-icon bi-check oxd-checkbox-input-icon\"]")
    private WebElement consentCheckbox;

    @FindBy(xpath = "//span[@class=\"oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message\"]")
    private WebElement requiredErrMessage;

    @FindBy(xpath = "//span[@class=\"oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message\"]")
    private WebElement errMaxLength;

    @FindBy(xpath = "//span[text()='Expected format: admin@example.com']")
    private WebElement errEmailField;

    @FindBy(xpath = "//span[text()='Allows numbers and only + - / ( )']")
    private WebElement errContactNumberField;

    @FindBy(xpath = "//span[@class=\"oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message\"]")
    private WebElement errAttachSize;

    @FindBy(xpath = "//span[@class=\"oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message\"]")
    private WebElement errAttachFormat;

    @FindBy(xpath = "//button[@class=\"oxd-button oxd-button--medium oxd-button--ghost\"]")
    private WebElement cancelButton;

    @FindBy(xpath = "//a[contains(@class, 'oxd-topbar-body-nav-tab-item') and text()='Candidates']")
    private WebElement candidateTab;


    public void clickRecruitmentMenu() {
        recruitmentMenuButton.click();
    }

    public void clickAddButton() {
        addButton.click();
    }

    public void fillCandidateForm(AddCandidateVO candidate) {
        firstNameField.click();
        firstNameField.sendKeys(candidate.getFirstName());

        lastNameField.click();
        lastNameField.sendKeys(candidate.getLastName());

        emailField.click();
        emailField.sendKeys(candidate.getEmail());
    }

    public void saveCandidate() {
        saveButton.click();
    }

    public void waitForPopupToDisappear() {
        waitForInvisibility(popupWindow);
    }

    public String getCandidateProfileTitle() {
        return candidateProfilePage.getText();
    }

    public void fillCandidateFormWithAllFields(AddCandidateVO candidate) {
        firstNameField.click();
        firstNameField.sendKeys(candidate.getFirstName());
        lastNameField.click();
        lastNameField.sendKeys(candidate.getLastName());
        middleNameField.click();
        middleNameField.sendKeys(candidate.getMiddleName());
        middleNameField.click();
        emailField.click();
        emailField.sendKeys(candidate.getEmail());
        vacancyDropdown.click();
        WebElement vacancyOption = driver.findElement(By.xpath("//div[contains(@class, 'oxd-select-dropdown')]//span[text()='Junior Account Assistant']"));
        vacancyOption.click();
        contactNumberField.click();
        contactNumberField.sendKeys(candidate.getContactNumber());
        uploadResume.sendKeys(candidate.getResumePath());
        keywordsField.click();
        keywordsField.sendKeys(candidate.getKeywords());
        notesField.click();
        notesField.sendKeys(candidate.getNotes());
        if (candidate.isConsentToKeepData() && !consentCheckbox.isSelected()) {
            consentCheckbox.click();
        }
    }

    public String getRequiredMessage() {
        return requiredErrMessage.getText();
    }

    public void fillFirstAndLastName(AddCandidateVO candidate) {
        firstNameField.click();
        firstNameField.sendKeys(candidate.getFirstName());

        lastNameField.click();
        lastNameField.sendKeys(candidate.getLastName());
    }

    public void fillEmail(AddCandidateVO candidate) {
        emailField.click();
        emailField.sendKeys(candidate.getEmail());
    }

    public String getErrMaxLengthMessage() {
        return errMaxLength.getText();
    }

    public String getErrEmailField() {
        return errEmailField.getText();
    }

    public String getErrContactNumberMessage() {
        return errContactNumberField.getText();
    }

    public void contactNumberFieldCheck(AddCandidateVO candidate) {
        contactNumberField.click();
        contactNumberField.sendKeys(candidate.getContactNumber());

    }

    public void uploadResume(String filePath) {
        uploadResume.sendKeys(filePath);
    }

    public String getErrAttachSize() {
        return errAttachSize.getText();
    }

    public String getErrAttachFormat() {
        return errAttachFormat.getText();
    }

    public void clickCancelButton() {
        cancelButton.click();
    }

    public String getCandidateTab() {
        return candidateTab.getText();
    }
}
