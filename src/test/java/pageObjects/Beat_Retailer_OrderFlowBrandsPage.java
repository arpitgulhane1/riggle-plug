package pageObjects;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Beat_Retailer_OrderFlowBrandsPage extends BasePage {

    Beat_Retailer_OrderFlowBrands_ProductPage 
        BeatRetailerOrderFlowBrandsProductPage =
            new Beat_Retailer_OrderFlowBrands_ProductPage(driver);

    public Beat_Retailer_OrderFlowBrandsPage(WebDriver driver) {
        super(driver);
    }

    // 🔍 Search box
    @FindBy(xpath = "//input[@placeholder='Search brands']")
    WebElement searchBrand;

    // 📦 All visible brand cards
    @FindBy(xpath = "//h4[contains(@class,'font-weight-semibold')]")
    List<WebElement> brandList;

    /* ============================================================
       1️⃣ MAIN METHOD – SELECT BRAND UNTIL PRODUCT FOUND
       ============================================================ */
    public void selectBrandUntilProductFound() {

        int attempt = 0;
        int maxAttempts = 100;

        try {
            while (attempt < maxAttempts) {
                attempt++;
                System.out.println("\n🔁 Brand attempt: " + attempt);

                String brandName = selectRandomBrand();
                System.out.println("📦 Trying brand: " + brandName);

                // ❌ No product case
                if (BeatRetailerOrderFlowBrandsProductPage.isNoProductAvailable()) {

                    System.out.println("❌ No product found, going back");

                    BeatRetailerOrderFlowBrandsProductPage.clickBackArrow();
                    wait.until(ExpectedConditions.visibilityOf(searchBrand));
                    continue;
                }

                // ✅ Product found
                if (BeatRetailerOrderFlowBrandsProductPage.isProductDisplayed()) {
                    System.out.println("✅ Product found for brand: " + brandName);
                    return;
                }
            }

            throw new RuntimeException("❌ No brand found with products after retries");

        } catch (Exception e) {
            System.out.println("🚨 Error in selectBrandUntilProductFound()");
            e.printStackTrace();
            throw e;
        }
    }

    /* ============================================================
       2️⃣ RANDOM BRAND SELECTION
       ============================================================ */
    public String selectRandomBrand() {

        try {
            System.out.println("🎲 Selecting random brand");

            wait.until(ExpectedConditions.elementToBeClickable(searchBrand));
            searchBrand.clear();

            wait.until(driver -> brandList.size() > 0);

            if (brandList.isEmpty()) {
                throw new RuntimeException("❌ Product list is empty");
            }

            Random random = new Random();
            int index = random.nextInt(brandList.size());

            WebElement randomBrand = brandList.get(index);
            String selectedBrandName = randomBrand.getText().trim();

            System.out.println("📦 Random brand selected: " + selectedBrandName);

            randomBrand.click();
            System.out.println("🖱️ Clicked on brand: " + selectedBrandName);

            return selectedBrandName;

        } catch (Exception e) {
            System.out.println("🚨 Failed to select random brand");
            e.printStackTrace();
            throw e;
        }
    }

    /* ============================================================
       3️⃣ SEARCH AND CLICK SPECIFIC BRAND
       ============================================================ */
    public void searchAndClickBrand(String brandName) {

        try {
            System.out.println("🔍 Searching brand: " + brandName);

            wait.until(ExpectedConditions.elementToBeClickable(searchBrand));
            searchBrand.clear();
            searchBrand.sendKeys(brandName);

            By brandLocator =
                    By.xpath("//h4[normalize-space()='" + brandName + "']");

            WebElement brand = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(brandLocator)
            );

            System.out.println("✅ Brand found: " + brand.getText());
            brand.click();
            System.out.println("🖱️ Clicked on brand: " + brandName);

        } catch (TimeoutException e) {
            System.out.println("❌ Brand NOT found: " + brandName);
            throw new RuntimeException("Brand not found: " + brandName);
        }
    }

    /* ============================================================
       4️⃣ OPTIONAL PAGE WAIT (SAFE)
       ============================================================ */
//    private void waitForPageLoad(int seconds) {
//        try {
//            Thread.sleep(seconds * 1000L);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }
    
    public void selectRandomBrandWithEnabledProduct() {
        int attempt = 0;
        int maxAttempts = 100; // retry limit
        Random random = new Random();

        while (attempt < maxAttempts) {
            attempt++;
            System.out.println("\n🔁 Brand attempt: " + attempt);

            // 🎲 Select random brand (this page)
            String brandName = this.selectRandomBrand();
            System.out.println("📦 Brand selected: " + brandName);

            // Wait for products to load in ProductPage
            try {
                // Wait until either products appear or no product message
                wait.until(driver -> BeatRetailerOrderFlowBrandsProductPage.isProductDisplayed()
                                      || BeatRetailerOrderFlowBrandsProductPage.isNoProductAvailable());
            } catch (Exception e) {
                System.out.println("⚠ Timeout waiting for products");
            }

            // ❌ Case 1: No products
            if (BeatRetailerOrderFlowBrandsProductPage.isNoProductAvailable()) {
                System.out.println("❌ No products found, clicking back");
                BeatRetailerOrderFlowBrandsProductPage.clickBackArrow();
                continue;
            }

            // ❌ Case 2: All + icons disabled
            List<WebElement> enabledPlusList = BeatRetailerOrderFlowBrandsProductPage.driver.findElements(
                By.xpath("//div[contains(@style,'rgb(252, 140, 77)') " +
                         "and contains(@style,'border-radius: 6px')]//*[normalize-space()='+']" +
                         "[not(contains(@style,'not-allowed'))]")
            );

            if (enabledPlusList.isEmpty()) {
                System.out.println("❌ All + icons disabled, clicking back");
                BeatRetailerOrderFlowBrandsProductPage.clickBackArrow();
                continue;
            }

            // ✅ Brand has at least one product with + enabled
            System.out.println("✅ Brand has enabled + icon, proceeding: " + brandName);
            return;
        }

        throw new RuntimeException("❌ No brand with products and enabled + icon found after retries");
    }


}
