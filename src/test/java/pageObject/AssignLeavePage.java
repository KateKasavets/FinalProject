package pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AssignLeavePage {

    public WebDriver driver;

    public AssignLeavePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(@class, 'oxd-main-menu-item') and .//span[text()='Leave']]")
    private WebElement leaveMenuButton;

    @FindBy(xpath = "//a[text()='Assign Leave']")
    private WebElement assignLeaveTab;

    @FindBy(xpath = "//input[@placeholder=\"Type for hints...\"]")
    private WebElement employeeNameField;

    @FindBy(xpath = "//div[@class=\"oxd-select-text oxd-select-text--active\"]")
    private WebElement leaveTypeField;


    @FindBy(xpath = "(//div[@class='oxd-date-input']//input)[1]")
    private WebElement fromDateField;

    @FindBy(xpath = "(//div[@class='oxd-date-input']//input)[2]")
    private WebElement toDateField;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement submitButton;

    @FindBy(xpath = "//button[@class=\"oxd-button oxd-button--medium oxd-button--secondary orangehrm-button-margin\"]")
    private WebElement confirmLeaveAssignButton;

    @FindBy(xpath = "//div[contains(@class, 'modal') or contains(@class, 'popup')]")
    private WebElement popupWindow;

    @FindBy(xpath = "//label[normalize-space()='Employee Name']")
    private WebElement employeeNameLabel;

    @FindBy(xpath = "//label[normalize-space()='Leave Type']")
    private WebElement leaveTypeLabel;

    @FindBy(xpath = "//label[normalize-space()='Leave Balance']")
    private WebElement leaveBalanceLabel;

    @FindBy(xpath = "//label[normalize-space()='From Date']")
    private WebElement fromDateLabel;

    @FindBy(xpath = "//label[normalize-space()='To Date']")
    private WebElement toDateLabel;

    @FindBy(xpath = "//label[normalize-space()='Comments']")
    private WebElement commentsLabel;

    @FindBy(xpath = "//h6[normalize-space()='Assign Leave']")
    private WebElement assignLeaveTitle;

    @FindBy(xpath = "//span[@class=\"oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message\"]")
    private WebElement requiredErrMessage;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    private WebElement invalidNameErrMessage;

    @FindBy(xpath = "//span[normalize-space()='To date should be after from date']")
    private WebElement dateErrMessage;

    public void clickLeaveMenu() {
        leaveMenuButton.click();
    }

    public void clickAssingLeaveTab() {
        assignLeaveTab.click();
    }

    public void enterEmployeeName(String employeeName) {
        employeeNameField.click();
        employeeNameField.sendKeys(employeeName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstDropdownOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'oxd-autocomplete-option') and span[text()='" + employeeName + "']]")
        ));
        firstDropdownOption.click();
    }

    public void selectLeaveType() {
        leaveTypeField.click();
        driver.findElement(By.xpath("//span[normalize-space()='CAN - Bereavement']")).click();
    }

    public void enterDates(String fromDate, String toDate) {
        fromDateField.clear();
        fromDateField.sendKeys(fromDate);
        toDateField.sendKeys(Keys.CONTROL + "a");
        toDateField.sendKeys(Keys.DELETE);
        toDateField.clear();
        toDateField.sendKeys(toDate);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void clickConfirmLeaveAssignButton() {
        confirmLeaveAssignButton.click();
    }

    public boolean isLeaveCreatedSuccessfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'oxd-toast-content')]")
        ));
        return toastMessage.getText().contains("Successfully");
    }

    public boolean areAllElementsDisplayed() {
        return employeeNameLabel.isDisplayed() &&
                leaveTypeLabel.isDisplayed() &&
                leaveBalanceLabel.isDisplayed() &&
                fromDateLabel.isDisplayed() &&
                toDateLabel.isDisplayed() &&
                commentsLabel.isDisplayed() &&
                submitButton.isDisplayed() &&
                assignLeaveTitle.isDisplayed();
    }

    public String getRequiredErrMessage() {
        return requiredErrMessage.getText();
    }

    public void enterInvalidEmployeeName(String employeeName) {
        employeeNameField.click();
        employeeNameField.sendKeys(employeeName);
    }

    public String getInvalidNameErrMessage() {
        return invalidNameErrMessage.getText();
    }

    public String getDateErrMessage() {
        return dateErrMessage.getText();
    }
}
