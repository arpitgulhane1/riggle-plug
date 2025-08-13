package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//img[@alt='Potluck Technologies Pvt Ltd. logo']")
	WebElement riggleLogo;
	@FindBy(xpath = "//ul[@class='ant-menu-overflow ant-menu ant-menu-root ant-menu-horizontal ant-menu-light ant-dropdown-trigger d-flex align-item-center']")
	WebElement user_MenuItem_List;
	@FindBy(xpath = "//img[@alt='icon']")
	WebElement announcements_Icon;
	@FindBy(xpath = "//ul[@class='ant-menu-overflow ant-menu ant-menu-root ant-menu-horizontal ant-menu-light mt-2']//li[@role='menuitem']")
	WebElement trainingVideos;
	@FindBy(xpath = "//span[@aria-label='bell']//*[name()='svg']")
	WebElement notification;
	@FindBy(xpath = "//span[@aria-label='menu-fold']//*[name()='svg']")
	WebElement menuToggleButton;

	@FindBy(xpath = "//span[contains(text(),'Sign Out')]")
	WebElement signOutButton;

	// menu
	@FindBy(xpath = "//a[@href='/v1/r-plug/dashboard']")
	WebElement dashboard;
	@FindBy(xpath = "//a[@href='/v1/r-plug/brands']")
	WebElement brands;
	@FindBy(xpath = "//li//span[text()='Users']") // take time
	WebElement users;
	@FindBy(xpath = "//li//span[text()='My HR']") // take time
	WebElement MyHR;
	@FindBy(xpath = "//a[@href='/v1/r-plug/target-and-acheivement']")
	WebElement Target;
	@FindBy(xpath = "//a[@href='/v1/r-plug/arena']")
	WebElement Arena;
	@FindBy(xpath = "//li//span[text()='Orders']") // take time
	WebElement Orders;
	@FindBy(xpath = "//a[@href='/v1/r-plug/asset']")
	WebElement Asset;
	@FindBy(xpath = "//a[@href='/v1/r-plug/beat']")
	WebElement Beat;
	@FindBy(xpath = "//a[@href='/v1/r-plug/reports']")
	WebElement Reports;
	@FindBy(xpath = "//a[@href='/v1/r-plug/wallet-usage']")
	WebElement Wallet;
	@FindBy(xpath = "a[@href='/v1/r-plug/communication']")
	WebElement Communication;
	@FindBy(xpath = "a[@href='/v1/r-plug/inventory']")
	WebElement Inventory;
	@FindBy(xpath = "a[@href='/v1/r-plug/tally']")
	WebElement Tally;

	public boolean verifyLogo() {
		boolean ststus = riggleLogo.isDisplayed();
		return ststus;
	}

	public void clickOnUserMenuList() {
		user_MenuItem_List.click();
	}

	public void clickOnAnnouncementsIcon() {
		announcements_Icon.click();
	}

	public void clickOnTrainingVideos() {
		trainingVideos.click();
	}

	public void clickOnSignOutButton() {
//		clickOnUserMenuList();
		signOutButton.click();
	}

//	--------------------------------------------------

	public void clickOnBrandsMenu() {
//		try {
//			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("ul.ant-dropdown-menu")));
//			wait.until(ExpectedConditions.elementToBeClickable(brands));
//			brands.click();
//		} catch (Exception e) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", brands);
//		}
	}

	public void clickOnDashboardMenu() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", dashboard);
	}

	public void clickOnMyHRMenu() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", MyHR);
	}

	public void clickOnTargetMenu() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Target);
	}

	public void clickOnArenaMenu() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Arena);
	}

	public void clickOnOrdersMenu() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Orders);
	}

	public void clickOnAssetMenu() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Asset);
	}

	public void clickOnBeatMenu() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Beat);
	}

	public void clickOnReportsMenu() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Reports);
	}

	public void clickOnWalletMenu() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Wallet);
	}

	public void clickOnUsersMenu() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", users);
	}

	public void clickOnCommunicationMenu() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Communication);
	}

	public void clickOnInventoryMenu() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Inventory);
	}

	public void clickOnTallyMenu() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Tally);
	}

}
