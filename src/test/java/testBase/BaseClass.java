package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import pageObjects.LoginPage;
import pageObjects.VerifyOtpPage;

public class BaseClass {

	public WebDriver driver;
	public Properties prop;

	@BeforeClass
	public void loadConfig() throws IOException {
		FileReader file = new FileReader(".\\src\\test\\resources\\config.properties");
		prop = new Properties();
		prop.load(file);
	}

//	@BeforeClass
	@BeforeMethod
	public void setup() throws IOException {
		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--user-data-dir=C:/temp/chrome-profile"); // Make sure the folder exists
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-gpu");
		options.addArguments("--remote-allow-origins=*");

		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get(prop.getProperty("URL"));

	}

//	@AfterClass
	@AfterMethod
	public void tearDown() {
		if (driver != null) {
//            driver.quit();
		}
	}

	public void loginToApp() {
		LoginPage login = new LoginPage(driver);
		login.enterUserName(prop.getProperty("mobileNumber"));
		login.clickOnTermsAndPolicyCheckbox();
		login.clickOnsubmitButton();

		VerifyOtpPage votp = new VerifyOtpPage(driver);
		votp.enterOtp(prop.getProperty("mobileNumber"));
		votp.clickOnSubmitButton();
	}

}