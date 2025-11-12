package pageObjects;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utility.TestDataGenerator;
import utility.UsersUtility;

public class User_AddChannelPartnerPage extends BasePage {
	public User_AddChannelPartnerPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
    
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	private static String mobileNumber, emailId, firstName, lastName, firmName, channelPartnerId, gstNumber, panNumber, fssaiNumber, fssaiExpiryDate, addressStreetArea, addressLandmark, addressPincode, addressCity, addressState, coAdminDetails, addCoAdminButton, saveButton;

	
	@FindBy(xpath="//span[@title='Channel Partners']")
	WebElement channelPartnerPageTital;
	
	@FindBy(xpath="//div[@class=\"ant-modal-title\"]")
	WebElement addChannelPartnerPageTital;
	
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
        	wait.until(ExpectedConditions.visibilityOf(addChannelPartnerPageTital));
        	mobileNumber = TestDataGenerator.generateRandomMobileNumberWithFaker();
            mobileNumber_InputField.clear();
            mobileNumber_InputField.sendKeys(mobileNumber.toString());
            System.out.println("✅ Mobile Number Entered: " + mobileNumber);
        } catch (Exception e) {
            System.out.println("❌ Error in addmobileNumber_InputField_InputField_InputField(): " + e.getMessage());
        }
    }
    
//    public String generateRandomMobileNumber() {
//		Random random = new Random();
//		// Ensure the number starts with 6-9
//		int firstDigit = 6 + random.nextInt(4); // 6, 7, 8, or 9
//		long remainingDigits = 100000000L + (long) (random.nextDouble() * 899999999L); // 9 digits
//		return firstDigit + String.valueOf(remainingDigits);
//	}

    public void enterEmail() {
        try {
        	emailId = TestDataGenerator.getRandomEmail();
            email_InputField.clear();
            email_InputField.sendKeys(emailId);
            System.out.println("✅ Email Entered: " + emailId);
        } catch (Exception e) {
            System.out.println("❌ Error in addEmail(): " + e.getMessage());
        }
    }

    public void enterFirstName() {
        try {
        	firstName = TestDataGenerator.getRandomFirstName();
            firstName__InputField.clear();
            firstName__InputField.sendKeys(firstName);
            System.out.println("✅ First Name Entered: " + firstName);
        } catch (Exception e) {
            System.out.println("❌ Error in addFirstName(): " + e.getMessage());
        }
    }

    public void enterLastName() {
        try {
        	lastName = TestDataGenerator.getRandomLastName()+"CP";
            lastName_InputField.clear();
            lastName_InputField.sendKeys(lastName);
            System.out.println("✅ Last Name Entered: " + lastName);
        } catch (Exception e) {
            System.out.println("❌ Error in addLastName(): " + e.getMessage());
        }
    }

    public void enterFirmName() {
        try {
        	firmName = TestDataGenerator.getRandomFirmName();
            firmName_InputField.clear();
            firmName_InputField.sendKeys(firmName);
            System.out.println("✅ Firm Name Entered: " + firmName);
        } catch (Exception e) {
            System.out.println("❌ Error in addFirmName(): " + e.getMessage());
        }
    }

    public void enterChannelPartnerId() {
        try {
        	channelPartnerId = randomNumber(6);
            channelPartnerId__InputField.clear();
            channelPartnerId__InputField.sendKeys(channelPartnerId);
            System.out.println("✅ Channel Partner ID Entered: " + channelPartnerId);
        } catch (Exception e) {
            System.out.println("❌ Error in addChannelPartnerId(): " + e.getMessage());
        }
    }
 

    public void enterGstNumber() {
        try {
        	gstNumber = TestDataGenerator.generateValidGST();
            gstNumber__InputField.clear();
            gstNumber__InputField.sendKeys(gstNumber);
            System.out.println("✅ GST Number Entered: " + gstNumber);
        } catch (Exception e) {
            System.out.println("❌ Error in addGstNumber(): " + e.getMessage());
        }
    }

    public void enterPanNumber() {
        try {
        	panNumber = "ABCDE" + randomNumber(4) + "F";
            panNumber__InputField.clear();
            panNumber__InputField.sendKeys(panNumber);
            System.out.println("✅ PAN Number Entered: " + panNumber);
        } catch (Exception e) {
            System.out.println("❌ Error in addPanNumber(): " + e.getMessage());
        }
    }

    public void enterFssaiNumber() {
        try {
        	fssaiNumber = TestDataGenerator.getRandomFssaiNumber();
            fssaiNumber_InputField.clear();
            fssaiNumber_InputField.sendKeys(fssaiNumber);
            System.out.println("✅ FSSAI Number Entered: " + fssaiNumber);
        } catch (Exception e) {
            System.out.println("❌ Error in addFssaiNumber(): " + e.getMessage());
        }
    }

    public void enterFssaiExpiryDate() {
        try {
            // Create a future date (e.g., 2 years ahead)
        	fssaiExpiryDate = TestDataGenerator.getFutureDate();

            // Use JS to set value directly (bypasses clear issue)
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value=arguments[1]; arguments[0].dispatchEvent(new Event('change'));",
                fssaiExpiryDate__InputField, fssaiExpiryDate
            );

            System.out.println("✅ FSSAI Expiry Date Entered: " + fssaiExpiryDate);
        } catch (Exception e) {
            System.out.println("❌ Error in addFssaiExpiryDate(): " + e.getMessage());
            e.printStackTrace();
        }
    }
 
    
    public void fillAddress() {
    	addressStreetArea = "House No. " + (100 + random.nextInt(900)) + ", Street No. " + (1 + random.nextInt(50));
    	// Fill only address, landmark, and pincode
    	address__InputField.clear();
    	address__InputField.sendKeys(addressStreetArea);
    	System.out.println("Address Line : " + addressStreetArea);
    }
    
    public void fillLandmark() {
    	addressLandmark = TestDataGenerator.getRandomLandmark();
    	landmark_InputField.clear();
    	landmark_InputField.sendKeys(addressLandmark);
    	System.out.println("Landmark     : " + addressLandmark);
    }
    
    public void fillPincode() {
    	addressPincode = TestDataGenerator.getRandomPinCode();
    	pincode__InputField.clear();
    	pincode__InputField.sendKeys(addressPincode);
    	System.out.println("Pincode      : " + addressPincode);
    }
    
    public String getCity() {
    	addressCity =  city__InputField.getAttribute("value");
        System.out.println("Auto City  : " + city__InputField.getAttribute("value"));
        return addressCity;
    }
    
    public String getState() {
    	addressState = state__InputField.getAttribute("value");
    	System.out.println("Auto State : " + state__InputField.getAttribute("value"));
    	return addressState;
    }
    

    
    public void clickOnSaveChannelPartnerButton() {
        try {
            // Scroll to the Save button before clicking
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnSave);

            btnSave.click();
            System.out.println("🖱️ Save button clicked successfully!");
            saveDetails();
            System.out.println("✅ Data saved successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error while saving data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void saveDetails() {
        Map<String, String> channelPartnerDetails = new HashMap<>();
        channelPartnerDetails.put("MobileNumber", mobileNumber);
        channelPartnerDetails.put("EmailId", emailId);
        channelPartnerDetails.put("FirstName", firstName);
        channelPartnerDetails.put("LastName", lastName);
        channelPartnerDetails.put("FirmName", firmName);
        channelPartnerDetails.put("ChannelPartnerId", channelPartnerId);
        channelPartnerDetails.put("GSTNumber", gstNumber);
        channelPartnerDetails.put("PANNumber", panNumber);
        channelPartnerDetails.put("FSSAINumber", fssaiNumber);
        channelPartnerDetails.put("FSSAIExpiryDate", fssaiExpiryDate);
        channelPartnerDetails.put("AddressStreetArea", addressStreetArea);
        channelPartnerDetails.put("AddressLandmark", addressLandmark);
        channelPartnerDetails.put("AddressPincode", addressPincode);
        channelPartnerDetails.put("AddressCity", addressCity);
        channelPartnerDetails.put("AddressState", addressState);
        UsersUtility.writeJson("ChannelPartner", channelPartnerDetails);
        System.out.println("Channel Partner details saved successfully");
    }

    public boolean verifyChannelPartnerSuccessMessage() {
		try {
			// change tital to success message when dev done
			wait.until(ExpectedConditions.visibilityOf(channelPartnerPageTital));
//			String actualText = productCreatedSuccessMessage.getText().trim();
//			return actualText.equals("Sales Person Added Successfully");
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}
    
}