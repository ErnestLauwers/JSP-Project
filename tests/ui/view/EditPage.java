package ui.view;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPage extends RegisterPage{

    public EditPage(WebDriver driver) {
        super(driver);
        this.driver.get(Config.BASE_URL+"Controller?command=StartEdit&id=3");
    }

    @FindBy(id = "edit")
    private WebElement editButton;

    public void edit()
    {
        this.editButton.click();
    }
}