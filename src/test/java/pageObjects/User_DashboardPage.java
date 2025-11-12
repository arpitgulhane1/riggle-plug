package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class User_DashboardPage extends BasePage {
	HomePage homep;
	User_DashboardPage userDashboardPage ;
	User_SalesPerson_User salesPersonUserPage;
	User_AddSalesPersonPage addSalesPersonPage;
	
	public User_DashboardPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//div[contains(@class, 'ant-col') and contains(@class, 'ant-col-md-6')]//strong[normalize-space()='Sales Persons']")
	  WebElement salesPersonsMenu;
	
	@FindBy(xpath="//div[contains(@class, 'ant-col') and contains(@class, 'ant-col-md-6')]//strong[normalize-space()='Channel Partners']")
	WebElement channelPartnersMenu; 
	
	@FindBy(xpath="//div[contains(@class, 'ant-col') and contains(@class, 'ant-col-md-6')]//strong[normalize-space()='R-Ops']")
	WebElement rOpsMenu;

	  public void salesPersonMenu() {
	  salesPersonsMenu.click();
}
	  
	  public void channelPartnerMenu() {
			channelPartnersMenu.click();
		}
	
	
}
