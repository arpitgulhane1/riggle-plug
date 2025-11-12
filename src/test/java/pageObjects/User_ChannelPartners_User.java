package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class User_ChannelPartners_User extends BasePage {
	public User_ChannelPartners_User(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	@FindBy(xpath="//span[normalize-space()='Add Channel Partner']")
	WebElement channelPartners;
	
	@FindBy(xpath = "//span[@class='ant-input-affix-wrapper']")
	WebElement searchChannelPartner;
	
	
	public void ClickOnChannelPartnersMenu() {
		wait.until(ExpectedConditions.elementToBeClickable(channelPartners));
		channelPartners.click();
	}
	
	public void searchChannelPartner() {
		searchChannelPartner.click();
	}
}
