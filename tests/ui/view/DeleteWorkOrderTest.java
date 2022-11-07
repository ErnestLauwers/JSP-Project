package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class DeleteWorkOrderTest {
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
        WorkOrdersPage workOrdersPage = PageFactory.initElements(driver,WorkOrdersPage.class);
        workOrdersPage.delete();
        assertEquals("Delete Work Order", workOrdersPage.getTitle());
    }

    @Test
    public void test_Delete_The_work_order_is_not_removed_if_cancelled(){
        DeleteWorkOrderPage deletePage = PageFactory.initElements(driver,DeleteWorkOrderPage.class);
        deletePage.cancel();

        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.navigateWorkOrders();

        WorkOrdersPage workOrdersPage = PageFactory.initElements(driver, WorkOrdersPage.class);
        assertEquals("Work Orders",deletePage.getTitle());
        assertTrue(workOrdersPage.containsWorkOrderWithId("27"));
    }

    @Test
    public void test_Delete_The_deleted_work_order_is_removed_from_the_overview(){
        DeleteWorkOrderPage deletePage = PageFactory.initElements(driver,DeleteWorkOrderPage.class);
        deletePage.confirm();

        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.navigateWorkOrders();

        WorkOrdersPage workOrdersPage = PageFactory.initElements(driver, WorkOrdersPage.class);
        assertEquals("Work Orders",homePage.getTitle());
        assertFalse(workOrdersPage.containsWorkOrderWithId("27"));
    }
}
