package in.akbar.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import in.akbar.pageobjects.AccountRegistrationPage;
import in.akbar.pageobjects.HomePage;
import in.akbar.testbase.BaseClass;
import jdk.internal.org.jline.utils.Log;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression", "Master"})
	public void verify_account_registration() throws InterruptedException {
		logger.info("***** Starting TC001_AccountRegistrationTest  ****");
		logger.debug("This is a debug log message");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickOnMyAccount();
			logger.info("Clicked on MyAccount Link.. ");
			hp.clickOnRegister();
			logger.info("Clicked on Register Link.. ");
			
			logger.info("Providing customer details...");
			AccountRegistrationPage ap = new AccountRegistrationPage(driver);
			ap.setUserFirstName(randomString());
			ap.setUserLastName(randomString());
			ap.setEmail(randomString()+"@gmail.com");
			ap.setTelephone(randomNumber());
			String password = randomAlphaNumeric();
			ap.setPassword(password);
			ap.setConfirmPassword(password);
			ap.clickChkbox();
			ap.clickContinue();
			
			logger.info("Validating expected message..");
			if(ap.getConfirmationMsg().equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
				logger.info("Test passed");
				
			} else {
				logger.error("Test failed");
				Assert.assertTrue(false);
			}
			//Assert.assertEquals(ap.getConfirmationMsg(), "Your Account Has Been Created!");
		} catch(Exception e) {
			Assert.fail("Test failed: " + e.getMessage());
		} finally {
			logger.info("***** Finished TC001_AccountRegistrationTest *****");
		}
	}
}