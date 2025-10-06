package pageObjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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



}
