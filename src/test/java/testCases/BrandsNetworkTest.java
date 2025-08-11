package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Brand_RatePage;
import pageObjects.BrandsPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utility.TestDataGenerator;

public class BrandsNetworkTest extends BaseClass{

	BrandsPage bpage ;
	HomePage homep ;
	Brand_RatePage brand_RatePage;
	
	 @Test
	    public void TC_AddNewBrandAndVerifyNetworkPaheEmptyMessage() {
	        try {
	            loginToApp();
	            HomePage homep = new HomePage(driver);
	            brand_RatePage = new Brand_RatePage(driver);
	            homep.clickOnBrandsMenu();

	            BrandsPage bpage = new BrandsPage(driver);
	            bpage.clickOnAddBrand();
	            bpage.uploadBrandPhoto();
	            String testBrandName = TestDataGenerator.getRandomBrandName();  // Get and store random brand name
	            bpage.addBrandName(testBrandName);
	            bpage.enterCityName();
	            bpage.saveNewBrand();
	            bpage.clickOnCloseAddBrandTemplate();
	            
	            bpage.searchBrand();
	            bpage.clickOnRateBrandPage();
	            brand_RatePage.isNoProductsAddedMessageDisplay();
	            brand_RatePage.clickOnBackButtonWhenNoProduct();
	            
//	            System.out.println("Brand added successfully: " + testBrandName);
	        } catch (Exception e) {
	            System.err.println("❌ Failed to add brand: " + e.getMessage());
	            Assert.fail("Exception in testAddNewBrand: " + e.getMessage());
	        }
	    }
	
	
	
}
