package com.docker;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SelWebDriver {

	
	WebDriver driver;
	GlobalDTO globalData;
	DesiredCapabilities capabilities;
	
	public enum Mode {
		Local,
		Docker,
		SuaceLabs,
		BrowserStack
	};
	
	public enum Browser {
		Firefox,
		Chrome,
		InternetExplorer,
		Safari
	}

	public SelWebDriver(GlobalDTO globalData) {
		this.globalData = globalData;
	}
	public void getWebDriver() {
		Mode mode = Mode.valueOf(globalData.getMode());
		Browser browser = Browser.valueOf(globalData.getBrowser());
		switch (mode) {
		case Local:
			driver = this.getLocalDriver(browser);
			break;

		case Docker:
			try {
				capabilities = this.getDesiredCapabilities(browser);
				driver = new RemoteWebDriver(new URL(globalData.getHubUrl()), capabilities);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case SuaceLabs:
			// TODO
			break;
			
		case BrowserStack:
			// TODO
			break;
			
		default:
			// TODO
			break;
		}
	}
	
	public WebDriver getLocalDriver(Browser browser) {
		getWebDriverPath();
		switch (browser) {
		case Firefox:
			// TODO
			System.setProperty("webdriver.firefox.driver", globalData.getDriverPath());
			driver = new FirefoxDriver();
			break;

		case Chrome:
			// TODO
			System.setProperty("webdriver.chrome.driver", "");
			driver = new ChromeDriver();
			break;
			
		case InternetExplorer:
			// TODO
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("requireWindowFocus", true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
			System.setProperty("webdriver.ie.driver", "");
			driver = new InternetExplorerDriver(capabilities);
			break;
			
		case Safari:
			// TODO
			break;
			
		default:
			break;
		}
		return driver;
	}
	
	public DesiredCapabilities getDesiredCapabilities(Browser browser) {
		DesiredCapabilities capabilities = null;
		switch (browser) {
		
		case Firefox:
			capabilities = DesiredCapabilities.firefox();
			FirefoxOptions fOptions = new FirefoxOptions();
			if(globalData.getExecType().equals("headless")) {
				fOptions.setHeadless(true);
			}
			capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, fOptions);
			break;
		
		case Chrome:
			capabilities = DesiredCapabilities.chrome();
			ChromeOptions cOptions = new ChromeOptions();
			cOptions.addArguments("start-maximized");
			if(globalData.getExecType().equals("headless")) {
				cOptions.setHeadless(true);
			}
			capabilities.setCapability(ChromeOptions.CAPABILITY, cOptions);
			break;

		default:
			break;
		}
		capabilities.setJavascriptEnabled(true);
		capabilities.setAcceptInsecureCerts(true);
		return capabilities;
	}
	
	public void getWebDriverPath() {
		if(globalData.getBrowser().equals("Chrome")) {
			if(System.getProperty("os.name").contains("Linux")) {
				globalData.setDriverPath(Constants.CHROME_LINUX_DRIVER_PATH);
			} else if(System.getProperty("os.name").contains("Mac")) {
				globalData.setDriverPath(Constants.CHROME_MAC_DRIVER_PATH);
			} else if(System.getProperty("os.name").contains("Windows")) {
				globalData.setDriverPath(Constants.CHROME_WINDOWS_DRIVER_PATH);
			}
		} if(globalData.getBrowser().equals("Firefox")) {
			if(System.getProperty("os.name").contains("Linux")) {
				globalData.setDriverPath(Constants.FIREFOX_LINUX_DRIVER_PATH);
			} else if(System.getProperty("os.name").contains("Mac")) {
				globalData.setDriverPath(Constants.FIREFOX_MAC_DRIVER_PATH);
			} else if(System.getProperty("os.name").contains("Windows")) {
				globalData.setDriverPath(Constants.FIREFOX_WINDOWS_DRIVER_PATH);
			}
		} if(globalData.getBrowser().equals("IE")) {
			globalData.setDriverPath(Constants.IE_DRIVER_PATH);
		}
	}
}
