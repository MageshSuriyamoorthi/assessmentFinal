package com.atmecs.phphotelbooking.testbase;

import java.io.File;
/**
 * This method contains all paths needed for this project
 * 
 * @author magesh.suriyamoorthi
 *
 */
public class Classpaths {
	public static String USERDIR = System.getProperty("user.dir");
	
	public static String RESOURCEFILE = USERDIR + File.separator + "src" + File.separator + "test" + File.separator
			+ "resources";
	public static String PROPERTYFILE = RESOURCEFILE + File.separator + "locator" + File.separator
			+ "phphotelbooking.properties";
	public static String CHROMEFILE=USERDIR + File.separator +"lib" + File.separator + "chromedriver.exe";
	
	public static String FIREFOXFILE=USERDIR + File.separator +"lib" + File.separator + "geckodriver.exe";
	
	public static String IEFILE=USERDIR + File.separator +"lib" + File.separator + "IEDriverServer.exe";
	
	public static String LOG4JFILE = RESOURCEFILE + File.separator + "log4j" + File.separator
			+ "log4j.properties";
	
	public static String CONFIGFILE=USERDIR + File.separator + "config.properties";
	
	public static String TESTDATAFILE=RESOURCEFILE + File.separator + "testdata" + File.separator +"phphotelbooking.xlsx";
	
}
