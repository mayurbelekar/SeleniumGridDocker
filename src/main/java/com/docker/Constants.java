package com.docker;

import java.io.File;

public class Constants {

	public static final String PROJECT_PATH = System.getProperty("user.dir") + File.separator;
	public static final String TEST_RESOURCES_PATH = PROJECT_PATH + "src" + File.separator +"test" + File.separator + "resources" + File.separator;
	public static final String TEST_JAVA_PATH = PROJECT_PATH + "src" + File.separator +"test" + File.separator + "java" + File.separator;
	
	public static final String FIREFOX_NODE = "FIREFOX_";
	public static final String CHROME_NODE = "CHROME_";
	public static final String FIREFOX_BROWSER = "Firefox";
	public static final String CHROME_BROWSER = "Chrome";
	
	public static final String CHROME_WINDOWS_DRIVER_PATH = TEST_RESOURCES_PATH + "chromedriver" + File.separator + "chromedriver.exe";
	public static final String CHROME_LINUX_DRIVER_PATH = TEST_RESOURCES_PATH + "chromedriver" + File.separator + "chromedriver_linux";
	public static final String CHROME_MAC_DRIVER_PATH = TEST_RESOURCES_PATH + "chromedriver" + File.separator + "chromedriver_mac";
	
	public static final String FIREFOX_WINDOWS_DRIVER_PATH = TEST_RESOURCES_PATH + "firefoxdriver" + File.separator + "geckodriver.exe";
	public static final String FIREFOX_LINUX_DRIVER_PATH = TEST_RESOURCES_PATH + "firefoxdriver" + File.separator + "geckodriver_linux";
	public static final String FIREFOX_MAC_DRIVER_PATH = TEST_RESOURCES_PATH + "firefoxdriver" + File.separator + "geckodriver_mac";

	public static final String IE_DRIVER_PATH = TEST_RESOURCES_PATH + "iedriver" + File.separator + "IEDriverServer.exe";
}
