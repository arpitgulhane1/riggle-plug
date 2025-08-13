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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utility.ConfigReader;

public class ProductsPage extends BasePage {

	private static Map<String, Integer> brandProductCount = new HashMap<>(); // make unique product

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
	@FindBy(xpath = "//div[@class='ant-select-dropdown ant-select-dropdown-placement-bottomLeft ']//div[@title]")
	List<WebElement> productCategoryAllOptions;
	@FindBy(xpath = "//input[@id='nest-messages_sub_category']")
	WebElement productSubCategory;
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
	@FindBy(xpath = "//div[@class='ant-select-dropdown ant-select-dropdown-placement-bottomLeft ']//div[@title]")
	List<WebElement> expiryUnitDropDownAllOptions;
	@FindBy(xpath = "//input[@id='nest-messages_base_unit']")
	WebElement measurementUnitDropDown;
	@FindBy(xpath = "//div[@class='ant-select-dropdown ant-select-dropdown-placement-bottomLeft ']//div[@title]")
	List<WebElement> measurementUnitDropDownAllOptions;

	// Add Unit Code
	@FindBy(xpath = "//span[normalize-space()='Add Unit']")
	WebElement addUnitButton;
	@FindBy(xpath = "//div[@class='ant-select-dropdown ant-select-dropdown-placement-bottomLeft ']//div//div[@class='rc-virtual-list']//div[@title]")
	List<WebElement> packagingUnitAllOptions;
	@FindBy(xpath = "//input[@id='nest-messages_unitTiers_0_from_unit']")
	WebElement packagingUnit;

	@FindBy(xpath = "//input[@id='nest-messages_unitTiers_0_to_unit']")
	WebElement packagingUnitSecond;
	@FindBy(xpath = "//div[@class='ant-select-dropdown ant-select-dropdown-placement-bottomLeft ']//div[@title]")
	List<WebElement> packagingUnitAllOptionsSecond;

	@FindBy(xpath = "//input[@id='nest-messages_unitTiers_0_conversion_factor']")
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
	@FindBy(xpath = "//input[@id='nest-messages_catalog']")
	WebElement Product_CatalogImage;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement submitButton;
	@FindBy(xpath = "//span[@aria-label='close']//*[name()='svg']")
	WebElement closeCross;

	public String getProductBrandPath() {
		String productBrandName = productBrandPath.getText();
		return productBrandName;
	}

	public void addProductButton() {
		wait.until(ExpectedConditions.elementToBeClickable(addProductButton));
		addProductButton.click();
	}

	public void addProductName() {
		String productBrandName = getProductBrandPath(); // EX : "Amul"
		// Get the current count for this brand, or start at 1
		int count = brandProductCount.getOrDefault(productBrandName, 0) + 1;

		// Save updated count
		brandProductCount.put(productBrandName, count);

		// Create unique product name
		String newProductName = productBrandName + " Product " + count;

		// Send to input field
		productName.sendKeys(newProductName);
	}

	public void addProductMRP() {
		Random rand = new Random();
		int randomMRP = rand.nextInt(961) + 40; // generates value between 40 and 1000 (inclusive)
		productMRP.sendKeys(String.valueOf(randomMRP));
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

		for (WebElement op : productCategoryAllOptions) {
			if (op.getText().trim().equalsIgnoreCase(desiredCategory)) {
				op.click();
				break;
			}
		}
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
//	    System.out.println("Selected SubCategory: " + randomOption.getText());
		randomOption.click();
//	    for(WebElement op:productSubCategoryAllOptions) {
//			System.out.println(op.getText());
//			String str =  op.getText();
//			if(str.equals("chocolate cake design")) {
//				op.click();
//			}
//		}
	}

	public void clickOnAddUnitButton() {
		addUnitButton.click();
	}

	public void addProductUnitWT() {
		Random random = new Random();
		int unitWeight = random.nextInt(46) + 5; // generate number between 5 and 50
		productUnitWT.sendKeys(String.valueOf(unitWeight));
	}

	public void addProductCode() {
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String uniqueCode = "PROD-" + timestamp;
		productCode.sendKeys(uniqueCode);
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
	}
//	-------------------------------------------------------------

	public void addInputBestBefore() {
		Random random = new Random();
		int bestBefore = random.nextInt(9) + 4; // generates number between 4 and 12
		inputBestBefore.sendKeys(String.valueOf(bestBefore));
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
		Random rand = new Random();
		int randomIndex = rand.nextInt(measurementUnitDropDownAllOptions.size());

		WebElement randomOption = measurementUnitDropDownAllOptions.get(randomIndex);
//	    System.out.println("Selected SubCategory: " + randomOption.getText());
		randomOption.click();
	}

	public void selectpackagingUnit() {
//		packagingUnit
//		packagingUnitAllOptions
//		wait.until(ExpectedConditions.visibilityOf(packagingUnit));
		packagingUnit.click();
//		measurementUnitDropDownAllOptions
		// dropdown options are loaded before proceeding
		if (packagingUnitAllOptions == null || packagingUnitAllOptions.isEmpty()) {
			throw new RuntimeException("No subcategory options found!");
		}

		// randomly pick one option from the list
		Random rand = new Random();
		int randomIndex = rand.nextInt(packagingUnitAllOptions.size());

		WebElement randomOption = packagingUnitAllOptions.get(randomIndex);
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
//	    System.out.println("Selected SubCategory: " + randomOption.getText());
		randomOption.click();
	}

	public void packagingUnitContains() {
//		wait.until(ExpectedConditions.visibilityOf(containsInput));
		Random random = new Random();
		int unitWeight = random.nextInt(25) + 2; // Generates number between 5 and 50 (inclusive)
		containsInput.sendKeys(String.valueOf(unitWeight));

	}

	public void addHSNCodeInput() {
		Random random = new Random();
		int hsnCode = 1000 + random.nextInt(9000); // Generates 1000–9999
		HSNCodeInput.sendKeys(String.valueOf(hsnCode));
	}

	public void addGST_Percentage() {
		Random random = new Random();
		int unitWeight = random.nextInt(12) + 2; // Generates number between 5 and 50 (inclusive)
		GST_Percentage.sendKeys(String.valueOf(unitWeight));
	}

	public void addCess_Percentage() {
		Random random = new Random();
		int unitWeight = random.nextInt(9) + 2; // Generates number between 5 and 50 (inclusive)
		Cess_Percentage.sendKeys(String.valueOf(unitWeight));
	}

	public void addProduct_Description() {
		product_Description.sendKeys("Product Description For Testing");
	}

	public void uploadProductImage() {
//		ProductImage.sendKeys("");
		File file = new File(".\\src\\test\\resources\\Product.png");
		String absolutePath = file.getAbsolutePath();
		ProductImage.sendKeys(absolutePath);
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
	}

	public void clickSubmitButton() {
		submitButton.click();
	}

	public void closeCross() {
		closeCross.click();
	}

//	------------ Call Internal methods  ------------------------------------

	public void addUnit() {
		clickOnAddUnitButton();
		selectpackagingUnit();
		packagingUnitContains(); // containsInput
		SelectPackagingUnitSecond();
	}

}
