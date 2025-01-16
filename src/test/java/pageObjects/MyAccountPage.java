package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	
	@FindBy(xpath="//h2[text()=\"My Account\"]") WebElement txtHeading;
	@FindBy(xpath="//div[@class=\"list-group mb-3\"]//a[text()=\"Logout\"]") WebElement Logout;
	
	public boolean isMyAccountPageExists() {
		return txtHeading.isDisplayed();
	}
	
	public void btnLogout() {
		Logout.click();
	}
}
