package pageObjects;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerifyOtpPage extends BasePage {

	public VerifyOtpPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = ("//input[@aria-label='Please enter OTP character 1']"))
	WebElement otpFistDigit;
	@FindBy(xpath = ("//input[@aria-label='Please enter OTP character 2']"))
	WebElement otpSecondDigit;
	@FindBy(xpath = ("//input[@aria-label='Please enter OTP character 3']"))
	WebElement otpThirdDigit;
	@FindBy(xpath = ("//input[@aria-label='Please enter OTP character 4']"))
	WebElement otpFourthDigit;
	@FindBy(xpath = ("//input[@aria-label='Please enter OTP character 5']"))
	WebElement otpFifthDigit;
	@FindBy(xpath = ("//input[@aria-label='Please enter OTP character 6']"))
	WebElement otpSixthDigit;
	@FindBy(xpath = ("//button[@type='submit']"))
	WebElement submitButton;
	@FindBy(xpath = ("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/form[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]"))
	WebElement absuluteSubmitButton;

	@FindBy(xpath = ("//div[@class='ant-notification-notice-description']"))
	WebElement invalidOTP_Error;
	@FindBy(xpath = ("//div[@id='login-form_otp_help']//div[text()='Please input your OTP']"))
	WebElement errorInputyourOTP;

	// action methods 007880
	public void enterOtp(String mobileNumber) {

		String lastSixDigits = mobileNumber.substring(mobileNumber.length() - 6);
		System.out.println("Last 6 digits: " + lastSixDigits);

		char[] digitsArray = new char[lastSixDigits.length()];

		// Send each digit separately
		for (int i = 0; i < lastSixDigits.length(); i++) {
			digitsArray[i] = lastSixDigits.charAt(i);
		}
//        sendKeys() expects a String, not a char
//        So you must convert char to String
		otpFistDigit.sendKeys(String.valueOf(digitsArray[0]));
		otpSecondDigit.sendKeys(String.valueOf(digitsArray[1]));
		otpThirdDigit.sendKeys(String.valueOf(digitsArray[2]));
		otpFourthDigit.sendKeys(String.valueOf(digitsArray[3]));
		otpFifthDigit.sendKeys(String.valueOf(digitsArray[4]));
		otpSixthDigit.sendKeys(String.valueOf(digitsArray[5]));
	}

	public void enterInvalidOtp() {
		Random random = new Random();
		int sixDigitNumber = 100000 + random.nextInt(900000);

		String otpString = String.valueOf(sixDigitNumber);

		// Convert to char array
		char[] digitsArray = new char[otpString.length()];
		for (int i = 0; i < otpString.length(); i++) {
			digitsArray[i] = otpString.charAt(i);
		}
		otpFistDigit.sendKeys(String.valueOf(digitsArray[0]));
		otpSecondDigit.sendKeys(String.valueOf(digitsArray[1]));
		otpThirdDigit.sendKeys(String.valueOf(digitsArray[2]));
		otpFourthDigit.sendKeys(String.valueOf(digitsArray[3]));
		otpFifthDigit.sendKeys(String.valueOf(digitsArray[4]));
		otpSixthDigit.sendKeys(String.valueOf(digitsArray[5]));

	}

	public void clearOtpInputFields() {
		otpFistDigit.sendKeys("");
		otpSecondDigit.sendKeys("");
		otpThirdDigit.sendKeys("");
		otpFourthDigit.sendKeys("");
		otpFifthDigit.sendKeys("");
		otpSixthDigit.sendKeys("");
	}

	public void clickOnSubmitButton() {

		try {
			submitButton.click();
		} catch (Exception e) {
			absuluteSubmitButton.click();
		}

	}

	public boolean isInvalidErrorShown() {
//		invalidOTP_Error.isDisplayed();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.visibilityOf(invalidOTP_Error));
			return invalidOTP_Error.isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}

	}

	public boolean isPleaseInputYourOtpErrorMessageDisplayed() {
//		return errorInputyourOTP.isDisplayed();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.visibilityOf(errorInputyourOTP));
			return errorInputyourOTP.isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}
	}

}
