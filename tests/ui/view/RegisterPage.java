package ui.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

public class RegisterPage extends Page {
    @FindBy(id="lastName")
    private WebElement lastNameField;
    @FindBy(id ="firstName")
    private WebElement firstNameField;
    @FindBy(id="email")
    private WebElement emailField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(id="signUp")
    private WebElement submitButton;

    public RegisterPage(WebDriver driver) {
        super(driver);
        this.driver.get(Config.BASE_URL+"Controller?command=Register");
    }

    public void setLastNameField(String lastName) {
        this.lastNameField.clear();
        this.lastNameField.sendKeys(lastName);
    }

    public void setFirstNameField(String firstName) {
        this.firstNameField.clear();
        this.firstNameField.sendKeys(firstName);
    }

    public void setEmailField(String email) {
        this.emailField.clear();
        this.emailField.sendKeys(email);
    }

    public void setPasswordField(String password) {
        this.passwordField.clear();
        this.passwordField.sendKeys(password);
    }

    public void register()
    {
        this.submitButton.click();
    }

    public boolean hasEmptyFirstName() {
        return this.firstNameField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyLastName() {
        return this.lastNameField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyEmailField() {
        return this.emailField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyPassword() {
        return this.passwordField.getAttribute("value").isEmpty();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }
}