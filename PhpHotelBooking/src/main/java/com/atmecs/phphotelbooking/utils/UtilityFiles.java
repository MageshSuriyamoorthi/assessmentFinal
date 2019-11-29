package com.atmecs.phphotelbooking.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.atmecs.phphotelbooking.reports.Log4j;

/**
 * This method is used to use selenium reusable methods for creating scripts
 * 
 * @author magesh.suriyamoorthi
 *
 */
public class UtilityFiles {
	Calendar calender = Calendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


	Log4j log = new Log4j();
	
	int newValue;

	/**
	 * This method is uses by property for assigning locators
	 * 
	 * @param locatorvalue
	 * @return
	 */
	public By locator(String locatorvalue) {
		By by = null;
		String[] locator = locatorvalue.split(":", 2);
		String locatorName = locator[0].toUpperCase();
		switch (locatorName) {
		case "ID":
			by = By.id(locator[1]);
			break;
		case "NAME":
			by = By.name(locator[1]);
			break;
		case "CLASSNAME":
			by = By.className(locator[1]);
			break;
		case "LINKEDTEXT":
			by = By.linkText(locator[1]);
			break;
		case "PARTIALLINKEDTEXT":
			by = By.partialLinkText(locator[1]);
			break;
		case "XPATH":
			by = By.xpath(locator[1]);
			break;
		case "CSS":
			by = By.cssSelector(locator[1]);
			break;
		default:
			log.info("enter correct locator");
		}
		return by;
	}

	/**
	 * This method is used to find element in a web site
	 * 
	 * @param driver
	 * @param locator
	 * @return
	 */
	public WebElement FindElement(WebDriver driver, String locator) {
		WebElement elememt = driver.findElement(locator(locator));
		return elememt;
	}

	/**
	 * This method is used to find elements in a web site
	 * 
	 * @param driver
	 * @param locator
	 * @return
	 */
	public List<WebElement> FindElements(WebDriver driver, String locator) {
		List<WebElement> elememt = driver.findElements(locator(locator));
		return elememt;
	}

	
	/**
	 * This method is used to open invoke
	 * 
	 * @param driver
	 * @param url
	 */
	public void geturl(WebDriver driver, String url) {
		driver.get(url);
	}

	/**
	 * This method is maximize the browser
	 * 
	 * @param driver
	 */
	public void maximize(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * This method is used to get title from current url
	 * 
	 * @param driver
	 * @return
	 */
	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	/**
	 * This method is used to click button in web page
	 * 
	 * @param driver
	 * @param locator
	 */
	public void click(WebDriver driver, String locator) {
		FindElement(driver, locator).click();
	}

	/**
	 * This method is used to clear text in textbox
	 * 
	 * @param driver
	 * @param locator
	 */
	public void clear(WebDriver driver, String locator) {
		FindElement(driver, locator).clear();
	}

	/**
	 * This method is used to insert values in enter keys in text box
	 * 
	 * @param driver
	 * @param locator
	 * @param text
	 */
	public void sendKeys(WebDriver driver, String locator, String text) {
		FindElement(driver, locator).sendKeys(text);
	}

	/**
	 * This method is used to scroll web page
	 * 
	 * @param driver
	 */
	public void upwardScroll(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-200)");
	}

	/**
	 * This method is used to move downwards in a web page
	 * 
	 * @param driver
	 */
	public void downwardScroll(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,75)");
	}

	/**
	 * This method is used to move upwards in a web page
	 * 
	 * @param driver
	 * @param locator
	 */
	public void scrolltoview(WebDriver driver, String locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", FindElement(driver, locator));
	}

	/**
	 * This method is used to get text by giving locator
	 * 
	 * @param driver
	 * @param locator
	 * @return
	 */
	public String getText(WebDriver driver, String locator) {
		WebElement element = FindElement(driver, locator);
		String getText = element.getText();
		return getText;
	}

	/**
	 * This method is used to get due date by giving date from blog
	 * 
	 * @param duedate
	 * @param duevalue
	 * @return
	 */
	public String getCheckOutDate(String duedate, String value) {
		newValue=Integer.parseInt(value);
		
		try {
			calender.setTime(dateFormat.parse(duedate));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		calender.add(Calendar.DATE, newValue);
		String date = dateFormat.format(calender.getTime());

		return date;
	}

	/**
	 * This method is used to perform keyboard function called reverse tab
	 * 
	 * @param driver
	 */
	public void reverse(WebDriver driver) {
		Actions builder = new Actions(driver);

		builder.sendKeys(Keys.LEFT_SHIFT, Keys.TAB);
	}

	/**
	 * This method is used to perform keyboard function called forward tab
	 * 
	 * @param driver
	 */
	public void forward(WebDriver driver,String locator) {
		FindElement(driver, locator).sendKeys(Keys.TAB);
	}
/**
 * This method is use to get date by adding days from current days
 * 
 * @param value
 * @return
 */
	public String getCheckInDate(String value) {
		newValue=Integer.parseInt(value);
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, newValue);
		return dateFormat.format(c.getTime());
	}
}
