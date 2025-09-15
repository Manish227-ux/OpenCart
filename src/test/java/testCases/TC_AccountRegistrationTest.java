package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_AccountRegistrationTest extends BaseClass{
	
	
	
	
	@Test(groups= {"Sanity","Master"})
	public void verify_account_registration() {
		
		try {
			logger.info("Started Test TC_AccountRegistrationTest");
			
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account");
			hp.clickRegister();
			logger.info("Clicked on Register");
			
			AccountRegistrationPage ap = new AccountRegistrationPage(driver);
			logger.info("Entering values in Registration Page");
			ap.setFirstName(randomString().toUpperCase());
			ap.setLastName(randomString().toUpperCase());
			ap.setEmail(randomString() + "@gmail.com");
			ap.setTelephone(randomNumericString());
			
			String password = randomAlphaNumericString();
			ap.setPassword(password);
			ap.setConfirmPassword(password);
			
			ap.clickAgreeCheckBox();
			ap.clickContinueButton();
			
		} catch (Exception e) {
			
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			
			Assert.fail();

		}
		
		logger.info("Finished Test TC_AccountRegistrationTest");
	}

}
