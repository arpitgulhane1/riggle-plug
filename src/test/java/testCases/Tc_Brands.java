package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BrandsPage;
import pageObjects.HomePage;
import pageObjects.ProductsPage;
import testBase.BaseClass;
import utility.TestDataGenerator;

public class Tc_Brands extends BaseClass {
	 private String testBrandName;
	 
	 @Test
	    public void testAddNewBrand() {
	        try {
	            loginToApp();
	            HomePage homep = new HomePage(driver);
	            homep.clickOnBrandsMenu();

	            BrandsPage bpage = new BrandsPage(driver);
//	            bpage.clickOnAddBrand();
//	            bpage.uploadBrandPhoto();
//	            testBrandName = TestDataGenerator.getRandomBrandName();  // Get and store random brand name
//	            bpage.addBrandName(testBrandName);
//	            bpage.enterCityName();
//	            bpage.saveNewBrand();
//	            
//	            bpage.clickOnCloseAddBrandTemplate();
	            bpage.searchBrand();
	            bpage.clickEditNewBrand();
	            Assert.assertTrue(bpage.verifyAddBrandDetail()); 
	            
	            System.out.println("Brand added successfully: " + testBrandName);
	        } catch (Exception e) {
	            System.err.println("❌ Failed to add brand: " + e.getMessage());
	            Assert.fail("Exception in testAddNewBrand: " + e.getMessage());
	        }
	    }

	    @Test
	    public void testSearchBrandAndClickProduct2() {
	    	for (int j=0 ; j<5 ; j++) {
	    	testSearchBrandAndClickProduct();
	    	}
	    }
	    
	    @Test
	    public void testSearchBrandAndClickProduct() {
	        try {
	            loginToApp();
	            HomePage homep = new HomePage(driver);
	            ProductsPage prodPage = new ProductsPage(driver);
	            BrandsPage bpage = new BrandsPage(driver);
	            
	            homep.clickOnBrandsMenu();

//	            bpage.setBrandName(testBrandName); // Inject brand name to page
//	            bpage.setBrandName("Tesla"); // Remove after test run
	            bpage.searchBrand();
	            bpage.clickOnBrandProduct();
	            
	            Assert.assertTrue(bpage.isBrandDisplayed(), "❌ Brand not displayed: " + testBrandName);    // check same value which is add as brand 
	           
	            prodPage.addProductButton();
	            prodPage.addProductName();
	            prodPage.addProductMRP();
	            prodPage.addProductCode();
	            prodPage.addProductCategory();
	            prodPage.addProductSubCategory();
	            prodPage.clickProductAimCheckboxRandomly();
	            prodPage.addInputBestBefore();
	            prodPage.selectExpiryUnitDropDown();
	            prodPage.addProductUnitWT();
	            Thread.sleep(2000);
	            prodPage.selectMeasurementUnitDropDown();
	            Thread.sleep(2000);
	            prodPage.uploadProductImage();
	            Thread.sleep(2000);
	            prodPage.uploadProduct_CatalogImage();

	            Thread.sleep(2000);
	            // Add Unit Code
//	            prodPage.addUnit();
//	            prodPage.clickOnAddUnitButton();
//	            prodPage.selectpackagingUnit();
//	            prodPage.packagingUnitContains(); 	//containsInput
//	            prodPage.SelectPackagingUnitSecond();
	            
	            // GST Details 
	            prodPage.addHSNCodeInput();
	            prodPage.addGST_Percentage();
	            prodPage.addCess_Percentage();
	            prodPage.addProduct_Description();
	            prodPage.clickSubmitButton();
	           
	            
	            System.out.println("Brand search and product click successful.");
	        } catch (Exception e) {
	            System.err.println("❌ Error in brand search/product click: " + e.getMessage());
	            Assert.fail("Exception in testSearchBrandAndClickProduct: " + e.getMessage());
	        }
	    }
	    
	    
	    
	    
	    // test
	    
	    
	    

	
}
