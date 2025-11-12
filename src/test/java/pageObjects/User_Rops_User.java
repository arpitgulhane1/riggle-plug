package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class User_Rops_User extends BasePage{
	public User_Rops_User (WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
//	
	@FindBy(xpath="//strong[normalize-space()='R-Ops']")
	WebElement rOps;
	
	@FindBy(xpath="//span[normalize-space()='Add Ops User']")
	WebElement addRopsUser;
	
	
	
	public void ClickOnRopsMenu() {
		rOps.click();
		
	}
	public void addRopsUser() {
		addRopsUser.click();
	}
}
