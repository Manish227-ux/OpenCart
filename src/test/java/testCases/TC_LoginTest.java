package testCases;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountsPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_LoginTest extends BaseClass{
	
	
	
	@Test(groups= {"Regression","Master"})
	void verifyLogin() throws IOException {
		
		logger.info("Started Test TC_LoginTest");
		
		Properties p = new Properties();
		FileReader file = new FileReader("C:\\Users\\manis\\eclipse-workspace\\OpenCart\\src\\test\\resources\\config.properties");
		p.load(file);
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account");
		hp.clickLogin();
		logger.info("Clicked on Login");
		
		
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail("bwayne@gmail.com");
		logger.info(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		logger.info("Entered Password");
		lp.clickLoginButton();
		logger.info("Clicked on Login Button");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AccountsPage ap = new AccountsPage(driver);
		boolean isLoggedIn = ap.isAccountMessageDisplayed();
		
		Assert.assertEquals(isLoggedIn, true);
		logger.info("Verified Login Screen Text");
		
		logger.info("Finished Test TC_LoginTest");
	}

}
