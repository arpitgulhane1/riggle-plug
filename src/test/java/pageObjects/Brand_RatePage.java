package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Brand_RatePage extends BasePage {

	public Brand_RatePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//h2[contains(text(),'No Products added for')]")
	private WebElement noProductsAddedMessage;

	@FindBy(xpath = "//button[@type='button']")
	private WebElement backButtonOnNoProductScreen;

	@FindBy(xpath = "//span[normalize-space()='Add Rate Structure']")
	private WebElement addRateStructureButton;

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

}
