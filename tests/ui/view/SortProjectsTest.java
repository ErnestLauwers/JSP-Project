package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class SortProjectsTest {

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
    public void test_Sort_The_ascending_button_navigates_to_the_projects_page(){
        SortProjectsPage sortProjectsPage = PageFactory.initElements(driver,SortProjectsPage.class);
        sortProjectsPage.sortAsc();
        assertEquals("Projects", sortProjectsPage.getTitle());
    }

    @Test
    public void test_Sort_The_descending_button_navigates_to_the_projects_page(){
        SortProjectsPage sortProjectsPage = PageFactory.initElements(driver,SortProjectsPage.class);
        sortProjectsPage.sortDesc();
        assertEquals("Projects", sortProjectsPage.getTitle());
    }
}
