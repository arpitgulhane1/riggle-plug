package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.net.UrlChecker.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	WebDriver driver;
	 protected WebDriverWait wait;
	
	BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	JavascriptExecutor js = (JavascriptExecutor) driver;

	public void waitForElementVisible(WebElement element, int timeoutInSeconds) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	    wait.until(ExpectedConditions.visibilityOf(element));
	}
	
//	public void waitForMultipleElementsVisible(List<WebElement> elements, int timeoutInSeconds) {
//	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
//	    wait.until(ExpectedConditions.visibilityOfAllElements(elements));
//	}

	public void waitForMultipleElementsVisible(List<WebElement> elements, int timeoutInSeconds) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        wait.until(ExpectedConditions.visibilityOfAllElements(elements));

	        System.out.println("✅ All elements became visible within " + timeoutInSeconds + " seconds.");

	    } 
//	    catch (TimeoutException e) {
//	        System.out.println("❌ Timeout: Not all elements became visible within " + timeoutInSeconds + " seconds.");
//	        e.printStackTrace();
//	    } 
	    catch (NoSuchElementException e) {
	        System.out.println("❌ One or more elements were not found in the DOM.");
	        e.printStackTrace();

	    } catch (StaleElementReferenceException e) {
	        System.out.println("⚠️ Elements became stale while waiting.");
	        e.printStackTrace();

	    } catch (Exception e) {
	        System.out.println("⚠️ Unexpected exception while waiting for multiple elements:");
	        e.printStackTrace();
	    }
	}

	
}
