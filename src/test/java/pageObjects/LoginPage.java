package pageObjects;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {


	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	// dfine constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// ineslize Locators
	@FindBy(xpath = "//input[@id='login-form_mobile']")
	WebElement mobileNumber;
	@FindBy(xpath = "//input[@id='login-form_agreement']")
	WebElement termsAndPolicyCheckbox;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement submitButton;

	@FindBy(xpath = "//div[contains(text(),'Please enter your mobile number')]")
	WebElement enterMobileNumberError;
	@FindBy(xpath = "//div[contains(text(),'Accept terms and conditions.')]")
	WebElement termsAndconError;
	@FindBy(xpath = "//span[@class='ant-checkbox-inner']")
	WebElement checkbox;
	@FindBy(xpath = "//div[@class='ant-notification ant-notification-topRight']//div")
	WebElement invalidMobileNumberMsg;
	@FindBy(xpath = "//a[normalize-space()='Terms and Conditions']")
	WebElement TermsAndConditionsLink;
	@FindBy(xpath = "//a[normalize-space()='Privacy Policy']")
	WebElement PrivacyPolicy;

	// Action Methods
	public void enterUserName(String number) {
		mobileNumber.clear();
		mobileNumber.sendKeys(number);
	}

	public void clickOnTermsAndPolicyCheckbox() {
		if (!termsAndPolicyCheckbox.isSelected()) {
			termsAndPolicyCheckbox.click();
		}

	}

	public void clickOnsubmitButton() {
		submitButton.click();
	}

	public void uncheck_CheckBox() {
		if (checkbox.isSelected()) {
			checkbox.click();
		}
	}

//		-------------------------------------------------------
	public boolean showInvalidMobileNumberMsg() {
		try {
			wait.until(ExpectedConditions.visibilityOf(invalidMobileNumberMsg));
			return invalidMobileNumberMsg.isDisplayed();
		} catch (TimeoutException e) {
			return false; // Element not visible within wait time
		}
	}

	public void generateRandomNumber() {
		Random random = new Random();

		int numberOfDigits = random.nextInt(7) + 3; // (0 to 6) + 3 => (3 to 9)

		// Minimum number (example: 100 for 3 digits)
		int min = (int) Math.pow(10, numberOfDigits - 1);
		// Maximum number (example: 999 for 3 digits)
		int max = (int) Math.pow(10, numberOfDigits) - 1;

		// Generate random number between min and max
		int randomNumber = random.nextInt((max - min) + 1) + min;
		String stringRandomNumber = String.valueOf(randomNumber);
		mobileNumber.clear();
		mobileNumber.sendKeys(stringRandomNumber);
//        return randomNumber;
	}

	public void SpecialCharGenerator() {
		String specialChars = "!@#$%^&*()-_=+[]{}|;:,.<>?/`~";
		StringBuilder result = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < 10; i++) {
			int index = random.nextInt(specialChars.length());
			result.append(specialChars.charAt(index));
		}

		// Store in a String variable
		String randomSpecialString = result.toString();
		mobileNumber.clear();
		mobileNumber.sendKeys(randomSpecialString);
	}

	public boolean showEmptyNoError() {
//			boolean eNumStatus = enterMobileNumberError.isDisplayed();
//			return eNumStatus;
		try {
			return enterMobileNumberError.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean openTermandConAndPrivacyPolyLink() {

		TermsAndConditionsLink.click();
		PrivacyPolicy.click();
		String defaultWindowId = driver.getWindowHandle();
		boolean ststus = false;
		Set<String> windowId = driver.getWindowHandles();

		for (String winId : windowId) {
			String url = driver.switchTo().window(winId).getCurrentUrl();
			if (url.equals("https://riggleapp.in/privacy-policy.html")) {
				ststus = true;
				driver.close();
			} else if (url.equals("https://riggleapp.in/terms-and-conditions.html")) {
				ststus = true;
				driver.close();
			}
			driver.switchTo().window(defaultWindowId);
		}
		return ststus;

	}

	public boolean showEmptyCheckBoxError() {
//			boolean eCheckBoxStatus = termsAndconError.isDisplayed();
//			return eCheckBoxStatus;

		try {
			return termsAndconError.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
