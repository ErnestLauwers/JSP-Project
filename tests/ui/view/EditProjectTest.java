package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class EditProjectTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverHelper.getDriver();
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.login();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Update_with_start_date_not_filled_in_gives_error() {
        EditProjectPage editProjectPage = PageFactory.initElements(driver, EditProjectPage.class);
        editProjectPage.setStartDateField("");
        editProjectPage.edit();
        assertNotEquals("Edit Project", editProjectPage.getTitle());
    }

    @Test
    public void test_Edit_with_correct_parameters_edits_project_successfully() {
        EditProjectPage editProjectPage= PageFactory.initElements(driver, EditProjectPage.class);
        editProjectPage.setStartDateField("2023-04-18");
        editProjectPage.edit();

        ProjectsPage projectsPage = PageFactory.initElements(driver, ProjectsPage.class);
        assertEquals("Projects", projectsPage.getTitle());
        assertTrue(projectsPage.containsProjectWithName("First"));
    }
}