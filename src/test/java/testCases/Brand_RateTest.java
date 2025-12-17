package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Brand_RatePage;
import pageObjects.BrandsPage;
import pageObjects.HomePage;
import pageObjects.ProductsPage;
import testBase.BaseClass;
import utility.TestDataGenerator;

public class Brand_RateTest extends BaseClass {

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
//        bpage.searchBrand();
        bpage.searchBrandByName("Adobee");
//        bpage.clickOnRateBrandPage();
        brand_RatePage.clickOnRate();
//        brand_RatePage.isNoProductsAddedMessageDisplay();
//        brand_RatePage.clickOnBackButtonWhenNoProduct();
        brand_RatePage.clickOnAddRateStructureButton();
        brand_RatePage.clickOnNewRateStructure();
        brand_RatePage.clickOnAddRateStructureButton();
//        brand_RatePage.duplicateRateStructure();
      
        
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
//	        bpage.searchBrandByName("A New Brand");
	        brand_RatePage.clickOnRate();
	        brand_RatePage.clickOnchannelPartner();
	        brand_RatePage.getChannelPartnerName();
	        brand_RatePage.clickOnEditChannelPartnerName();
		}
		catch(Exception e) {
			
		}
	}
		@Test
		public void verifyProductListInRate() {
			try {
				 loginToApp();
				  HomePage homep = new HomePage(driver);
			        homep.clickOnBrandsMenu();
			        BrandsPage bpage = new BrandsPage(driver);   
			        Brand_RatePage brand_RatePage = new Brand_RatePage(driver);
			        bpage.searchBrand();
	//		        bpage.searchBrandByName("A New Brand");
			        brand_RatePage.clickOnRate();
			        System.out.println("✅ Entering sequential rates for available products...");
			        brand_RatePage.getAllProductName();
	
			}catch (Exception e) {
			        e.printStackTrace();
			        Assert.fail("❌ Test failed due to exception: " + e.getMessage());
			    }
		}
		
		@Test
		public void testEditFirstCpRate() throws InterruptedException {
			try {
				loginToApp();
				  HomePage homep = new HomePage(driver);
			        homep.clickOnBrandsMenu();
			        BrandsPage bpage = new BrandsPage(driver);   
			        ProductsPage prodPage = new ProductsPage(driver);
			        Brand_RatePage brand_RatePage = new Brand_RatePage(driver);
			        bpage.searchBrand();
			        brand_RatePage.clickOnRate();
			       
			        
//			        brand_RatePage.clickOnchannelPartner();
			        brand_RatePage.clickOnEditChannelPartnerName();
			        brand_RatePage. searchProduct();
			        
			        double mrp = 100.0;  // or fetch dynamically
			        brand_RatePage.editAllCpRatesAndMoq(mrp);
			        
			}catch (Exception e) {
				
			}
		}
		
		
		@Test
		public void verifyRateStructureDetails() throws InterruptedException {
//		    RateStructurePage page = new RateStructurePage(driver);
			loginToApp();
			  HomePage homep = new HomePage(driver);
		        homep.clickOnBrandsMenu();
		        BrandsPage bpage = new BrandsPage(driver);   
		        ProductsPage prodPage = new ProductsPage(driver);
		        Brand_RatePage brand_RatePage = new Brand_RatePage(driver);
//		        bpage.searchBrandByName("Beer Ferry And Kris"); // 1 rate
//		        bpage.searchBrandByName("Parisian Inc"); //4 Rate
		        bpage.searchBrandByName("Amul"); //2 rate
//		        bpage.searchBrandByName("Adobe"); //3 rate
		        brand_RatePage.clickOnRate();
			
			    
//		        brand_RatePage.updateAllRateStructures();
//		        brand_RatePage.updateAllRateStructuresRowWise();
		        brand_RatePage.updateAllRateStructuresRowWise_IfRateIsZero();
//		        brand_RatePage.updateAllRateStructures();
		}

		
		
		
	
}