package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@placeholder=\"E-Mail Address\"]") WebElement txtEmail;
	@FindBy(xpath="//input[@placeholder=\"Password\"]") WebElement txtPassword;
	@FindBy(xpath="//button[text()=\"Login\"]") WebElement btnLogin;
	
	
	public void email(String Email) {
		txtEmail.sendKeys(Email);
	}
	
	public void password(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void login() {
		btnLogin.click();
	}
}
