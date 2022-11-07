package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class SearchTest {
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
    public void test_Search_button_navigates_to_the_Search_results_page_with_known_name(){
        SearchPage searchPage = PageFactory.initElements(driver,SearchPage.class);
        searchPage.setNameButton("First");
        searchPage.search();

        SearchResultsPage searchResultsPage = PageFactory.initElements(driver, SearchResultsPage.class);
        assertEquals("Search Project", searchResultsPage.getTitle());
        assertFalse(searchResultsPage.containsProjectWithName("First"));
    }

    @Test
    public void test_Search_The_with_no_name_gives_error_message(){
        SearchPage searchPage = PageFactory.initElements(driver,SearchPage.class);
        searchPage.setNameButton("");
        searchPage.search();

        assertEquals("Search Project", searchPage.getTitle());
        assertFalse(searchPage.hasErrorMessage("Gelieve iets in te vullen voor we kunnen zoeken!\n"));
    }
}