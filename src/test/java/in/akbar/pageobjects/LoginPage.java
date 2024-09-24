package in.akbar.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-email']")
	private WebElement txtEmail;
	
	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement txtPassword;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement btnSubmit;
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void clickLogin() {
		btnSubmit.click();
	}
}