package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import valueObjects.AddUserVO;

import java.time.Duration;

public class AddUserPage {
    public WebDriver driver;

    public AddUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@href='/web/index.php/pim/viewPimModule']")
    private WebElement pimMenuButton;

    @FindBy(xpath = "//div[@class='orangehrm-header-container']/button")
    private WebElement addButton;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement profilePicture;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@name='middleName']")
    private WebElement middleNameField;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastNameField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@class='orangehrm-edit-employee-name']//*")
    private WebElement userCardName;

    @FindBy(xpath = "//div[contains(@class, 'modal') or contains(@class, 'popup')]")
    private WebElement popupWindow;

    @FindBy(xpath = "//span[@class=\"oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message\"]")
    private WebElement requiredErrMessage;

    @FindBy(xpath = "//div[@class=\"oxd-grid-2 orangehrm-full-width-grid\"]//input")
    private WebElement employeeIdField;

    @FindBy(xpath = "//span[contains(@class, 'oxd-input-field-error-message')]")
    private WebElement errEmployeeIdMessage;

    @FindBy(xpath = "//span[contains(@class, 'oxd-input-field-error-message')]")
    private WebElement errMaxLengthMessage;

    @FindBy(xpath = "//span[@class=\"oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message\"]")
    private WebElement errFileTypeMessage;

    @FindBy(xpath = "//span[@class=\"oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message\"]")
    private WebElement errFileSizeMessage;

    @FindBy(xpath = "//div[@class=\"oxd-form-actions\"]//button")
    private WebElement cancelButton;

    @FindBy(xpath = "//a[@class=\"oxd-topbar-body-nav-tab-item\"]")
    private WebElement employeeListTab;

    public String getEmployeeListTab() {
        return employeeListTab.getText();
    }

    public String getErrFileTypeMessage() {
        return errFileTypeMessage.getText();
    }

    public void clickCancelButton() {
        cancelButton.click();
    }


    public String getErrorIDMessage() {
        return errEmployeeIdMessage.getText();
    }

    public void clickPimMenu() {
        pimMenuButton.click();
    }

    public void clickAddButton() {
        addButton.click();
    }

    public void fillEmployeeId() {
        employeeIdField.click();
        employeeIdField.clear();
        employeeIdField.sendKeys("12345678910111111");
    }

    public void uploadProfilePicture(String filePath) {
        profilePicture.sendKeys(filePath);
    }

    public void fillEmployeeForm(AddUserVO employee) {
        firstNameField.click();
        firstNameField.sendKeys(employee.getFirstName());

        middleNameField.click();
        middleNameField.sendKeys(employee.getMiddleName());

        lastNameField.click();
        lastNameField.sendKeys(employee.getLastName());
    }

    public void saveEmployee() {
        saveButton.click();
    }

    public void waitForPopupToDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(popupWindow));
    }

    public String getDisplayedUserName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(userCardName));
        return userCardName.getText();
    }

    public String getExpectedFullName(AddUserVO user) {
        return user.getFirstName() + " " + user.getLastName();
    }

    public String getRequiredMessage() {
        return requiredErrMessage.getText();
    }

    public String getErrMaxLength() {
        return errMaxLengthMessage.getText();
    }
}
