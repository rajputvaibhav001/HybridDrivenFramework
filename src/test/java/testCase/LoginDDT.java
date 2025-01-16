package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.baseClass;
import utilities.DataProviders;

public class LoginDDT extends baseClass{
	@Test(dataProvider= "LoginData", dataProviderClass= DataProviders.class, groups="DataDriver")
	public void verify_Login(String email, String pwd, String exp) {
		logger.info("LoginDDT Started...");
		try {
			HomePage hp= new HomePage(driver);
			hp.MaccountClick();
			hp.loginBtn();
			
			LoginPage lp= new LoginPage(driver);
			lp.email(email);
			Thread.sleep(3000);
			lp.password(pwd);
			Thread.sleep(3000);
			lp.login();
			Thread.sleep(3000);
			
			MyAccountPage map= new MyAccountPage(driver);
			boolean targetPage= map.isMyAccountPageExists();
			if(exp.equalsIgnoreCase("Valid")) {
				if(targetPage== true) {
					map.btnLogout();
					Assert.assertTrue(true);
				}
				else {
					Assert.assertTrue(false);
				}
			}
			if(exp.equalsIgnoreCase("Invalid")) {
				if(targetPage== true) {
					map.btnLogout();
					Assert.assertTrue(false);
				}
				else {
					Assert.assertTrue(true);
				}
			}
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("Finished LoginDDT...");
	}
}
