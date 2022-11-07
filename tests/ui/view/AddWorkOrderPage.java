package ui.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

public class AddWorkOrderPage extends Page {
    @FindBy(id="date")
    private WebElement date;
    @FindBy(id ="startTime")
    private WebElement startTime;
    @FindBy(id="endTime")
    private WebElement endTime;
    @FindBy(id = "description")
    private WebElement description;
    @FindBy(id="signUp")
    private WebElement submitButton;

    public AddWorkOrderPage(WebDriver driver) {
        super(driver);
        this.driver.get(Config.BASE_URL+"Controller?command=AddWorkOrderPage");
    }

    public void setDateField(String date2) {
        this.date.clear();
        this.date.sendKeys(date2);
    }

    public void setStartTimeField(String startTime2) {
        this.startTime.clear();
        this.startTime.sendKeys(startTime2);
    }

    public void setEndTimeField(String endTime2) {
        this.endTime.clear();
        this.endTime.sendKeys(endTime2);
    }

    public void setDescriptionField(String description2) {
        this.description.clear();
        this.description.sendKeys(description2);
    }

    public void addWorkOrder()
    {
        this.submitButton.click();
    }

    public boolean hasEmptyFirstName() {
        return this.date.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyLastName() {
        return this.startTime.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyEmailField() {
        return this.endTime.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyPassword() {
        return this.description.getAttribute("value").isEmpty();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }
}