package ui.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchWorkOrdersPage extends Page{

    public SearchWorkOrdersPage(WebDriver driver) {
        super(driver);
        this.driver.get(Config.BASE_URL+"Controller?command=SearchWorkOrderPage");
    }

    @FindBy(id = "search")
    private WebElement teamButton;

    @FindBy(id = "signUp")
    private WebElement searchButton;

    public void search()
    {
        this.searchButton.click();
    }

    public void setTeamButton(String teamButton1) {
        this.teamButton.clear();
        this.teamButton.sendKeys(teamButton1);
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }

}