package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@name='username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//span[@class='oxd-topbar-header-breadcrumb']")
    private WebElement pageTitle;

    @FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
    private WebElement profileButton;

    public void inputLogin(String login) {
        usernameField.sendKeys(login);
    }

    public void inputPasswd(String passwd) {
        passwordField.sendKeys(passwd);
    }

    public void clickLoginBtn() {
        loginButton.click();
    }

    public void login(String login, String password) {
        inputLogin(login);
        inputPasswd(password);
        clickLoginBtn();
    }

    public boolean isPageTitleDisplayed() {
        return pageTitle.isDisplayed();
    }

    public String getPageTitleText() {
        return pageTitle.getText();
    }

    public boolean isProfileButtonDisplayed() {
        return profileButton.isDisplayed();
    }
}
