package pageObjects;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utility.BrandUtility;
public class Brand_NetworkPage extends BasePage{
	
	private static String selectedRateStructur="" ,selectedRole="", selectedCP ="" ,selectedLinkSellerName ="" , selectedSalesPerson= "" ,selectedPrimarySalesPerson="";
	
    public Brand_NetworkPage(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }
    @FindBy(xpath = "//span[normalize-space()='Network']")
    WebElement network;
    
    @FindBy(xpath = "//input[@id='search']")
    WebElement searchBrand;
    
    @FindBy(xpath ="//span[contains(text(),'Assign Channel Partner')]")
    WebElement assignChannelPartner;
    
    @FindBy(xpath="//input[@id='nest-messages_buyer']")
    WebElement channelPartnerList;
    
    @FindBy(xpath="//div[@class=\"ant-select-item-option-content\"]")
    List<WebElement> channelPartnerListAllOption;
    
    @FindBy(xpath="(//span[@class=\"ant-select-selection-item\"])[1]")
    WebElement channelPartnerEditName;
    
    @FindBy(xpath="//input[@id='nest-messages_role']")
    WebElement chooseChannelPartnerRole;
    
    @FindBy(xpath="(//span[@class=\"ant-select-selection-item\"])[2]")
    WebElement channelPartnerEditRole;
    
    @FindBy(xpath="//div[@class=\"ant-select-dropdown ant-select-dropdown-placement-bottomLeft \"]//div[@class=\"ant-select-item-option-content\"]")
    List<WebElement> chooseChannelPartnerRoleAllOption;
    
    @FindBy(xpath="//input[@id='nest-messages_seller']")
    WebElement linkSeller;
    
    @FindBy(xpath="(//span[@class=\"ant-select-selection-item\"])[3]")
    WebElement channelPartnerLinkSellerName;
    
    @FindBy(xpath="//div[@class=\"ant-select-dropdown ant-select-dropdown-placement-bottomLeft \"]//div[@class=\"ant-select-item-option-content\"]")
    List<WebElement> chooseLinkSellerAllOption;
    
    @FindBy(xpath="//input[@id='nest-messages_rate_structure']")
    WebElement rateStructure;
    
    @FindBy(xpath="(//span[@class=\"ant-select-selection-item\"])[4]")
    WebElement channelPartnerEditRateStrecture;
    
    @FindBy(xpath="//div[@class=\"ant-select-dropdown ant-select-dropdown-placement-bottomLeft \"]//div[@class=\"ant-select-item-option-content\"]")
    List<WebElement> chooseRateStructureAllOption;
    
    @FindBy(xpath="//div[@class='ant-select-selection-overflow']")
    WebElement assignSalesPerson;
//    @FindBy(xpath="//div[@class=\"rc-virtual-list-holder\"]//div[@class=\"ant-select-item-option-content\"]")

    @FindBy(xpath="//div[@class='ant-select-dropdown ant-select-dropdown-placement-bottomLeft ']//div[@class='ant-select-item-option-content']")
    List<WebElement> chooseAssignSalesPersonAllOption;
    
//    @FindBy(xpath="//div[@class='ant-select-dropdown ant-select-dropdown-placement-bottomLeft ']//div[@class='ant-select-item ant-select-item-option']")
//    List<WebElement> chooseAssignSalesPersonAllOption;
    
    @FindBy(xpath="//input[@id='nest-messages_primary_salesperson']")
    WebElement assignPrimarySalesPerson;
    @FindBy(xpath="//div[@class='ant-select-dropdown ant-select-dropdown-placement-topLeft  ant-select-dropdown-hidden']//div[@class=\"rc-virtual-list-holder\"]//div[@class=\"ant-select-item-option-content\"]")
    List<WebElement> chooseAssignPrimarySalesPersonAllOption;
    
    @FindBy(xpath="//button[@type='submit']")
    WebElement assign;
    
    @FindBy(xpath="//input[@placeholder='Search Buyer Name']")
    WebElement searchBuyerName;
    
    // search buyer and edit click
    @FindBy(xpath="//div[contains(@class,'ant-list ant-list-lg ant-list-split ant-list-bordered')]//button[@type='button']")
    List<WebElement> editAssignChannelPartner_Button;
    
    // get Cp name verify path
    @FindBy(xpath="(//span[@class=\"ant-select-selection-item\"])[1]")
    WebElement cpNameElement;
    // Negative Test all mandatory field empty xpath
    
    @FindBy(xpath="//div[contains(text(),'Please enter CP Name')]")
    WebElement errorMsgChannelPartnerEmpty;
    
    @FindBy(xpath="//div[@class=\"ant-modal-title\"]")
    WebElement editChannelPartnerPageTital;
    
    @FindBy(xpath="//span[@class='ant-modal-close-x']")
    WebElement closexEditChannelPartnerPage;
    
    
    public void clickOnNetwork() {
        network.click();
        
    }
    
    public void search(String searchValue) {
        searchBrand.sendKeys(searchValue);
        
    }
    
    public void clickOnAssignChannelPartner() {
        assignChannelPartner.click();
    }
    
    public String getAssignChannelPartnerName() {
    	return BrandUtility.readJson("Network", "CP_Name");
    }
    public String getRole() {
    	return BrandUtility.readJson("Network", "Role");
    }
    public String getLinkSeller() {
    	return BrandUtility.readJson("Network", "LinkSeller");
    }
    public String getRateStrecture() {
    	return BrandUtility.readJson("Network", "RateStrecture");
    }
    public String getAssignSalsePerson() {
    	return BrandUtility.readJson("Network", "AssignSalsePerson");
    }
    public String getPrimarySalsePerson() {
    	return BrandUtility.readJson("Network", "PrimarySalesPerson");
    }
    
//  public void clickOnRandomChannelPartner() {
//      if (channelPartnerListAll.size() > 0) {
//          // Generate random index
//          int randomIndex = new Random().nextInt(channelPartnerListAll.size());
//          
//          // Get random CP
//          WebElement randomCP = channelPartnerListAll.get(randomIndex);
//
//          // Print which one is clicked
//          System.out.println("Clicked on CP: " + randomCP.getText());
//
//          // Click on it
//          randomCP.click();
//      } else {
//          System.out.println("❌ No Channel Partners found.");
//      }
//  }
//  
    public void enterChannelPartnerName() {
        wait.until(ExpectedConditions.visibilityOf(channelPartnerList));
        channelPartnerList.click();
//      channelPartnerListAllOption
//      int getvalue = channelPartnerListAllOption.size();
//      waitForMultipleElementsVisible(channelPartnerListAllOption,7);
//      wait.until(ExpectedConditions.visibilityOfAllElements(channelPartnerListAllOption));
        Random rd = new Random();
        WebElement option = channelPartnerListAllOption.get(rd.nextInt(channelPartnerListAllOption.size()));
        selectedCP = option.getText();
        option.click();
    }
    
    public void enterChannelPartnerRole() {
        wait.until(ExpectedConditions.visibilityOf(chooseChannelPartnerRole));
        chooseChannelPartnerRole.click();
        
        Random rd = new Random();
        WebElement option = chooseChannelPartnerRoleAllOption.get(rd.nextInt(chooseChannelPartnerRoleAllOption.size()));
        selectedRole = option.getText();
        option.click();
    }
    public void clickOnLinkSeller() {
        linkSeller.click();
    }
    
    public void enterLinkSellerName() {
        wait.until(ExpectedConditions.visibilityOf(linkSeller));
        linkSeller.click();
        Random rd = new Random();
        WebElement option = chooseLinkSellerAllOption.get(rd.nextInt(chooseLinkSellerAllOption.size()));
        selectedLinkSellerName = option.getText();
        option.click();
    }
    
    public void clickOnRateStructure() {
        rateStructure.click();
    }
    
    public void enterRateStructureName() {
        wait.until(ExpectedConditions.visibilityOf(rateStructure));
        rateStructure.click();
        Random rd = new Random();
        WebElement option = chooseRateStructureAllOption.get(rd.nextInt(chooseRateStructureAllOption.size()));
        selectedRateStructur = option.getText();
        option.click();
    }
    
    public void clickOnAssignSalesPerson() {
        assignSalesPerson.click();
    }
    
//    public void enterAssignSalesPersonName() {
//        // 1. Open dropdown
//        wait.until(ExpectedConditions.elementToBeClickable(assignSalesPerson)).click();
//
//        // 2. Wait until options are visible
//        wait.until(ExpectedConditions.visibilityOfAllElements(chooseAssignSalesPersonAllOption));
//
//        // 3. Pick random option
//        Random rd = new Random();
//        WebElement option = chooseAssignSalesPersonAllOption.get(rd.nextInt(chooseAssignSalesPersonAllOption.size()));
//        String selected = option.getText().trim();
//
//        // 4. Scroll into view
////        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
//
//        // 5. JS click
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
//
//        System.out.println("✅ Randomly selected: " + selected);
//    }

    
    public void enterAssignSalesPersonName() {
        wait.until(ExpectedConditions.visibilityOf(assignSalesPerson));
        assignSalesPerson.click();
        
        Random rd = new Random();
        System.out.println("size = "+chooseAssignSalesPersonAllOption.size());
//      waitForMultipleElementsVisible(chooseAssignSalesPersonAllOption, 7);
        wait.until(ExpectedConditions.visibilityOfAllElements(chooseAssignSalesPersonAllOption));
        
        for (WebElement op : chooseAssignSalesPersonAllOption) {
            String title = op.getAttribute("title");
            String text  = op.getText();
            System.out.println("Title: " + title + " | Text: " + text);
        }

        System.out.println("size = "+chooseAssignSalesPersonAllOption.size());
        WebElement option = chooseAssignSalesPersonAllOption.get(rd.nextInt(chooseAssignSalesPersonAllOption.size()));
        selectedSalesPerson = option.getText();
        option.click();
    }
    

    
    public void clickOnassignPrimarySalesPerson() {
        assignPrimarySalesPerson.click();
    }
    
    public void enterAssignPrimarySalesPersonName() {
        wait.until(ExpectedConditions.visibilityOf(assignPrimarySalesPerson));
        assignPrimarySalesPerson.click();
        Random rd = new Random();
        WebElement option = chooseAssignPrimarySalesPersonAllOption.get(rd.nextInt(chooseAssignPrimarySalesPersonAllOption.size()));
        String selectedoption = option.getText();
        option.click();
    }
    public void clickOnAssignButton() {
        assign.click();
        assignAllValue();
    }
    
    public void clickOnSearchBuyerName() {
    	waitForElementVisible(searchBuyerName, 7);
        searchBuyerName.click();
        searchBuyerName.clear();
        searchBuyerName.sendKeys(getAssignChannelPartnerName());
    }
    
    public boolean verifyAssignChannelPartnerDetails() {
//        waitForElementVisible(editAssignChannelPartner_Button, 5);
        waitForMultipleElementsVisible(editAssignChannelPartner_Button, 7);
       
        String expectedName = getAssignChannelPartnerName();
        String expectedRole = getRole();
        String expectedLinkSeller = getLinkSeller();
        String expectedRateStructure = getRateStrecture();

        for (WebElement editButton : editAssignChannelPartner_Button) {
            editButton.click();
            waitForElementVisible(editChannelPartnerPageTital, 5);

            // Actual values
            String actualName = channelPartnerEditName.getText();
            String actualRole = channelPartnerEditRole.getText();
            String actualLinkSeller = channelPartnerLinkSellerName.getText();
            String actualRateStructure = channelPartnerEditRateStrecture.getText();

            if (actualName.equals(expectedName) &&
                actualRole.equals(expectedRole) &&
                actualLinkSeller.equals(expectedLinkSeller) &&
                actualRateStructure.equals(expectedRateStructure)) {

                System.out.println("Matched");
                return true;
            } else {
                System.out.println("Not Matched");
                closexEditChannelPartnerPage.click(); 
            }
        }

        return false;
    }


 
    public void assignAllValue(){
    	Map<String, String> productDetails = new HashMap<>();
    	productDetails.put("CP_Name", selectedCP);
    	productDetails.put("Role", selectedRole);
    	productDetails.put("LinkSeller", selectedLinkSellerName);
    	productDetails.put("RateStrecture", selectedRateStructur);
    	productDetails.put("AssignSalsePerson", selectedSalesPerson);
    	productDetails.put("PrimarySalesPerson", selectedPrimarySalesPerson);
    	BrandUtility.writeJson("Network", productDetails);
    }
    
    
}