package pageObjects;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utility.TestDataGenerator;

public class User_AddChannelPartnerPage extends BasePage {
	public User_AddChannelPartnerPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
    
	@FindBy(xpath="//input[@id='nest-messages_mobile']")
	WebElement mobileNumber_InputField;
	
	@FindBy(xpath ="//input[@id='nest-messages_email']")
	WebElement email_InputField;
	 
	@FindBy(xpath="//input[@id='nest-messages_first_name']")
	WebElement firstName__InputField;
	
	@FindBy(xpath ="//input[@id='nest-messages_last_name']")
	WebElement lastName_InputField;
	
	@FindBy(xpath="//input[@id='nest-messages_name']")
	WebElement firmName_InputField;
	
	@FindBy(xpath="//input[@id='nest-messages_channel_id']")
	WebElement channelPartnerId__InputField;
	
	@FindBy(xpath="//input[@id='nest-messages_gst_number']")
	WebElement gstNumber__InputField;
	
	@FindBy(xpath="//input[@id='nest-messages_pan_number']")
	WebElement panNumber__InputField;
	
	@FindBy(xpath="//input[@id='nest-messages_fssai']")
	WebElement fssaiNumber_InputField;
	
	@FindBy(xpath="//input[@id='nest-messages_fssai_expiry_date']")
	WebElement fssaiExpiryDate__InputField;
	
	@FindBy(xpath="//input[@id='nest-messages_address_line']")
	WebElement address__InputField;
	
	@FindBy(xpath="//input[@id='nest-messages_locality']")
	WebElement landmark_InputField;
	
	@FindBy(xpath="//input[@id='nest-messages_pincode']")
	WebElement pincode__InputField;
	
	@FindBy(xpath="//input[@id='nest-messages_city']")
	WebElement city__InputField;
	
	@FindBy(xpath="//input[@id='nest-messages_state']")
	WebElement state__InputField;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement btnSave;
	
	  Random random = new Random();

	 // ---------- HELPER METHODS ----------
    private String randomAlphabetic(int len) {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) sb.append(chars.charAt(random.nextInt(chars.length())));
        return sb.substring(0, 1).toUpperCase() + sb.substring(1);
    }

    private String randomNumber(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) sb.append(random.nextInt(10));
        return sb.toString();
    }

    // ---------- FIELD METHODS ----------

    public void enterMobileNumber() {
        try {
            String[] prefixes = {"9", "8", "7"};
            StringBuilder mobile = new StringBuilder(prefixes[random.nextInt(prefixes.length)]);
            for (int i = 1; i < 10; i++) mobile.append(random.nextInt(10));
            mobileNumber_InputField.clear();
            mobileNumber_InputField.sendKeys(mobile.toString());
            System.out.println("✅ Mobile Number Entered: " + mobile);
        } catch (Exception e) {
            System.out.println("❌ Error in addmobileNumber_InputField_InputField_InputField(): " + e.getMessage());
        }
    }

    public void enterEmail() {
        try {
            String email = randomAlphabetic(5).toLowerCase() + random.nextInt(1000) + "@gmail.com";
            email_InputField.clear();
            email_InputField.sendKeys(email);
            System.out.println("✅ Email Entered: " + email);
        } catch (Exception e) {
            System.out.println("❌ Error in addEmail(): " + e.getMessage());
        }
    }

    public void enterFirstName() {
        try {
            String firstName = randomAlphabetic(6);
            firmName_InputField.clear();
            firmName_InputField.sendKeys(firstName);
            System.out.println("✅ First Name Entered: " + firstName);
        } catch (Exception e) {
            System.out.println("❌ Error in addFirstName(): " + e.getMessage());
        }
    }

    public void enterLastName() {
        try {
            String lastName = randomAlphabetic(6);
            lastName_InputField.clear();
            lastName_InputField.sendKeys(lastName);
            System.out.println("✅ Last Name Entered: " + lastName);
        } catch (Exception e) {
            System.out.println("❌ Error in addLastName(): " + e.getMessage());
        }
    }

    public void enterFirmName() {
        try {
            String firm = randomAlphabetic(8) + " Traders";
            firmName_InputField.clear();
            firmName_InputField.sendKeys(firm);
            System.out.println("✅ Firm Name Entered: " + firm);
        } catch (Exception e) {
            System.out.println("❌ Error in addFirmName(): " + e.getMessage());
        }
    }

    public void enterChannelPartnerId() {
        try {
            String id = randomNumber(6);
            channelPartnerId__InputField.clear();
            channelPartnerId__InputField.sendKeys(id);
            System.out.println("✅ Channel Partner ID Entered: " + id);
        } catch (Exception e) {
            System.out.println("❌ Error in addChannelPartnerId(): " + e.getMessage());
        }
    }
    public String generateValidGST() {
        return String.format("%02d", random.nextInt(36)) +
        		randomAlphabetic(5).toUpperCase() +
        		randomNumber(4) +
               "F" +
               random.nextInt(10) +
               "Z" +
               random.nextInt(9);
    }
    public void enterGstNumber() {
        try {
            String gst = generateValidGST();
            gstNumber__InputField.clear();
            gstNumber__InputField.sendKeys(gst);
            System.out.println("✅ GST Number Entered: " + gst);
        } catch (Exception e) {
            System.out.println("❌ Error in addGstNumber(): " + e.getMessage());
        }
    }

    public void enterPanNumber() {
        try {
            String pan = "ABCDE" + randomNumber(4) + "F";
            panNumber__InputField.clear();
            panNumber__InputField.sendKeys(pan);
            System.out.println("✅ PAN Number Entered: " + pan);
        } catch (Exception e) {
            System.out.println("❌ Error in addPanNumber(): " + e.getMessage());
        }
    }

    public void enterFssaiNumber() {
        try {
            String fssai = "100" + randomNumber(11); // 14-digit valid pattern
            firmName_InputField.clear();
            firmName_InputField.sendKeys(fssai);
            System.out.println("✅ FSSAI Number Entered: " + fssai);
        } catch (Exception e) {
            System.out.println("❌ Error in addFssaiNumber(): " + e.getMessage());
        }
    }

    public void enterFssaiExpiryDate() {
        try {
            // Create a future date (e.g., 2 years ahead)
            LocalDate futureDate = LocalDate.now().plusYears(2);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String expiryDate = futureDate.format(formatter);

            // Use JS to set value directly (bypasses clear issue)
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value=arguments[1]; arguments[0].dispatchEvent(new Event('change'));",
                fssaiExpiryDate__InputField, expiryDate
            );

            System.out.println("✅ FSSAI Expiry Date Entered: " + expiryDate);
        } catch (Exception e) {
            System.out.println("❌ Error in addFssaiExpiryDate(): " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static final String[] VALID_PINCODES = {
            "400001", // Mumbai
            "411001", // Pune
            "110001", // Delhi
            "560001", // Bangalore
            "500001", // Hyderabad
            "600001", // Chennai
            "700001", // Kolkata
            "302001", // Jaipur
            "226001", // Lucknow
            "380001"  // Ahmedabad
    };

 // 🔹 Method with same name, but only enters PINCODE (city/state auto)
    public void fillRandomAddressDetails() {

    	String pincode = TestDataGenerator.getRandomPinCode();
//        String pincode = VALID_PINCODES[random.nextInt(VALID_PINCODES.length)];
        String[] landmarks = {"Near Market", "Opp. School", "Behind Mall", "Near Temple"};
        String address = "House No. " + (100 + random.nextInt(900)) + ", Street No. " + (1 + random.nextInt(50));
        String landmark = landmarks[random.nextInt(landmarks.length)];

        System.out.println("===== AUTO ADDRESS ENTRY STARTED =====");
        System.out.println("Address Line : " + address);
        System.out.println("Landmark     : " + landmark);
        System.out.println("Pincode      : " + pincode);
        System.out.println("======================================");

        // Fill only address, landmark, and pincode
        address__InputField.clear();
        address__InputField.sendKeys(address);

        landmark_InputField.clear();
        landmark_InputField.sendKeys(landmark);

        panNumber__InputField.clear();
        panNumber__InputField.sendKeys(pincode);

        // Wait for city and state to auto-fill (by backend logic)
        try {
            Thread.sleep(1500); // short delay to allow autofill
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ✅ Print whatever city/state system auto-fills
        System.out.println("Auto City  : " + city__InputField.getAttribute("value"));
        System.out.println("Auto State : " + state__InputField.getAttribute("value"));
        System.out.println("======================================");
    }
    
    public void btnSave() {
        try {
            // Scroll to the Save button before clicking
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnSave);
            Thread.sleep(500); // small delay to allow scroll animation

            btnSave.click();
            System.out.println("🖱️ Save button clicked successfully!");

           
          

            System.out.println("✅ Data saved successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error while saving data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}