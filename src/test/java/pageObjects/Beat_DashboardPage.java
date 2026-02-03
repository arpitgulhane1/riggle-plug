package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Beat_DashboardPage extends BasePage {
	HomePage homep;

	public Beat_DashboardPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//span[normalize-space()='View Retailers']")
	WebElement retailers_Button;
	
	@FindBy(xpath="//span[normalize-space()='Approvals']")
	WebElement approvals_Button;
	
	@FindBy(xpath="//span[normalize-space()='Add Beat']")
	WebElement add_Beat_Button;
	
	@FindBy(xpath="//input[@placeholder='Retailer Search']")
	WebElement retailer_Search;
	
	public void clickOnRetailers() {
		retailers_Button.click();
			
	}
	
	public void clickOnApprovals() {
		approvals_Button.click();
		
	}
	public void ClickOnAddBeat() {
		add_Beat_Button.click();
		
	}
	public void clickOnRetailerSearch() {
		retailer_Search.click();
		
	}
}

