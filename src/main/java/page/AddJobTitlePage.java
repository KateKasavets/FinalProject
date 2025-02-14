package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddJobTitlePage extends BasePage {

    public AddJobTitlePage(WebDriver driver) {
     super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(@class, 'oxd-main-menu-item') and .//span[text()='Admin']]")
    private WebElement adminMenuButton;

    @FindBy(xpath = "(//span[@class=\"oxd-topbar-body-nav-tab-item\"])[2]")
    private WebElement jobTab;

    @FindBy(xpath = "//a[text()='Job Titles']")
    private WebElement jobTitlesButton;

    @FindBy(xpath = "//button[@class=\"oxd-button oxd-button--medium oxd-button--secondary\"]")
    private WebElement addButton;

    @FindBy(xpath = "//div[@class=\"oxd-form-row\"]//input")
    private WebElement jobTitleField;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement saveButton;

    @FindBy(xpath = "//i[@class=\"oxd-icon bi-trash oxd-button-icon\"]")
    private WebElement deleteConfirmButton;

    @FindBy(xpath = "//div[contains(@class, 'modal') or contains(@class, 'popup')]")
    private WebElement popupWindow;

    public boolean isJobTitlePresent(String jobTitle) {
        String xpath = String.format("//div[@role='cell' and contains(@class, 'oxd-padding-cell')]//div[text()='%s']", jobTitle);
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }

    public void clickAdminMenu() {
        adminMenuButton.click();
    }

    public void clickJobTab() {
        jobTab.click();
    }

    public void clickJobTitlesButton() {
        jobTitlesButton.click();
    }

    public void clickAddButton() {
        addButton.click();
    }

    public void enterJobTitle(String jobTitle) {
        jobTitleField.click();
        jobTitleField.sendKeys(jobTitle);
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public void waitForPopupToDisappear() {
       waitForInvisibility(popupWindow);
    }

    public void deleteJobTitle(String jobTitle) {
        String jobTitleXpath = String.format(
                "//div[@class='oxd-table-cell oxd-padding-cell' and .//div[text()='%s']]//following-sibling::div[@class='oxd-table-cell oxd-padding-cell']//div[@class='oxd-table-cell-actions']//i[contains(@class, 'bi-trash')]",
                jobTitle
        );
        WebElement deleteButton = driver.findElement(By.xpath(jobTitleXpath));
        deleteButton.click();
        deleteConfirmButton.click();
    }

    public boolean isJobTitleDeleted(String jobTitle) {
        String xpath = String.format("//div[@role='cell' and contains(@class, 'oxd-padding-cell')]//div[text()='%s']", jobTitle);
        return driver.findElements(By.xpath(xpath)).isEmpty();
    }
}
