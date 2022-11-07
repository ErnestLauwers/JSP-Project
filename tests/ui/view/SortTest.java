package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class SortTest {
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
    public void test_Sort_The_ascending_button_navigates_to_the_work_orders_page(){
        SortPage sortPage = PageFactory.initElements(driver,SortPage.class);
        sortPage.sortAsc();
        assertEquals("Work Orders", sortPage.getTitle());
    }

    @Test
    public void test_Sort_The_descending_button_navigates_to_the_work_orders_page(){
        SortPage sortPage = PageFactory.initElements(driver,SortPage.class);
        sortPage.sortDesc();
        assertEquals("Work Orders", sortPage.getTitle());
    }
}