package com.docker;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SelWebDriver {

	
	WebDriver driver;
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

	public void getWebDriver(GlobalDTO globalData) {
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
		
		switch (browser) {
		case Firefox:
			// TODO
			break;

		case Chrome:
			// TODO
			break;
			
		case InternetExplorer:
			// TODO
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
			break;
		
		case Chrome:
			capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			break;

		default:
			break;
		}
		capabilities.setJavascriptEnabled(true);
		capabilities.setAcceptInsecureCerts(true);
		return capabilities;
	}
}
