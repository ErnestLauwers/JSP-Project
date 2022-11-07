package ui.view;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SortPage extends Page{

    public SortPage(WebDriver driver) {
        super(driver);
        this.driver.get(Config.BASE_URL+"Controller?command=WorkOrders");
    }

    @FindBy(id = "ascLink")
    private WebElement ascButton;

    @FindBy(id = "descLink")
    private WebElement descButton;

    public void sortAsc()
    {
        this.ascButton.click();
    }

    public void sortDesc()
    {
        this.descButton.click();
    }
}