package com.atmecs.phphotelbooking.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.atmecs.phphotelbooking.reports.Log4j;

/**
 * This method contains various wait methods and alert method
 * 
 * @author magesh.suriyamoorthi
 */
public class WaitsandSimpleAlert {
	private Log4j log = new Log4j();
	WebElement element;
	UtilityFiles util = new UtilityFiles();

	/**
	 * implicit wait method
	 * 
	 * @param driver
	 * @param waitingTime
	 */
	public void implicitWait(WebDriver driver, long waitingTime) {
		driver.manage().timeouts().implicitlyWait(waitingTime, TimeUnit.SECONDS);
	}

	/**
	 * page load timeout
	 * 
	 * @param driver
	 * @param waitingTime
	 */
	public void pageLoadTimeout(WebDriver driver, long waitingTime) {
		driver.manage().timeouts().pageLoadTimeout(waitingTime, TimeUnit.SECONDS);
	}

	/**
	 * script timeout method
	 * 
	 * @param driver
	 * @param waitingTime
	 */
	public void setScriptTimeoutCommand(WebDriver driver, long waitingTime) {
		driver.manage().timeouts().setScriptTimeout(waitingTime, TimeUnit.SECONDS);
	}

	/**
	 * element to be click method
	 * 
	 * @param driver
	 * @param locator
	 * @param waitingTime
	 * @return
	 */
	public WebElement waitsElementToBeClickable(WebDriver driver, String locator, long waitingTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitingTime);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(util.locator(locator)));
		return element;
	}

	/**
	 * element to be select method
	 * 
	 * @param driver
	 * @param locator
	 * @param waitingTime
	 * @return
	 */
	public Boolean waitsElementToBeSelectableLocator(WebDriver driver, String locator, long waitingTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitingTime);
		Boolean element = wait.until(ExpectedConditions.elementToBeSelected(util.locator(locator)));
		return element;
	}

	/**
	 * boolean wait
	 * 
	 * @param driver
	 * @param locator
	 * @param waitingTime
	 * @return
	 */
	public Boolean waitsElementToBeWebElement(WebDriver driver, String locator, long waitingTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitingTime);
		Boolean element = wait.until(ExpectedConditions.elementToBeSelected(util.locator(locator)));
		return element;
	}

	/**
	 * list of element to be wait
	 * 
	 * @param driver
	 * @param locator
	 * @param waitingTime
	 * @return
	 */
	public List<WebElement> waitsVisibilityOfAllElementLocBy(WebDriver driver, String locator, long waitingTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitingTime);
		List<WebElement> element = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(util.locator(locator)));
		return element;
	}

	/**
	 * visibility of element to be wait for one element
	 * 
	 * @param driver
	 * @param locator
	 * @param waitingTime
	 * @return
	 */
	public WebElement waitsVisibilityOfAllElement(WebDriver driver, String locator, long waitingTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitingTime);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(util.locator(locator)));
		return element;
	}

	/**
	 * java wait or non Selenium wait
	 * 
	 * @param waitingTime
	 */
	public void ThreadWait(long waitingTime) {
		try {
			Thread.sleep(waitingTime);
		} catch (Exception exception) {
			log.info(exception + " appeared");
		}
	}

	/**
	 * fluent wait method
	 * 
	 * @param driver
	 * @param locator
	 */
	public void fluentWait(WebDriver driver, String locator) {
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).ignoring(Exception.class)
				.pollingEvery(3, TimeUnit.SECONDS).withTimeout(30, TimeUnit.SECONDS);

		fluentWait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				element = util.FindElement(driver, locator);
				return true;
			}
		});
	}

	/**
	 * alert method
	 * 
	 * @param driver
	 * @param waitingTime
	 */
	public void alertPresent(WebDriver driver, long waitingTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitingTime);
		if (wait.until(ExpectedConditions.alertIsPresent()) == null)
			log.info("alert was not present");
		else
			log.info("alert was present");
	}

	/**
	 * boolean alert method for neglecting it
	 * 
	 * @param driver
	 * @param actions
	 * @return
	 */
	public boolean isAlertPresent(WebDriver driver, String actions) {
		try {
			Alert alert = driver.switchTo().alert();
			if (actions.equals("accept")) {
				alert.accept();
			} else if (actions.equals("dismiss")) {
				alert.dismiss();
			}
			return true;
		} catch (Exception Ex) {
			return false;
		}
	}
}
