package in.akbar.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	private WebElement txtFirstName;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	private WebElement txtLastName;
	
	@FindBy(xpath = "//input[@id='input-email']")
	private WebElement txtEmail;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	private WebElement txtTelephone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement txtPassword;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	private WebElement txtConfirmPassword;
	
	@FindBy(xpath = "//input[@name='agree']")
	private WebElement chkPolicy;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement btnContinue;
	
	@FindBy(xpath = "//div[@id='content']/h1")
	private WebElement msgConfirmation;
	
	public void setUserFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	
	public void setUserLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String telphone) {
		txtTelephone.sendKeys(telphone);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void setConfirmPassword(String confirmPassword) {
		txtConfirmPassword.sendKeys(confirmPassword);
	}
	
	public void clickChkbox() {
		chkPolicy.click();
	}
	
	public void clickContinue() {
		btnContinue.click();
	}
	
	public String getConfirmationMsg() {
		try {
			return msgConfirmation.getText();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}