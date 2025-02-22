package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

public class PiMFormPage extends BasePage {


    public PiMFormPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//a[normalize-space()='Job']")
    private WebElement jobTab;

    @FindBy(xpath = "(//div[@class=\"oxd-select-text-input\"])[3]")
    private WebElement sunUnitField;

    @FindBy(xpath = "//div[contains(@class, 'oxd-select-option') and @role='option']//span[text()='Sales']")
    private WebElement salesUnit;


    @FindBy(xpath = "//div[contains(@class, 'oxd-select-option') and @role='option']//span[normalize-space()='Sales']")
    private WebElement saleSubUnitSelectPiMPage;

    @FindBy(xpath = "(//div[contains(text(),'-- Select --')])[3]")
    private WebElement subUnitInputFieldPiMPage;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    private WebElement searchButtonPiMPage;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@role='cell']//div[contains(text(),'Sales')]")
    private WebElement foundUser;

    @FindBy(xpath = "//i[@class=\"oxd-icon bi-pencil-fill\"]")
    private WebElement pencilIcon;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement lastNameField;

    @FindBy(xpath = "//div[@class='oxd-select-text-input' and text()='Sales']")
    private WebElement salesCheckField;

    @FindBy(xpath = "//div[@role='option' and contains(@class, 'oxd-select-option') and contains(., 'VP - Sales & Marketing')]")
    private WebElement vpSales;

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[1]")
    private WebElement jobTitle;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement saveJobDetails;

    @FindBy(xpath = "//div[contains(text(),'VP - Sales & Marketing')]")
    private WebElement vpSalesJobTitle;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//input[@type='file' and contains(@class, 'oxd-file-input')]")
    private WebElement fileInput;

    @FindBy(xpath = "(//button[@type=\"submit\"])[2]")
    private WebElement saveButton2;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span']")
    private WebElement recordFoundString;

    public boolean isFoundUser() {
        return foundUser.isDisplayed();
    }

    public void selectSalesUnit() {
        jobTab.click();
        sunUnitField.click();
        salesUnit.click();
    }

    public void searchForSalesSubUnit() {
        subUnitInputFieldPiMPage.click();
        saleSubUnitSelectPiMPage.click();
        searchButtonPiMPage.click();
    }

    public void clickPencilIcon() {
        pencilIcon.click();
    }

    public void clickJobTab() {
        jobTab.click();
    }

    public boolean isSalesUnitDisplayed() {
        return salesCheckField.getText().equals("Sales");
    }

    public void selectJobTitle() {
        waitElementBeVisible(jobTitle).click();
        waitElementBeVisible(vpSales).click();
    }

    public boolean isJobTitleDisplayed() {
        return vpSalesJobTitle.isDisplayed();
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public void clickSaveButton2() {
        saveButton2.click();
    }

    public void waitForElementToBeVisible() {
        waitForElementToBeVisible(By.xpath("//div[@id='oxd-toaster_1']"));
    }


    public void uploadImage() {
        String fileName = "PIM.jpg";
        String filePath = new File("src/test/resources/images/" + fileName).getAbsolutePath();
        fileInput.sendKeys(filePath);
    }

    public void clickAddButton() {
        addButton.click();
    }

    public boolean isFoundRecorder() {
        return recordFoundString.isDisplayed();
    }
}
