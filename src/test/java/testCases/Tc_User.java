package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Brand_RatePage;
import pageObjects.HomePage;
import pageObjects.User_AddSalesPersonPage;
import pageObjects.User_DashboardPage;
import pageObjects.User_SalesPerson_User;
import testBase.BaseClass;


public class Tc_User extends BaseClass {

	HomePage homep;
	User_DashboardPage userDashboardPage ;
	User_SalesPerson_User salesPersonUserPage;
	User_AddSalesPersonPage addSalesPersonPage;
	
	@Test
	public void testAddSalesPerson() {
		try {
			 loginToApp();
	            HomePage homep = new HomePage(driver);
	            User_DashboardPage userDashboardPage = new User_DashboardPage(driver);
	            User_SalesPerson_User salesPersonUserPage = new User_SalesPerson_User(driver);
	            User_AddSalesPersonPage addSalesPersonPage = new User_AddSalesPersonPage(driver);
	            homep.clickOnUsersMenu();
	            userDashboardPage.salesPersonMenu();
	            salesPersonUserPage.addSalesPerson();
	            addSalesPersonPage.addFirstName();
	            addSalesPersonPage.addLastName();
	            addSalesPersonPage.addEmail();
	            addSalesPersonPage.addMobileNumber();
	            addSalesPersonPage.selectBloodGroup();
	            addSalesPersonPage.addDateOfBirth();
	            addSalesPersonPage.selectDesignation();
	            addSalesPersonPage.selectReportingManager();
	            addSalesPersonPage.addHeadquarterCity();
	            addSalesPersonPage.addAssignCities();
	            addSalesPersonPage.addDateOfJoin();
	            addSalesPersonPage.addSalesPersonId();
	            addSalesPersonPage.addHomeLocation();
//	            addSalesPersonPage.clickVanSales();
//	            addSalesPersonPage.clickHideCPInSalesApp();
//	            addSalesPersonPage.clickMarkDeliver();
//	            addSalesPersonPage.clickSave();
	            
		}catch (Exception e) {
      
        }
	}
	@Test

	public void test_verifysalesPersonDetails() {
		try {
			loginToApp();
            HomePage homep = new HomePage(driver);
            User_DashboardPage userDashboardPage = new User_DashboardPage(driver);
			User_SalesPerson_User salesPersonUserPage = new User_SalesPerson_User(driver);
			homep.clickOnUsersMenu();
	        userDashboardPage.salesPersonMenu();
			salesPersonUserPage.searchSalesPerson();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

}
