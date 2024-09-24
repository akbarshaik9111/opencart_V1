package in.akbar.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//a[@title='My Account']")
	private WebElement link_myaccount;
	
	@FindBy(css = " a[href*='register']")
	private WebElement link_register;
	
	@FindBy(css = "ul a[href*='route=account/login']")
	private WebElement link_login;
	
	public void clickOnMyAccount() {
		link_myaccount.click();
	}
	
	public void clickOnRegister() {
		link_register.click();
	}
	
	public void clickOnLogin() {
		link_login.click();
	}
}