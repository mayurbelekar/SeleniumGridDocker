package com.docker;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;

public class SelWebDriver {

	
	WebDriver driver;
	GlobalDTO globalData = null;
	DesiredCapabilities capabilities;
	
	public enum Mode {
		Local,
		Remote,
		Docker,
		SuaceLabs,
		BrowserStack
	};
	
	public enum Browser {
		Firefox,
		Chrome,
		InternetExplorer,
		Safari,
		Android,
		IOS,
		
	}
	
	public enum WiniumOptions {
		WinDesktop,
		WinMobile
	}
	

	public SelWebDriver(GlobalDTO globalData) {
		this.globalData = globalData;
	}
	
	public WiniumDriver getWiniumDesktopDriver() {
		
		DesktopOptions options = new DesktopOptions();
		options.setApplicationPath(globalData.getDesktopApplication());
		
		WiniumDriverService service = new WiniumDriverService.Builder()
				.usingDriverExecutable(new File(""))
				.usingAnyFreePort()
				.withVerbose(true)
				.withSilent(false)
				.buildDesktopService();
		
		WiniumDriver driver = new WiniumDriver(service, options);
		
		return driver;
	}
	
	public WebDriver getWebDriver() {
		Mode mode = Mode.valueOf(globalData.getMode());
		Browser browser = Browser.valueOf(globalData.getBrowser());
		switch (mode) {
		case Local:
			driver = this.getLocalDriver(browser);
			break;

		case Remote:
			// TODO
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
			try {
				capabilities.setCapability(CapabilityType.PLATFORM_NAME, globalData.getPlatformName());
				capabilities.setCapability(CapabilityType.BROWSER_VERSION, globalData.getBrowserVersion());
				driver = new RemoteWebDriver(new URL("https://"+globalData.getSauceLabsUsername()+":"+
						globalData.getSauceLabsAccessKey()+"@ondemand.saucelabs.com:443/wd/hub"), capabilities);
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case BrowserStack:
			// TODO
			break;
			
		default:
			// TODO
			break;
		}
		
		return driver;
	}
	
	public WebDriver getLocalDriver(Browser browser) {
		getWebDriverPath();
		
		switch (browser) {
		case Firefox:
			// TODO
			System.setProperty("webdriver.firefox.driver", globalData.getDriverPath());
			driver = new FirefoxDriver(getDesiredCapabilities(browser));
			break;

		case Chrome:
			// TODO
			System.setProperty("webdriver.chrome.driver", globalData.getDriverPath());
			driver = new ChromeDriver(getDesiredCapabilities(browser));
			break;
			
		case InternetExplorer:
			// TODO
			System.setProperty("webdriver.ie.driver", globalData.getDriverPath());
			driver = new InternetExplorerDriver(getDesiredCapabilities(browser));
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
		Proxy proxy = null;
		if(globalData.getProxyType().equals("manualProxy")) {
			String proxyConfig = globalData.getManualProxyIP()+":"+globalData.getManualProxyPort();
			proxy = new Proxy()
					.setHttpProxy(proxyConfig)
					.setFtpProxy(proxyConfig)
					.setSslProxy(proxyConfig)
					.setSocksProxy(proxyConfig);
			proxy.setProxyType(ProxyType.MANUAL);
		} else if(globalData.getProxyType().equals("automaticProxy")) {
			proxy = new Proxy()
					.setProxyAutoconfigUrl(globalData.getAutomaticProxyUrl());
			proxy.setProxyType(ProxyType.PAC);
		}
		
		switch (browser) {
			
			case Firefox:
				capabilities = DesiredCapabilities.firefox();
				FirefoxOptions fOptions = new FirefoxOptions();
				if(globalData.getExecType().equals("headless")) {
					fOptions.setHeadless(true);
				}
				fOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				fOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				fOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
				fOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
				fOptions.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
				fOptions.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, PageLoadStrategy.NORMAL);
				capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, fOptions);
				break;
			
			case Chrome:
				
				capabilities = DesiredCapabilities.chrome();
				ChromeOptions cOptions = new ChromeOptions();
				cOptions.addArguments("start-maximized");
				if(globalData.getExecType().equals("headless")) {
					cOptions.setHeadless(true);
				}
				cOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				cOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				cOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
				cOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
				cOptions.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
				cOptions.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, PageLoadStrategy.NORMAL);
				capabilities.setCapability(ChromeOptions.CAPABILITY, cOptions);
				break;
	
			case InternetExplorer:
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability("requireWindowFocus", true);
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
				capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
				break;
				
			default:
				break;
		}
		//capabilities.setJavascriptEnabled(true);
		//capabilities.setAcceptInsecureCerts(true);
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
