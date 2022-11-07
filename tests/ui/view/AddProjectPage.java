package ui.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

public class AddProjectPage extends Page {
    @FindBy(id="projectName")
    private WebElement name;
    @FindBy(id="startDate")
    private WebElement startDate;
    @FindBy(id ="endDate")
    private WebElement endDate;
    @FindBy(id="signUp")
    private WebElement submitButton;

    public AddProjectPage(WebDriver driver) {
        super(driver);
        this.driver.get(Config.BASE_URL+"Controller?command=AddProjectPage");
    }

    public void setNameField(String name2) {
        this.name.clear();
        this.name.sendKeys(name2);
    }

    public void setStartDateField(String date2) {
        this.startDate.clear();
        this.startDate.sendKeys(date2);
    }

    public void setEndDateField(String date2) {
        this.endDate.clear();
        this.endDate.sendKeys(date2);
    }

    public void addProject()
    {
        this.submitButton.click();
    }

    public boolean hasEmptyName() {
        return this.name.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyStartDateGField() {
        return this.startDate.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyEndDateField() {
        return this.endDate.getAttribute("value").isEmpty();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }
}