package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Brand_RatePage;
import pageObjects.BrandsPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utility.TestDataGenerator;

public class Brand_Rate extends BaseClass {

	BrandsPage bpage;
	HomePage homep;
	Brand_RatePage brand_RatePage;

	@Test

   public void testAddRateStructure() {
    try {
        loginToApp();
        HomePage homep = new HomePage(driver);
        homep.clickOnBrandsMenu();
        BrandsPage bpage = new BrandsPage(driver);   
        Brand_RatePage brand_RatePage = new Brand_RatePage(driver);
        bpage.searchBrand();
//        bpage.clickOnRateBrandPage();
        brand_RatePage.clickOnRate();
//        brand_RatePage.isNoProductsAddedMessageDisplay();
//        brand_RatePage.clickOnBackButtonWhenNoProduct();
        brand_RatePage.clickOnAddRateStructureButton();
        brand_RatePage.clickOnNewRateStructure();
        brand_RatePage.clickOnAddRateStructureButton();
        brand_RatePage.duplicateRateStructure();
      
        
//        System.out.println("Brand added successfully: " + testBrandName);
    } catch (Exception e) {
        System.err.println("❌ Failed to add brand: " + e.getMessage());
        Assert.fail("Exception in testAddNewBrand: " + e.getMessage());
    }
}
	@Test
	public void testAddNewChannelPartner(){
		try {
		 loginToApp();
	        HomePage homep = new HomePage(driver);
	        homep.clickOnBrandsMenu();
	        BrandsPage bpage = new BrandsPage(driver);   
	        Brand_RatePage brand_RatePage = new Brand_RatePage(driver);
	        bpage.searchBrand();
	        brand_RatePage.clickOnRate();
//	        brand_RatePage.clickOnchannelPartner();
	        brand_RatePage.getChannelPartnerName();
	        brand_RatePage.clickOnEditChannelPartnerName();
		}
		catch(Exception e) {
			
		}
	}
	
}