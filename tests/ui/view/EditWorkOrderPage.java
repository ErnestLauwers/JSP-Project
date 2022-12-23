package ui.view;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditWorkOrderPage extends AddWorkOrderPage{

    public EditWorkOrderPage(WebDriver driver) {
        super(driver);
        this.driver.get(Config.BASE_URL+"Controller?command=EditWorkOrderPage&workOrderId=2");
    }

    @FindBy(id = "signUp")
    private WebElement editButton;

    public void edit()
    {
        this.editButton.click();
    }
}