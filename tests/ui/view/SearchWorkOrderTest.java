package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class SearchWorkOrderTest {
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
    public void test_Search_button_navigates_to_the_Search_results_page_with_known_team(){
        SearchWorkOrdersPage searchWorkOrdersPage = PageFactory.initElements(driver,SearchWorkOrdersPage.class);
        searchWorkOrdersPage.setTeamButton("beta");
        searchWorkOrdersPage.search();

        SearchWorkOrderResultsPage searchWorkOrderResultsPage = PageFactory.initElements(driver, SearchWorkOrderResultsPage.class);
        assertEquals("Search Work Order", searchWorkOrderResultsPage.getTitle());
        assertFalse(searchWorkOrderResultsPage.containsWorkOrderWithName("Beta"));
    }

    @Test
    public void test_Search_The_with_no_Team_gives_error_message(){
        SearchWorkOrdersPage searchWorkOrdersPage = PageFactory.initElements(driver,SearchWorkOrdersPage.class);
        searchWorkOrdersPage.setTeamButton("");
        searchWorkOrdersPage.search();

        assertEquals("Search Work Order", searchWorkOrdersPage.getTitle());
        assertFalse(searchWorkOrdersPage.hasErrorMessage("Gelieve iets in te vullen voor we kunnen zoeken!\n"));
    }
}