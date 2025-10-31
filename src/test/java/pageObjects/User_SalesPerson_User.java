package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utility.BrandUtility;

public class User_SalesPerson_User extends BasePage {
	public User_SalesPerson_User(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
//	private static String selectedSalesPersonName; 		
	  
	 @FindBy(xpath ="//span[normalize-space()='Add Sales Person']")
	 WebElement addSalesPerson;
	 @FindBy(xpath = "//span[@class='ant-input-affix-wrapper']")
	 WebElement searchSalesPerson;
//	
	
	  public void addSalesPerson() {
		  addSalesPerson.click();
	  }
	public void searchSalesPerson() {
		searchSalesPerson.click();
	}
	public String getSalesPersonName() {
		String SalesPersonName = BrandUtility.readJson("", "SalesPersonName");
		return SalesPersonName;
	}


}
