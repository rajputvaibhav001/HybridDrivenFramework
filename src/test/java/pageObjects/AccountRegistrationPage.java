package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	//WebDriver driver;
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	//Locators
	@FindBy(xpath="//input[@placeholder=\"First Name\"]")
	WebElement Fname;
	@FindBy(xpath="//input[@placeholder=\"Last Name\"]")
	WebElement Lname;
	@FindBy(xpath="//input[@placeholder=\"E-Mail\"]")
	WebElement email;
	@FindBy(xpath="//input[@placeholder=\"Password\"]")
	WebElement password;
	@FindBy(xpath="//input[@name=\"agree\"]")
	WebElement privacy;
	@FindBy(xpath="//button[@type=\"submit\"]")
	WebElement btnContinue;
	@FindBy(tagName="h1")
	WebElement msg;
	
	//Methods
	public void Fnamee(String nameF) {
		Fname.sendKeys(nameF);
	}
	public void Lnamee(String nameL) {
		Lname.sendKeys(nameL);
	}
	public void emaill(String maile) {
		email.sendKeys(maile);
	}
	public void passwordd(String pass) {
		password.sendKeys(pass);
	}
	public void privacyy() {
		privacy.click();
	}
	public void continueBtn() {
		btnContinue.click();
	}
	public String msgg() {
		try {
			return msg.getText();
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
}
