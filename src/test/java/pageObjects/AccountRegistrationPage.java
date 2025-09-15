package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	//Locators
	
	@FindBy(id="input-firstname")
	WebElement firstName;
	
	@FindBy(id="input-lastname")
	WebElement lastName;
	
	@FindBy(id="input-email")
	WebElement email;
	
	@FindBy(id="input-telephone")
	WebElement telephone;
	
	@FindBy(id="input-password")
	WebElement password;
	
	@FindBy(id="input-confirm")
	WebElement confirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement agreeCheckBox; 
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement continueButton;
	
	
	//Action Methods
	
	public void setFirstName(String fName) {
		
		firstName.sendKeys(fName);
	}
	
	public void setLastName(String lName) {
		
		lastName.sendKeys(lName);
	}

	public void setEmail(String emailvalue) {
	
		email.sendKeys(emailvalue);
	}

	public void setTelephone(String phone) {
	
		telephone.sendKeys(phone);
	}

	public void setPassword(String pwd) {
	
		password.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String cpwd) {
		
		password.sendKeys(cpwd);
	}
	
	public void clickAgreeCheckBox() {
		
		agreeCheckBox.click();
	}
	
	public void clickContinueButton() {
		
		continueButton.click();
	}

}
