package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class AddProjectTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverHelper.getDriver();
        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.login();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Add_AllFieldsFilledInCorrectly_ProjectIsAdded()
    {
        AddProjectPage addProjectPage = PageFactory.initElements(driver,AddProjectPage.class);
        addProjectPage.setNameField("Everest");
        addProjectPage.setStartDateField("2023-05-12");
        addProjectPage.setEndDateField("2023-05-19");
        addProjectPage.addProject();

        ProjectsPage projectsPage = PageFactory.initElements(driver, ProjectsPage.class);
        assertEquals("Projects",projectsPage.getTitle());
        assertFalse(projectsPage.containsProjectWithName("Everest"));
    }

    @Test
    public void test_Add_NameNotFilledIn_ErrorMessageGivenFornName(){
        AddProjectPage addProjectPage = PageFactory.initElements(driver,AddProjectPage.class);
        addProjectPage.setNameField("");
        addProjectPage.setStartDateField("2023-05-12");
        addProjectPage.setEndDateField("2023-05-19");
        addProjectPage.addProject();

        assertNotEquals("Add Project",addProjectPage.getTitle());
    }
}