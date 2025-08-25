package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.VerifyOtpPage;
import testBase.BaseClass;

public class Tc_Login extends BaseClass {

	@Test
	public void testEmptyMobileNumberWithoutCheckbox() {
		try {
			LoginPage login = new LoginPage(driver);
			login.clickOnsubmitButton();
			Thread.sleep(2000);

			boolean eNumStatus = login.showEmptyNoError();
//			System.out.println("eNumStatus = " + eNumStatus);
			login.uncheck_CheckBox();
			boolean eCheckBoxStatus = login.showEmptyCheckBoxError();
			System.out.println("eNumStatus = " + eCheckBoxStatus);
//		Assert.assertEquals(true, eNumStatus);
//		Assert.assertEquals(true, eCheckBoxStatus);
			Assert.assertTrue(eNumStatus, "Empty Mobile Number error is not displayed!");
			Assert.assertTrue(eCheckBoxStatus, "Empty Checkbox error is not displayed!");

		} catch (Exception e) {
			System.out.println("Error is not display");
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	}

	@Test
	public void testOpenTermsAndPrivacyLinks() {
		try {
			LoginPage login = new LoginPage(driver);
			boolean flag = login.openTermandConAndPrivacyPolyLink();
			System.out.println("flag = " + flag);
			Assert.assertTrue(flag, "Empty Checkbox error is not displayed!");
		} catch (Exception e) {
			Assert.fail("Test failed due to Term Privacy policy not found : " + e.getMessage());
		}
	}

	@Test
	public void testEmptyMobileNumberWithCheckbox() {
		try {
			LoginPage login = new LoginPage(driver);
			login.clickOnTermsAndPolicyCheckbox();
			login.clickOnsubmitButton();
			Thread.sleep(2000);

			boolean eNumStatus = login.showEmptyNoError();
			System.out.println("eNumStatus = " + eNumStatus);
//		Assert.assertEquals(true, eNumStatus);
//		Assert.assertEquals(true, eCheckBoxStatus);
			Assert.assertTrue(eNumStatus, "Empty Mobile Number error is not displayed!");

		} catch (Exception e) {
			System.out.println("Error is not display");
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	}

//	------------------------------------------------------------------------------
	@Test
	public void testLessThanTenDigitMobileNumber() {
		LoginPage login = new LoginPage(driver);
		login.clickOnTermsAndPolicyCheckbox();
		login.generateRandomNumber();
		login.clickOnsubmitButton();
//		Thread.sleep("");
		boolean errorStatus = login.showInvalidMobileNumberMsg();
		Assert.assertTrue(errorStatus, "Invalid mobile number error is not displayed!");
	}

	@Test
	public void testSpecialCharacterMobileNumber() {
		LoginPage login = new LoginPage(driver);
		login.clickOnTermsAndPolicyCheckbox();
		login.SpecialCharGenerator();
		login.clickOnsubmitButton();
		boolean errorStatus = login.showInvalidMobileNumberMsg();
		Assert.assertTrue(errorStatus, "Invalid mobile number error is not displayed!");
	}

	@Test
	public void testInvalidOTP() {
		LoginPage login = new LoginPage(driver);
		login.enterUserName(prop.getProperty("mobileNumber"));
		login.clickOnTermsAndPolicyCheckbox();
		login.clickOnsubmitButton();

		VerifyOtpPage votp = new VerifyOtpPage(driver);
		votp.enterInvalidOtp();
		votp.clickOnSubmitButton();
		boolean errorStatus = votp.isInvalidErrorShown();
		Assert.assertTrue(errorStatus, "Invalid OTP error is not displayed!");
	}

	@Test
	public void testOtpNotEnteredError() { // without enter OTP
		LoginPage login = new LoginPage(driver);
		login.enterUserName(prop.getProperty("mobileNumber"));
		login.clickOnTermsAndPolicyCheckbox();
		login.clickOnsubmitButton();

		VerifyOtpPage votp = new VerifyOtpPage(driver);
		votp.clearOtpInputFields();
		votp.clickOnSubmitButton();
		boolean status = votp.isPleaseInputYourOtpErrorMessageDisplayed();
//		System.out.println("status = "+status);
		Assert.assertTrue(status, "Please Input Your OTP Error is not displayed!");
	}

	@Test
	public void testSuccessfulLogin() {
		try {
			LoginPage login = new LoginPage(driver);
			login.enterUserName(prop.getProperty("mobileNumber"));
			login.clickOnTermsAndPolicyCheckbox();
			login.clickOnsubmitButton();

			VerifyOtpPage votp = new VerifyOtpPage(driver);
			votp.enterOtp(prop.getProperty("mobileNumber"));
			votp.clickOnSubmitButton();

			HomePage hp = new HomePage(driver);
			boolean loginSuccess = hp.verifyLogo();
			Assert.assertTrue(loginSuccess, "Login verification failed: Logo not found.");
		} catch (Exception e) {
			System.out.println("Exception occurred during login: " + e.getMessage());
			e.printStackTrace(); // Helpful for debugging
			Assert.fail("Login test failed due to exception.");
		}

	}

	@Test(groups = { "login", "regression" })
	public void testSuccessfulLoginAndLogout() {
		SoftAssert softAssert = new SoftAssert();

		try {
			LoginPage login = new LoginPage(driver);
			login.enterUserName(prop.getProperty("mobileNumber"));
			login.clickOnTermsAndPolicyCheckbox();
			login.clickOnsubmitButton();

			VerifyOtpPage votp = new VerifyOtpPage(driver);
			votp.enterOtp(prop.getProperty("mobileNumber"));
			votp.clickOnSubmitButton();

			HomePage hp = new HomePage(driver);
			boolean loginSuccess = hp.verifyLogo(); // Check if login succeeded visually

			softAssert.assertTrue(loginSuccess, "Login verification failed: Logo not found.");
//			System.out.println("Login successful");

			// Continue with post-login actions only if login succeeded
			if (loginSuccess) {
				hp.clickOnUserMenuList();
				Thread.sleep(2000);
				hp.clickOnSignOutButton();
			}

		} catch (Exception e) {
			System.out.println("Exception occurred during login: " + e.getMessage());
			e.printStackTrace(); // Helpful for debugging
			Assert.fail("Login test failed due to exception.");
		}

		// Always call assertAll at the end
		softAssert.assertAll();
	}

}
