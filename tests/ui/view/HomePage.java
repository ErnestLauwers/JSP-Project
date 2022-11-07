package ui.view;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver.get(Config.BASE_URL+"Controller?command=Home");
    }

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(id = "overviewNav")
    private WebElement overviewNav;

    @FindBy(id = "workOrdersNav")
    private WebElement workOrdersNav;

    public void setMailField(String mail) {
        this.emailField.clear();
        this.emailField.sendKeys(mail);
    }

    public void setPassField(String pass) {
        this.passwordField.clear();
        this.passwordField.sendKeys(pass);
    }

    public void loginClick(){
        this.loginButton.click();
    }

    public void login() {
        setMailField("director@ucll.be");
        setPassField("t");
        loginClick();
    }

    public void navigateOverview() {
        this.overviewNav.click();
    }

    public void navigateWorkOrders() {
        this.workOrdersNav.click();
    }
}