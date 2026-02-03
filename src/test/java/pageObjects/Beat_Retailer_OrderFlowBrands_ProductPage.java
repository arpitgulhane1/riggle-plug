package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Random;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class Beat_Retailer_OrderFlowBrands_ProductPage extends BasePage {

	public Beat_Retailer_OrderFlowBrands_ProductPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	 public int qtyBefore;
	    public int qtyAfter;
	    public int expectedQty;
	    public int moq;
	    public int calculatedSet;
	    public double uiTotalAfter;
	    public double expectedTotal;

	
	 @FindBy(xpath="//p[@class='mb-0']")
	 WebElement noProductAvailable;
		
	 @FindBy(xpath="//span[@aria-label='arrow-left']")
	 WebElement backArrow;
	    
	@FindBy(xpath="//div[contains(@class,'ant-col') and contains(@class,'ant-col-xs-24')]")
	List<WebElement> totalProductCount;
	
	@FindBy(xpath ="//div[contains(@style,'rgb(252, 140, 77)') and contains(@style,'border-radius: 6px')]//*[normalize-space()='+'][not(contains(@style,'not-allowed'))]")
	WebElement enabledPlusIcon;
	
	@FindBy(xpath ="//div[contains(@style,'rgb(252, 140, 77)') and contains(@style,'border-radius: 6px')]//*[normalize-space()='+'][not(contains(@style,'not-allowed'))]")
	List<WebElement> enabledPlusIconList;


	@FindBy(xpath="//*[contains(@style,'not-allowed')]")
	WebElement disabledText;
	@FindBy(xpath="//*[contains(@style,'not-allowed')]")
	List<WebElement> disabledTextList;

	// BRAND NAME
    @FindBy(xpath = "//body/div[@id='root']//div[contains(@class,'container-fluid')]/div[1]/div[1]/div[1]/div[1]")
    WebElement brandNames;

    // PRODUCT NAME
    @FindBy(xpath = "//div[@class='ant-image']//img[@alt]")
    List<WebElement> productNames;
 // PRODUCT NAME
    @FindBy(xpath = "//div[@class='ant-image']//img[@alt]")
    WebElement productName;
  
    
//    @FindBy(xpath="//div[starts-with(normalize-space(), 'MRP:')]")
//    WebElement mrp;
    // MRP 
    @FindBy(xpath = "//div[contains(text(),'MRP')]")
    WebElement mrp;
    @FindBy(xpath = "//div[contains(text(),'MRP')]")
    List<WebElement> mrpList;
////    
////    //MOQ 
//    @FindBy(xpath="//div[contains(.,'MOQ')]/text()[contains(., 'MOQ')]")
//    int moq;
//    @FindBy(xpath="//div[contains(.,'MOQ')]/text()[contains(., 'MOQ')]")
//    List<WebElement> moqList;

    // QTY input
    @FindBy(xpath = "//span[contains(text(),'Qty')]/following::input[1]")
    WebElement qtyInput;
    @FindBy(xpath = "//span[contains(text(),'Qty')]/following::input[1]")
    List<WebElement> qtyInputList;

    // RATE input
    @FindBy(xpath = "//span[contains(text(),'Rate')]/following::input[1]")
    WebElement rateInput;
    @FindBy(xpath = "//span[contains(text(),'Rate')]/following::input[1]")
    List<WebElement> rateInputList;

    @FindBy(xpath = "//span[contains(text(),'Qty')]/following::input[1][@value!='0'][1]")
    List<WebElement> getQTYInputList_inCart;
  
    @FindBy(xpath = "//div[normalize-space() = '+']")
    List<WebElement> plusButtons;

    @FindBy(xpath = "//div[normalize-space() = '-']")
    List<WebElement> minusButtons;
    

    // SET and TOTAL
    @FindBy(xpath = "//div[contains(text(),'Set')]")
    WebElement setValues;
    @FindBy(xpath = "//div[contains(text(),'Set')]")
    List<WebElement> setValuesList;

    @FindBy(xpath = "//span[contains(text(),'Total:')]]")
    WebElement totalValues;
    @FindBy(xpath = "//span[contains(text(),'Total:')]")
    List<WebElement> totalValuesList;
    
    @FindBy(xpath="//span[normalize-space()='Cart']")
    WebElement cart;
    
    @FindBy(xpath="//span[@class='ant-scroll-number-only-unit current']")
    WebElement cartCount;
    


    // ✅ WAIT UNTIL PRODUCT PAGE RESPONSE
    public boolean isNoProductAvailable() {
        try {
            wait.until(ExpectedConditions.visibilityOf(noProductAvailable));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isProductDisplayed() {
        try {
            wait.until(driver -> totalProductCount.size() > 0);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickBackArrow() {
        wait.until(ExpectedConditions.elementToBeClickable(backArrow)).click();
    }




    public void printProductAndBrandDetails() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;

    	 try {
    	        // 👉 Print brand name
    	        System.out.println("Brand Name : " + brandNames.getText().trim());

    	        // 👉 Scroll till bottom (easy version)
    	        int count = 0;
    	        while (true) {
    	            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    	            Thread.sleep(600);

    	            int newCount = totalProductCount.size();
    	            if (newCount == count) {  // no new product loaded
    	                break;
    	            }
    	            count = newCount;
    	        }

    	        // 👉 Print total products
    	        System.out.println("Total Products Loaded : " + count);

    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	}
    
 // ================= BRAND =================
    public void printBrandName() {
        System.out.println("Brand Name: " + brandNames.getText());
    }
    
    
    
    // ================= MAIN LOGIC =================
    public void selectRandomProductAndPrintDetails() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Random random = new Random();

        js.executeScript("window.scrollTo(0,0)");
        Thread.sleep(800);

        int totalProducts = totalProductCount.size();
        int index = random.nextInt(totalProducts);

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                totalProductCount.get(index)
        );
        Thread.sleep(800);

        String productName = productNames.get(index).getAttribute("alt").trim();
        String mrpText = mrpList.get(index).getText().trim();

        // ===== MOQ EXTRACT =====
        moq = Integer.parseInt(
                mrpText.replaceAll(".*MOQ\\s*:?\\s*", "")
                       .replaceAll("[^0-9]", "")
        );

        qtyBefore = Integer.parseInt(
                qtyInputList.get(index).getAttribute("value").trim()
        );

        double rate = Double.parseDouble(
                rateInputList.get(index).getAttribute("value").trim()
        );

        // ===== BEFORE PRINT =====
        System.out.println("\n🔹 BEFORE + CLICK 🔹");
        System.out.println("✅ Product : " + productName);
        System.out.println("💰 " + mrpText);
        System.out.println("📦 Qty    : " + qtyBefore);
        System.out.println("📦 MOQ    : " + moq);
        System.out.println("💵 Rate   : " + rate);
        System.out.println("🧮 Set    : " + (qtyBefore / moq));
        System.out.println("🧮 Total  : ₹" + (qtyBefore * rate));

        // ===== CLICK + (MOQ BASED) =====
        js.executeScript("arguments[0].click();", plusButtons.get(index));
        Thread.sleep(1000);

        qtyAfter = Integer.parseInt(
                qtyInputList.get(index).getAttribute("value").trim()
        );

        expectedQty = qtyBefore + moq;
        calculatedSet = qtyAfter / moq;
        expectedTotal = qtyAfter * rate;

        // round to 2 decimal
        expectedTotal = Math.round(expectedTotal * 100.0) / 100.0;

        String setAndTotalText = setValuesList.get(index).getText().trim();

        uiTotalAfter = Double.parseDouble(
                setAndTotalText.replaceAll(".*Total:\\s*₹?", "")
                               .replaceAll(",", "")
                               .trim()
        );

        // ===== AFTER PRINT =====
        System.out.println("\n🔹 AFTER + CLICK 🔹");
        System.out.println("📦 Qty After : " + qtyAfter);
        System.out.println("📦 Expected  : " + expectedQty);
        System.out.println("🧮 Set After : " + calculatedSet);
        System.out.println("🧮 UI Total  : ₹" + uiTotalAfter);
        System.out.println("🧮 Expected  : ₹" + expectedTotal);
    }


    
    public int getCartCount() {

        try {
            String cartText = cartCount.getText().trim();   // Cart 2 / Cart(2)
            String number = cartText.replaceAll("\\D+", "");
            return number.isEmpty() ? 0 : Integer.parseInt(number);
        } catch (Exception e) {
            return 0;
        }
    }

    public int getAddedProductCount() {

//        int count = 0;
        
        return getQTYInputList_inCart.size();
        
//        for (WebElement qty : qtyInputList) {
//            try {
//                int value = Integer.parseInt(qty.getAttribute("value"));
//                if (value > 0) {
//                    count++;
//                }
//            } catch (Exception e) {
//                // ignore empty or invalid fields
//            }
//        }
//        return count;
    }

    public void verifyCartCountMatchesProducts() {

        int expectedProducts = getAddedProductCount();
        int actualCartCount = getCartCount();

        System.out.println("🧮 Products Added : " + expectedProducts);
        System.out.println("🛒 Cart Count              : " + actualCartCount);

        if (expectedProducts == actualCartCount) {
            System.out.println("✅ Cart count VERIFIED");
        } else {
            System.out.println("❌ Cart count MISMATCH");
        }
    }
    
    public void clickOnCart() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Scroll Cart into view (top-right)
            js.executeScript("arguments[0].scrollIntoView({block:'center', inline:'end'});", cart);
            Thread.sleep(500);

            // Click using JS to avoid overlay issues
            js.executeScript("arguments[0].click();", cart);

            System.out.println("🛒 Cart clicked successfully");

        } catch (Exception e) {
            System.out.println("❌ Failed to click Cart");
            e.printStackTrace();
        }
    }

//	public void waitUntilProductPageLoads1() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void waitUntilProductPageLoads() {
//		// TODO Auto-generated method stub
//		
//	}


    public void selectRandomEnabledProductAndPrintDetails() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Random random = new Random();

        wait.until(driver -> totalProductCount.size() > 0);

        // collect enabled + only
        List<Integer> enabledIndexes = new ArrayList<>();

        for (int i = 0; i < totalProductCount.size(); i++) {
            if (!plusButtons.get(i).getAttribute("style").contains("not-allowed")) {
                enabledIndexes.add(i);
            }
        }

        if (enabledIndexes.isEmpty()) {
            throw new RuntimeException("❌ No enabled + product found");
        }

        int index = enabledIndexes.get(random.nextInt(enabledIndexes.size()));

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                totalProductCount.get(index)
        );

        // ===== READ DATA BEFORE CLICK =====
        String productName = productNames.get(index).getAttribute("alt").trim();
        String mrpText = mrpList.get(index).getText().trim();

        int moq = Integer.parseInt(
                mrpText.replaceAll(".*MOQ\\s*:?\\s*", "")
                       .replaceAll("[^0-9]", "")
        );

        int qtyBefore = Integer.parseInt(
                qtyInputList.get(index).getAttribute("value").trim()
        );

        double rate = Double.parseDouble(
                rateInputList.get(index).getAttribute("value").trim()
        );

        System.out.println("\n🔹 BEFORE + CLICK 🔹");
        System.out.println("📦 Product : " + productName);
        System.out.println("💰 " + mrpText);
        System.out.println("📦 Qty     : " + qtyBefore);
        System.out.println("📦 MOQ     : " + moq);
        System.out.println("🧮 Set     : " + (qtyBefore / moq));
        System.out.println("💵 Rate    : " + rate);

        // ===== CLICK + =====
        wait.until(ExpectedConditions.elementToBeClickable(plusButtons.get(index)));
        js.executeScript("arguments[0].click();", plusButtons.get(index));

        // ===== VERIFY QTY UPDATED =====
        wait.until(driver ->
            Integer.parseInt(
                qtyInputList.get(index).getAttribute("value")
            ) > qtyBefore
        );

        int qtyAfter = Integer.parseInt(
                qtyInputList.get(index).getAttribute("value").trim()
        );

        int expectedQty = qtyBefore + moq;
        int calculatedSet = qtyAfter / moq;
        double expectedTotal = Math.round(qtyAfter * rate * 100.0) / 100.0;

        String setAndTotalText = setValuesList.get(index).getText().trim();
        double uiTotalAfter = Double.parseDouble(
                setAndTotalText.replaceAll(".*Total:\\s*₹?", "")
                               .replaceAll(",", "")
                               .trim()
        );

        System.out.println("\n🔹 AFTER + CLICK 🔹");
        System.out.println("📦 Qty After : " + qtyAfter);
        System.out.println("📦 Expected  : " + expectedQty);
        System.out.println("🧮 Set After : " + calculatedSet);
        System.out.println("🧮 UI Total  : ₹" + uiTotalAfter);
        System.out.println("🧮 Expected  : ₹" + expectedTotal);

       
    }
    

    
    

}
   

  










