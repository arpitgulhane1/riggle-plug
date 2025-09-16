package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Brand_NetworkPage;
import pageObjects.Brand_RatePage;
import pageObjects.BrandsPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utility.TestDataGenerator;
public class BrandsNetworkTest extends BaseClass {
	BrandsPage bpage;
	HomePage homep;
	Brand_RatePage brand_RatePage;
	Brand_NetworkPage brand_NetworkPage;
	
	
	@Test
	public void TC_AddNewBrandAndVerifyNetworkEmptyMessage() {
		try {
			loginToApp();
			HomePage homep = new HomePage(driver);
			Brand_RatePage brand_RatePage = new Brand_RatePage(driver);
			
			Brand_NetworkPage brand_NetworkPage = new Brand_NetworkPage(driver);
			BrandsPage bpage = new BrandsPage(driver);
			homep.clickOnBrandsMenu();
//			bpage.clickOnAddBrand();
//			bpage.uploadBrandPhoto();
//			String testBrandName = TestDataGenerator.getRandomBrandName(); // Get and store random brand name
//			bpage.addBrandName(testBrandName);
//			bpage.enterCityName();
//			bpage.saveNewBrand();
//			bpage.clickOnCloseAddBrandTemplate();
			
			bpage.searchBrand();
			bpage.clickOnNetworkBrandPage();
			brand_NetworkPage.clickOnAssignChannelPartner();
			brand_NetworkPage.enterChannelPartnerName();
			brand_NetworkPage.enterChannelPartnerRole();
			brand_NetworkPage.clickOnLinkSeller();
			brand_NetworkPage.enterLinkSellerName();
			brand_NetworkPage.enterRateStructureName();
//			brand_NetworkPage.enterAssignSalesPersonName();
//			brand_NetworkPage.clickOnAssignButton();
//			brand_NetworkPage.clickOnSearchBuyerName();
			brand_NetworkPage.clickOnAssignButton();
			
//	            System.out.println("Brand added successfully: " + testBrandName);
		} catch (Exception e) {
			System.err.println(":x: Failed to add brand: " + e.getMessage());
			Assert.fail("Exception in testAddNewBrand: " + e.getMessage());
		}
	}
	
	public void verifyNetworkDetails() {
		
		
	}
	
	@Test
	public void testEmptyFieldsValidation() {
		loginToApp();
		HomePage homep = new HomePage(driver);
		brand_RatePage = new Brand_RatePage(driver);
	
		BrandsPage bpage = new BrandsPage(driver);
		Brand_NetworkPage brand_NetworkPage = new Brand_NetworkPage(driver);
		homep.clickOnBrandsMenu();
		bpage.searchBrand();
		bpage.clickOnNetworkBrandPage();
		brand_NetworkPage.clickOnAssignChannelPartner();
		
//		brand_NetworkPage.enterAssignSalesPersonName();
//		brand_NetworkPage.enterAssignSalesPersonName();
		brand_NetworkPage.enterChannelPartnerRole();
		brand_NetworkPage.enterLinkSellerName();
		brand_NetworkPage.enterRateStructureName();
		brand_NetworkPage.clickOnAssignButton(); // don’t fill anything
//	Assert.assertTrue(brand_NetworkPage.isPopMessageDisplayed());


	}
	
	
	@Test
	public void testVerifyAssignChannelPartnerDetails() {
		loginToApp();
		HomePage homep = new HomePage(driver);
		brand_RatePage = new Brand_RatePage(driver);
		BrandsPage bpage = new BrandsPage(driver);
		Brand_NetworkPage brand_NetworkPage = new Brand_NetworkPage(driver);
		homep.clickOnBrandsMenu();
		bpage.searchBrand();
		bpage.clickOnNetworkBrandPage();
		brand_NetworkPage.clickOnSearchBuyerName();
		
		 boolean status = brand_NetworkPage.verifyAssignChannelPartnerDetails();
		    Assert.assertTrue(status, " Channel Partner details did not match any entry!");
		
		
	}
	
	
}