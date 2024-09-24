package in.akbar.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import in.akbar.pageobjects.HomePage;
import in.akbar.pageobjects.LoginPage;
import in.akbar.pageobjects.MyAccountPage;
import in.akbar.testbase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups= {"Sanity", "Master"})
	public void verifyMessageVisibility() {
		
		logger.info("***** Starting TC002_LoginTest  ****");
		
		try {
			HomePage hp = new HomePage(driver);
			hp.clickOnMyAccount();
			logger.info("Clicked on MyAccount Link.. ");
			hp.clickOnLogin();
			logger.info("Clicked on Login Link.. ");
			
			logger.info("Providing login details...");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(props.getProperty("email"));
			lp.setPassword(props.getProperty("password"));
			lp.clickLogin();
			
			MyAccountPage macc = new MyAccountPage(driver);
			boolean status = macc.checkMessage();
			
			logger.info("Validating display MyAccount text");
			Assert.assertEquals(status, true, "Test Failed");
			//Assert.assertTrue(status);
		} catch(Exception e) {
			Assert.fail("Test Failed: "+e.getMessage());
		} finally {
			logger.info("***** Finished TC002_LoginTest *****");
		}
	}
}