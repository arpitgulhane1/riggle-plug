package pageObjects;

import java.io.File;
import java.io.FileReader;
//import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utility.BrandUtility;

public class BrandsPage extends BasePage {

	private static String[] brands;
	 private static String selectedBrandName; 			//  Cached random brand name

//	private String selectedBrandName ="Tata";

	public BrandsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='search']")
	WebElement searchBrand;
	@FindBy(xpath = "//span[normalize-space()='Add New Brand']")
	WebElement addNewBrand;
	@FindBy(xpath = "//input[@type='file']")
	WebElement uploadBrandPhoto;
	@FindBy(xpath = "//input[@id='nest-messages_name']")
	WebElement brandName;
	@FindBy(xpath = "//input[@id='nest-messages_cities']")
	WebElement enterCityName;
	@FindBy(xpath = "//span[@aria-label='close']") WebElement closeAddBrandTemplate;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement saveNewBrand;

	@FindBy(xpath = ".//span[contains(text(),'Product')]")
	WebElement brandProduct;
	@FindBy(xpath = ".//span[contains(text(),'Rate')]")
	WebElement brandRate;
	@FindBy(xpath = "//div[contains(@class,'ant-empty-description')]")
	WebElement noBrandDataPresent;
	@FindBy(xpath = "//div[contains(@class, 'ant-col ant-col-xs-24')]")
	WebElement brandContainerData;
	
	@FindBy(xpath = "//div[@class='ant-card ant-card-bordered ant-card-hoverable']//h4")
	WebElement brandAlreadyAdded;

	
	
//	Actions act = new Actions(driver);
//	act.keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
//	driver.findElement(By.xpath("//div[@id='rc_unique_0']")).click();

	public void searchBrand() {
		
		searchBrand.clear();
		String bradName = BrandUtility.readJson("Brand", "BrandName");
		searchBrand.sendKeys(bradName);
//		wait.until(ExpectedConditions.visibilityOf(selectedBrandName));
		try {
			wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOf(brandContainerData),
					ExpectedConditions.visibilityOf(noBrandDataPresent)));
		} catch (TimeoutException e) {
			throw new AssertionError("No brand result or empty message found within wait time.");
		}
	}

//	public void setBrandName(String name) {
//		this.selectedBrandName = name;
//	}
	
	public void clickOnCloseAddBrandTemplate() {
		closeAddBrandTemplate.click();
		
	}

	public boolean isBrandDisplayed() {
		return brandContainerData.isDisplayed() && brandContainerData.getText().contains(selectedBrandName);
	}

	public void clickOnAddBrand() {
	    wait.until(ExpectedConditions.elementToBeClickable(addNewBrand));
	    addNewBrand.click();
	}


	public void uploadBrandPhoto() {
		File file = new File(".\\src\\test\\resources\\BrandLogo1.png");
		String absolutePath = file.getAbsolutePath();
		uploadBrandPhoto.sendKeys(absolutePath);
	}

	public void addBrandName(String newbrandName) {
		selectedBrandName = newbrandName;
		brandName.sendKeys(newbrandName);
	}

	public void enterCityName() {
		enterCityName.sendKeys("Mumbai");
		Actions act = new Actions(driver);
		act.keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
		driver.findElement(By.xpath("//div[@id='rc_unique_0']")).click();

		enterCityName.sendKeys("Pune");
		act.keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
		driver.findElement(By.xpath("//div[@id='rc_unique_0']")).click();
	}

	public void saveNewBrand() {
		saveNewBrand.click();
		System.out.println("selectedBrandName = "+ selectedBrandName);
		BrandUtility.writeJson("Brand", "BrandName", selectedBrandName);				
	}

	public void clickOnBrandProduct() {
		wait.until(ExpectedConditions.visibilityOf(brandProduct));
		String bradName = BrandUtility.readJson("Brand", "BrandName");
		wait.until(driver -> brandAlreadyAdded.getText().equalsIgnoreCase(bradName));
		
		if (brandAlreadyAdded.getText().equalsIgnoreCase(bradName)) {
			Actions action = new Actions(driver);
			action.keyDown(Keys.CONTROL)
			.click(brandProduct)
			.keyUp(Keys.CONTROL)
			.build()
			.perform();
			List<String> tabsId = new ArrayList<>(driver.getWindowHandles());
			
			if (tabsId.size() < 2) {
				throw new RuntimeException("Brand not Found New tab did not open as expected");
			}
			driver.switchTo().window(tabsId.get(1));
		}
	}
	
	public void clickOnRateBrandPage() {
		wait.until(ExpectedConditions.visibilityOf(brandRate));
		String bradName = BrandUtility.readJson("Brand", "BrandName");
		wait.until(driver -> brandAlreadyAdded.getText().equalsIgnoreCase(bradName));

		if (brandAlreadyAdded.getText().equalsIgnoreCase(bradName)) {
		  Actions action = new Actions(driver);
		    action.keyDown(Keys.CONTROL)
		          .click(brandRate)
		          .keyUp(Keys.CONTROL)
		          .build()
		          .perform();
		    List<String> tabsId = new ArrayList<>(driver.getWindowHandles());
		    
		    if (tabsId.size() < 2) {
		        throw new RuntimeException("Brand not Found New tab did not open as expected");
		    }
		    driver.switchTo().window(tabsId.get(1));
		}
	}

	
	
	public String getBrandName() {
		if (selectedBrandName == null) {
			try (FileReader reader = new FileReader(".\\src\\test\\resources\\brands.properties")) {
				Properties prop = new Properties();
				prop.load(reader);
				String brandString = prop.getProperty("brands");
				String[] brands = brandString.split(",");
				selectedBrandName = brands[new Random().nextInt(brands.length)];
			} catch (IOException e) {
				throw new RuntimeException("Failed to read brand names from properties file", e);
			}
		}
		
		return selectedBrandName;
	}

}
