package pageObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utility.BrandUtility;

public class Brand_RatePage extends BasePage {

	private static Map<String, Integer> ChannelPartnerCount = new HashMap<>(); // make unique product
	private static String newProductRate;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	public Brand_RatePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//span[normalize-space()='Rate']")
	WebElement rate;

	@FindBy(xpath = "//h2[contains(text(),'No Products added for')]")
	private WebElement noProductsAddedMessage;

	@FindBy(xpath = "//button[@type='button']")
	private WebElement backButtonOnNoProductScreen;

	@FindBy(xpath = "//span[normalize-space()='Add Rate Structure']")
	private WebElement addRateStructureButton;

	@FindBy(xpath = "//input[@id='name']")
	WebElement inputRateStructure;

	@FindBy(xpath = "//span[normalize-space()='Duplicate Existing']")
	WebElement duplicateExisting;

	@FindBy(xpath = "//div[@class='ant-form-item-control-input-content']//div[@class='ant-select-selector']")
	WebElement radioButtonDuplicateExistingRateStructure;

	@FindBy(xpath = "//div[@class='rc-virtual-list']//div[@class='ant-select-item-option-content']")
	List<WebElement> selectDuplicateOption;

	@FindBy(xpath = "//span[normalize-space()='Create']")
	WebElement create;

	@FindBy(xpath = "//span[@class='ant-select-selection-search']")
	WebElement search;

	@FindBy(xpath = "//span[@class='ml-2']")
	WebElement addChannelPartner;

	@FindBy(xpath = "//div[@class='ant-table-filter-column']//span[@class='ant-dropdown-trigger ant-table-filter-trigger']")
	WebElement editIcons;

	@FindBy(xpath = "//div[@class='ant-table-filter-column']//span[@class='ant-dropdown-trigger ant-table-filter-trigger']")
	List<WebElement> currentEditIcons;

//	@FindBy(xpath="//span[normalize-space()='Edit']")
//	WebElement editTextOption;
	@FindBy(xpath = "//span[normalize-space()='Edit']")
	List<WebElement> editTextOptions;

	@FindBy(xpath = "//div[@class='ant-table-filter-column']//span[@class='ant-dropdown-trigger ant-table-filter-trigger']")
	List<WebElement> editChannelPartnerName;

	@FindBy(xpath = "//input[@type=\"text\"]")
	WebElement enterNewChannelName;

	@FindBy(xpath = "//input[@type=\"text\"]")
	List<WebElement> channelPartnerFields;

	@FindBy(xpath = "//span[normalize-space()='Update']")
	WebElement updateButton;

	@FindBy(xpath = "//span[normalize-space()='Cancel']")
	WebElement cancel_UpdateButton;

	@FindBy(xpath = "//div[@class='ant-table-filter-column']//span[@class='ant-table-column-title']")
	List<WebElement> cpName;

	@FindBy(xpath = "//div[@class='ant-table-body']")
	WebElement scrollContainer;
	
	@FindBy(xpath ="//table//tr[contains(@class,'ant-table-row')]")
	List<WebElement> rows;
	
	@FindBy(xpath="//div[@class=\"font-weight-semibold font-size-base text-darkgreen \"]")
	WebElement productNames;
	
	@FindBy(xpath="//div[@class=\"font-weight-semibold font-size-base text-darkgreen \"]")
	List<WebElement> productNameList;
	
	@FindBy(xpath="//input[@placeholder='Search Product']")
	WebElement searchProdcut;
	
//	@FindBy(xpath="//div[@class=\"ant-table-body\"]//div[@class=\"d-flex  flex-row align-items-center \"]")
//	WebElement editRateAndMoq;
//	
//	@FindBy(xpath="//div[@class=\"ant-form-item-control-input-content\"]//input[@type=\"number\"]")
//	WebElement inputRateAndMoq; 
	@FindBy(xpath = "//div[@class=\"d-flex  flex-row align-items-center \"]")
	List<WebElement> editIconsSize;
	
	@FindBy(xpath = "//div[@class=\"d-flex  flex-row align-items-center \"]")
	WebElement editIconProduct;
	
	@FindBy(xpath = "//input[@placeholder='Enter Rate']")
	WebElement rateInput;
	
	@FindBy(xpath="//input[@placeholder='Enter MOQ']")
	WebElement moqInput;
	
	@FindBy(xpath = "//span[normalize-space()='Update']")
	WebElement updateButtonProduct;
	public void clickOnRate() throws InterruptedException {
		Thread.sleep(2000);
		rate.click();
	}

	public boolean isNoProductsAddedMessageDisplay() {
		wait.until(ExpectedConditions.visibilityOf(noProductsAddedMessage));
		return noProductsAddedMessage.isDisplayed();
	}

	public void clickOnBackButtonWhenNoProduct() {
		wait.until(ExpectedConditions.visibilityOf(backButtonOnNoProductScreen));
		backButtonOnNoProductScreen.click();
	}

	public void clickOnAddRateStructureButton() {
		addRateStructureButton.click();
	}

	public void clickOnNewRateStructure() {
		inputRateStructure.click();
		String randomName = "RateStructure_" + UUID.randomUUID().toString().substring(0, 5);
		inputRateStructure.sendKeys(randomName);

		create.click();

	}

	public void duplicateRateStructure() {
//		wait.until(ExpectedConditions.visibilityOf(addRateStructureButton));
//		addRateStructureButton.click();
		inputRateStructure.click();
		String randomName = "RateStructure_" + UUID.randomUUID().toString().substring(0, 5);
		inputRateStructure.sendKeys(randomName);
		duplicateExisting.click();
//		radioButtonDuplicateExistingRateStructure.click();
		wait.until(ExpectedConditions.visibilityOf(radioButtonDuplicateExistingRateStructure));
		radioButtonDuplicateExistingRateStructure.click();
		Random rd = new Random();
		WebElement option = selectDuplicateOption.get(rd.nextInt(selectDuplicateOption.size()));
		String selectedoption = option.getText();
		option.click();

	}

	public void clickOnchannelPartner() {
		addChannelPartner.click();
		addChannelPartner.click();
		addChannelPartner.click();
	}

	public void getChannelPartnerName() {
		
		for (int i = 0; i < cpName.size(); i++) {
			String originalName = cpName.get(i).getText();
			System.out.println("Original Cp Name " + (i + 1) + ": " + originalName);
		}
	}


	public void clickOnEditChannelPartnerName() {
		Actions actions = new Actions(driver);
		String os = System.getProperty("os.name").toLowerCase();
		Keys selectAll = os.contains("mac") ? Keys.COMMAND : Keys.CONTROL;

		Random random = new Random();
		int totalCPs = cpName.size();

		System.out.println("Total CPs found: " + totalCPs);

		for (int i = 0; i < totalCPs; i++) {
			System.out.println("Loop Count =" + i);

			// Get fresh element each loop to avoid stale
			WebElement editIcon = currentEditIcons.get(i);

			// ✅ Horizontal scroll into view
//	       
			if (!editIcon.isDisplayed()) {
				js.executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth;", scrollContainer);
			}

			wait.until(ExpectedConditions.elementToBeClickable(editIcon)).click();
			WebElement editOption = editTextOptions.get(i);
			wait.until(ExpectedConditions.elementToBeClickable(editOption)).click();

			wait.until(ExpectedConditions.visibilityOf(enterNewChannelName)).click();

			// Clear field
			enterNewChannelName.sendKeys(selectAll + "a", Keys.DELETE);

			// Enter new CP name
			String newCPName = "CP_" + (1000 + random.nextInt(9000));
			enterNewChannelName.sendKeys(newCPName);
			System.out.println("Updated CP " + (i + 1) + " to: " + newCPName);

			// Save changes (not cancel)
			wait.until(ExpectedConditions.elementToBeClickable(updateButton)).click();

			// Wait for modal to close
//	        wait.until(ExpectedConditions.invisibilityOf(enterNewChannelName));
		}

		System.out.println("All CPs updated successfully!");
	}

	public String[] getAllProductName() {
	    List<String> productNames = new ArrayList<>();

	    // Collect only non-empty product names
	    for (WebElement product : productNameList) {
	        String name = product.getText().trim();
	        if (!name.isEmpty()) {
	            System.out.println("Product Name " + (productNames.size() + 1) + ": " + name);
	            productNames.add(name);
	        }
	    }

	    // If no products found, hide or skip rate feature
	    if (productNames.isEmpty()) {
	        System.out.println("No products found — rate feature hidden.");
	        // Example: disable or skip rate-related logic here
	        // rateSectionElement.setDisplayed(false); // (depends on your actual implementation)
	        return new String[0];
	    }

	    System.out.println("Total Products Found: " + productNames.size());
	    return productNames.toArray(new String[0]);
	}
	public String getProductName() {
		String productName = BrandUtility.readJson("Product", "ProductName");
		return productName;
	}
	public void searchProduct() {
		
		searchProdcut.click();
		js.executeScript("arguments[0].scrollRight = arguments[0].scrollWidth;", scrollContainer);

		waitForElementVisible(searchProdcut, 10);
		searchProdcut.clear();
		searchProdcut.sendKeys(getProductName());
		
		
	}
	

	

	public void editAllCpRatesAndMoq(double mrp) {
		
	    int totalIcons = editIconsSize.size();
	    System.out.println("Total Edit Icons Found: " + totalIcons);

	    if (totalIcons == 0) {
	        System.out.println("❌ No edit icons found on page.");
	        return;
	    }
	    Random random = new Random();
	    // --- Generate increasing rates under MRP ---
	    double cp1Rate = Math.round((mrp * 0.65 + random.nextDouble() * (mrp * 0.05)) * 100.0) / 100.0; // 65–70%
	    double cp2Rate = Math.round((cp1Rate * 1.10) * 100.0) / 100.0; // +10%
	    double cp3Rate = Math.round((cp2Rate * 1.10) * 100.0) / 100.0; // +10%
	    double retailerRate = Math.round((cp3Rate * 1.10) * 100.0) / 100.0; // +10%

	    if (retailerRate >= mrp) retailerRate = mrp - 1;
	    int moq = random.nextInt(5) + 1; // 1–5, same for all

	    System.out.println("Generated Rates & MOQ:");
	    System.out.println("CP1: " + cp1Rate + " | CP2: " + cp2Rate + " | CP3: " + cp3Rate + " | Retailer: " + retailerRate);
	    System.out.println("MOQ: " + moq);

	    // Store all rates in order (for however many icons exist)
	    double[] rates = {cp1Rate, cp2Rate, cp3Rate, retailerRate};

	    // --- Loop through all edit icons ---
	    for (int i = 0; i < totalIcons; i++) {
	        WebElement icon = editIconsSize.get(i);

	        js.executeScript("arguments[0].scrollIntoView(true);", icon);
	        wait.until(ExpectedConditions.elementToBeClickable(icon)).click();
	        System.out.println("Clicked Edit Icon " + (i + 1));

	        // Wait for rate input field
	        wait.until(ExpectedConditions.visibilityOf(rateInput));

	        // --- Clear existing Rate if present ---
	        try {
	            String oldRate = rateInput.getAttribute("value");
	            if (oldRate != null && !oldRate.isEmpty()) {
	                rateInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
	                System.out.println("Cleared old rate value: " + oldRate);
	            }
	        } catch (Exception ignored) {}

	        // --- Enter new Rate ---
	        double rateToEnter = rates[Math.min(i, rates.length - 1)];
	        rateInput.sendKeys(String.valueOf(rateToEnter));

	        // --- Clear existing MOQ if present ---
	        wait.until(ExpectedConditions.visibilityOf(moqInput));
	        try {
	            String oldMoq = moqInput.getAttribute("value");
	            if (oldMoq != null && !oldMoq.isEmpty()) {
	                moqInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
	                System.out.println("Cleared old MOQ value: " + oldMoq);
	            }
	        } catch (Exception ignored) {}

	        // --- Enter new MOQ ---
	        moqInput.sendKeys(String.valueOf(moq));

	        // --- Click Update ---
	        wait.until(ExpectedConditions.elementToBeClickable(updateButtonProduct)).click();

	        // Wait until Update modal closes
	        wait.until(ExpectedConditions.invisibilityOf(updateButtonProduct));

	        System.out.println("✅ Updated CP " + (i + 1) + " | Rate: " + rateToEnter + " | MOQ: " + moq);
	    }

	    // --- Validation ---
	    if (cp1Rate < cp2Rate && cp2Rate < cp3Rate && cp3Rate < retailerRate) {
	        System.out.println("✅ Validation Passed: CP1 < CP2 < CP3 < Retailer");
	    } else {
	        System.out.println("❌ Validation Failed: Rate order incorrect");
	    }
	}


}
