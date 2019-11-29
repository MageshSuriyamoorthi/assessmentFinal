package com.atmecs.phphotelbooking.utils;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.atmecs.phphotelbooking.helper.ExcelReader;
import com.atmecs.phphotelbooking.reports.Log4j;
import com.atmecs.phphotelbooking.testbase.Classpaths;
/**
 * 
 * This method used to assert and validate with test data.
 * 
 *  @author  magesh.suriyamoorthi
*/
public class AssertionAndValidationManager {
	private Log4j log = new Log4j();
	ExcelReader datareader =new ExcelReader();


/**
 * getting data value by giving sheet no, name, index
 * 
 * @param sheetno
 * @param sheetname
 * @param index
 * @return
 */
	public String getdataval(String sheetname, String columnname, String testcase) {
		log.info("Getting Value from an testdata");
		String dataname = datareader.getData(Classpaths.TESTDATAFILE,sheetname, columnname, testcase);
		log.info("The value is: " + dataname);
		return dataname;
	}

/**
 * 
 * asserting two strings
 * @param actual
 * @param expected
 */
	public void assertequals(String actual, String expected) {
		log.info("Assertion Starts");
		Assert.assertEquals(actual, expected, "Assert not equals");
		log.info("Assertion Done and the actual and expected are equal");
	}

//asserting string with list 
	public void listassertequals(String actual, List<String> listnames, String message) {
		log.info("Performing assertion");
		Assert.assertEquals(actual, listnames, "Assert not Equals");
		log.info("Assertion Done and the actual and expected are equal");
	}

//asserting two strings 
	public void Stringassertequals(String actual, String expected, String message) {
		log.info("Assertion starts");
		assertEquals(actual, expected);
		log.info("Assertion Done and the actual and expected are equal");

	}

//split a string into two
	public List<String> Splittext(String element, String text) {
		log.info("splittext performs");
		String data = text;
		String[] contents = data.split(element);
		List<String> list = new ArrayList<String>();
		for (String arr : contents)
			list.add(arr);
		log.info("text splitted");
		return list;

	}

//is display method
	public boolean verifyTrue(boolean condition, String message) {
		boolean result = false;
		try {
			Assert.assertTrue(condition);
			log.info("PASS : " + message);
			result = true;
		} catch (AssertionError assertionError) {

			result = false;
		}
		return result;

	}

//splitting testdata which is getting from excel
	public List<String> getexpected(String symbol, String sheetname, String columnname, String text) {
		log.info("split text from testdata");
		String data = datareader.getData(Classpaths.TESTDATAFILE,sheetname, columnname, text);
		String[] contents = data.split(symbol);
		List<String> list = new ArrayList<String>();
		for (String arr : contents)
			list.add(arr);
		log.info("Testdata splitted");
		return list;
	}

}

