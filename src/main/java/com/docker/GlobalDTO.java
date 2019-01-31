package com.docker;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GlobalDTO {

	private String browser = null;
	private String browserVersion = null;
	private String platformName = null;
	private String mode = null;
	private String hubUrl = null;
	private int nodeCount = 0;
	private GlobalDTO globalData = null;
	private WebDriver driver = null;
	private WebElement element = null;
	private String sessionId = null;
	private String nodeName = null;
	private String execType = null;
	private String driverpath = null;
	private String proxyType = null;
	private String manualProxyIP = null;
	private String manualProxyPort = null;
	private String automaticProxyUrl = null;
	private String sauceLabsUsername = null;
	private String sauceLabsAccessKey = null;
	private String desktopApplication = null;
	
	public String getBrowser() {
		return browser;
	}
	
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}
	
	public String getBrowserVersion() {
		return browserVersion;
	}
	
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	
	public String getPlatformName() {
		return platformName;
	}
	
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public String getMode() {
		return mode;
	}
	
	public void setHubUrl(String hubUrl) {
		this.hubUrl = hubUrl;
	}
	
	public String getHubUrl() {
		return hubUrl;
	}
	
	public void setNodeCount(int nodeCount) {
		this.nodeCount = nodeCount;
	}
	
	public int getNodeCount() {
		return nodeCount;
	}
	
	public void setGlobalData(GlobalDTO globalData) {
		this.globalData = globalData;
	}
	
	public GlobalDTO getGlobalData() {
		return globalData;
	}
	
	public void setWebDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebDriver getWebDriver() {
		return driver;
	}
	
	public void setWebElement(WebElement element) {
		this.element = element;
	}
	
	public WebElement getWebElement() {
		return element;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	public String getNodeName(){
		return nodeName;
	}
	
	public void setExecType(String execType) {
		this.execType = execType;
	}
	
	public String getExecType() {
		return execType;
	}
	
	public void setDriverPath(String driverpath) {
		this.driverpath = driverpath;
	}
	
	public String getDriverPath() {
		return driverpath;
	}
	
	public void setProxyType(String proxyType) {
		this.proxyType = proxyType;
	}
	
	public String getProxyType() {
		return proxyType;
	}
	
	public void setManualProxyIP(String manualProxyIP) {
		this.manualProxyIP = manualProxyIP;
	}
	
	public String getManualProxyIP() {
		return manualProxyIP;
	}
	
	public void setManualProxyPort(String manualProxyPort) {
		this.manualProxyPort = manualProxyPort;
	}
	
	public String getManualProxyPort() {
		return manualProxyPort;
	}
	
	public void setAutomaticProxyUrl(String automaticProxyUrl) {
		this.automaticProxyUrl = automaticProxyUrl;
	}
	
	public String getAutomaticProxyUrl() {
		return automaticProxyUrl;
	}
	
	public void setSauceLabsUsername(String sauceLabsUsername) {
		this.sauceLabsUsername = sauceLabsUsername;
	}
	
	public String getSauceLabsUsername() {
		return sauceLabsUsername;
	}
	
	public void setSauceLabsAccessKey(String sauceLabsAccessKey) {
		this.sauceLabsAccessKey = sauceLabsAccessKey;
	}
	
	public String getSauceLabsAccessKey() {
		return sauceLabsAccessKey;
	}
	
	public void setDesktopApplication(String desktopApplication) {
		this.desktopApplication = desktopApplication;
	}
	
	public String getDesktopApplication() {
		return desktopApplication;
	}
	
}
