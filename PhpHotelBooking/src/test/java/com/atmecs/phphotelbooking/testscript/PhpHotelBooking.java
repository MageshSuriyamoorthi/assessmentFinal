package com.atmecs.phphotelbooking.testscript;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.at.ecs.phphotelbooking.scriptbase.PhpHotelBookingBase;
import com.atmecs.phphotelbooking.testbase.TestSuiteBase;
import com.atmecs.phphotelbooking.utils.AssertionAndValidationManager;

public class PhpHotelBooking extends TestSuiteBase {

	AssertionAndValidationManager assertion = new AssertionAndValidationManager();
	PhpHotelBookingBase base = new PhpHotelBookingBase();
	JavascriptExecutor javascriptexecuter;
	List<String> prices, sortedPrices;

	int totallist, ratinglist;
	String alteredvalue;

	@BeforeClass
	public void invokeUrl() {

		utils.geturl(driver, propertyreader.getLocatorValue("config.urlname"));
		utils.maximize(driver);

		log.info("STEP 1:URL has been initiated in the browser");

		actual = utils.getTitle(driver);

		expected = assertion.getdataval("phptitle", "Title", "phptitle");

		if (actual.equalsIgnoreCase(expected)) {
			log.info("STEP 2: USER has been landed into the home page.");
		} else
			log.info("STEP 2:USER hasn't been landed into the home page.");
	}

	@Test
	public void searchAndBookHotel() {

		actual = utils.FindElement(driver, propertyreader.getLocatorValue("loc.hotels.tab")).getAttribute("class");

		if (actual.contains("active")) {
			log.info("STEP 3:In Default Hotels tab has been selected ");
		} else {
			log.info("Step 3:In default Hotels tab hasn't been selected");
		}
		utils.click(driver, propertyreader.getLocatorValue("loc.destination.click"));

		utils.sendKeys(driver, propertyreader.getLocatorValue("loc.destination.enter"),
				assertion.getdataval("formdata", "TestData", "Destination"));

		log.info("STEP 4:Destination Banglore has been entered");

		wait.ThreadWait(2000);

		utils.forward(driver, propertyreader.getLocatorValue("loc.destination.enter"));

		date = utils.getCheckInDate(assertion.getdataval("formdata", "TestData", "CheckinDateDiff"));

		utils.sendKeys(driver, propertyreader.getLocatorValue("loc.checkin.date"), date);

		log.info("STEP 5: CheckIn date has been entered");

		utils.forward(driver, propertyreader.getLocatorValue("loc.checkin.date"));

		date = utils.getCheckOutDate(date, assertion.getdataval("formdata", "TestData", "CheckoutDateDiff"));

		utils.sendKeys(driver, propertyreader.getLocatorValue("loc.checkout.date"), date);

		log.info("STEP 6:CheckOut Date has been entered");

		utils.forward(driver, propertyreader.getLocatorValue("loc.checkout.date"));

		base.clickParentsData(driver, propertyreader.getLocatorValue("loc.adult.plusbutton"),
				assertion.getdataval("formdata", "TestData", "Parents"));

		base.clickChildrenData(driver, propertyreader.getLocatorValue("loc.child.plusbutton"),
				assertion.getdataval("formdata", "TestData", "Childrens"));

		log.info("STEP 7:Passengers list has been entered");

		utils.click(driver, propertyreader.getLocatorValue("loc.search.search"));

		log.info("STEP 8:Search button has been clicked and navigated to home page");

		actual = utils.getText(driver, propertyreader.getLocatorValue("loc.header.title"));

		expected = assertion.getdataval("formdata", "TestData", "SearchHeader");

		assertion.assertequals(actual, expected);

		log.info("STEP 9: User has sucessfully redirected to the respective page.");

		utils.click(driver, propertyreader.getLocatorValue("loc.modify.button"));

		base.modifySearch(driver, "loc.modify.place", "Place");
		
		log.info("Place has been validated");

		log.info("STEP 10:Modified search has been validated");

		totalsearch = assertion.getdataval("formdata", "TestData", "totalsearch");

		totalsearchvalue = Integer.parseInt(totalsearch);

		utils.click(driver, propertyreader.getLocatorValue("loc.dismiss.button"));

		element = driver.findElement(utils.locator(propertyreader.getLocatorValue("loc.loadmore.button")));

		base.viewMore(element, driver);

		log.info("STEP 11:View More Button is Clicked");

		elementlist = utils.FindElements(driver, propertyreader.getLocatorValue("loc.totallist.hotels"));

		ratelist = utils.FindElements(driver, propertyreader.getLocatorValue("loc.four.rating"));

		for (totallist = 0; totallist <= elementlist.size(); totallist++)

		{
			for (ratinglist = 0; ratinglist <= ratelist.size(); ratinglist++) {

				alteredvalue = propertyreader.getLocatorValue("loc.totallist.individual").replace("*", totallist + "");

				element = utils.FindElement(driver, alteredvalue);
				base.viewMoreLoop(element);

				actual = utils.getText(driver, alteredvalue);

				alteredvalue = propertyreader.getLocatorValue("loc.four.ratingindividual").replace("*",
						ratinglist + "");

				element = utils.FindElement(driver, alteredvalue);
				base.viewMoreLoop(element);

				expected = utils.getText(driver, alteredvalue);
				if (actual.equals(expected)) {

					alteredvalue = propertyreader.getLocatorValue("loc.get.price").replace("*", totallist + "");

					element = utils.FindElement(driver, alteredvalue);
					base.viewMoreLoop(element);

					actual = utils.getText(driver, alteredvalue);

					prices = new ArrayList<String>();

					prices.add(actual);

				}
				Collections.sort(prices);
			}
		}
		sortedPrices = new ArrayList<String>(prices);
		
	}
}