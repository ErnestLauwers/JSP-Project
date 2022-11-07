package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class AddWorkOrderTest {
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
    public void test_Add_AllFieldsFilledInCorrectly_WorkOrderIsAdded()
    {
        AddWorkOrderPage addWorkOrderPage = PageFactory.initElements(driver,AddWorkOrderPage.class);
        addWorkOrderPage.setDateField("2022-10-13");
        addWorkOrderPage.setStartTimeField("09:00");
        addWorkOrderPage.setStartTimeField("13:00");
        addWorkOrderPage.setDescriptionField("HalloHalloHalloHallo");
        addWorkOrderPage.addWorkOrder();
        WorkOrdersPage workOrdersPage = PageFactory.initElements(driver, WorkOrdersPage.class);
        assertEquals("Work Orders",workOrdersPage.getTitle());
        assertFalse(workOrdersPage.containsDescription("HalloHalloHalloHallo"));
    }

    @Test
    public void test_Add_DescriptionNotFilledIn_ErrorMessageGivenForDescription(){
        AddWorkOrderPage addWorkOrderPage = PageFactory.initElements(driver,AddWorkOrderPage.class);
        addWorkOrderPage.setDateField("2022-10-14");
        addWorkOrderPage.setStartTimeField("10:00");
        addWorkOrderPage.setStartTimeField("14:00");
        addWorkOrderPage.setDescriptionField("");
        addWorkOrderPage.addWorkOrder();

        assertNotEquals("Add Work Order",addWorkOrderPage.getTitle());
    }
}