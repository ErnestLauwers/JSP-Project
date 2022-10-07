package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class EditUserTest {
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
    public void test_Update_with_email_not_filled_in_gives_error(){
        EditPage editPage = PageFactory.initElements(driver,EditPage.class);
        editPage.setEmailField("");
        editPage.edit();
        assertTrue(editPage.hasErrorMessage("No email given"));
    }

    @Test
    public void test_Update_with_firstName_not_filled_in_gives_error(){
        EditPage editPage = PageFactory.initElements(driver,EditPage.class);
        editPage.setFirstNameField("");
        editPage.edit();
        assertTrue(editPage.hasErrorMessage("No firstname given"));
    }

    @Test
    public void test_Edit_with_correct_parameters_edits_user_successfully(){
        EditPage editPage = PageFactory.initElements(driver,EditPage.class);
        editPage.setEmailField("lauwers.ernest@gmail.com");
        editPage.edit();

        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        assertEquals("Overview",overviewPage.getTitle());
        assertTrue(overviewPage.containsUserWithEmail("lauwers.ernest@gmail.com"));
    }
}