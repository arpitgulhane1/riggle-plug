package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

//import pageObjects.Brand_RatePage;
import pageObjects.HomePage;
import pageObjects.User_AddChannelPartnerPage;
import pageObjects.User_AddRopsPage;
import pageObjects.User_AddSalesPersonPage;
import pageObjects.User_ChannelPartners_User;
import pageObjects.User_DashboardPage;
import pageObjects.User_Rops_User;
import pageObjects.User_SalesPerson_User;
import testBase.BaseClass;

public class Tc_User extends BaseClass {

	HomePage homep;
	User_DashboardPage userDashboardPage;
	User_SalesPerson_User salesPersonUserPage;
	User_AddSalesPersonPage addSalesPersonPage;
	User_ChannelPartners_User channelPartnerUserPage;
	User_AddChannelPartnerPage addChannelPartnerPage;
	User_Rops_User userRopsPage;
	User_AddRopsPage userAddRopsPage;

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
			addSalesPersonPage.enterMobileNumber();
			addSalesPersonPage.selectBloodGroup();
			addSalesPersonPage.addDateOfBirth();
			addSalesPersonPage.selectDesignation();
			addSalesPersonPage.selectReportingManager();
			addSalesPersonPage.addHeadquarterCity();
			addSalesPersonPage.addAssignCities();
			addSalesPersonPage.addDateOfJoin();
			addSalesPersonPage.addSalesPersonId();
			addSalesPersonPage.addHomeLocation();
			addSalesPersonPage.clickVanSales();
			addSalesPersonPage.clickHideCPInSalesApp();
			addSalesPersonPage.clickMarkDeliver();
			addSalesPersonPage.clickOnSubmitButton();

		} catch (Exception e) {

		}
	}

	@Test
//    @Test(invocationCount = 3, threadPoolSize = 3)
	public void test_AddMultiple_SalesPerson() {
		try {
			loginToApp();
			HomePage homep = new HomePage(driver);
			User_DashboardPage userDashboardPage = new User_DashboardPage(driver);
			User_SalesPerson_User salesPersonUserPage = new User_SalesPerson_User(driver);
			User_AddSalesPersonPage addSalesPersonPage = new User_AddSalesPersonPage(driver);

			homep.clickOnUsersMenu();
			userDashboardPage.salesPersonMenu();

			int salsePersonCount = 1000;

			for (int i = 1; i <= salsePersonCount; i++) {
				
				Thread.sleep(500);
				salesPersonUserPage.addSalesPerson();
				addSalesPersonPage.addFirstName();
				addSalesPersonPage.addLastName();
				addSalesPersonPage.addEmail();
//				addSalesPersonPage.enterMobileNumber("9123820496");
				addSalesPersonPage.enterMobileNumber();
				addSalesPersonPage.selectBloodGroup();
				addSalesPersonPage.addDateOfBirth();
				addSalesPersonPage.selectDesignation();
//				addSalesPersonPage.selectDesignation("ASM");
				addSalesPersonPage.selectReportingManager();
//				addSalesPersonPage.selectReportingManager("arpit saless (ASM)");
				addSalesPersonPage.addHeadquarterCity();
				addSalesPersonPage.addAssignCities();
				addSalesPersonPage.addDateOfJoin();
				addSalesPersonPage.addSalesPersonId();
				addSalesPersonPage.addHomeLocation();
//				addSalesPersonPage.clickVanSales();
//				addSalesPersonPage.clickHideCPInSalesApp();
				addSalesPersonPage.clickMarkDeliver();
				addSalesPersonPage.clickOnSubmitButton();

				Assert.assertTrue(addSalesPersonPage.verifySalsePersonCreatedSuccessMessage(),
						"Salse person created success message not displayed or mismatched!");
				System.out.println("No === : "+i);
			}

		} catch (Exception e) {

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

	@Test
	public void testAddChannelPartners() {
		try {
			loginToApp();
			HomePage homep = new HomePage(driver);
			User_DashboardPage userDashboardPage = new User_DashboardPage(driver);
			User_ChannelPartners_User channelPartnerUserPage = new User_ChannelPartners_User(driver);
			User_AddChannelPartnerPage addChannelPartnerPage = new User_AddChannelPartnerPage(driver);
			homep.clickOnUsersMenu();
			userDashboardPage.channelPartnerMenu();
			
			
			channelPartnerUserPage.ClickOnChannelPartnersMenu();
			addChannelPartnerPage.enterMobileNumber();
			addChannelPartnerPage.fillPincode();
			addChannelPartnerPage.enterEmail();
			addChannelPartnerPage.enterFirstName();
			addChannelPartnerPage.enterLastName();
			addChannelPartnerPage.enterFirmName();
			addChannelPartnerPage.enterChannelPartnerId();
			addChannelPartnerPage.enterGstNumber();
			addChannelPartnerPage.enterPanNumber();
			addChannelPartnerPage.enterFssaiNumber();
			addChannelPartnerPage.enterFssaiExpiryDate();
			addChannelPartnerPage.fillAddress();
			addChannelPartnerPage.fillLandmark();
			addChannelPartnerPage.getCity();
			addChannelPartnerPage.getState();
			addChannelPartnerPage.clickOnSaveChannelPartnerButton();
			
			Assert.assertTrue(addChannelPartnerPage.verifyChannelPartnerSuccessMessage(),
					"Salse person created success message not displayed or mismatched!");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void test_AddMultiple_ChannelPartners() {
		try {
			loginToApp();
			HomePage homep = new HomePage(driver);
			User_DashboardPage userDashboardPage = new User_DashboardPage(driver);
			User_ChannelPartners_User channelPartnerUserPage = new User_ChannelPartners_User(driver);
			User_AddChannelPartnerPage addChannelPartnerPage = new User_AddChannelPartnerPage(driver);
			homep.clickOnUsersMenu();
			userDashboardPage.channelPartnerMenu();
			
			int CPCount = 3;

			for (int i = 1; i <= CPCount; i++) {
						
			channelPartnerUserPage.ClickOnChannelPartnersMenu();
			addChannelPartnerPage.enterMobileNumber();
			addChannelPartnerPage.fillPincode();
			addChannelPartnerPage.enterEmail();
			addChannelPartnerPage.enterFirstName();
			addChannelPartnerPage.enterLastName();
			addChannelPartnerPage.enterFirmName();
			addChannelPartnerPage.enterChannelPartnerId();
			addChannelPartnerPage.enterGstNumber();
			addChannelPartnerPage.enterPanNumber();
			addChannelPartnerPage.enterFssaiNumber();
			addChannelPartnerPage.enterFssaiExpiryDate();
			addChannelPartnerPage.fillAddress();
			addChannelPartnerPage.fillLandmark();
			addChannelPartnerPage.getCity();
			addChannelPartnerPage.getState();
			addChannelPartnerPage.clickOnSaveChannelPartnerButton();
			
			Assert.assertTrue(addChannelPartnerPage.verifyChannelPartnerSuccessMessage(),
					"Salse person created success message not displayed or mismatched!");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}


	@Test
	public void testAddRopsUser_Runner() {
		try {
			loginToApp();
			HomePage homep = new HomePage(driver);
			User_DashboardPage userDashboardPage = new User_DashboardPage(driver);
			User_Rops_User userRopsPage = new User_Rops_User(driver);
			User_AddRopsPage userAddRopsPage = new User_AddRopsPage(driver);
			homep.clickOnUsersMenu();
			userRopsPage.ClickOnRopsMenu();
			
			userRopsPage.addRopsUser();
			userAddRopsPage.enterFirstName();
			userAddRopsPage.enterLastName("Runner");
			userAddRopsPage.enterMobileNumber();
			userAddRopsPage.selectRunner();
			userAddRopsPage.clickSave();
			
			Assert.assertTrue(userAddRopsPage.verifyRopsSuccessMessage(),
					"Salse person created success message not displayed or mismatched!");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void test_AddMultipleRopsUser_Runner() {
		try {
			loginToApp();
			HomePage homep = new HomePage(driver);
			User_DashboardPage userDashboardPage = new User_DashboardPage(driver);
			User_Rops_User userRopsPage = new User_Rops_User(driver);
			User_AddRopsPage userAddRopsPage = new User_AddRopsPage(driver);
			homep.clickOnUsersMenu();
			userRopsPage.ClickOnRopsMenu();
			
			int count = 1000;

			for (int i = 1; i <= count; i++) {
			userRopsPage.addRopsUser();
			userAddRopsPage.enterFirstName();
			userAddRopsPage.enterLastName("Runner");
			userAddRopsPage.enterMobileNumber();
			userAddRopsPage.selectRunner();
			userAddRopsPage.clickSave();

			Assert.assertTrue(userAddRopsPage.verifyRopsSuccessMessage(),
					"Salse person created success message not displayed or mismatched!");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	@Test
	public void testAddRopsUser_Production() {
		try {
			loginToApp();
			HomePage homep = new HomePage(driver);
			User_DashboardPage userDashboardPage = new User_DashboardPage(driver);
			User_Rops_User userRopsPage = new User_Rops_User(driver);
			User_AddRopsPage userAddRopsPage = new User_AddRopsPage(driver);
			homep.clickOnUsersMenu();
			userRopsPage.ClickOnRopsMenu();
			userRopsPage.addRopsUser();
			
			userAddRopsPage.enterFirstName();
			userAddRopsPage.enterLastName("Production");
			userAddRopsPage.enterMobileNumber();
			userAddRopsPage.selectProduction();
			userAddRopsPage.clickSave();
			Assert.assertTrue(userAddRopsPage.verifyRopsSuccessMessage(),
					"Salse person created success message not displayed or mismatched!");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testAddMultipleRopsUser_Production() {
		try {
			loginToApp();
			HomePage homep = new HomePage(driver);
			User_DashboardPage userDashboardPage = new User_DashboardPage(driver);
			User_Rops_User userRopsPage = new User_Rops_User(driver);
			User_AddRopsPage userAddRopsPage = new User_AddRopsPage(driver);
			homep.clickOnUsersMenu();
			userRopsPage.ClickOnRopsMenu();
			
			int count = 3;

			for (int i = 1; i <= count; i++) { 
			userRopsPage.addRopsUser();
			userAddRopsPage.enterFirstName();
			userAddRopsPage.enterLastName("Production");
			userAddRopsPage.enterMobileNumber();
			userAddRopsPage.selectProduction();
			userAddRopsPage.clickSave();
			Assert.assertTrue(userAddRopsPage.verifyRopsSuccessMessage(),
					"Salse person created success message not displayed or mismatched!");
			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testAddRopsUser_Storage() {
		try {
			loginToApp();
			HomePage homep = new HomePage(driver);
			User_DashboardPage userDashboardPage = new User_DashboardPage(driver);
			User_Rops_User userRopsPage = new User_Rops_User(driver);
			User_AddRopsPage userAddRopsPage = new User_AddRopsPage(driver);
			homep.clickOnUsersMenu();
			userRopsPage.ClickOnRopsMenu();
			userRopsPage.addRopsUser();
			
			userAddRopsPage.enterFirstName();
			userAddRopsPage.enterLastName("Storage");
			userAddRopsPage.enterMobileNumber();
			userAddRopsPage.selectStorage();
			userAddRopsPage.clickSave();
			Assert.assertTrue(userAddRopsPage.verifyRopsSuccessMessage(),
					"Salse person created success message not displayed or mismatched!");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testAddMultipleRopsUser_Storage() {
		try {
			loginToApp();
			HomePage homep = new HomePage(driver);
			User_DashboardPage userDashboardPage = new User_DashboardPage(driver);
			User_Rops_User userRopsPage = new User_Rops_User(driver);
			User_AddRopsPage userAddRopsPage = new User_AddRopsPage(driver);
			homep.clickOnUsersMenu();
			userRopsPage.ClickOnRopsMenu();
			
			int count = 3;

			for (int i = 1; i <= count; i++) {
			userRopsPage.addRopsUser();
			userAddRopsPage.enterFirstName();
			userAddRopsPage.enterLastName("Storage");
			userAddRopsPage.enterMobileNumber();
			userAddRopsPage.selectStorage();
			userAddRopsPage.clickSave();
			Assert.assertTrue(userAddRopsPage.verifyRopsSuccessMessage(),
					"Salse person created success message not displayed or mismatched!");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	
}
