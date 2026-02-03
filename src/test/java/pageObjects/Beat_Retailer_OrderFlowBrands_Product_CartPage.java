package pageObjects;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Beat_Retailer_OrderFlowBrands_Product_CartPage extends BasePage {
	static int brandsProductScheme=0;
	
    public Beat_Retailer_OrderFlowBrands_Product_CartPage(WebDriver driver) {
        super(driver);
    }

    // ================= ELEMENTS =================
    @FindBy(xpath ="//div[@id='root']//div[contains(@class,'ant-card-body')]//span[2]/strong")
    WebElement productTotalAmount;
    
    
    @FindBy(xpath = "//span[normalize-space()='Place Order']")
    WebElement placeOrder;

    @FindBy(xpath = "//textarea[@placeholder='Add any remarks for this order...']")
    WebElement remarkInputField;

    @FindBy(xpath = "//button[@class='ant-btn ant-btn-primary']")
    WebElement placeOrder2tmp;

    @FindBy(xpath = "//span[normalize-space()='Add On']")
    WebElement addOn;

    @FindBy(xpath = "//div[@class='ant-dropdown ant-dropdown-placement-bottomLeft ']//span[normalize-space()='Cash Discount']")
    WebElement cashDiscountAddOn;
    
    @FindBy(xpath = "//input[@placeholder='Enter amount']")
    WebElement enterFixedAmountDiscount;
    
    @FindBy(xpath="//span[normalize-space()='% (Percentage)']")
    WebElement selectPercentageRadioButton;
    
    @FindBy(xpath="//input[@placeholder='Enter percentage']")
    WebElement enterPercentageDiscount;
    
    @FindBy(xpath = "//textarea[@placeholder='Add a remark for this add-on...']")
    WebElement addOnRemark;
    
    @FindBy(xpath="//span[normalize-space()='Sample']")
    WebElement sampleAddOn;
    
    @FindBy(xpath="//span[normalize-space()='Display']")
    WebElement displayAddOn;
    
    @FindBy(xpath="//span[normalize-space()='Product Scheme']")
    WebElement productSchemeAddOn;
    
    @FindBy(xpath="//input[@id='rc_select_2']")
    WebElement selectBrand;
    
    @FindBy(xpath="//div[@id]/following::div[contains(@class,'ant-select-item-option')]")
    List<WebElement>  selectBrandProduct_Scheme;
    
    @FindBy(xpath="(//div[@class=\"ant-col ant-form-item-control\"]//div[contains(@class,'ant-select-selector')])[2]")
    WebElement clickSelectProduct;
    
	    @FindBy(xpath="//div[@class='ant-empty-image']")
	    WebElement emptySelectProduct;
    
    @FindBy(xpath="//div[@id]/following::div[contains(@class,'ant-select-item-option')]")
    List<WebElement> selectProduct_ProductScheme;
    
    @FindBy(xpath="//input[@placeholder='Enter free quantity']")
    WebElement input_Field_FreeProduct;
    
    @FindBy(xpath="//textarea[@placeholder='Add a remark for this product scheme...']")
    WebElement input_Field_ProductScheme_Remark;
    
    
    @FindBy(xpath ="//div[@class='ant-notification-notice-description']")
    WebElement discountNotificationErrorMessage;

    
    @FindBy(xpath="//div[contains(@class,'ant-modal-root')]//button[2]")
    WebElement addOn_Save;
    
    
    // Cart Add On Add
    
    @FindBy(xpath="//span[normalize-space()='Add Cart Add-On']")
    WebElement addCartAddOnButton;

 // ================= ACTION METHODS =================

    public void clickOnAddOn() {
        wait.until(ExpectedConditions.elementToBeClickable(addOn)).click();
        System.out.println("✅ Clicked on AddOn button");
    }

    public void clickOnProductCashDiscountAddOn() {
        wait.until(ExpectedConditions.elementToBeClickable(cashDiscountAddOn)).click();
        System.out.println("✅ Clicked on Cash Discount");
    }
    
    public void clickOnProductSampleAddOn() {
    	wait.until(ExpectedConditions.elementToBeClickable(sampleAddOn)).click();
        System.out.println("✅ Clicked on Sample");
    }
    
    public void clickOnProductDisplayAddOn() {
    	wait.until(ExpectedConditions.elementToBeClickable(displayAddOn)).click();
        System.out.println("✅ Clicked on Display");
    	
    }
    
    public void clickOnProduct_ProductSchemeAddOn() {
    	wait.until(ExpectedConditions.elementToBeClickable(productSchemeAddOn)).click();
        System.out.println("✅ Clicked on Product Scheme");
    	
    }
    public void clickOnSelectBrand() {
    	wait.until(ExpectedConditions.elementToBeClickable(selectBrand)).click();
        System.out.println("✅ Clicked on Select Brand");
    	
    }
    
    public void selectBrandProdcutScheme() {
        try {
            // wait till ANY ONE option is visible (NOT all)
            wait.until(driver -> selectBrandProduct_Scheme.size() > 0);
            int psize = selectBrandProduct_Scheme.size();
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // rc-virtual-list holder
            WebElement listHolder = driver.findElement(
                    By.className("rc-virtual-list-holder")
            );

            // scroll container to load virtual items
            for (int i = 0; i < 3; i++) {
                js.executeScript(
                    "arguments[0].scrollTop = arguments[0].scrollHeight",
                    listHolder
                );
                Thread.sleep(300);
            }

            int size = selectBrandProduct_Scheme.size();
            if (size == 0) {
                System.out.println("No Brand Product Scheme found");
                return;
            }

            // random selection
            brandsProductScheme = size;
            WebElement randomOption =
                    selectBrandProduct_Scheme.get(new Random().nextInt(size));

            
            // click via JS (AntD safest)
            js.executeScript("arguments[0].click();", randomOption);

            System.out.println("Selected Brand Scheme: "
                    + randomOption.getAttribute("title"));

        } catch (Exception e) {
            System.out.println("Unable to select Brand Product Scheme");
            e.printStackTrace();
        }
    }
    
    // this is only one brand select Onida
    public void selectBrandProdcutSchemes() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // 1️⃣ Wait for at least one brand to appear (lazy loading might take time)
            wait.until(driver -> selectBrandProduct_Scheme.size() > 0);

            // 2️⃣ Scroll the virtual list fully to load all brands
            WebElement listHolder = driver.findElement(By.className("rc-virtual-list-holder"));

            long lastScrollHeight = 0;
            long scrollHeight = (long) js.executeScript("return arguments[0].scrollHeight;", listHolder);

            // scroll until fully loaded
            while (scrollHeight > lastScrollHeight) {
                js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", listHolder);
                Thread.sleep(300);
                lastScrollHeight = scrollHeight;
                scrollHeight = (long) js.executeScript("return arguments[0].scrollHeight;", listHolder);
            }

            boolean found = false;

            // 3️⃣ Loop through all options after full scroll
            for (WebElement option : selectBrandProduct_Scheme) {
                String brandName =
                        option.getText().trim().length() > 0
                                ? option.getText().trim()
                                : option.getAttribute("title");

                if (brandName != null && brandName.equalsIgnoreCase("Onida")) {
                    // scroll into view & JS click
                    js.executeScript("arguments[0].scrollIntoView({block:'center'});", option);
                    js.executeScript("arguments[0].click();", option);

                    System.out.println("✅ Onida Brand Scheme selected successfully");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("❌ Onida Brand not found in the list");
            }

        } catch (Exception e) {
            System.out.println("❌ Unable to select Onida Brand Product Scheme");
            e.printStackTrace();
        }
    }
    public void selectBrandProductScheme(String brand) {
        try {
            // wait till ANY ONE option is visible
            wait.until(driver -> selectBrandProduct_Scheme.size() > 0);

            JavascriptExecutor js = (JavascriptExecutor) driver;

            // rc-virtual-list holder
            WebElement listHolder = driver.findElement(
                    By.className("rc-virtual-list-holder")
            );

            // scroll to load virtual items
            for (int i = 0; i < 3; i++) {
                js.executeScript(
                        "arguments[0].scrollTop = arguments[0].scrollHeight",
                        listHolder
                );
                Thread.sleep(300);
            }

            int size = selectBrandProduct_Scheme.size();
            if (size == 0) {
                System.out.println("No Brand Product Scheme found");
                return;
            }

            WebElement selectedOption = null;

            //  Try exact match first
            for (WebElement option : selectBrandProduct_Scheme) {
                String optionText = option.getAttribute("title").trim();

                if (optionText.equalsIgnoreCase(brand)) {
                    selectedOption = option;
                    break;
                }
            }

            // If brand NOT found → select random
            if (selectedOption == null) {
                System.out.println("Brand not found: " + brand + " | Selecting random option");
                selectedOption = selectBrandProduct_Scheme.get(
                        new Random().nextInt(size)
                );
            }

            // click via JS (AntD safest)
            js.executeScript("arguments[0].click();", selectedOption);

            System.out.println("Selected Brand Scheme: "
                    + selectedOption.getAttribute("title"));

        } catch (Exception e) {
            System.out.println("Unable to select Brand Product Scheme");
            e.printStackTrace();
        }
    }

    
    public void clickOnSelectProduct_ProductScheme() {
        try {
            // Wait until the dropdown container is visible and clickable
            WebElement productSchemeDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                clickSelectProduct  // this should already point to .ant-select-selector div
            ));

            // Click to open the dropdown
            productSchemeDropdown.click();

            System.out.println("✅ Product Scheme dropdown clicked successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    public void selectRandomProductScheme() {
//        try {
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//
//            // 1️⃣ Wait until options exist
//            wait.until(driver -> selectProduct_ProductScheme.size() > 0);
//
//            WebElement listHolder = driver.findElement(By.className("rc-virtual-list-holder"));
//
//            // 2️⃣ Scroll slowly to load items
//            for (int i = 0; i < 3; i++) {
//                js.executeScript(
//                    "arguments[0].scrollTop = arguments[0].scrollTop + 200",
//                    listHolder
//                );
//                Thread.sleep(300);
//            }
//
//            // 3️⃣ Re-wait after scrolling
//            wait.until(driver -> selectProduct_ProductScheme.size() > 0);
//
//            int size = selectProduct_ProductScheme.size();
//            System.out.println("Product Scheme size = " + size);
//
//            if (size <= brandsProductScheme) {
//                System.out.println("⚠ Only brand schemes present, no product schemes");
//                return;
//            }
//
//            // 4️⃣ SAFE random index
//            int minIndex = brandsProductScheme;
//            int randomIndex = new Random().nextInt(size - minIndex) + minIndex;
//
//            if(randomIndex <0) {
//            	clickOnSelectBrand();
//            	selectBrandProdcutScheme();
//            	clickOnSelectProduct_ProductScheme();
//            	selectRandomProductScheme();
//            }
//            
//            WebElement randomOption = selectProduct_ProductScheme.get(randomIndex);
//
//            // 5️⃣ Scroll option into view
//            js.executeScript("arguments[0].scrollIntoView({block:'center'});", randomOption);
//
//            // 6️⃣ JS click
//            js.executeScript("arguments[0].click();", randomOption);
//
//            // 7️⃣ Read name
//            String schemeName =
//                    randomOption.getAttribute("aria-label") != null
//                    ? randomOption.getAttribute("aria-label")
//                    : randomOption.getText();
//
//            System.out.println("✅ Random Product Scheme selected: " + schemeName);
//
//        } catch (Exception e) {
//            System.out.println("❌ Unable to select Product Scheme");
//            e.printStackTrace();
//        }
//    }
    public void selectRandomProductScheme() {

        int retryCount = 0;
        int maxRetry = 50;

        while (retryCount < maxRetry) {

            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;

                wait.until(driver -> selectProduct_ProductScheme.size() > 0);

                WebElement listHolder =
                        driver.findElement(By.className("rc-virtual-list-holder"));

                // Load virtual items
                for (int i = 0; i < 3; i++) {
                    js.executeScript(
                        "arguments[0].scrollTop = arguments[0].scrollTop + 250",
                        listHolder
                    );
                    Thread.sleep(300);
                }

                int size = selectProduct_ProductScheme.size();
                System.out.println("🔹 Product Scheme size = " + size);

                // ❌ No product schemes for this brand
                if (size <= brandsProductScheme) {
                    System.out.println("⚠ No product scheme found → Selecting new brand");

                    retryCount++;

                    clickOnSelectBrand();
                    selectBrandProdcutScheme();

                    clickOnSelectProduct_ProductScheme();
                    continue;
                }

                // ✅ Product schemes exist
                int minIndex = brandsProductScheme;
                int randomIndex =
                        new Random().nextInt(size - minIndex) + minIndex;

                WebElement randomOption =
                        selectProduct_ProductScheme.get(randomIndex);

                js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    randomOption
                );
                js.executeScript("arguments[0].click();", randomOption);

                String schemeName =
                        randomOption.getAttribute("aria-label") != null
                                ? randomOption.getAttribute("aria-label")
                                : randomOption.getText();

                System.out.println("✅ Random Product Scheme selected: " + schemeName);
                return;

            } catch (Exception e) {
                retryCount++;
                System.out.println("⚠ Retry due to error, attempt: " + retryCount);
            }
        }

        throw new RuntimeException("❌ Product scheme not found after retries");
    }


    public void enterRandomFreeProductQuantity() {
        try {
            // 1️⃣ Wait until input is visible & clickable
            WebElement input = wait.until(
                    ExpectedConditions.elementToBeClickable(input_Field_FreeProduct)
            );

            // 2️⃣ Click & clear existing value
            input.click();
            input.clear();

            // 3️⃣ Generate random number (1–20)
            int randomQty = new Random().nextInt(20) + 1;

            // 4️⃣ Enter value
            input.sendKeys(String.valueOf(randomQty));

            System.out.println("✅ Free Product quantity entered: " + randomQty);

        } catch (Exception e) {
            System.out.println("❌ Unable to enter Free Product quantity");
            e.printStackTrace();
        }
    }

    public void enterRandomProductSchemeRemark() {
        try {
            // 1️⃣ Wait until textarea is clickable
            WebElement remarkField = wait.until(
                    ExpectedConditions.elementToBeClickable(input_Field_ProductScheme_Remark)
            );

            // 2️⃣ Click & clear
            remarkField.click();
            remarkField.clear();

            // 3️⃣ Meaningful remarks list
            String[] remarks = {
                    "Applied seasonal offer as discussed",
                    "Scheme selected based on retailer preference",
                    "Approved by sales manager",
                    "Special discount scheme applied",
                    "Offer applied for bulk purchase",
                    "Scheme applied for loyal customer",
                    "Promotional scheme selected",
                    "Approved scheme for this order",
                    "Applied scheme after negotiation",
                    "Best applicable scheme selected"
            };

            // 4️⃣ Pick random remark
            String randomRemark =
                    remarks[new Random().nextInt(remarks.length)];

            // 5️⃣ Enter remark
            remarkField.sendKeys(randomRemark);

            System.out.println("✅ Product Scheme Remark entered: " + randomRemark);

        } catch (Exception e) {
            System.out.println("❌ Unable to enter Product Scheme Remark");
            e.printStackTrace();
        }
    }




    public boolean isProductSchemeEmpty() {
        try {
            // Check if the empty placeholder is displayed
            if (emptySelectProduct.isDisplayed()) {
                System.out.println("⚠ Product Scheme dropdown is empty.");
                return true;
            }
        } catch (NoSuchElementException e) {
            // Element not found means dropdown is not empty
            return false;
        }
        return false;
    }



    // ================= BUSINESS LOGIC =================
    
    private Double cachedProductTotalAmount = null;

    public double getProductTotalAmount() {

        // ✅ Return cached value (NO LOG)
        if (cachedProductTotalAmount != null) {
            return cachedProductTotalAmount;
        }

        wait.until(ExpectedConditions.visibilityOf(productTotalAmount));

        String rawText = productTotalAmount.getText();

        // Remove currency, comma but keep decimal
        String cleanedText = rawText.replaceAll("[^0-9.]", "");

        cachedProductTotalAmount = Double.parseDouble(cleanedText);

        // ✅ LOG ONLY ONCE
        System.out.println("🧮 Product Total Amount : " + cachedProductTotalAmount);

        return cachedProductTotalAmount;
    }


    private Integer appliedDiscount = null;

    public void enterRandomIntegerDiscountLessThanTotalOnlyOnce() {

        wait.until(ExpectedConditions.visibilityOf(enterFixedAmountDiscount));

        if (appliedDiscount != null) {
            System.out.println("⚠️ Discount already applied: " + appliedDiscount);
            return;
        }

        double totalAmount = getProductTotalAmount();

        int maxDiscount = (int) Math.floor(totalAmount) - 1;
        if (maxDiscount <= 0) {
            throw new RuntimeException("❌ Invalid total for discount");
        }

        appliedDiscount = new Random().nextInt(maxDiscount) + 1;

        enterFixedAmountDiscount.click();
        enterFixedAmountDiscount.clear();
        enterFixedAmountDiscount.sendKeys(String.valueOf(appliedDiscount));
        enterFixedAmountDiscount.sendKeys(Keys.ENTER);

        System.out.println(
            "🎲 Discount Applied: " + appliedDiscount +
            " | Original Total: " + totalAmount
        );
    }

    
    public double getUpdatedProductTotalAmount(double oldTotal) {

        wait.until(driver -> {
            double currentTotal = Double.parseDouble(
                productTotalAmount.getText().replaceAll("[^0-9.]", "")
            );
            return Math.abs(currentTotal - oldTotal) > 0.01;
        });

        double updatedTotal = Double.parseDouble(
            productTotalAmount.getText().replaceAll("[^0-9.]", "")
        );

        System.out.println("🧮 Updated Product Total Amount : " + updatedTotal);
        return updatedTotal;
    }


    public void validateFinalTotalAfterDiscount() {

        if (appliedDiscount == null) {
            throw new RuntimeException("❌ Discount missing for validation");
        }

        double originalTotal = getProductTotalAmount();

        double updatedTotal = getUpdatedProductTotalAmount(originalTotal);

        double expectedTotal =
            Math.round((originalTotal - appliedDiscount) * 100.0) / 100.0;

        if (Math.abs(expectedTotal - updatedTotal) > 0.01) {
            throw new AssertionError(
                "❌ Final Total Mismatch ❌\n" +
                "Original Total: " + originalTotal + "\n" +
                "Discount: " + appliedDiscount + "\n" +
                "Expected Total: " + expectedTotal + "\n" +
                "Actual Total: " + updatedTotal
            );
        }

        System.out.println(
            "✅ Final Total Verified Successfully\n" +
            "Final Amount: " + updatedTotal
        );
    }
    
    public void ClickOnPercentage() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(selectPercentageRadioButton))
            .click();
    }
    
    public void clickOnPercentageInput_Field() {
    	int percentage = new Random().nextInt(99) + 1;

        wait.until(ExpectedConditions.elementToBeClickable(enterPercentageDiscount));
        enterPercentageDiscount.clear();
        enterPercentageDiscount.sendKeys(String.valueOf(percentage));

        System.out.println("Entered Percentage: " + percentage + "%");
    }
    public void verifyThreeDigitDiscountRestriction(String inputValue) {
        // Enter value
        wait.until(ExpectedConditions.elementToBeClickable(enterPercentageDiscount));
        enterPercentageDiscount.clear();
        enterPercentageDiscount.sendKeys(inputValue);
        System.out.println("Entered Discount Value: " + inputValue);

        // Wait for notification
        wait.until(ExpectedConditions.visibilityOf(discountNotificationErrorMessage));
        String notification = discountNotificationErrorMessage.getText().trim();
        System.out.println("Notification: " + notification);

        // Conditional check for notification
        if (notification.equals("Discount value 100 or more than 100 is not allowed")) {
            System.out.println("PASS: Proper notification displayed for 3-digit discount.");
        } else {
            System.out.println("FAIL: Notification missing or incorrect.");
        }

        // Check field value truncated
        String fieldValue = enterPercentageDiscount.getAttribute("value");
        System.out.println("Final field value after restriction: " + fieldValue);

        int value = Integer.parseInt(fieldValue);
        if (value < 100) {
            System.out.println("PASS: Field correctly truncated input to max allowed.");
        } else {
            System.out.println("FAIL: Field incorrectly allowed 3-digit value.");
        }
    }




    // 📝 ADD ADD-ON REMARK
    public void addAddOnRemark() {
        wait.until(ExpectedConditions.visibilityOf(addOnRemark));
        addOnRemark.click();
        addOnRemark.clear();
        addOnRemark.sendKeys("Automation discount test");
        System.out.println("📝 Add-On Remark Added");
    }
    
    public void clickOnAddOnSave() {
    	addOn_Save.click();
		
	}
 // ✅ OPTIONAL ASSERTION
    public void validateDiscountIsLessThanTotal() {

        double total = getProductTotalAmount();
        int discount = Integer.parseInt(enterFixedAmountDiscount.getAttribute("value"));

        if (discount >= total) {
            throw new AssertionError(
                "❌ Discount Invalid! Discount: " + discount +
                " | Total: " + total
            );
        }

        System.out.println("✅ Discount validation passed");
    }
    
    
    

    // 📝 ORDER REMARK
    public void enterOrderRemark() {

        String[] remarks = {
            "Handle with care",
            "Deliver ASAP",
            "Check expiry date",
            "Ensure proper packaging",
            "Urgent order, prioritize"
        };

        String remark = remarks[new Random().nextInt(remarks.length)];

        wait.until(ExpectedConditions.visibilityOf(remarkInputField));
        remarkInputField.click();
        remarkInputField.clear();
        remarkInputField.sendKeys(remark);

        System.out.println("📝 Order Remark: " + remark);
    }

    // 🛒 PLACE ORDER
    public void clickOnPlaceOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrder)).click();
        System.out.println("🛒 Clicked Place Order");
    }

    public void clickOnPlaceOrderSecond() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrder2tmp)).click();
        System.out.println("✅ Final Place Order Clicked");
    }

   
}
