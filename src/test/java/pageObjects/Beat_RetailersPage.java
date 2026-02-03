package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import testBase.BaseClass;

public class Beat_RetailersPage  extends BasePage{

	public Beat_RetailersPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//td[@class=\"ant-table-cell\"]//img[contains(@src,\"/static/media/orderIcon\")]")
	WebElement placeOrder_Icon;
	
	public void clickOnPlaceOrder_Icon() {
		 wait.until(ExpectedConditions.visibilityOf(placeOrder_Icon));
		    wait.until(ExpectedConditions.elementToBeClickable(placeOrder_Icon));

		    placeOrder_Icon.click();
	
	}
}
