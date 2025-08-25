package pageObjects;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utility.BrandUtility;
import utility.ConfigReader;
import utility.TestDataGenerator;

public class ProductsPage extends BasePage {

	private static Map<String, Integer> brandProductCount = new HashMap<>(); // make unique product
	private static String newProductName, productNewMRP, productUniqueCode, productCategoryName, productSubCategoryName,
			checkBoxPromotedValue, checkBoxFocusedValue, checkBoxMustSellValue, bestBeforeValue,
			expiryUnitDropDownOption, productUnitWTValue, selectedMeasurementUnitValue, productImageValue,
			productCatalogImageValue, packagingUnitValue, packagingUnitContainsValue, packagingUnitSecondContainsValue,
			HSNCodeValue, GST_PercentageValue, Cess_PercentageValue, productDescValue;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	public ProductsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[@class='ant-page-header-heading-title']")
	WebElement productBrandPath;

//	Mandatory Fields 
	@FindBy(xpath = "//button[@class='ant-btn ant-btn-primary']")
	WebElement addProductButton;
	@FindBy(xpath = "//input[@id='nest-messages_name']")
	WebElement productName;
	@FindBy(xpath = "//input[@id='nest-messages_mrp']")
	WebElement productMRP;
	@FindBy(xpath = "//input[@id='nest-messages_category']")
	WebElement productCategory;
	@FindBy(xpath = "//input[@id='nest-messages_category']/ancestor::div[@class='ant-select-selector']//span[@class='ant-select-selection-item']")
	WebElement productCategoryOnEdit;
	@FindBy(xpath = "//div[@class='ant-select-dropdown ant-select-dropdown-placement-bottomLeft ']//div[@title]")
	List<WebElement> productCategoryAllOptions;
	@FindBy(xpath = "//input[@id='nest-messages_sub_category']")
	WebElement productSubCategory;
	@FindBy(xpath = "//input[@id='nest-messages_sub_category']/ancestor::div[@class='ant-select-selector']//span[@class='ant-select-selection-item']")
	WebElement productSubCategoryOnEdit;
	@FindBy(xpath = "//div[@class='ant-select-dropdown ant-select-dropdown-placement-bottomLeft ']//div[@title]")
	List<WebElement> productSubCategoryAllOptions;
	@FindBy(xpath = "//input[@id='nest-messages_base_quantity']")
	WebElement productUnitWT;

//	Not Mandatory
	@FindBy(xpath = "//input[@id='nest-messages_product_code']")
	WebElement productCode;
	@FindBy(xpath = "//input[contains(@value,'Must Sell')]")
	WebElement checkBoxMustSell;
	@FindBy(xpath = "//input[@value='Focused']")
	WebElement checkBoxFocused;
	@FindBy(xpath = "//input[@value='Promoted']")
	WebElement checkBoxPromoted;

	@FindBy(xpath = "//input[@id='nest-messages_expiry_in']")
	WebElement inputBestBefore;
	@FindBy(xpath = "//input[@id='nest-messages_expiry_unit']")
	WebElement expiryUnitDropDown;
	@FindBy(xpath = "//input[@id='nest-messages_expiry_unit']/ancestor::div[@class='ant-select-selector']//span[@class='ant-select-selection-item']")
	WebElement expiryUnitDropDownOnEdit;
	@FindBy(xpath = "//div[@class='ant-select-dropdown ant-select-dropdown-placement-bottomLeft ']//div[@title]")
	List<WebElement> expiryUnitDropDownAllOptions;
	@FindBy(xpath = "//input[@id='nest-messages_base_unit']")
	WebElement measurementUnitDropDown;
	@FindBy(xpath = "//input[@id='nest-messages_base_unit']/ancestor::div[@class='ant-select-selector']//span[@class='ant-select-selection-item']")
	WebElement measurementUnitDropDownOnEdit;
	@FindBy(xpath = "//div[@class='ant-select-dropdown ant-select-dropdown-placement-bottomLeft ']//div[@title]")
	List<WebElement> measurementUnitDropDownAllOptions;

	// Add Unit Code
	@FindBy(xpath = "//span[normalize-space()='Add Unit']")
	WebElement addUnitButton;
	@FindBy(xpath = "//div[@class='ant-select-dropdown ant-select-dropdown-placement-bottomLeft ']//div//div[@class='rc-virtual-list']//div[@title]")
	List<WebElement> packagingUnitAllOptions;
	@FindBy(xpath = "//span[normalize-space()='Select Unit']")
	WebElement packagingUnit;
//	@FindBy(xpath = "//div[@class=\"ant-col ant-col-6\"]..//span[normalize-space()='Select Unit']")
//	WebElement packagingUnit;
//	@FindBy(xpath = "//div[@class='ant-select-item-option-content' and text()='Box']\"")
//	WebElement packagingUnit;

	@FindBy(xpath = "//input[@id='nest-messages_unitTiers_0_to_unit']")
	WebElement packagingUnitSecond;
	@FindBy(xpath = "//div[@class='ant-select-dropdown ant-select-dropdown-placement-bottomLeft ']//div[@title]")
	List<WebElement> packagingUnitAllOptionsSecond;

	@FindBy(xpath = "//input[@placeHolder=\"Enter count\"]")
	WebElement containsInput;

//	GST Details 
	@FindBy(xpath = "//input[@id='nest-messages_hsn_code']")
	WebElement HSNCodeInput;
	@FindBy(xpath = "//input[@id='nest-messages_gst']")
	WebElement GST_Percentage;
	@FindBy(xpath = "//input[@id='nest-messages_cess']")
	WebElement Cess_Percentage;
	@FindBy(xpath = "//textarea[@id='nest-messages_description']")
	WebElement product_Description;

//	Image
	@FindBy(xpath = "//input[contains(@accept,'image/jpeg, image/jpg, image/png')]")
	WebElement ProductImage;
	@FindBy(xpath = "//div[@class='ant-upload-drag-container']//img")
	WebElement productImagePresent;
	@FindBy(xpath = "//input[@id='nest-messages_catalog']")
	WebElement Product_CatalogImage;
	@FindBy(xpath = "//div[@class=\"ant-upload-list-text-container\"]//div[@class=\"ant-upload-text-icon\"]")
	WebElement product_CatalogImageIsPathAfterUpload;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement submitButton;
	@FindBy(xpath = "//span[@aria-label='close']//*[name()='svg']")
	WebElement closeCross;
	@FindBy(xpath = "//span[contains(text(),'Product created successfully')]")
	WebElement productCreatedSuccessMessage;

	@FindBy(xpath = "//input[@id=\"search\"]")
	WebElement searchProductBar;

	// Edit Product
	@FindBy(xpath = "//div[@class=\"ant-modal-title\"]")
	WebElement editProductPageTitleText;

	@FindBy(xpath = "//span[@aria-label=\"edit\"]")
	WebElement editProductButton;

	@FindBy(xpath = "div[class=\"ant-col ant-col-6\"]")
	WebElement productContainer;

	@FindBy(xpath = "(//div[@class=\"ant-col ant-col-6\"]//span[@class=\"font-size-base font-weight-semibold font-normal text-darkgreen\"])[1]")
	WebElement productNameFromContainer;

	public String getProductBrandPath() {
		String productBrandName = productBrandPath.getText();
		return productBrandName;
	}

	public String getProductName() {
		String productName = BrandUtility.readJson("Product", "ProductName");
		return productName;
	}

	public boolean verifyProductCreatedSuccessMessage() {
		try {
			wait.until(ExpectedConditions.visibilityOf(productCreatedSuccessMessage));

			String actualText = productCreatedSuccessMessage.getText().trim();
			return actualText.equals("Product created successfully");
		} catch (TimeoutException e) {
			return false;
		}
	}

	public void addProductButton() {
		js.executeScript("document.body.style.zoom = '70%'");
		wait.until(ExpectedConditions.elementToBeClickable(addProductButton));
		addProductButton.click();
	}

	public void addProductName() {
//		String productBrandName = getProductBrandPath(); // EX : "Amul"
		// Get the current count for this brand, or start at 1

		// Create unique product name
		newProductName = TestDataGenerator.getRandomProductName();

		// Send to input field
		productName.sendKeys(newProductName);
	}

	public void addProductMRP() {
		Random rand = new Random();
		int randomMRP = rand.nextInt(961) + 40; // generates value between 40 and 1000 (inclusive)
		productNewMRP = String.valueOf(randomMRP);
		productMRP.sendKeys(productNewMRP);
	}

	public void addProductCategory(String desireProductCategory) {
		productCategory.click();
//		productCategoryAllOptions
		for (WebElement op : productCategoryAllOptions) {
			System.out.println(op.getText());
			String str = op.getText();
			if (str.equals("chocolate cake design")) {
				op.click();
			}
		}
	}

	public void addProductCategory() {
		String desiredCategory = ConfigReader.getProperty("product.category");
		productCategory.click();
		waitForMultipleElementsVisible(productCategoryAllOptions, 5);
		if (productCategoryAllOptions.isEmpty()) {
			throw new RuntimeException("❌ No product categories available!");
		}

		Random rand = new Random();
		int randomIndex = rand.nextInt(productCategoryAllOptions.size()); // 0 to size-1
		WebElement randomOption = productCategoryAllOptions.get(randomIndex);

		productCategoryName = randomOption.getText();
		randomOption.click();

		System.out.println("✅ Selected product category: " + productCategoryName);
	}

	public void addProductSubCategory() {
		wait.until(ExpectedConditions.elementToBeClickable(productSubCategory));
		productSubCategory.click();

		// dropdown options are loaded before proceeding
		if (productSubCategoryAllOptions == null || productSubCategoryAllOptions.isEmpty()) {
			throw new RuntimeException("No subcategory options found!");
		}

		// randomly pick one option from the list
		Random rand = new Random();
		int randomIndex = rand.nextInt(productSubCategoryAllOptions.size());

		WebElement randomOption = productSubCategoryAllOptions.get(randomIndex);
		productSubCategoryName = randomOption.getText();
		randomOption.click();
	}

	public void clickOnAddUnitButton() {
		addUnitButton.click();
	}

	public void addProductUnitWT() {

		js.executeScript("document.body.style.zoom = '80%'");
		Random random = new Random();
		int unitWeight = random.nextInt(46) + 5; // generate number between 5 and 50
		productUnitWTValue = String.valueOf(unitWeight);
		productUnitWT.sendKeys(String.valueOf(unitWeight));
	}

	public void addProductCode() {
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		productUniqueCode = "PROD-" + timestamp;
		productCode.sendKeys(productUniqueCode);
	}

//	--------------------- Check Box
	public void clickCheckBoxMustSell() {
		checkBoxMustSell.click();
	}

	public void clickCheckBoxFocused() {
		productName.click();
	}

	public void clickCheckBoxPromoted() {
		checkBoxPromoted.click();
	}

	public void clickProductAimCheckboxRandomly() {
		List<Runnable> checkboxActions = Arrays.asList(() -> checkBoxMustSell.click(), () -> checkBoxFocused.click(),
				() -> checkBoxPromoted.click());

		Collections.shuffle(checkboxActions); // Shuffle the list to randomize
		Random random = new Random();
		int numberOfCheckboxesToClick = random.nextInt(3) + 1; // 1 to 3

		for (int i = 0; i < numberOfCheckboxesToClick; i++) {
			checkboxActions.get(i).run(); // Click the randomly chosen checkboxes
		}
		checkBoxMustSellValue = String.valueOf(checkBoxMustSell.isSelected());
		checkBoxFocusedValue = String.valueOf(checkBoxFocused.isSelected());
		checkBoxPromotedValue = String.valueOf(checkBoxPromoted.isSelected());
	}
//	-------------------------------------------------------------

	public void addInputBestBefore() {
		Random random = new Random();
		int bestBefore = random.nextInt(9) + 4; // generates number between 4 and 12
		bestBeforeValue = String.valueOf(bestBefore);
		inputBestBefore.sendKeys(bestBeforeValue);
	}

	public void selectExpiryUnitDropDown() {
//		expiryUnitDropDownAllOptions
		expiryUnitDropDown.click();

		// dropdown options are loaded before proceeding
		if (expiryUnitDropDownAllOptions == null || expiryUnitDropDownAllOptions.isEmpty()) {
			throw new RuntimeException("No subcategory options found!");
		}

		// randomly pick one option from the list
		Random rand = new Random();
		int randomIndex = rand.nextInt(expiryUnitDropDownAllOptions.size());

		WebElement randomOption = expiryUnitDropDownAllOptions.get(randomIndex);
		expiryUnitDropDownOption = randomOption.getText();
//		    System.out.println("Selected SubCategory: " + randomOption.getText());
		randomOption.click();

	}

	public void selectMeasurementUnitDropDown() {
		measurementUnitDropDown.click();
//		measurementUnitDropDownAllOptions
		// dropdown options are loaded before proceeding
		if (measurementUnitDropDownAllOptions == null || measurementUnitDropDownAllOptions.isEmpty()) {
			throw new RuntimeException("No subcategory options found!");
		}

		// randomly pick one option from the list
		Random random = new Random();
		int randomIndex = random.nextInt(measurementUnitDropDownAllOptions.size());

		WebElement randomOption = measurementUnitDropDownAllOptions.get(randomIndex);
		selectedMeasurementUnitValue = randomOption.getText();
//	    System.out.println("Selected SubCategory: " + randomOption.getText());
		randomOption.click();
	}

	public void selectpackagingUnit() {
//		packagingUnit
//		packagingUnitAllOptions
//		wait.until(ExpectedConditions.visibilityOf(packagingUnit));
//		packagingUnit.click();
		js.executeScript("arguments[0].click();", packagingUnit);
//		measurementUnitDropDownAllOptions
		// dropdown options are loaded before proceeding
		if (packagingUnitAllOptions == null || packagingUnitAllOptions.isEmpty()) {
			throw new RuntimeException("No subcategory options found!");
		}

		// randomly pick one option from the list
		Random rand = new Random();
		int randomIndex = rand.nextInt(packagingUnitAllOptions.size());

		WebElement randomOption = packagingUnitAllOptions.get(randomIndex);
		packagingUnitValue = randomOption.getText();
//	    System.out.println("Selected SubCategory: " + randomOption.getText());
		randomOption.click();
	}

	public void SelectPackagingUnitSecond() {
		packagingUnitSecond.click();
//		packagingUnitAllOptionsSecond

		// dropdown options are loaded before proceeding
		if (packagingUnitAllOptionsSecond == null || packagingUnitAllOptionsSecond.isEmpty()) {
			throw new RuntimeException("No subcategory options found!");
		}

		// randomly pick one option from the list
		Random rand = new Random();
		int randomIndex = rand.nextInt(packagingUnitAllOptionsSecond.size());

		WebElement randomOption = packagingUnitAllOptionsSecond.get(randomIndex);
		packagingUnitSecondContainsValue = randomOption.getText();
//	    System.out.println("Selected SubCategory: " + randomOption.getText());
		randomOption.click();
	}

	public void packagingUnitContains() {
		wait.until(ExpectedConditions.visibilityOf(containsInput));
		Random random = new Random();
		int unitWeight = random.nextInt(25) + 2; // Generates number between 5 and 50 (inclusive)
		packagingUnitContainsValue = String.valueOf(unitWeight);
		containsInput.sendKeys(packagingUnitContainsValue);
	}

	public void addHSNCodeInput() {
		Random random = new Random();
		int hsnCode = 1000 + random.nextInt(9000); // Generates 1000–9999
		HSNCodeValue = String.valueOf(hsnCode);
		HSNCodeInput.sendKeys(HSNCodeValue);
	}

	public void addGST_Percentage() {
		Random random = new Random();
		int unitWeight = random.nextInt(12) + 2; // Generates number between 5 and 50 (inclusive)
		GST_PercentageValue = String.valueOf(unitWeight);
		GST_Percentage.sendKeys(GST_PercentageValue);
	}

	public void saveDetails() {
		Map<String, String> productDetails = new HashMap<>();
		productDetails.put("ProductName", newProductName);
		productDetails.put("ProductMRP", productNewMRP);
		productDetails.put("ProductUniqueCode", productUniqueCode);
		productDetails.put("ProductCategoryName", productCategoryName);
		productDetails.put("productSubCategoryName", productSubCategoryName);
		productDetails.put("CheckBoxPromotedValue", checkBoxPromotedValue);
		productDetails.put("CheckBoxFocusedValue", checkBoxFocusedValue);
		productDetails.put("CheckBoxMustSellValue", checkBoxMustSellValue);
		productDetails.put("BestBeforeValue", bestBeforeValue);
		productDetails.put("ExpiryUnitDropDownOption", expiryUnitDropDownOption);
		productDetails.put("ProductUnitWTValue", productUnitWTValue);
		productDetails.put("selectedMeasurementUnitValue", selectedMeasurementUnitValue);
		productDetails.put("ProductImageValue", productImageValue);
		productDetails.put("ProductCatalogImageValue", productCatalogImageValue);
//		productDetails.put("PackagingUnitValue" , packagingUnitValue);
//		productDetails.put("PackagingUnitContainsValue" , packagingUnitContainsValue);
//		productDetails.put("PackagingUnitSecondContainsValue" , packagingUnitSecondContainsValue);
		productDetails.put("HSNCodeValue", HSNCodeValue);
		productDetails.put("GST_PercentageValue", GST_PercentageValue);
		productDetails.put("Cess_PercentageValue", Cess_PercentageValue);
		productDetails.put("productDescValue", productDescValue);
		BrandUtility.writeJson("Product", productDetails);

	}

	public void addCess_Percentage() {
		Random random = new Random();
		int unitWeight = random.nextInt(9) + 2; // Generates number between 5 and 50 (inclusive)
		Cess_PercentageValue = String.valueOf(unitWeight);
		Cess_Percentage.sendKeys(Cess_PercentageValue);
	}

	public void addProduct_Description() {
		productDescValue = "Product Description For Testing";
		product_Description.sendKeys(productDescValue);
	}

	public void uploadProductImage() {
//		ProductImage.sendKeys("");
		File file = new File(".\\src\\test\\resources\\Product.png");
		String absolutePath = file.getAbsolutePath();
		ProductImage.sendKeys(absolutePath);
		productImageValue = "true";
	}

	public void uploadProduct_CatalogImage() {
//		Product_CatalogImage.sendKeys("");
		File file1 = new File(".\\src\\test\\resources\\Catalog 1.png");
		File file2 = new File(".\\src\\test\\resources\\Catalog 2.jpeg");
		File file3 = new File(".\\src\\test\\resources\\Catalog 3.png");

		String path1 = file1.getAbsolutePath();
		String path2 = file2.getAbsolutePath();
		String path3 = file3.getAbsolutePath();

		Product_CatalogImage.sendKeys(path1 + "\n" + path2 + "\n" + path3);
		productCatalogImageValue = "true";
	}

	
	// ---------------- EDIT METHODS ----------------

	public void editProductName() {
		newProductName = TestDataGenerator.getRandomProductName();
		productName.clear();
		productName.sendKeys(newProductName);
	}

	public void editProductMRP() {
		Random rand = new Random();
		int randomMRP = rand.nextInt(961) + 40;
		productNewMRP = String.valueOf(randomMRP);
		productMRP.clear();
		productMRP.sendKeys(productNewMRP);
	}

	public void editProductCategory(String desiredProductCategory) {
		productCategory.click();
		for (WebElement op : productCategoryAllOptions) {
			if (op.getText().equalsIgnoreCase(desiredProductCategory)) {
				op.click();
				break;
			}
		}
	}

	public void editProductCategory() {
		productCategory.click();
		waitForMultipleElementsVisible(productCategoryAllOptions, 5);
		if (productCategoryAllOptions.isEmpty()) {
			throw new RuntimeException("❌ No product categories available!");
		}
		Random rand = new Random();
		int randomIndex = rand.nextInt(productCategoryAllOptions.size());
		WebElement randomOption = productCategoryAllOptions.get(randomIndex);
		productCategoryName = randomOption.getText();
		randomOption.click();
	}

	public void editProductSubCategory() {
		wait.until(ExpectedConditions.elementToBeClickable(productSubCategory));
		productSubCategory.click();
		if (productSubCategoryAllOptions == null || productSubCategoryAllOptions.isEmpty()) {
			throw new RuntimeException("No subcategory options found!");
		}
		Random rand = new Random();
		int randomIndex = rand.nextInt(productSubCategoryAllOptions.size());
		WebElement randomOption = productSubCategoryAllOptions.get(randomIndex);
		productSubCategoryName = randomOption.getText();
		randomOption.click();
	}

	public void editProductUnitWT() {
		js.executeScript("document.body.style.zoom = '80%'");
		Random random = new Random();
		int unitWeight = random.nextInt(46) + 5;
		productUnitWTValue = String.valueOf(unitWeight);
		productUnitWT.clear();
		productUnitWT.sendKeys(productUnitWTValue);
	}

	public void editProductCode() {
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		productUniqueCode = "PROD-" + timestamp;
		productCode.clear();
		productCode.sendKeys(productUniqueCode);
	}

	public void editInputBestBefore() {
		Random random = new Random();
		int bestBefore = random.nextInt(9) + 4;
		bestBeforeValue = String.valueOf(bestBefore);
		inputBestBefore.clear();
		inputBestBefore.sendKeys(bestBeforeValue);
	}

	public void editHSNCodeInput() {
		Random random = new Random();
		int hsnCode = 1000 + random.nextInt(9000);
		HSNCodeValue = String.valueOf(hsnCode);
		HSNCodeInput.clear();
		HSNCodeInput.sendKeys(HSNCodeValue);
	}

	public void editGST_Percentage() {
		Random random = new Random();
		int unitWeight = random.nextInt(12) + 2;
		GST_PercentageValue = String.valueOf(unitWeight);
		GST_Percentage.clear();
		GST_Percentage.sendKeys(GST_PercentageValue);
	}

	public void editCess_Percentage() {
		Random random = new Random();
		int unitWeight = random.nextInt(9) + 2;
		Cess_PercentageValue = String.valueOf(unitWeight);
		Cess_Percentage.clear();
		Cess_Percentage.sendKeys(Cess_PercentageValue);
	}

	public void editProduct_Description() {
		productDescValue = "Edited Product Description For Testing";
		product_Description.clear();
		product_Description.sendKeys(productDescValue);
	}

	public void editPackagingUnitContains() {
		wait.until(ExpectedConditions.visibilityOf(containsInput));
		Random random = new Random();
		int unitWeight = random.nextInt(25) + 2;
		packagingUnitContainsValue = String.valueOf(unitWeight);
		containsInput.clear();
		containsInput.sendKeys(packagingUnitContainsValue);
	}

	public void clickSubmitButton() {
		try {
			submitButton.click();
			saveDetails();
			System.out.println("Click succeeded.");
		} catch (Exception e) {
			System.out.println(" click failed ");
		}
	}

	public void closeCross() {
		closeCross.click();
	}

//	------------ Call Internal methods  ------------------------------------

	public void addUnit() {
		js.executeScript("document.body.style.zoom = '70%'");
		clickOnAddUnitButton();
		selectpackagingUnit();
		packagingUnitContains(); // containsInput
//		SelectPackagingUnitSecond();
	}

	public void searchProduct() {
		waitForElementVisible(searchProductBar, 7);
		searchProductBar.clear();
		searchProductBar.sendKeys(getProductName());
	}

	public void clickOnEditProductIcon() {
		String expectedProductName = getProductName().trim();
		wait.until(ExpectedConditions.textToBePresentInElement(productNameFromContainer, expectedProductName));

		if (productNameFromContainer.getText().trim().equals(expectedProductName)) {
			editProductButton.click();
			js.executeScript("document.body.style.zoom = '80%'");
		} else {
			throw new RuntimeException("Product name did not match. Expected: " + expectedProductName + " but found: "
					+ productNameFromContainer.getText());
		}
	}


	public String getEditProductPageTitalText() {
		System.out.println("editProductPageTitalText = = " + editProductPageTitleText);
		return editProductPageTitleText.getText();
	}

	public boolean isEditProductPageTitleMatchingProductName() {
		String actualTitle = editProductPageTitleText.getText().trim();
		String expectedTitle = "Edit " + getProductName().trim();
		System.out.println("actualTitle = " + actualTitle);
		System.out.println("expectedTitle = " + expectedTitle);
		return actualTitle.equals(expectedTitle);
	}

	public void getProductNameFromInputField() {
		waitForElementVisible(editProductPageTitleText, 6);
		newProductName = productName.getAttribute("value");
	}

	public void getProductCategoryFromInputField() {
		productCategoryName = productCategoryOnEdit.getText();
	}

	public void getProductSubCategoryFromInputField() {
		productSubCategoryName = productSubCategoryOnEdit.getText();
	}

	public void getProductCodeFromInputField() {
		productUniqueCode = productCode.getAttribute("value");
	}

	public void getProductMRPFromInputField() {
		productNewMRP = productMRP.getAttribute("value");
	}

	public void getProductImageStatus() {
		productImageValue = String.valueOf(productImagePresent.isDisplayed());
	}

	public void getProductCatalogImageStatus() {
		productCatalogImageValue = String.valueOf(product_CatalogImageIsPathAfterUpload.isDisplayed());
	}

	public void getProductPromotedCheckBoxStatus() {
		checkBoxPromotedValue = String.valueOf(checkBoxPromoted.isSelected());
	}

	public void getProductFocusedCheckBoxStatus() {
		checkBoxFocusedValue = String.valueOf(checkBoxFocused.isSelected());
	}

	public void getProductMustSellCheckBoxStatus() {
		checkBoxMustSellValue = String.valueOf(checkBoxMustSell.isSelected());
	}

	public void getBestBeforeValue() {
		bestBeforeValue = inputBestBefore.getAttribute("value");
	}

	public void getExpiryUnitValue() {
		expiryUnitDropDownOption = expiryUnitDropDownOnEdit.getText();
	}

	public void getUnitWTValue() {
		productUnitWTValue = productUnitWT.getAttribute("value");
	}

	public void getMeasurementUnitValue() {
		selectedMeasurementUnitValue = measurementUnitDropDownOnEdit.getText();
	}

	public void getHSNCodeValue() {
		HSNCodeValue = HSNCodeInput.getAttribute("value");
	}

	public void getGSTValue() {
		GST_PercentageValue = GST_Percentage.getAttribute("value");
	}

	public void getCessValue() {
		Cess_PercentageValue = Cess_Percentage.getAttribute("value");
	}

	public void getProductDescriptionValue() {
		productDescValue = product_Description.getAttribute("value");
	}

	public void captureAllValuesFromFields() {
		getProductNameFromInputField();
		getProductCategoryFromInputField();
		getProductSubCategoryFromInputField();
		getProductCodeFromInputField();
		getProductMRPFromInputField();
		getProductImageStatus();
		getProductCatalogImageStatus();
		getProductPromotedCheckBoxStatus();
		getProductFocusedCheckBoxStatus();
		getProductMustSellCheckBoxStatus();
		getBestBeforeValue();
		getExpiryUnitValue();
		getUnitWTValue();
		getMeasurementUnitValue();
		getHSNCodeValue();
		getGSTValue();
		getCessValue();
		getProductDescriptionValue();
	}

	public boolean areProductDetailsValid() {
		try {
			captureAllValuesFromFields();
			
			Map<String, String> oldData = new HashMap<>();
			oldData.put("ProductName", BrandUtility.readJson("Product", "ProductName"));
			oldData.put("ProductMRP", BrandUtility.readJson("Product", "ProductMRP"));
			oldData.put("BestBeforeValue", BrandUtility.readJson("Product", "BestBeforeValue"));
			oldData.put("ExpiryUnitDropDownOption", BrandUtility.readJson("Product", "ExpiryUnitDropDownOption"));
			oldData.put("productDescValue", BrandUtility.readJson("Product", "productDescValue"));
			oldData.put("ProductCategoryName", BrandUtility.readJson("Product", "ProductCategoryName"));
			oldData.put("CheckBoxMustSellValue", BrandUtility.readJson("Product", "CheckBoxMustSellValue"));
			oldData.put("GST_PercentageValue", BrandUtility.readJson("Product", "GST_PercentageValue"));
			oldData.put("selectedMeasurementUnitValue",
					BrandUtility.readJson("Product", "selectedMeasurementUnitValue"));
			oldData.put("ProductUniqueCode", BrandUtility.readJson("Product", "ProductUniqueCode"));
			oldData.put("ProductUnitWTValue", BrandUtility.readJson("Product", "ProductUnitWTValue"));
			oldData.put("ProductCatalogImageValue", BrandUtility.readJson("Product", "ProductCatalogImageValue"));
			oldData.put("productSubCategoryName", BrandUtility.readJson("Product", "productSubCategoryName"));
//	        oldData.put("PackagingUnitValue", BrandUtility.readJson("Product", "PackagingUnitValue"));
//	        oldData.put("PackagingUnitContainsValue", BrandUtility.readJson("Product", "PackagingUnitContainsValue"));
			oldData.put("ProductImageValue", BrandUtility.readJson("Product", "ProductImageValue"));
			oldData.put("Cess_PercentageValue", BrandUtility.readJson("Product", "Cess_PercentageValue"));
			oldData.put("CheckBoxFocusedValue", BrandUtility.readJson("Product", "CheckBoxFocusedValue"));
			oldData.put("CheckBoxPromotedValue", BrandUtility.readJson("Product", "CheckBoxPromotedValue"));
			oldData.put("HSNCodeValue", BrandUtility.readJson("Product", "HSNCodeValue"));

			// Step 3: Actual captured values (from UI)
			Map<String, String> capturedData = new HashMap<>();
			capturedData.put("ProductName", newProductName);
			capturedData.put("ProductMRP", productNewMRP);
			capturedData.put("BestBeforeValue", bestBeforeValue);
			capturedData.put("ExpiryUnitDropDownOption", expiryUnitDropDownOption);
			capturedData.put("productDescValue", productDescValue);
			capturedData.put("ProductCategoryName", productCategoryName);
			capturedData.put("CheckBoxMustSellValue", checkBoxMustSellValue);
			capturedData.put("GST_PercentageValue", GST_PercentageValue);
			capturedData.put("selectedMeasurementUnitValue", selectedMeasurementUnitValue);
			capturedData.put("ProductUniqueCode", productUniqueCode);
			capturedData.put("ProductUnitWTValue", productUnitWTValue);
			capturedData.put("ProductCatalogImageValue", productCatalogImageValue);
			capturedData.put("productSubCategoryName", productSubCategoryName);
//	        capturedData.put("PackagingUnitValue", packagingUnitValue);
//	        capturedData.put("PackagingUnitContainsValue", packagingUnitContainsValue);
			capturedData.put("ProductImageValue", productImageValue);
			capturedData.put("Cess_PercentageValue", Cess_PercentageValue);
			capturedData.put("CheckBoxFocusedValue", checkBoxFocusedValue);
			capturedData.put("CheckBoxPromotedValue", checkBoxPromotedValue);
			capturedData.put("HSNCodeValue", HSNCodeValue);

			boolean allMatch = true;

			for (Map.Entry<String, String> entry : oldData.entrySet()) {
				String key = entry.getKey();
				String expectedValue = entry.getValue();
				String actualValue = capturedData.get(key);

				if (expectedValue == null && actualValue == null) {
					continue; // Both are null → acceptable
				}

				if (actualValue == null || !expectedValue.equals(actualValue)) {
					System.out.println("❌ Mismatch in field: " + key);
					System.out.println("   Expected: " + expectedValue);
					System.out.println("   Actual   : " + actualValue);
					allMatch = false;
				}
			}

			if (allMatch) {
				System.out.println("✅ All Product fields match with JSON.");
			} else {
				System.out.println("❌ One or more Product fields did not match.");
			}

			return allMatch;

		} catch (Exception e) {
			System.out.println("❌ Exception during Product data verification: " + e.getMessage());
			return false;
		}
	}

}
