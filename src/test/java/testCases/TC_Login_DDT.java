package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountsPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_Login_DDT extends BaseClass{
	
	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void verifyLoginViaDDT(String email, String password) {
		
		logger.info("Started Test TC_LoginDataDrivenTest");
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account");
		hp.clickLogin();
		logger.info("Clicked on Login");
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		logger.info("Entered Email");
		lp.setPassword(password);
		logger.info("Entered Password");
		lp.clickLoginButton();
		logger.info("Clicked on Login Button");
		
		AccountsPage ap = new AccountsPage(driver);
		
		if(ap.isAccountMessageDisplayed()) {
			
			ap.clickLogoutButton();
			
			Assert.assertTrue(true);
		}
		
		else {
			
			Assert.assertTrue(false);
		}
		
		
	}

}
