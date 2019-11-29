package com.at.ecs.phphotelbooking.scriptbase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.atmecs.phphotelbooking.helper.PropertiesFileReader;
import com.atmecs.phphotelbooking.reports.Log4j;
import com.atmecs.phphotelbooking.utils.AssertionAndValidationManager;
import com.atmecs.phphotelbooking.utils.UtilityFiles;
import com.atmecs.phphotelbooking.utils.WaitsandSimpleAlert;

public class PhpHotelBookingBase {
	public Log4j log = new Log4j();
	private UtilityFiles utils = new UtilityFiles();
	private AssertionAndValidationManager assertion = new AssertionAndValidationManager();
	private PropertiesFileReader propertyreader = new PropertiesFileReader();
	private WaitsandSimpleAlert wait = new WaitsandSimpleAlert();
	JavascriptExecutor javascriptexecuter;

	public List<WebElement> elementlist, ratinglist;
	public List<String> list, lists, sortedrating, newratinglist;

	int person;
	String actual, expected;

	public void clickParentsData(WebDriver driver, String locator, String value) {
		person = Integer.parseInt(value);
		for (int adultPerson = 2; adultPerson < person; adultPerson++)
			utils.click(driver, locator);
	}

	public void clickChildrenData(WebDriver driver, String locator, String value) {
		person = Integer.parseInt(value);
		for (int childPerson = 0; childPerson < person; childPerson++)
			utils.click(driver, locator);
	}

	public void modifySearch(WebDriver driver, String locator, String value) {
		actual = utils.getText(driver, propertyreader.getLocatorValue(locator));
		expected = assertion.getdataval("formdata", "TestData", value);

		assertion.assertequals(actual, expected);
	}

	public void sortdata() {
		newratinglist = new ArrayList<String>();
		for (WebElement elemnt : ratinglist) {
			newratinglist.add(elemnt.getText());
		}
		sortedrating = new ArrayList<String>(newratinglist);
		Collections.sort(sortedrating);
		for (int oldindex = 0; oldindex <= elementlist.size(); oldindex++)
			for (int index = 0; index <= sortedrating.size(); index++) {
				if (sortedrating.equals(newratinglist)) {

				}
			}

	}

	public void viewMoreLoop(WebElement element) {
		if (element != null) {

			javascriptexecuter.executeScript("arguments[0].scrollIntoView(false)", element);

			wait.ThreadWait(2000);
			
			log.info("Button has been clicked");

			element.click();
		}
	}

	public void viewMore(WebElement element, WebDriver driver) {
		while (element != null) {

			javascriptexecuter = (JavascriptExecutor) driver;

			javascriptexecuter.executeScript("arguments[0].scrollIntoView(false)", element);

			wait.ThreadWait(2000);

			element.click();

			element = driver.findElement(utils.locator(propertyreader.getLocatorValue("loc.loadmore.button")));
			viewMoreLoop(element);
			
			element = driver.findElement(utils.locator(propertyreader.getLocatorValue("loc.loadmore.button")));
			viewMoreLoop(element);
			
			element = driver.findElement(utils.locator(propertyreader.getLocatorValue("loc.loadmore.button")));
			viewMoreLoop(element);
		
		}
	}
}
