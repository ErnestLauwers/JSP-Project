package ui.view;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteWorkOrderPage extends Page{

    public DeleteWorkOrderPage(WebDriver driver) {
        super(driver);
        // id veld hangt af van welke work order id's er in de database zijn!
        this.driver.get(Config.BASE_URL+"Controller?command=DeleteWorkOrderConf&workOrderId=3");
    }

    @FindBy(id = "submitYes")
    private WebElement yesButton;

    @FindBy(id = "submitNo")
    private WebElement noButton;

    public void confirm()
    {
        this.yesButton.click();
    }

    public void cancel()
    {
        this.noButton.click();
    }
}
