package ui.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class ProjectsPage extends Page{

    public ProjectsPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"Controller?command=Projects");
    }

    public boolean containsProjectWithName(String name) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(name)) {
                found=true;
            }
        }
        return found;
    }

    @FindBy(id = "knopVerwijder")
    private WebElement deleteButton;

    @FindBy(id = "knopWijzig")
    private WebElement editButton;

    public void delete() {
        this.deleteButton.click();
    }

    public void edit() {
        this.editButton.click();
    }
}
