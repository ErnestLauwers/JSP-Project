package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class DeleteUserTest {
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
        OverviewPage overviewPage = PageFactory.initElements(driver,OverviewPage.class);
        overviewPage.delete();
        assertEquals("Delete", overviewPage.getTitle());
    }

    @Test
    public void test_Delete_The_user_is_not_removed_if_cancelled(){
        DeletePage deletePage = PageFactory.initElements(driver,DeletePage.class);
        deletePage.cancel();

        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.navigateOverview();

        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        assertEquals("Overview",deletePage.getTitle());
        assertFalse(overviewPage.containsUserWithEmail("bart.smith@gmail.com"));
    }

    @Test
    public void test_Delete_The_deleted_user_is_removed_from_the_overview(){
        DeletePage deletePage = PageFactory.initElements(driver,DeletePage.class);
        deletePage.confirm();

        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.navigateOverview();

        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        assertEquals("Overview",homePage.getTitle());
        assertFalse(overviewPage.containsUserWithEmail("bart.smith@gmail.com"));
    }
}
