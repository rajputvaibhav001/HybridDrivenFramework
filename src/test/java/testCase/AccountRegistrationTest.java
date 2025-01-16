package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.baseClass;

public class AccountRegistrationTest extends baseClass {
	
	@Test(groups={"Regression","Master"})
	public void accountRegistration() throws InterruptedException {
		//try {
			HomePage hp= new HomePage(driver);
			logger.info("Starting TestCase.");
			driver.navigate().refresh();
			Thread.sleep(3000);
			hp.MaccountClick();
			logger.info("Click on My Account");
			
			Thread.sleep(10000);
			hp.MyReg();
			logger.info("Click on Registration");
			
			
			AccountRegistrationPage arp= new AccountRegistrationPage(driver);
			arp.Fnamee(randomString().toUpperCase());
			logger.info("First Name entered");
			arp.Lnamee(randomString().toUpperCase());
			logger.info("Last Name entered");
			arp.emaill(randomString().toLowerCase()+"@yopmail.com");
			logger.info("Email entered");
			arp.passwordd(alphaNumeric());
			logger.info("Password entered");
			Thread.sleep(3000);
			arp.privacyy();
			logger.info("Privacy enabled");
			arp.continueBtn();
			logger.info("Continue button clicked");
			Thread.sleep(3000);
			String confMsg= arp.msgg();
			Assert.assertEquals(confMsg, "Your Account Has Been Created!");
			logger.info("Msg displayed");
		/*} catch(Exception e) {
			logger.error("Test Failed");
			//logger.debug("Debug Logs");
			Assert.fail();
		}*/
		logger.info("Test Completed");
		
		
	}
	

	
}
