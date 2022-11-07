package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class DeleteProjectTest {
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
    public void test_Delete_The_delete_button_navigates_to_the_delete_page(){
        ProjectsPage projectsPage = PageFactory.initElements(driver,ProjectsPage.class);
        projectsPage.delete();
        assertEquals("Delete Project", projectsPage.getTitle());
    }

    @Test
    public void test_Delete_The_project_is_not_removed_if_cancelled(){
        DeleteProjectPage deleteProjectPage = PageFactory.initElements(driver,DeleteProjectPage.class);
        deleteProjectPage.cancel();

        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.navigateWorkOrders();

        ProjectsPage projectsPage = PageFactory.initElements(driver, ProjectsPage.class);
        assertEquals("Projects",deleteProjectPage.getTitle());
        assertTrue(projectsPage.containsProjectWithName("First"));
    }

    @Test
    public void test_Delete_The_deleted_project_is_removed_from_the_overview(){
        DeleteProjectPage deleteProjectPage = PageFactory.initElements(driver, DeleteProjectPage.class);
        deleteProjectPage.confirm();

        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.navigateWorkOrders();

        ProjectsPage projectsPage = PageFactory.initElements(driver, ProjectsPage.class);
        assertEquals("Projects",homePage.getTitle());
        assertFalse(projectsPage.containsProjectWithName("Second"));
    }
}
