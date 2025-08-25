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
	    public void testAddNewBrand_ShouldCreateBrandSuccessfully() {
	        try {
	            loginToApp();
	            HomePage homep = new HomePage(driver);
	            homep.clickOnBrandsMenu();

	            BrandsPage bpage = new BrandsPage(driver);
	            bpage.clickOnAddBrand();
	            bpage.uploadBrandPhoto();
	            testBrandName = TestDataGenerator.getRandomBrandName();  // Get and store random brand name
	            bpage.addBrandName(testBrandName);
	            bpage.enterCityName();
	            bpage.saveNewBrand();
	            
	            bpage.clickOnCloseAddBrandTemplate();
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
	    public void testSearchBrand_AddNewProduct_VerifyProductSuccessfully() {
	        try {
	            loginToApp();
	            HomePage homep = new HomePage(driver);
	            ProductsPage prodPage = new ProductsPage(driver);
	            BrandsPage bpage = new BrandsPage(driver);
	            
	            homep.clickOnBrandsMenu();

//	            bpage.setBrandName(testBrandName); // Inject brand name to page
//	            bpage.setBrandName("Tesla"); // Remove after test run
	            bpage.searchBrand();
	            
	            
//	            Assert.assertTrue(bpage.isBrandDisplayed(), "❌ Brand not displayed: " + testBrandName);    // check same value which is add as brand 
	            bpage.clickOnBrandProduct();
	            
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
	            prodPage.selectMeasurementUnitDropDown();
	            prodPage.uploadProductImage();
	            prodPage.uploadProduct_CatalogImage();
	            // Add Unit Code
//	            prodPage.addUnit();
//	            prodPage.clickOnAddUnitButton();
//	            prodPage.selectpackagingUnit();
//	            prodPage.packagingUnitContains(); 	//containsInput
	            
	            // GST Details 
	            prodPage.addHSNCodeInput();
	            prodPage.addGST_Percentage();
	            prodPage.addCess_Percentage();
	            prodPage.addProduct_Description();
	            prodPage.clickSubmitButton();
	           
	            Assert.assertTrue(prodPage.verifyProductCreatedSuccessMessage(), 
	                    "Product created success message not displayed or mismatched!");
	            
	            prodPage.searchProduct();
	            prodPage.clickOnEditProductIcon();
			   Assert.assertTrue(prodPage.isEditProductPageTitleMatchingProductName(),"Edit Product page title text is not matching!");
			   
			   Assert.assertTrue(prodPage.areProductDetailsValid(), "Product details from UI do not match.");
			   
	            System.out.println("Brand search and product click successful.");
	        } catch (Exception e) {
	            System.err.println("❌ Error in brand search/product click: " + e.getMessage());
	            Assert.fail("Exception in testSearchBrandAndClickProduct: " + e.getMessage());
	        }
	    }
	    
	    @Test
	    public void testSearchBrand_EditProduct_VerifyProductSuccessfully() {
	    		loginToApp();
			   ProductsPage prodPage = new ProductsPage(driver);
			   HomePage homep = new HomePage(driver);
			   BrandsPage bpage = new BrandsPage(driver);
			   
			   homep.clickOnBrandsMenu();
			   bpage.searchBrand();
			   bpage.clickOnBrandProduct();
			   prodPage.searchProduct();
			   prodPage.clickOnEditProductIcon();
			   
			   Assert.assertTrue(prodPage.isEditProductPageTitleMatchingProductName(),"Edit Product page title text is not matching!");
			   
			   
			   
			   prodPage.editProductName();
			   prodPage.editProductMRP();
			   prodPage.editProductCode();
			   prodPage.editProductCategory();
			   prodPage.editProductSubCategory();
			   prodPage.clickProductAimCheckboxRandomly();
			   prodPage.editInputBestBefore();
			   prodPage.selectExpiryUnitDropDown();
			   prodPage.editProductUnitWT();
			   prodPage.selectMeasurementUnitDropDown();
			   prodPage.uploadProductImage();
			   prodPage.uploadProduct_CatalogImage();

//			// Add Unit Code
//	            prodPage.addUnit();
//	            prodPage.clickOnAddUnitButton();
//	            prodPage.selectpackagingUnit();
//	            prodPage.packagingUnitContains(); 
			   
			   // GST Details
			   prodPage.editHSNCodeInput();
			   prodPage.editGST_Percentage();
			   prodPage.editCess_Percentage();
			   prodPage.editProduct_Description();

			   
			   
			   
//			   Assert.assertTrue(productPage.areProductDetailsValid(), "Product details from UI do not match."); 
	    }
	    
	   @Test
	   public void verifyProductDetails() {
		   loginToApp();
		   ProductsPage productPage = new ProductsPage(driver);
		   HomePage homep = new HomePage(driver);
		   BrandsPage bpage = new BrandsPage(driver);
		   
		   homep.clickOnBrandsMenu();
		   bpage.searchBrand();
		   bpage.clickOnBrandProduct();
		   productPage.searchProduct();
		   productPage.clickOnEditProductIcon();
		   Assert.assertTrue(productPage.isEditProductPageTitleMatchingProductName(),"Edit Product page title text is not matching!");
		   
		   Assert.assertTrue(productPage.areProductDetailsValid(), "Product details from UI do not match."); 
	   }
	    
	    

	
}
