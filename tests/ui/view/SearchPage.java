package ui.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends Page{

    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver.get(Config.BASE_URL+"Controller?command=SearchProjectPage");
    }

    @FindBy(id = "search")
    private WebElement nameButton;

    @FindBy(id = "signUp")
    private WebElement searchButton;

    public void search()
    {
        this.searchButton.click();
    }

    public void setNameButton(String nameButton1) {
        this.nameButton.clear();
        this.nameButton.sendKeys(nameButton1);
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }

}
