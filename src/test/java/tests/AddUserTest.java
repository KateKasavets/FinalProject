package tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import page.AddUserPage;
import entities.AddUserVO;

public class AddUserTest extends BaseTest {
    private AddUserPage addUserPage;

    @Test
    public void addUserTest() {
        AddUserVO user = new AddUserVO();
        addUserPage = new AddUserPage(driver);

        addUserPage.clickPimMenu();
        addUserPage.clickAddButton();
        addUserPage.uploadProfilePicture(user.getProfilePicture().getAbsolutePath());
        addUserPage.fillEmployeeForm(user);
        addUserPage.saveEmployee();
        addUserPage.waitForPopupToDisappear();


        String displayedUserName = addUserPage.getDisplayedUserName();
        String expectedUserName = addUserPage.getExpectedFullName(user);
        System.out.println("Имя нового пользователя: " + displayedUserName);

        Assert.assertEquals(displayedUserName, expectedUserName, "Имя на карточке не совпадает с ожидаемым именем!");
    }
}
