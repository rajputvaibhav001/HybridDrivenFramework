package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.baseClass;

public class LoginTest extends baseClass {
	@Test(groups={"Sanity", "Master"})
	public void verifyLogin() throws InterruptedException {
		logger.info("Starting LoginTest");
		HomePage hp= new HomePage(driver);
		driver.navigate().refresh();
		Thread.sleep(7000);
		hp.MaccountClick();
		Thread.sleep(3000);
		hp.loginBtn();
		Thread.sleep(5000);
		logger.info("Clicked Login Button");
		
		LoginPage lp= new LoginPage(driver);
		lp.email("test6397@yopmail.com");
		lp.password("Test@123");
		lp.login();
		logger.info("Logged in successfully");
		
		MyAccountPage map= new MyAccountPage(driver);
		boolean targetPage= map.isMyAccountPageExists();
		//Assert.assertEquals(targetPage, true,"Login Failed");
		Assert.assertTrue(targetPage);
		logger.info("Target page exists");
		Thread.sleep(3000);
		map.btnLogout();
		logger.info("Logged out successfully");
		
		logger.info("Finished LoginTest");
	}
}
