package ui.view;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeletePage extends Page {

    public DeletePage(WebDriver driver) {
        super(driver);
        this.driver.get(Config.BASE_URL+"Controller?command=DeleteConfirmation&id=2");
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