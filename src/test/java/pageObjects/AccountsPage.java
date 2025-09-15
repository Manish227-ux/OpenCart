package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountsPage extends BasePage{

	public AccountsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[contains(text(),'My Account')]")
	WebElement accountText;
	
	@FindBy(xpath="(//a[contains(text(),'Logout')])[2]") 
	WebElement logoutLink;
	
	
	public void clickLogoutButton() {
		
		logoutLink.click();
	}
	
	public boolean isAccountMessageDisplayed() {
		
		try {
			
			boolean message = accountText.isDisplayed();
			
			return message;
		}
		catch(Exception e) {
			
			return false;
		}
		
	}

}
