package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Beat_DashboardPage;
import pageObjects.Beat_Retailer_OrderFlowBrandsPage;
import pageObjects.Beat_Retailer_OrderFlowBrands_ProductPage;
import pageObjects.Beat_Retailer_OrderFlowBrands_Product_CartPage;
import pageObjects.Beat_RetailersPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class Beat_Retailers_Order extends BaseClass {
	HomePage homep;
	  Beat_Retailer_OrderFlowBrandsPage beatRetailerOrderFlowBrandPage ;
	    Beat_Retailer_OrderFlowBrands_ProductPage BeatRetailerOrderFlowBrandsProductPage;
	
	
	@Test
	public void test_Retailers_Order() throws InterruptedException {
		try {
	    loginToApp();    
	    HomePage homep = new HomePage(driver);
	    Beat_DashboardPage beatPage = new Beat_DashboardPage(driver);
	    Beat_RetailersPage beatRetailerPage = new Beat_RetailersPage(driver);
	    Beat_Retailer_OrderFlowBrandsPage beatRetailerOrderFlowBrandPage = new Beat_Retailer_OrderFlowBrandsPage(driver);
	    Beat_Retailer_OrderFlowBrands_ProductPage BeatRetailerOrderFlowBrandsProductPage = new Beat_Retailer_OrderFlowBrands_ProductPage(driver);
	    Beat_Retailer_OrderFlowBrands_Product_CartPage BeatRetailerOrderFlowBrandsProductCartPage = new  Beat_Retailer_OrderFlowBrands_Product_CartPage(driver);
	    homep.clickOnBeatMenu();
	    beatPage.clickOnRetailers();
	    beatRetailerPage.clickOnPlaceOrder_Icon();
	    beatRetailerOrderFlowBrandPage.searchAndClickBrand("Notebook");
	 
	    BeatRetailerOrderFlowBrandsProductPage.printBrandName();
	    
	    BeatRetailerOrderFlowBrandsProductPage.selectRandomProductAndPrintDetails();
	 // ========= ASSERTS =========
	    Assert.assertEquals(
	    		BeatRetailerOrderFlowBrandsProductPage.qtyAfter,
	    		BeatRetailerOrderFlowBrandsProductPage.expectedQty,
	            "❌ Qty did not increase by MOQ"
	    );

	    Assert.assertEquals(
	    		BeatRetailerOrderFlowBrandsProductPage.calculatedSet,
	    		BeatRetailerOrderFlowBrandsProductPage.qtyAfter / BeatRetailerOrderFlowBrandsProductPage.moq,
	            "❌ Set calculation mismatch"
	    );

	    Assert.assertEquals(
	    		BeatRetailerOrderFlowBrandsProductPage.uiTotalAfter,
	            BeatRetailerOrderFlowBrandsProductPage.expectedTotal,
	            0.01,
	            "❌ Total calculation mismatch"
	    );

	    Assert.assertEquals(
	    		BeatRetailerOrderFlowBrandsProductPage.getCartCount(),
	    		BeatRetailerOrderFlowBrandsProductPage.getAddedProductCount(),
	            "❌ Cart count mismatch"
	    );

	  
	    System.out.println("🎯 Retailer order flow PASSED");
	

	    BeatRetailerOrderFlowBrandsProductPage.getCartCount();
	    BeatRetailerOrderFlowBrandsProductPage.getAddedProductCount();
	    BeatRetailerOrderFlowBrandsProductPage.verifyCartCountMatchesProducts();
	    BeatRetailerOrderFlowBrandsProductPage.clickOnCart();
	    BeatRetailerOrderFlowBrandsProductCartPage.clickOnPlaceOrder();
	    BeatRetailerOrderFlowBrandsProductCartPage.enterOrderRemark();
	    BeatRetailerOrderFlowBrandsProductCartPage.clickOnPlaceOrderSecond();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	      
	
	}
	
	@Test
	public void test_Retailer_Order_AddOn_CashDiscount_FixedAmount() throws InterruptedException {
		try {
		loginToApp();    
	    HomePage homep = new HomePage(driver);
	    Beat_DashboardPage beatPage = new Beat_DashboardPage(driver);
	    Beat_RetailersPage beatRetailerPage = new Beat_RetailersPage(driver);
	    Beat_Retailer_OrderFlowBrandsPage beatRetailerOrderFlowBrandPage = new Beat_Retailer_OrderFlowBrandsPage(driver);
	    Beat_Retailer_OrderFlowBrands_ProductPage BeatRetailerOrderFlowBrandsProductPage = new Beat_Retailer_OrderFlowBrands_ProductPage(driver);
	    Beat_Retailer_OrderFlowBrands_Product_CartPage BeatRetailerOrderFlowBrandsProductCartPage = new  Beat_Retailer_OrderFlowBrands_Product_CartPage(driver);
	    homep.clickOnBeatMenu();
	    beatPage.clickOnRetailers();
	    beatRetailerPage.clickOnPlaceOrder_Icon();
	    beatRetailerOrderFlowBrandPage.searchAndClickBrand("Notebook");
	 
	    BeatRetailerOrderFlowBrandsProductPage.selectRandomProductAndPrintDetails();
	    BeatRetailerOrderFlowBrandsProductPage.getCartCount();
	    BeatRetailerOrderFlowBrandsProductPage.getAddedProductCount();
	    BeatRetailerOrderFlowBrandsProductPage.verifyCartCountMatchesProducts();
	    BeatRetailerOrderFlowBrandsProductPage.clickOnCart();
	    BeatRetailerOrderFlowBrandsProductCartPage.clickOnAddOn();
	    BeatRetailerOrderFlowBrandsProductCartPage.clickOnProductCashDiscountAddOn();
	
	    
//	    BeatRetailerOrderFlowBrandsProductCartPage.clickOnEnterAmount();
//	    BeatRetailerOrderFlowBrandsProductCartPage.getProductTotalAmount();
	    BeatRetailerOrderFlowBrandsProductCartPage.enterRandomIntegerDiscountLessThanTotalOnlyOnce();
	    BeatRetailerOrderFlowBrandsProductCartPage.addAddOnRemark();
	    BeatRetailerOrderFlowBrandsProductCartPage.clickOnAddOnSave();
	    double originalTotal =  BeatRetailerOrderFlowBrandsProductCartPage.getProductTotalAmount();
	    BeatRetailerOrderFlowBrandsProductCartPage.getUpdatedProductTotalAmount(originalTotal);
	    
	    BeatRetailerOrderFlowBrandsProductCartPage.validateFinalTotalAfterDiscount();
	    
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		
	    
	}
	
	@Test
	public void test_Retailer_Order_AddOn_CashDiscount_Percentage() {
		try {
			loginToApp();    
		    HomePage homep = new HomePage(driver);
		    Beat_DashboardPage beatPage = new Beat_DashboardPage(driver);
		    Beat_RetailersPage beatRetailerPage = new Beat_RetailersPage(driver);
		    Beat_Retailer_OrderFlowBrandsPage beatRetailerOrderFlowBrandPage = new Beat_Retailer_OrderFlowBrandsPage(driver);
		    Beat_Retailer_OrderFlowBrands_ProductPage BeatRetailerOrderFlowBrandsProductPage = new Beat_Retailer_OrderFlowBrands_ProductPage(driver);
		    Beat_Retailer_OrderFlowBrands_Product_CartPage BeatRetailerOrderFlowBrandsProductCartPage = new  Beat_Retailer_OrderFlowBrands_Product_CartPage(driver);
		    homep.clickOnBeatMenu();
		    beatPage.clickOnRetailers();
		    beatRetailerPage.clickOnPlaceOrder_Icon();
		    beatRetailerOrderFlowBrandPage.searchAndClickBrand("Notebook");
		   
		    BeatRetailerOrderFlowBrandsProductPage.selectRandomProductAndPrintDetails();
		    BeatRetailerOrderFlowBrandsProductPage.getCartCount();
		    BeatRetailerOrderFlowBrandsProductPage.getAddedProductCount();
		    BeatRetailerOrderFlowBrandsProductPage.verifyCartCountMatchesProducts();
		    BeatRetailerOrderFlowBrandsProductPage.clickOnCart();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnAddOn();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnProductCashDiscountAddOn();
		    BeatRetailerOrderFlowBrandsProductCartPage.ClickOnPercentage();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnPercentageInput_Field();
		    BeatRetailerOrderFlowBrandsProductCartPage.addAddOnRemark();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnAddOnSave();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void test_Retailer_Order_AddOn_Sample_FixedAmount() {
		try {
			
			loginToApp();    
		    HomePage homep = new HomePage(driver);
		    Beat_DashboardPage beatPage = new Beat_DashboardPage(driver);
		    Beat_RetailersPage beatRetailerPage = new Beat_RetailersPage(driver);
		    Beat_Retailer_OrderFlowBrandsPage beatRetailerOrderFlowBrandPage = new Beat_Retailer_OrderFlowBrandsPage(driver);
		    Beat_Retailer_OrderFlowBrands_ProductPage BeatRetailerOrderFlowBrandsProductPage = new Beat_Retailer_OrderFlowBrands_ProductPage(driver);
		    Beat_Retailer_OrderFlowBrands_Product_CartPage BeatRetailerOrderFlowBrandsProductCartPage = new  Beat_Retailer_OrderFlowBrands_Product_CartPage(driver);
		    homep.clickOnBeatMenu();
		    beatPage.clickOnRetailers();
		    beatRetailerPage.clickOnPlaceOrder_Icon();
		    beatRetailerOrderFlowBrandPage.searchAndClickBrand("Notebook");
		 
		    BeatRetailerOrderFlowBrandsProductPage.selectRandomProductAndPrintDetails();
		    BeatRetailerOrderFlowBrandsProductPage.getCartCount();
		    BeatRetailerOrderFlowBrandsProductPage.getAddedProductCount();
		    BeatRetailerOrderFlowBrandsProductPage.verifyCartCountMatchesProducts();
		    BeatRetailerOrderFlowBrandsProductPage.clickOnCart();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnAddOn();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnProductSampleAddOn();
		    BeatRetailerOrderFlowBrandsProductCartPage.enterRandomIntegerDiscountLessThanTotalOnlyOnce();
		    BeatRetailerOrderFlowBrandsProductCartPage.addAddOnRemark();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnAddOnSave();
		    double originalTotal =  BeatRetailerOrderFlowBrandsProductCartPage.getProductTotalAmount();
		    BeatRetailerOrderFlowBrandsProductCartPage.getUpdatedProductTotalAmount(originalTotal);
		    
		    BeatRetailerOrderFlowBrandsProductCartPage.validateFinalTotalAfterDiscount();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@Test
	public void test_Retailer_Order_AddOn_Sample_Percentage() {
		try {
			loginToApp();    
		    HomePage homep = new HomePage(driver);
		    Beat_DashboardPage beatPage = new Beat_DashboardPage(driver);
		    Beat_RetailersPage beatRetailerPage = new Beat_RetailersPage(driver);
		    Beat_Retailer_OrderFlowBrandsPage beatRetailerOrderFlowBrandPage = new Beat_Retailer_OrderFlowBrandsPage(driver);
		    Beat_Retailer_OrderFlowBrands_ProductPage BeatRetailerOrderFlowBrandsProductPage = new Beat_Retailer_OrderFlowBrands_ProductPage(driver);
		    Beat_Retailer_OrderFlowBrands_Product_CartPage BeatRetailerOrderFlowBrandsProductCartPage = new  Beat_Retailer_OrderFlowBrands_Product_CartPage(driver);
		    homep.clickOnBeatMenu();
		    beatPage.clickOnRetailers();
		    beatRetailerPage.clickOnPlaceOrder_Icon();
		    beatRetailerOrderFlowBrandPage.searchAndClickBrand("Notebook");
		 
		    BeatRetailerOrderFlowBrandsProductPage.selectRandomProductAndPrintDetails();
		    BeatRetailerOrderFlowBrandsProductPage.getCartCount();
		    BeatRetailerOrderFlowBrandsProductPage.getAddedProductCount();
		    BeatRetailerOrderFlowBrandsProductPage.verifyCartCountMatchesProducts();
		    BeatRetailerOrderFlowBrandsProductPage.clickOnCart();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnAddOn();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnProductSampleAddOn();
		    BeatRetailerOrderFlowBrandsProductCartPage.ClickOnPercentage();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnPercentageInput_Field();
		    BeatRetailerOrderFlowBrandsProductCartPage.addAddOnRemark();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnAddOnSave();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void test_Retailer_Order_AddOn_Display_FixedAmount() {
		try {
			
			loginToApp();    
		    HomePage homep = new HomePage(driver);
		    Beat_DashboardPage beatPage = new Beat_DashboardPage(driver);
		    Beat_RetailersPage beatRetailerPage = new Beat_RetailersPage(driver);
		    Beat_Retailer_OrderFlowBrandsPage beatRetailerOrderFlowBrandPage = new Beat_Retailer_OrderFlowBrandsPage(driver);
		    Beat_Retailer_OrderFlowBrands_ProductPage BeatRetailerOrderFlowBrandsProductPage = new Beat_Retailer_OrderFlowBrands_ProductPage(driver);
		    Beat_Retailer_OrderFlowBrands_Product_CartPage BeatRetailerOrderFlowBrandsProductCartPage = new  Beat_Retailer_OrderFlowBrands_Product_CartPage(driver);
		    homep.clickOnBeatMenu();
		    beatPage.clickOnRetailers();
		    beatRetailerPage.clickOnPlaceOrder_Icon();
		    beatRetailerOrderFlowBrandPage.searchAndClickBrand("Notebook");
		
		    BeatRetailerOrderFlowBrandsProductPage.selectRandomProductAndPrintDetails();
		    BeatRetailerOrderFlowBrandsProductPage.getCartCount();
		    BeatRetailerOrderFlowBrandsProductPage.getAddedProductCount();
		    BeatRetailerOrderFlowBrandsProductPage.verifyCartCountMatchesProducts();
		    BeatRetailerOrderFlowBrandsProductPage.clickOnCart();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnAddOn();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnProductDisplayAddOn();
		    BeatRetailerOrderFlowBrandsProductCartPage.enterRandomIntegerDiscountLessThanTotalOnlyOnce();
		    BeatRetailerOrderFlowBrandsProductCartPage.addAddOnRemark();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnAddOnSave();
		    double originalTotal =  BeatRetailerOrderFlowBrandsProductCartPage.getProductTotalAmount();
		    BeatRetailerOrderFlowBrandsProductCartPage.getUpdatedProductTotalAmount(originalTotal);
		    
		    BeatRetailerOrderFlowBrandsProductCartPage.validateFinalTotalAfterDiscount();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@Test
	public void test_Retailer_Order_AddOn_Display_Percentage() {
		try {
			loginToApp();    
		    HomePage homep = new HomePage(driver);
		    Beat_DashboardPage beatPage = new Beat_DashboardPage(driver);
		    Beat_RetailersPage beatRetailerPage = new Beat_RetailersPage(driver);
		    Beat_Retailer_OrderFlowBrandsPage beatRetailerOrderFlowBrandPage = new Beat_Retailer_OrderFlowBrandsPage(driver);
		    Beat_Retailer_OrderFlowBrands_ProductPage BeatRetailerOrderFlowBrandsProductPage = new Beat_Retailer_OrderFlowBrands_ProductPage(driver);
		    Beat_Retailer_OrderFlowBrands_Product_CartPage BeatRetailerOrderFlowBrandsProductCartPage = new  Beat_Retailer_OrderFlowBrands_Product_CartPage(driver);
		    homep.clickOnBeatMenu();
		    beatPage.clickOnRetailers();
		    beatRetailerPage.clickOnPlaceOrder_Icon();
		    beatRetailerOrderFlowBrandPage.searchAndClickBrand("Notebook");
		 
		    BeatRetailerOrderFlowBrandsProductPage.selectRandomProductAndPrintDetails();
		    BeatRetailerOrderFlowBrandsProductPage.getCartCount();
		    BeatRetailerOrderFlowBrandsProductPage.getAddedProductCount();
		    BeatRetailerOrderFlowBrandsProductPage.verifyCartCountMatchesProducts();
		    BeatRetailerOrderFlowBrandsProductPage.clickOnCart();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnAddOn();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnProductDisplayAddOn();
		    BeatRetailerOrderFlowBrandsProductCartPage.ClickOnPercentage();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnPercentageInput_Field();
		    BeatRetailerOrderFlowBrandsProductCartPage.addAddOnRemark();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnAddOnSave();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void test_Retailer_Order_AddOn_ProductScheme() {
		try {
			loginToApp();    
		    HomePage homep = new HomePage(driver);
		    Beat_DashboardPage beatPage = new Beat_DashboardPage(driver);
		    Beat_RetailersPage beatRetailerPage = new Beat_RetailersPage(driver);
		    Beat_Retailer_OrderFlowBrandsPage beatRetailerOrderFlowBrandPage = new Beat_Retailer_OrderFlowBrandsPage(driver);
		    Beat_Retailer_OrderFlowBrands_ProductPage BeatRetailerOrderFlowBrandsProductPage = new Beat_Retailer_OrderFlowBrands_ProductPage(driver);
		    Beat_Retailer_OrderFlowBrands_Product_CartPage BeatRetailerOrderFlowBrandsProductCartPage = new  Beat_Retailer_OrderFlowBrands_Product_CartPage(driver);
		    homep.clickOnBeatMenu();
		    beatPage.clickOnRetailers();
		    beatRetailerPage.clickOnPlaceOrder_Icon();
//		    beatRetailerOrderFlowBrandPage.searchAndClickBrand("Notebook");
		    beatRetailerOrderFlowBrandPage.searchAndClickBrand("Coca Cola");
//		    beatRetailerOrderFlowBrandPage.selectRandomBrand();
//		    beatRetailerOrderFlowBrandPage.selectBrandUntilProductFound();
//		    beatRetailerOrderFlowBrandPage.selectRandomBrandWithEnabledProduct();
//		    BeatRetailerOrderFlowBrandsProductPage.selectRandomEnabledProductAndPrintDetails();
//		    BeatRetailerOrderFlowBrandsProductPage.selectRandomProductAndPrintDetails();
		    BeatRetailerOrderFlowBrandsProductPage.getCartCount();
		    BeatRetailerOrderFlowBrandsProductPage.getAddedProductCount();
		    BeatRetailerOrderFlowBrandsProductPage.verifyCartCountMatchesProducts();
		    BeatRetailerOrderFlowBrandsProductPage.clickOnCart();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnAddOn();
		    
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnProduct_ProductSchemeAddOn();
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnSelectBrand();
//		    beatRetailerOrderFlowBrandPage.selectBrandUntilProductFound();
		    BeatRetailerOrderFlowBrandsProductCartPage.selectBrandProdcutScheme();
		    
		    BeatRetailerOrderFlowBrandsProductCartPage.selectBrandProductScheme("Onida");
		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnSelectProduct_ProductScheme();
		    BeatRetailerOrderFlowBrandsProductCartPage.selectRandomProductScheme();
		    BeatRetailerOrderFlowBrandsProductCartPage.isProductSchemeEmpty();
		    BeatRetailerOrderFlowBrandsProductCartPage.enterRandomFreeProductQuantity();
		    BeatRetailerOrderFlowBrandsProductCartPage.enterRandomProductSchemeRemark();
//		    BeatRetailerOrderFlowBrandsProductCartPage.addAddOnRemark();
//		    BeatRetailerOrderFlowBrandsProductCartPage.clickOnAddOnSave();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
}
