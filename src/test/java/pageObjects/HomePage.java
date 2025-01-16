package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//span[text()=\"My Account\"]") WebElement MyAccount;
	@FindBy(xpath="//a[text()=\"Register\"]") WebElement Register;
	@FindBy(xpath="//a[text()=\"Login\"]") WebElement btnLogin;
	
	
	public void MaccountClick() {
		MyAccount.click();
		
	}
	
	public void MyReg() {
		Register.click();
	}
	
	public void loginBtn() {
		btnLogin.click();
	}
}
