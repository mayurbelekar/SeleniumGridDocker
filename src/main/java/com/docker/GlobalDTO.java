package com.docker;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GlobalDTO {

	private String browser = null;
	private String mode = null;
	private String hubUrl = null;
	private int nodeCount = 0;
	private GlobalDTO globalData = null;
	private WebDriver driver = null;
	private WebElement element = null;
	private String sessionId = null;
	private String nodeName = null;
	
	public String getBrowser() {
		return browser;
	}
	
	public void setBrowser(String browser) {
		this.browser = browser;
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
}
