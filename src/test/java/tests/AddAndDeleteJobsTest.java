package tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.AddJobTitlePage;

import java.util.ArrayList;
import java.util.List;


public class AddAndDeleteJobsTest extends BaseTest {

    private AddJobTitlePage addJobTitlePage;
    private List<String> jobTitles = new ArrayList<>();

    @DataProvider(name = "jobTitlesProvider")
    public Object[][] provideJobTitles() {
        Faker faker = new Faker();
        return new Object[][]{
                {faker.job().title()},
                {faker.job().title()},
                {faker.job().title()}
        };
    }


    @Test(dataProvider = "jobTitlesProvider")
    public void testAddJobTitle(String jobTitle) {

        addJobTitlePage = new AddJobTitlePage(driver);
        addJobTitlePage.clickAdminMenu();
        addJobTitlePage.clickJobTab();
        addJobTitlePage.clickJobTitlesButton();
        addJobTitlePage.clickAddButton();
        addJobTitlePage.enterJobTitle(jobTitle);
        addJobTitlePage.clickSaveButton();
        addJobTitlePage.waitForPopupToDisappear();

        Assert.assertTrue(addJobTitlePage.isJobTitlePresent(jobTitle), "Job Title '" + jobTitle + "' was not found on the page.");
        jobTitles.add(jobTitle);
    }

    @Test(dependsOnMethods = "testAddJobTitle")
    public void testDeleteJobTitles() {
        addJobTitlePage = new AddJobTitlePage(driver);
        addJobTitlePage.clickAdminMenu();
        addJobTitlePage.clickJobTab();
        addJobTitlePage.clickJobTitlesButton();

        jobTitles.forEach(jobTitle -> {
            addJobTitlePage.deleteJobTitle(jobTitle);
            addJobTitlePage.waitForPopupToDisappear();

            Assert.assertTrue(addJobTitlePage.isJobTitleDeleted(jobTitle), "Job Title '" + jobTitle + "' was not deleted successfully.");
        });
    }
}
