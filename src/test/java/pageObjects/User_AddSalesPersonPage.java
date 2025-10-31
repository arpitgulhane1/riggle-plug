package pageObjects;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class User_AddSalesPersonPage extends BasePage {
	public User_AddSalesPersonPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
//	private static String selectedSalesPersonName; 		
//	  @FindBy(xpath="//div[contains(@class, 'ant-col') and contains(@class, 'ant-col-md-6')]//strong[normalize-space()='Sales Persons']")
//	  WebElement salesPersonsMenu;
//	  
//	  @FindBy(xpath ="//span[normalize-space()='Add Sales Person']")
//	  WebElement addSalesPerson;
	  
	  @FindBy(xpath ="//input[@id='nest-messages_first_name']")
	  WebElement enterFirstName;
	  
	  @FindBy(xpath ="//input[@id='nest-messages_last_name']")
	  WebElement enterLastName;
	  
	  @FindBy(xpath ="//input[@id='nest-messages_email']")
	  WebElement enterEmail;
	  
	  @FindBy(xpath="//input[@id='nest-messages_mobile']")
	  WebElement enterMobileNumber;
	  
	  @FindBy(xpath="//input[@id='nest-messages_blood_group']")
	  WebElement selectBloodGroup;
	  
	  @FindBy(xpath="//div[@class=\"rc-virtual-list-holder-inner\"]//div[@class=\"ant-select-item ant-select-item-option\"]")
	  List<WebElement> selectBloodGroupAllOption;
	  
	  @FindBy(xpath="//input[@id='nest-messages_dob']")
	  WebElement enterDateOfBirth;
	  
	  @FindBy(xpath="//input[@id='nest-messages_sales_designation']")
	  WebElement selectDesignation;
	  
	  @FindBy(xpath="//div[@id='nest-messages_sales_designation_list']//following::div[@class='rc-virtual-list']//div[@aria-selected='false']")
	  List<WebElement> selectDesignationAllOption;
	  
	  @FindBy(xpath = "//input[@id='nest-messages_manager']")
	  WebElement enterReportingManager;
	  
	  @FindBy(xpath ="//div[@id='nest-messages_manager_list']//following::div[@class='rc-virtual-list']//div[@aria-selected='false']")
	  List<WebElement> selectReportingManagerAllOption;
	  
	  @FindBy(xpath="//input[@id='nest-messages_hq_city']")
	  WebElement HeadquarterCity;
	  
	  @FindBy(xpath ="//input[@id=\"nest-messages_cities\"]")
	  WebElement enterAssignCities;	
	  
	  @FindBy(xpath = "//div[@id='nest-messages_cities_list']//following::div[@class='rc-virtual-list']//div[@aria-selected='false']")
	  List<WebElement> selectAssignCitiesAllOption;
	  
	  @FindBy(xpath = "//div[@class='ant-modal-root']//div[4]")
	  WebElement assignCities;
	  
	  @FindBy(xpath ="//input[@id='nest-messages_date_of_joining']")
	  WebElement enterDateOfJoining;
	  
	  @FindBy(xpath ="//input[@id='nest-messages_employee_id']")
	  WebElement enterSalesPersonId;
	  
	  @FindBy(xpath ="//input[@id='nest-messages_home_location']")
	  WebElement enterHomeLocation;
	  
	  @FindBy(xpath="//button[@id='nest-messages_van_sales']")
	  WebElement btnVanSales;
	  
	  @FindBy(xpath = "//button[@id='nest-messages_hide_cp']")
	  WebElement btnHideCPInSalesApp;
	  
	  @FindBy(xpath = "//button[@id='nest-messages_can_mark_deliver']")
	  WebElement btnMarkDeliver;
	  
	  @FindBy(xpath = "//span[normalize-space()='Save']")
	  WebElement btnSave;
	  
	  
//	  public void salePersonMenu() {
//		  salesPersonsMenu.click();
//	  }
//	  public void addSalesPerson() {
//		  addSalesPerson.click();
//	  }
		    
	  private Random random = new Random();

	    // 🔹 Helper methods for random data
	    private String generateRandomAlphabeticName(int length) {
	        String alphabet = "abcdefghijklmnopqrstuvwxyz";
	        StringBuilder name = new StringBuilder();
	        for (int i = 0; i < length; i++) {
	            name.append(alphabet.charAt(random.nextInt(alphabet.length())));
	        }
	        name.setCharAt(0, Character.toUpperCase(name.charAt(0)));
	        return name.toString();
	    }

	    private String generateRandomEmail(String firstName, String lastName) {
	        return firstName.toLowerCase() + "." + lastName.toLowerCase() + "@gmail.com";
	    }

	    private String generateRandomMobileNumber() {
	        StringBuilder mobile = new StringBuilder("9");
	        for (int i = 0; i < 9; i++) {
	            mobile.append(random.nextInt(10));
	        }
	        return mobile.toString();
	    }
	    
	    // 🔹 Individual field methods (each works independently)
	    public String addFirstName() {
	        String firstName = generateRandomAlphabeticName(5 + random.nextInt(4));
	        enterFirstName.clear();
	        enterFirstName.sendKeys(firstName);
	        System.out.println("✅ First Name Entered: " + firstName);
	        return firstName;
	    }

	    public String addLastName() {
	        String lastName = generateRandomAlphabeticName(5 + random.nextInt(4));
	        enterLastName.clear();
	        enterLastName.sendKeys(lastName);
	        System.out.println("✅ Last Name Entered: " + lastName);
	        return lastName;
	    }

	    public void addEmail() {
	        String firstName = generateRandomAlphabeticName(5 + random.nextInt(4));
	        String lastName = generateRandomAlphabeticName(5 + random.nextInt(4));
	        String email = generateRandomEmail(firstName, lastName);
	        enterEmail.clear();
	        enterEmail.sendKeys(email);
	        System.out.println("✅ Email Entered: " + email);
	    }

	    public void addMobileNumber() {
	        String mobile = generateRandomMobileNumber();
	        enterMobileNumber.clear();
	        enterMobileNumber.sendKeys(mobile);
	        System.out.println("✅ Mobile Number Entered: " + mobile);
	    }
	    
	    public void selectBloodGroup() {
	        try {
//	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	            // Step 1: Click dropdown to open
	            wait.until(ExpectedConditions.elementToBeClickable(selectBloodGroup));
	            selectBloodGroup.click();

	            // Step 2: Wait for dropdown options to be visible
	            wait.until(ExpectedConditions.visibilityOfAllElements(selectBloodGroupAllOption));

	            if (selectBloodGroupAllOption.isEmpty()) {
	                throw new RuntimeException("❌ No dropdown options found!");
	            }

	            // Step 3: Select random option
	            int randomIndex = new Random().nextInt(selectBloodGroupAllOption.size());
	            WebElement randomOption = selectBloodGroupAllOption.get(randomIndex);
	            String selectedText = randomOption.getText().trim();

	            // Step 4: Scroll to element and click
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", randomOption);
	            wait.until(ExpectedConditions.elementToBeClickable(randomOption));
	            randomOption.click();

	            // Step 5: Print selected option
	            System.out.println("✅ Blood Group Selected: " + selectedText);

	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("❌ Failed to select random blood group: " + e.getMessage());
	        }
	    }

	
	    public void addDateOfBirth() {
	        try {
	            // Step 1: Define age range (minimum 18 years old)
	            int minAge = 18;
	            int maxAge = 60; // optional upper limit

	            // Step 2: Generate random DOB between (today - maxAge) and (today - minAge)
	            LocalDate today = LocalDate.now();
	            LocalDate startDate = today.minusYears(maxAge);
	            LocalDate endDate = today.minusYears(minAge);

	            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
	            long randomDays = new Random().nextInt((int) daysBetween + 1);

	            LocalDate randomDOB = startDate.plusDays(randomDays);

	            // Step 3: Format in dd-MM-yy
	            String formattedDate = randomDOB.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

	            // Step 4: Enter into field
	            enterDateOfBirth.click();
	            enterDateOfBirth.clear();
	            enterDateOfBirth.sendKeys(formattedDate);
	            enterDateOfBirth.sendKeys(Keys.ENTER);  // 👈 Press Enter key
	            System.out.println("✅ Random DOB (Age ≥ 18): " + formattedDate);

	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("❌ Failed to enter Date of Birth: " + e.getMessage());
	        }

	    }
	    public void selectDesignation() {
	        try {
//	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	            // Step 1: Click dropdown to open
	            wait.until(ExpectedConditions.elementToBeClickable(selectDesignation));
	            selectDesignation.click();

	            // Step 2: Wait until all dropdown options are visible
	            wait.until(ExpectedConditions.visibilityOfAllElements(selectDesignationAllOption));
//	            Thread.sleep(2000);
	            if (selectDesignationAllOption.isEmpty()) {
	                throw new RuntimeException("❌ No designation options found!");
	            }
	            // Step 3: Pick random option
	            int randomIndex = new Random().nextInt(selectDesignationAllOption.size());
	            WebElement randomOption = selectDesignationAllOption.get(randomIndex);
	            String selectedText = randomOption.getText().trim();

	            // Step 4: Wait until option clickable and click
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", randomOption);
	            wait.until(ExpectedConditions.elementToBeClickable(randomOption));
	            randomOption.click();

	            System.out.println("✅ Random Designation Selected: " + selectedText);

	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("❌ Failed to select random designation: " + e.getMessage());
	        }
	    }
	    public void selectReportingManager() {
	        try {
	            // Step 1: Open dropdown
	            enterReportingManager.click();
	            System.out.println("🟢 Opened 'Reporting Manager' dropdown...");

	            // Step 2: Fluent wait until all options load
	            Wait<WebDriver> wait = new FluentWait<>(driver)
	                    .withTimeout(Duration.ofSeconds(15))
	                    .pollingEvery(Duration.ofMillis(500))
	                    .ignoring(NoSuchElementException.class)
	                    .ignoring(StaleElementReferenceException.class);

	            List<WebElement> allOptions = wait.until(driver ->
	                    driver.findElements(By.xpath("//div[@id='nest-messages_manager_list']//following::div[@class='rc-virtual-list']//div[@aria-selected='false']"))
	            );

	            if (allOptions.isEmpty()) {
	                System.out.println("❌ No Reporting Manager options found!");
	                return;
	            }

	            // Step 3: Print all Reporting Managers
	            System.out.println("👔 Available Reporting Managers:");
	            for (int i = 0; i < allOptions.size(); i++) {
	                String managerName = allOptions.get(i).getText().trim();
	                if (managerName.isEmpty()) {
	                    try {
	                        managerName = allOptions.get(i).findElement(By.xpath(".//span | .//div")).getText().trim();
	                    } catch (NoSuchElementException ex) {
	                        managerName = "(No visible text)";
	                    }
	                }
	                System.out.println((i + 1) + ". " + managerName);
	            }

	            // Step 4: Select a random manager
	            int randomIndex = new Random().nextInt(allOptions.size());
	            WebElement randomOption = allOptions.get(randomIndex);

	            // Step 5: Extract name safely
	            String selectedText = randomOption.getText().trim();
	            if (selectedText.isEmpty()) {
	                try {
	                    selectedText = randomOption.findElement(By.xpath(".//span | .//div")).getText().trim();
	                } catch (NoSuchElementException ex) {
	                    selectedText = "(No visible text found)";
	                }
	            }

	            // Step 6: Scroll and click
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", randomOption);
//	            Thread.sleep(300);
	            randomOption.click();

	            // Step 7: Wait until dropdown closes
	            wait.until(ExpectedConditions.invisibilityOf(randomOption));
	            System.out.println("✅ Random Reporting Manager Selected: " + selectedText);
	            System.out.println("➡️ Dropdown closed successfully.");
	           
	           
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("❌ Failed to select random Reporting Manager: " + e.getMessage());
	        }
	    }
	    
	    public void addHeadquarterCity(){
	    	try  {
//	    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	
	    	        wait.until(ExpectedConditions.elementToBeClickable(HeadquarterCity));

	    	        // Step 1: Click the field
	    	        HeadquarterCity.click();

	    	        // Step 2: Generate random alphabetic city name (like "Delhi", "Pune", etc.)
	    	        String[] cities = {"Mumbai", "Delhi", "Pune", "Chennai", "Kolkata", "Jaipur", "Hyderabad", "Lucknow", "Indore", "Bangalore"};
	    	        int randomIndex = new Random().nextInt(cities.length);
	    	        String randomCity = cities[randomIndex];

	    	        // Step 3: Clear existing and enter random city
	    	        HeadquarterCity.clear();
	    	        HeadquarterCity.sendKeys(randomCity);
	    	        HeadquarterCity.sendKeys(Keys.ENTER); // confirm entry if needed

	    	        System.out.println("✅ Random Headquarter City Entered: " + randomCity);

	    	    } catch (Exception e) {
	    	        e.printStackTrace();
	    	        throw new RuntimeException("❌ Failed to enter random Headquarter City: " + e.getMessage());
	    	    }

	    }
	    public void addAssignCities() {
	        try {
	            // Step 1: Open dropdown
	            enterAssignCities.click();

	            // Step 2: Wait for options to appear
	            Wait<WebDriver> wait = new FluentWait<>(driver)
	                    .withTimeout(Duration.ofSeconds(15))
	                    .pollingEvery(Duration.ofMillis(500))
	                    .ignoring(NoSuchElementException.class)
	                    .ignoring(StaleElementReferenceException.class);

	            List<WebElement> allOptions = wait.until(driver ->
	                    driver.findElements(By.xpath("//div[@id='nest-messages_cities_list']//following::div[@class='rc-virtual-list']//div[@aria-selected='false']"))
	            );

	            // Step 3: Verify options
	            if (selectAssignCitiesAllOption.isEmpty()) {
	                System.out.println("❌ No assign city options found!");
	                return;
	            }

	            // Step 4: Print all city names
	            System.out.println("🌆 Available Cities:");
	            for (int i = 0; i < selectAssignCitiesAllOption.size(); i++) {
	                String cityName = selectAssignCitiesAllOption.get(i).getText().trim();
	                System.out.println((i + 1) + ". " + cityName);
	            }

	            // Step 5: Pick one random city
	            int randomIndex = new Random().nextInt(selectAssignCitiesAllOption.size());
	            WebElement randomCity = selectAssignCitiesAllOption.get(randomIndex);

	            // Step 6: Get visible city name safely
	            String selectedCity = randomCity.getText().trim();
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", randomCity);
	            randomCity.click();

	            System.out.println("✅ City Selected: " + selectedCity);

	            // Step 9: Click outside to close dropdown (your element)
	            assignCities.click();

	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("❌ Failed to print/select assign cities: " + e.getMessage());
	        }
	    }


	    public void addDateOfJoin() {
	    	try {
//	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.elementToBeClickable(enterDateOfJoining));

	            // Step 1: Click the field
	            enterDateOfJoining.click();

	            // Step 2: Get today's date in dd-MM-yyyy format
	            String currentDate = java.time.LocalDate.now()
	                    .format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));

	            // Step 3: Clear and enter the date
	            enterDateOfJoining.clear();
	            enterDateOfJoining.sendKeys(currentDate);
	            enterDateOfJoining.sendKeys(Keys.ENTER); // confirm the date if needed

	            System.out.println("✅ Date of Joining Entered (Today): " + currentDate);

	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("❌ Failed to enter Date of Joining: " + e.getMessage());
	        }
	    }
	    
	    public void addSalesPersonId() {
	        try {
//	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.elementToBeClickable(enterSalesPersonId));

	            // Step 1: Generate a random Sales Person ID (e.g., SP1234)
	            String prefix = "SP";
	            int randomNumber = 1000 + new Random().nextInt(9000); // 1000–9999
	            String salesPersonId = prefix + randomNumber;

	            // Step 2: Enter the ID
	            enterSalesPersonId.click();
	            enterSalesPersonId.clear();
	            enterSalesPersonId.sendKeys(salesPersonId);
	            enterSalesPersonId.sendKeys(Keys.TAB); // move out of field if needed

	            System.out.println("✅ Sales Person ID Entered: " + salesPersonId);

	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("❌ Failed to enter Sales Person ID: " + e.getMessage());
	        }
	    }
	    
	    public void addHomeLocation() {
	        try {
//	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.elementToBeClickable(enterHomeLocation));

	            // Step 1: Click input
	            enterHomeLocation.click();

	            // Step 2: Randomly select a location from a predefined list
	            String[] locations = {
	                "Andheri", "Baner", "Koramangala", "Salt Lake", 
	                "Banjara Hills", "Powai", "Vashi", "Indiranagar", 
	                "Rajouri Garden", "Gachibowli", "Whitefield", "Hadapsar"
	            };

	            int randomIndex = new Random().nextInt(locations.length);
	            String randomLocation = locations[randomIndex];

	            // Step 3: Clear existing and enter random location
	            enterHomeLocation.clear();
	            enterHomeLocation.sendKeys(randomLocation);
	            enterHomeLocation.sendKeys(Keys.TAB); // confirm if auto-suggest exists

	            System.out.println("✅ Random Home Location Entered: " + randomLocation);

	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("❌ Failed to enter Home Location: " + e.getMessage());
	        }
	    }
	    public void clickVanSales() {
	        btnVanSales.click();
	        System.out.println("✅ 'Van Sales' button clicked successfully!");
	    
	    }
	    public void clickHideCPInSalesApp() {
	        btnHideCPInSalesApp.click();
	        System.out.println("✅ 'Hide CP' button clicked successfully!");
	    }

	    public void clickMarkDeliver() {
	        btnMarkDeliver.click();
	        System.out.println("✅ 'Mark Deliver' button clicked successfully!");
	    }
	    public void clickSave() {
	        btnSave.click();
	        System.out.println("✅ 'Save' button clicked successfully!");
	    }
	    
	    
}


