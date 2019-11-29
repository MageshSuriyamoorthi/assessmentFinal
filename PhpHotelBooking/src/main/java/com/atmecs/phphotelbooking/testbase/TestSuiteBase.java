package com.atmecs.phphotelbooking.testbase;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import com.atmecs.phphotelbooking.helper.PropertiesFileReader;
import com.atmecs.phphotelbooking.reports.Log4j;
import com.atmecs.phphotelbooking.utils.UtilityFiles;
import com.atmecs.phphotelbooking.utils.WaitsandSimpleAlert;;

/**
 * This method used to select the browser or grid and leads to work on that
 * particular browser
 * 
 * @author magesh.suriyamoorthi
 *
 */
public class TestSuiteBase {
	public Log4j log = new Log4j();
	public PropertiesFileReader propertyreader = new PropertiesFileReader();
	public WaitsandSimpleAlert wait = new WaitsandSimpleAlert();
	public UtilityFiles utils = new UtilityFiles();

	public String browser;
	public WebDriver driver;
	public WebElement element;
	public String actual, expected, date, totalsearch;
	public int checkinDate, checkoutDate, totalsearchvalue;
	public List<WebElement> elementlist,ratelist;
	public List<String>list,lists,sortedrating,newratinglist;

	@BeforeTest
	public void browserSelect() {

		String browserName = propertyreader.getLocatorValue("config.browsername");
		browser = browserName.toUpperCase();

		switch (browser) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", Classpaths.CHROMEFILE);
			driver = new ChromeDriver();
			break;
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", Classpaths.FIREFOXFILE);
			driver = new FirefoxDriver();
			break;
		case "INTERNET EXPLORER":
			System.setProperty("webdriver.ie.driver", Classpaths.IEFILE);
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			ieCapabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
			driver = new InternetExplorerDriver(ieCapabilities);
			break;
		default:
			log.info("Driver name need to given correctly ");
		}
		if (driver != null) {
			log.info(browser + " DriverInitiated");
		}
		wait.implicitWait(driver, 20);
		wait.pageLoadTimeout(driver, 20);
	}

	@AfterClass
	public void browserQuit() {
		driver.close();
	}

}
