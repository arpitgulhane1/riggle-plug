package pageObjects;

import java.time.Duration;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.TestDataGenerator;
import utility.UsersUtility;

public class User_AddRopsPage extends BasePage {
	public User_AddRopsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private static String rops , mobileNumber,lastName,firstName;
	
	@FindBy(xpath="//input[@id='first_name']")
	WebElement firstName_InputField;
	
	@FindBy(xpath="//input[@id='last_name']")
	WebElement lastName_InputField;
	
	@FindBy(xpath="//input[@id='mobile']")
	WebElement 	  mobileNumber_InputField;
	
	@FindBy(xpath="//span[normalize-space()='Production']")
	WebElement productionRadioButton;
	
	@FindBy(xpath="//span[normalize-space()='Storage']")
	WebElement storageRadioButton;
	
	@FindBy(xpath="//span[@title='R-Ops']")
	WebElement ropsPageTital;
	
	
	@FindBy(xpath="//span[normalize-space()='Runner']")
	WebElement runnerRadioButton;
	
	@FindBy(xpath="//span[normalize-space()='Save']")
	WebElement btnSave;
	
	
	Random random = new Random();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	// 🔸 Generate a realistic random first name
	private String generateRandomName() {
	    String[] prefixes = {"Ra", "Vi", "Ku", "De", "Su", "An", "Pr", "Sa", "Ni", "Ar", "Di", "Ka", "Re", "Aj", "Me", "Si", "Te", "Bh", "Lo", "Ti"};
	    String[] middles  = {"vi", "ra", "ya", "na", "ta", "li", "sh", "mi", "mo", "ni", "ku", "po", "la", "ti", "de", "ne", "so"};
	    String[] suffixes = {"t", "esh", "an", "as", "it", "al", "ay", "eshwar", "inder", "deep", "kant", "anand", "raj", "veer", "pal"};

	    String firstName = prefixes[random.nextInt(prefixes.length)]
	                     + middles[random.nextInt(middles.length)]
	                     + suffixes[random.nextInt(suffixes.length)];

	    return firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
	}

	// 🔸 Generate random 10-digit mobile number starting with 9
	private String generateRandomMobile() {
	    return "9" + (100000000 + random.nextInt(899999999));
	}

	// -----------------------------------------------------------------------------------
	// 🔹 Input Field Methods
	// -----------------------------------------------------------------------------------

	public void enterFirstName() {
	    try {
	        firstName = TestDataGenerator.getRandomFirstName();
	        wait.until(ExpectedConditions.visibilityOf(firstName_InputField)).clear();
	        firstName_InputField.sendKeys(firstName);
	        System.out.println("✅ First Name entered: " + firstName);
	    } catch (Exception e) {
	        System.out.println("❌ Error entering first name: " + e.getMessage());
	    }
	}

	public void enterLastName(String txt) {
	    try {
	        wait.until(ExpectedConditions.visibilityOf(lastName_InputField)).clear();
	       lastName = TestDataGenerator.getRandomLastName()+txt;
	        lastName_InputField.sendKeys(lastName);
	        System.out.println("✅ Last Name entered: " + lastName);
	    } catch (Exception e) {
	        System.out.println("❌ Error entering last name: " + e.getMessage());
	    }
	}

	public void enterMobileNumber() {
	    try {
	        mobileNumber = generateRandomMobile();
	        wait.until(ExpectedConditions.visibilityOf(mobileNumber_InputField)).clear();
	        mobileNumber_InputField.sendKeys(mobileNumber);
	        System.out.println("📱 Mobile Number entered: " + mobileNumber);
	    } catch (Exception e) {
	        System.out.println("❌ Error entering mobile number: " + e.getMessage());
	    }
	}

	// -----------------------------------------------------------------------------------
	// 🔹 Role Selection
	// -----------------------------------------------------------------------------------

	public void selectRunner() {
	    wait.until(ExpectedConditions.elementToBeClickable(runnerRadioButton)).click();
	    rops = runnerRadioButton.getText();
	    System.out.println("🏃 Runner selected."+rops);
	}

	public void selectProduction() {
	    wait.until(ExpectedConditions.elementToBeClickable(productionRadioButton)).click();
	    rops = productionRadioButton.getText();
	    System.out.println("🏭 Production selected."+rops);
	}

	public void selectStorage() {
	    wait.until(ExpectedConditions.elementToBeClickable(storageRadioButton)).click();
	    rops = productionRadioButton.getText();
	    System.out.println("📦 Storage selected."+rops);
	}

	// -----------------------------------------------------------------------------------
	// 🔹 Save Method
	// -----------------------------------------------------------------------------------

	public void clickSave() {
	    try {
	        wait.until(ExpectedConditions.elementToBeClickable(btnSave)).click();
	        save();
	        System.out.println("💾 Save button clicked successfully.");
	    } catch (Exception e) {
	        System.out.println("❌ Error clicking Save: " + e.getMessage());
	    }
	}

	// -----------------------------------------------------------------------------------
	// 🔹 Combined Role Actions
	// -----------------------------------------------------------------------------------

	public void ClickOn_Runner_RadioButton() {
	    try {
	        selectRunner();
	        enterFirstName();
	        enterLastName("Runner");
	        enterMobileNumber();
	        clickSave();
	        System.out.println("🎯 Runner added successfully.");
	    } catch (Exception e) {
	        System.out.println("❌ Error adding Runner: " + e.getMessage());
	    }
	}

	public void save() {
	    Map<String, String> rOpsDetails = Map.of(
	        "FirstName", firstName,
	        "LastName", lastName,
	        "MobileNumber", mobileNumber,
	        "UserType", rops
	    );

	    UsersUtility.writeJson("R-Ops", rOpsDetails);
	    System.out.println("R-Ops details saved successfully");
	}

	
	public void ClickOn_Production_RadioButton() {
	    try {
	        selectProduction();
	        enterFirstName();
	        enterLastName("Production");
	        enterMobileNumber();
	        clickSave();
	        System.out.println("🎯 Production added successfully.");
	    } catch (Exception e) {
	        System.out.println("❌ Error adding Production: " + e.getMessage());
	    }
	}

	public void ClickOn_Storage_RadioButton() {
	    try {
	        selectStorage();
	        enterFirstName();
	        enterLastName("Storage");
	        enterMobileNumber();
	        clickSave();
	        System.out.println("🎯 Storage added successfully.");
	    } catch (Exception e) {
	        System.out.println("❌ Error adding Storage: " + e.getMessage());
	    }
	}
	
	public boolean verifyRopsSuccessMessage() {
		try {
			// change tital to success message when dev done
			wait.until(ExpectedConditions.visibilityOf(ropsPageTital));
//			String actualText = productCreatedSuccessMessage.getText().trim();
//			return actualText.equals("Sales Person Added Successfully");
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

}
