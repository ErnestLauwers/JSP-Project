package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class EditWorkOrderTest {
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
    public void test_Update_with_description_not_filled_in_gives_error(){
        EditWorkOrderPage editPage = PageFactory.initElements(driver,EditWorkOrderPage.class);
        editPage.setDescriptionField("");
        editPage.edit();
        assertTrue(editPage.hasErrorMessage("No description given"));
    }

    @Test
    public void test_Edit_with_correct_parameters_edits_work_order_successfully(){
        EditWorkOrderPage editPage = PageFactory.initElements(driver,EditWorkOrderPage.class);
        editPage.setDescriptionField("TestTestTestTestTest");
        editPage.edit();

        WorkOrdersPage workOrdersPage = PageFactory.initElements(driver, WorkOrdersPage.class);
        assertEquals("Work Orders",workOrdersPage.getTitle());
        assertTrue(workOrdersPage.containsWorkOrderWithId("2"));
    }
}