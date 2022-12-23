package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterUserTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverHelper.getDriver();
        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.login();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Register_AllFieldsFilledInCorrectly_UserIsRegistered_and_RoleIsEmployee()
    {
        RegisterPage registerPage = PageFactory.initElements(driver,RegisterPage.class);
        registerPage.setLastNameField("Lauwers");
        registerPage.setFirstNameField("Ernest");
        registerPage.setEmailField("ernest.lauwers@gmail.com");
        registerPage.setPasswordField("t");
        registerPage.register();
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        assertEquals("Users",overviewPage.getTitle());
        assertTrue(overviewPage.containsUserWithEmail("ernest.lauwers@gmail.com"));
    }

    @Test
    public void test_Register_EmailNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        RegisterPage registerPage = PageFactory.initElements(driver,RegisterPage.class);
        registerPage.setLastNameField("Lauwers");
        registerPage.setFirstNameField("Ernest");
        registerPage.setPasswordField("t");
        registerPage.register();

        assertEquals("Register",registerPage.getTitle());
        assertTrue(registerPage.hasEmptyEmailField());
        assertTrue(registerPage.hasErrorMessage("No email given"));
    }

    @Test
    public void test_Register_PasswordNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        RegisterPage registerPage = PageFactory.initElements(driver,RegisterPage.class);
        registerPage.setLastNameField("Lauwers");
        registerPage.setFirstNameField("Ernest");
        registerPage.setEmailField("ernest.lauwers@gmail.com");
        registerPage.register();

        assertEquals("Register",registerPage.getTitle());
        assertTrue(registerPage.hasEmptyPassword());
        assertTrue(registerPage.hasErrorMessage("No password given"));
    }

    @Test
    public void test_Register_FirstNameNotFilledIn_ErrorMessageGivenForFirstNameAndOtherFieldsValueKept()
    {
        RegisterPage registerPage = PageFactory.initElements(driver,RegisterPage.class);
        registerPage.setLastNameField("Lauwers");
        registerPage.setEmailField("ernest.lauwers@gmail.com");
        registerPage.setPasswordField("t");
        registerPage.register();

        assertEquals("Register",registerPage.getTitle());
        assertTrue(registerPage.hasEmptyFirstName());
        assertTrue(registerPage.hasErrorMessage("No firstname given"));
    }

    @Test
    public void test_Register_LastNameNotFilledIn_ErrorMessageGivenForLastNameAndOtherFieldsValueKept(){
        RegisterPage registerPage = PageFactory.initElements(driver,RegisterPage.class);
        registerPage.setFirstNameField("Ernest");
        registerPage.setEmailField("ernest.lauwers@gmail.com");
        registerPage.setPasswordField("t");
        registerPage.register();

        assertEquals("Register",registerPage.getTitle());
        assertTrue(registerPage.hasEmptyLastName());
        assertTrue(registerPage.hasErrorMessage("No last name given"));
    }

    @Test
    public void test_Register_User_Already_Exists_ErrorMessageGiven(){
        RegisterPage registerPage = PageFactory.initElements(driver,RegisterPage.class);
        registerPage.setLastNameField("Lauwers");
        registerPage.setFirstNameField("Ernest");
        registerPage.setEmailField("director@ucll.be");
        registerPage.setPasswordField("t");
        registerPage.register();
        assertEquals("Register", registerPage.getTitle());
        assertTrue(registerPage.hasErrorMessage("This email is already in use"));
    }
}