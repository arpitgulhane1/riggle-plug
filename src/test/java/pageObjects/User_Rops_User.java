package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class User_Rops_User extends BasePage{
	public User_Rops_User (WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
//	
	@FindBy(xpath="//strong[normalize-space()='R-Ops']")
	WebElement rOps;
	
	@FindBy(xpath="//span[normalize-space()='Add R-Ops User']")
	WebElement addRopsUser;
	
	
	
	public void ClickOnRopsMenu() {
		rOps.click();
		
	}
	public void addRopsUser() {
		wait.until(ExpectedConditions.elementToBeClickable(addRopsUser));
		addRopsUser.click();
	}
}
