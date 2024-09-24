package in.akbar.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import in.akbar.pageobjects.HomePage;
import in.akbar.pageobjects.LoginPage;
import in.akbar.pageobjects.MyAccountPage;
import in.akbar.testbase.BaseClass;
import in.akbar.utilities.DataProviders;

/* Data is valid  - login success - test pass  - logout
   Data is valid -- login failed - test fail

   Data is invalid - login success - test fail  - logout
   Data is invalid -- login failed - test pass
*/

public class TC003_LoginDataDrivenTest extends BaseClass {
	
	@Test(dataProvider = "logindata", dataProviderClass = DataProviders.class, groups="Datadriven")
	public void verifyLoginDDTest(String email, String password, String exp) {
	
		logger.info("***** Starting TC003_LoginDataDrivenTest  ****");
		
		try {
			// Home Page
			HomePage hp = new HomePage(driver);
			hp.clickOnMyAccount();
			logger.info("Clicked on MyAccount Link.. ");
			
			hp.clickOnLogin(); // Login link under MyAccount
			logger.info("Clicked on Login Link.. ");
			
			// Login Page
			logger.info("Providing login details...");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin();
			
			// MyAccount Page
			MyAccountPage macc = new MyAccountPage(driver);
			boolean status = macc.checkMessage();
			
			logger.info("Validating display MyAccount text");
			if(exp.equalsIgnoreCase("Valid")) {
				if(status == true) {
					macc.clickOnLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equalsIgnoreCase("Invalid")) {
				if(status == true) {
					macc.clickOnLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch(Exception e) {
			Assert.fail("Test Failed: "+e.getMessage());
		} finally {
			logger.info("***** Finished TC003_LoginDataDrivenTest *****");
		}
	}
}