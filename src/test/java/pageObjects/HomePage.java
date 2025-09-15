package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	//Locators
	
	@FindBy(xpath="//span[contains(text(),'My Account')]") 
	WebElement myAccountLink;
	
	@FindBy(linkText="Register")
	WebElement registerLink;
	
	@FindBy(linkText="Login")
	WebElement loginLink;
	
	
	//Action Methods
	
	public void clickMyAccount() {
		
		myAccountLink.click();
	}
	
	public void clickRegister() {
		
		registerLink.click();
	}
	
	public void clickLogin() {
		
		loginLink.click();
	}

}
