package in.akbar.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@id='content'] //h2[text()='My Account']") // MYACCOUNT PAGE HEADING
	private WebElement msgHeading;
	
	@FindBy(xpath = "//aside[@id='column-right'] //a[text()='Logout']") 
	WebElement linkLogout;
	 
	public boolean checkMessage() { // MYACCOUNT PAGE HEADING DISPLAY STATUS
		try {
			return msgHeading.isDisplayed();
		} catch(Exception e) {
			return false;
		}
	}
	
	public void clickOnLogout() {
		linkLogout.click();
	}
}