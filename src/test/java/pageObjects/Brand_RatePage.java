package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

	@FindBy(xpath = "//table//tr[contains(@class,'ant-table-row')]")
	List<WebElement> rows;

	@FindBy(xpath = "//div[@class=\"font-weight-semibold font-size-base text-darkgreen \"]")
	WebElement productNames;

	@FindBy(xpath = "//div[@class=\"font-weight-semibold font-size-base text-darkgreen \"]")
	List<WebElement> productNameList;

	@FindBy(xpath = "//input[@placeholder='Search Product']")
	WebElement searchProdcut;

//	@FindBy(xpath="//div[@class=\"ant-table-body\"]//div[@class=\"d-flex  flex-row align-items-center \"]")
//	WebElement editRateAndMoq;
//	
//	@FindBy(xpath="//div[@class=\"ant-form-item-control-input-content\"]//input[@type=\"number\"]")
//	WebElement inputRateAndMoq; 
	@FindBy(xpath = "//div[contains(@class,'d-flex  flex-row align-items-center')]")
	List<WebElement> editIconsSize;

	@FindBy(xpath = "//div[@class=\"d-flex  flex-row align-items-center \"]")
	WebElement editIconProduct;

	@FindBy(xpath = "//input[@placeholder='Enter Rate']")
	WebElement rateInput;

	@FindBy(xpath = "//input[@placeholder='Enter MOQ']")
	WebElement moqInput;

	@FindBy(xpath = "//span[normalize-space()='Update']")
	WebElement updateButtonProduct;

	// By Arpit
	// 1) Rate Structure Columns (SS, DB, Retailer, CP)
	@FindBy(xpath = "//thead[@class='ant-table-thead']//th[@class='ant-table-cell text-primary']")
	private List<WebElement> rateStructureColumns;

	// 2) Product Rows
	@FindBy(xpath = "//tr[contains(@class,'ant-table-row')]")
	private List<WebElement> productRows;

	// 3) MRP column (3rd column → span[2] = numeric value)
	@FindBy(xpath = "//tr[contains(@class,'ant-table-row')]/td[3]//span[2]")
	private List<WebElement> mrpValues;

	@FindBy(xpath = "//ul[contains(@class,'ant-pagination')]//li[@title='Next Page' and @aria-disabled='false']")
	private WebElement nextTablePageButton;

	// 4) Dynamic CP / Retailer Rate Columns
	private String dynamicRateColumnXpath = "//tr[contains(@class,'ant-table-row')]/td[%d]//span[2]";
	
	// 5) Dynamic CP / Retailer Rate Columns
	@FindBy(xpath = "//td[@class='ant-table-cell']//span[2]")
	private List<WebElement> rateAllValues;

	// By arpit end

	@FindBy(xpath = "//div[@aria-label='Page Size']//div[@class='ant-select-selector']")
	private WebElement pageSizeSelectorDropdown;
	
	@FindBy(xpath = "//div[contains(@title,'50 / page')]")
	private WebElement pageSize50;
	
	@FindBy(xpath = "//div[contains(@title,'30 / page')]")
	private WebElement pageSize30;
	
	@FindBy(xpath = "//div[contains(@title,'15 / page')]")
	private WebElement pageSize15;
	
	
	public void selectPageNumberOfProductSize(String number) {

	    wait.until(ExpectedConditions.visibilityOf(pageSizeSelectorDropdown));
	    pageSizeSelectorDropdown.click();

	    Map<String, WebElement> sizeMap = new HashMap<>();
	    sizeMap.put("15", pageSize15);
	    sizeMap.put("30", pageSize30);
	    sizeMap.put("50", pageSize50);

	    WebElement targetSize = sizeMap.get(number);

	    if (targetSize == null) {
	        throw new IllegalArgumentException("❌ Invalid page size: " + number);
	    }

	    wait.until(ExpectedConditions.visibilityOf(targetSize));
	    targetSize.click();

	    System.out.println("✅ Page size selected: " + number);
	}

	
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
			// rateSectionElement.setDisplayed(false); // (depends on your actual
			// implementation)
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

		if (retailerRate >= mrp)
			retailerRate = mrp - 1;
		int moq = random.nextInt(5) + 1; // 1–5, same for all

		System.out.println("Generated Rates & MOQ:");
		System.out.println(
				"CP1: " + cp1Rate + " | CP2: " + cp2Rate + " | CP3: " + cp3Rate + " | Retailer: " + retailerRate);
		System.out.println("MOQ: " + moq);

		// Store all rates in order (for however many icons exist)
		double[] rates = { cp1Rate, cp2Rate, cp3Rate, retailerRate };

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
			} catch (Exception ignored) {
			}

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
			} catch (Exception ignored) {
			}

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

//	-------------------------------------------------------------------------------

	// ============================================

	// ----------------------------------------------------------
	// FUNCTIONS – CLEAN PAGE OBJECT MODEL
	// ----------------------------------------------------------

	/** Get Rate Structure Column Count */
	public int getRateStructureColumnCount() {
		wait.until(ExpectedConditions.visibilityOfAllElements(rateStructureColumns));
		return rateStructureColumns.size();
	}

	
	/** Get number of products */
	public int getProductCount() {
		wait.until(ExpectedConditions.visibilityOfAllElements(productRows));
		return productRows.size();
	}

	/** Get all MRP values */
	public List<String> getAllMRPs() {
		wait.until(ExpectedConditions.visibilityOfAllElements(mrpValues));
		return mrpValues.stream().map(e -> e.getText().trim()).collect(Collectors.toList());
	}

	/** Get rate (CP1, CP2, CP3, Retailer) by td index */
	public List<String> getRateColumnValues(int tdIndex) {
		String finalXpath = String.format(dynamicRateColumnXpath, tdIndex);
		List<WebElement> list = driver.findElements(By.xpath(finalXpath));
		
		return list.stream().map(e -> e.getText().trim()).collect(Collectors.toList());
	}
	
	  // all rate cells in a row
    public List<WebElement> getRateCells(WebElement row) {
        return row.findElements(By.xpath(".//td[@class='ant-table-cell']//span[2]"));
    }

    public String getRateValue(WebElement cell) {
        return cell.getText().replaceAll("[^0-9.]", "").trim();
    }

    public boolean isZero(WebElement cell) {
        String value = getRateValue(cell);
        return value.equals("") || value.equals("0") || value.equals("0.0");
    }
    
    // by  arpit

	public void updateAllRateStructures1() {

		int rateColumns = getRateStructureColumnCount();
		int productCount = getProductCount();

		System.out.println("Total Rate Structure Columns: " + rateColumns);
		System.out.println("Total Products Found: " + productCount);

		List<String> mrpList = getAllMRPs();

		if (productCount == 0) {
			System.out.println("❌ No products found.");
			return;
		}

		Random random = new Random();

		for (int p = 0; p < productCount; p++) {

			double mrp = Double.parseDouble(mrpList.get(p));
			System.out.println("\n=== Product " + (p + 1) + " | MRP: " + mrp + " ===");

			// ---- Generate dynamic CP rates below MRP ----
			double cp1 = round(mrp * 0.65 + random.nextDouble() * (mrp * 0.05));
			double cp2 = round(cp1 * 1.10);
			double cp3 = round(cp2 * 1.10);
			double retailer = round(cp3 * 1.10);

			if (retailer >= mrp)
				retailer = mrp - 1;

			// ---- Print based on number of columns ----
			switch (rateColumns) {
			case 4:
				System.out.println("CP1: " + cp1 + " | CP2: " + cp2 + " | CP3: " + cp3 + " | Retailer: " + retailer);
				break;

			case 3:
				System.out.println("CP1: " + cp1 + " | CP2: " + cp2 + " | Retailer: " + retailer);
				break;

			case 2:
				System.out.println("CP1: " + cp1 + " | Retailer: " + retailer);
				break;

			case 1:
				System.out.println("Retailer: " + retailer);
				break;
			}

			// Prepare array for input
			double[] values;
			if (rateColumns == 4)
				values = new double[] { cp1, cp2, cp3, retailer };
			else if (rateColumns == 3)
				values = new double[] { cp1, cp2, retailer };
			else if (rateColumns == 2)
				values = new double[] { cp1, retailer };
			else
				values = new double[] { retailer };

			// ---- Update each rate: click edit modal one-by-one ----
			for (int i = 0; i < rateColumns; i++) {

				WebElement icon = editIconsSize.get(i);
//                js.executeScript("arguments[0].scrollIntoView(true);", icon);
				js.executeScript("arguments[0].scrollIntoView({behavior:'auto', block:'center', inline:'center'});",
						icon);

				wait.until(ExpectedConditions.elementToBeClickable(icon)).click();
				wait.until(ExpectedConditions.visibilityOf(rateInput));

				// Clear rate
				rateInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
				rateInput.sendKeys(String.valueOf(values[i]));

				// Clear MOQ
				moqInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
				moqInput.sendKeys("1");

				wait.until(ExpectedConditions.elementToBeClickable(updateButtonProduct)).click();
				wait.until(ExpectedConditions.invisibilityOf(updateButtonProduct));

				System.out.println("Updated CP" + (i + 1) + " → " + values[i]);
			}

			System.out.println("✔ Completed Product " + (p + 1));
		}
	}

	private double round(double val) {
		return Math.round(val * 100.0) / 100.0;
	}

	
	public void updateAllRateStructuresRowWise()  {
		js.executeScript("document.body.style.zoom = '70%'");
		
//		selectPageNumberOfProductSize("50");
		
		int currentPage = 1;

		   System.out.println("Starting at Page: " + currentPage);
		   
		boolean hasNext = true;

	    while (hasNext) {
	    	System.out.println("Current at Page Started : " + currentPage);
			int rateColumns = getRateStructureColumnCount();
			int productCount = getProductCount();
			List<String> mrpList = getAllMRPs();

			System.out.println("Rate Columns: " + rateColumns + " | Products: " + productCount);

			Random random = new Random();

			for (int p = 0; p < productCount; p++) {

				// ---------------- MRP Calculation ----------------
				String mrpText = mrpList.get(p).replaceAll("[^0-9.]", "");
				double mrp = Double.parseDouble(mrpText);

				System.out.println("\n--- Product " + (p + 1) + " | MRP: " + mrp + " ---");

				// ---------------- Rate Auto-Generation ----------------
				double cp1 = round(mrp * 0.65 + random.nextDouble() * (mrp * 0.05));
				double cp2 = round(cp1 * 1.10);
				double cp3 = round(cp2 * 1.10);
				double retailer = round(cp3 * 1.10);

				if (retailer >= mrp)
					retailer = mrp - 1;

				double[] values;
				switch (rateColumns) {
				case 4:
					values = new double[] { cp1, cp2, cp3, retailer };
					break;
				case 3:
					values = new double[] { cp1, cp2, retailer };
					break;
				case 2:
					values = new double[] { cp1, retailer };
					break;
				default:
					values = new double[] { retailer };
					break;
				}

				System.out.println("Will write: " + Arrays.toString(values));

				// =====================================================
				// ROW-WISE UPDATE
				// =====================================================

//				Random random = new Random();
				String moqRandom = String.valueOf(1 + random.nextInt(15)); 
				
				int[] possibleMoq = {1, 5, 10, 15, 20};
				String moqPossileSet = String.valueOf(possibleMoq[random.nextInt(possibleMoq.length)]);
				
				
				for (int col = 0; col < values.length; col++) {

					System.out.println("Wait for product - Colunm ="+col +" , Values ="+values.length);
					// Correct row
					String rowXpath = "(//tr[contains(@class,'ant-table-row')])[" + (p + 1) + "]";
					WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(rowXpath)));

					// Find icons inside this product row only
					List<WebElement> rowIcons = row
//							.findElements(By.xpath(".//div[contains(@class,'d-flex  flex-row align-items-center')]"));
							.findElements(By.xpath(".//div[contains(@class,'d-flex  flex-row align-items-center')]/button[@type='button']"));

					if (rowIcons.size() == 0) {
						System.out.println("⚠ No edit icons found in row " + (p + 1));
						continue;
					}

					if (col >= rowIcons.size()) {
						System.out.println("⚠ Not enough edit icons in row " + (p + 1));
						continue;
					}

					WebElement icon = rowIcons.get(col);

					System.out.println("Wait for Edit Icon ...");
					js.executeScript("arguments[0].scrollIntoView(true);", icon);
					wait.until(ExpectedConditions.elementToBeClickable(icon)).click();
//					wait.until(ExpectedConditions.elementToBeClickable(icon));
//					js.executeScript("arguments[0].click();", icon);
					
					

					System.out.println("Clicked Edit for Product " + (p + 1) + " - Column " + (col + 1));

					System.out.println("Wait for Rate InputBox");
					// ---------------- RATE INPUT ----------------
					wait.until(ExpectedConditions.visibilityOf(rateInput));
//					Thread.sleep(4000);					
					rateInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
					rateInput.sendKeys(String.valueOf(values[col]));

					System.out.println("Wait for MOQ InputBox");
					// ---------------- MOQ INPUT ----------------
					wait.until(ExpectedConditions.visibilityOf(moqInput));
					moqInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
					moqInput.sendKeys(moqPossileSet);

					// ---------------- SAVE ----------------
					System.out.println("Wait for Update Button Product");
					wait.until(ExpectedConditions.elementToBeClickable(updateButtonProduct)).click();

					System.out.println("Wait for invisiblity Update Button Product");
//                wait.until(ExpectedConditions.invisibilityOf(updateButtonProduct));
					wait.until(ExpectedConditions.visibilityOf(icon));

					System.out.println("✔ Updated rate: " + values[col]);
				}

				System.out.println("✔ Product " + (p + 1) + " completed.");

				js.executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth;", scrollContainer);

			}
			System.out.println("Current at Page End : " + currentPage);
			currentPage++;
			hasNext = clickNextPageIfAvailable();
		}
	    System.out.println("All pages completed.");
	}

	public boolean clickNextPageIfAvailable() {
	    try {
	        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(4));
	        shortWait.until(ExpectedConditions.visibilityOf(nextTablePageButton));

	        if (nextTablePageButton.isDisplayed()) {
	            nextTablePageButton.click();
	            Thread.sleep(500);  // allow table to refresh
	            return true; // Continue loop
	        }
	    } catch (Exception e) {
	        // Element not found OR not visible → last page
	    }

	    return false; // Stop loop
	}

	
	
	public void updateAllRateStructuresRowWise_IfRateIsZero()  {
		js.executeScript("document.body.style.zoom = '70%'");
//		selectPageNumberOfProductSize("50");
		int currentPage = 1;

		   System.out.println("Starting at Page: " + currentPage);
		   
		boolean hasNext = true;

	    while (hasNext) {
	    	System.out.println("Current at Page Started : " + currentPage);
			int rateColumns = getRateStructureColumnCount();
			int productCount = getProductCount();
			List<String> mrpList = getAllMRPs();

			System.out.println("Rate Columns: " + rateColumns + " | Products: " + productCount);

			Random random = new Random();

			for (int p = 0; p < productCount; p++) {

				// ---------------- MRP Calculation ----------------
				String mrpText = mrpList.get(p).replaceAll("[^0-9.]", "");
				double mrp = Double.parseDouble(mrpText);

				System.out.println("\n--- Product " + (p + 1) + " | MRP: " + mrp + " ---");

				// ---------------- Rate Auto-Generation ----------------
				double cp1 = round(mrp * 0.65 + random.nextDouble() * (mrp * 0.05));
				double cp2 = round(cp1 * 1.10);
				double cp3 = round(cp2 * 1.10);
				double retailer = round(cp3 * 1.10);

				if (retailer >= mrp)
					retailer = mrp - 1;

				double[] values;
				switch (rateColumns) {
				case 4:
					values = new double[] { cp1, cp2, cp3, retailer };
					break;
				case 3:
					values = new double[] { cp1, cp2, retailer };
					break;
				case 2:
					values = new double[] { cp1, retailer };
					break;
				default:
					values = new double[] { retailer };
					break;
				}

				System.out.println("Will write: " + Arrays.toString(values));

				// =====================================================
				// ROW-WISE UPDATE
				// =====================================================

//				Random random = new Random();
				String moqRandom = String.valueOf(1 + random.nextInt(15)); 
				
				int[] possibleMoq = {1, 5, 10, 15, 20};
				String moqPossileSet = String.valueOf(possibleMoq[random.nextInt(possibleMoq.length)]);
				
				
				for (int col = 0; col < values.length; col++) {

				    System.out.println("Checking Product = " + (p+1) + " | Column = " + (col+1));

				    WebElement row = productRows.get(p);

				    // Get table value (visible rate)
				    List<WebElement> rateCells = getRateCells(row);
				    WebElement cell = rateCells.get(col);

				    String existingRate = getRateValue(cell);
				    System.out.println("Rate found in table: " + existingRate);

				    // -------------------------
				    // VALIDATION
				    // -------------------------
				    if (!isZero(cell)) {
				        System.out.println("✔ Already filled, skipping...");
				        continue;
				    }

				    System.out.println("❌ RATE is ZERO → Need to update!");

				    // -------------------------
				    // FIND CORRESPONDING ICON
				    // -------------------------
				    List<WebElement> rowIcons = row.findElements(By.xpath(
				            ".//div[contains(@class,'d-flex  flex-row align-items-center')]/button[@type='button']"));

				    if (col >= rowIcons.size()) {
				        System.out.println("⚠ Icon not found for column!");
				        continue;
				    }

				    WebElement icon = rowIcons.get(col);

				    js.executeScript("arguments[0].scrollIntoView(true);", icon);
				    wait.until(ExpectedConditions.elementToBeClickable(icon)).click();
				    System.out.println("Clicked Edit Icon for ZERO rate");

					
					

					System.out.println("Clicked Edit for Product " + (p + 1) + " - Column " + (col + 1));

					System.out.println("Wait for Rate InputBox");
					// ---------------- RATE INPUT ----------------
					wait.until(ExpectedConditions.visibilityOf(rateInput));
//					Thread.sleep(4000);					
					rateInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
					rateInput.sendKeys(String.valueOf(values[col]));

					System.out.println("Wait for MOQ InputBox");
					// ---------------- MOQ INPUT ----------------
					wait.until(ExpectedConditions.visibilityOf(moqInput));
					moqInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
					moqInput.sendKeys(moqPossileSet);

					// ---------------- SAVE ----------------
					System.out.println("Wait for Update Button Product");
					wait.until(ExpectedConditions.elementToBeClickable(updateButtonProduct)).click();

					System.out.println("Wait for invisiblity Update Button Product");
//                wait.until(ExpectedConditions.invisibilityOf(updateButtonProduct));
					wait.until(ExpectedConditions.visibilityOf(icon));

					System.out.println("✔ Updated rate: " + values[col]);
				}

				System.out.println("✔ Product " + (p + 1) + " completed.");

				js.executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth;", scrollContainer);

			}
			System.out.println("Current at Page End : " + currentPage);
			currentPage++;
			hasNext = clickNextPageIfAvailable();
		}
	    System.out.println("All pages completed.");
	}

	
	
	
	
}
