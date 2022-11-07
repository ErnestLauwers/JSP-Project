package ui.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class WorkOrdersPage extends Page{

    public WorkOrdersPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"Controller?command=WorkOrders");
    }

    public boolean containsWorkOrderWithId(String workOrderId) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("strong"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(workOrderId)) {
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