package com.docker.action;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import com.docker.Constants;
import com.docker.GlobalDTO;
import com.docker.commands.DockerCommands;

public class DockerAction {

	DockerCommands commands = new DockerCommands();
	
	public void getHubUrl(GlobalDTO globalData) {
		String hubUrl = null;
		List<String> commandResponse = commands.executeProcessBuilder(commands.containerLogs("selenium-hub"));
		for(String resp: commandResponse) {
			if(resp.contains("/wd/hub")) {
				hubUrl = resp.substring(resp.indexOf("http"), resp.length());
				System.out.println("Selenium Grid hub url"+hubUrl);
				globalData.setHubUrl(hubUrl);
				break;
			}
		}
	}
	
	public int getExistingNodeCount() {
		int count = 0;
		List<String> commandResponse = commands.executeProcessBuilder(commands.listUpContainers());
		for(String node: commandResponse) {
			if(node.contains("node-chrome-debug") || node.contains("node-firefox-debug")) {
				System.out.println(node);
				count++;
			}
		}
		return count;
	}
	
	public void stopAndRemoveContainers(GlobalDTO globalData) throws InterruptedException {
		List<String> commandResponse = new ArrayList<String>();
		/*for(String nodeName: globalData.getNodeName()) {
			commandResponse = commands.executeProcessBuilder(commands.stopContainer(nodeName));
			System.out.println(commandResponse);
			Thread.sleep(5000);
			commandResponse = commands.executeProcessBuilder(commands.removeContainer(nodeName));
			System.out.println(commandResponse);
			Thread.sleep(5000);
		}*/
		commandResponse = commands.executeProcessBuilder(commands.stopContainer(globalData.getNodeName()));
		System.out.println(commandResponse);
		Thread.sleep(5000);
		commandResponse = commands.executeProcessBuilder(commands.removeContainer(globalData.getNodeName()));
		System.out.println(commandResponse);
		Thread.sleep(5000);
	}
	
	public void launchSeleniumNodes(GlobalDTO globalData) {
		DockerCommands commands = new DockerCommands();
		List<String> commandResponse;
		String browserName = globalData.getBrowser();
		int nodesAvailable = 16 - getExistingNodeCount();
		int j=1;
		for(int i=1; i <= globalData.getNodeCount(); i++) {
			for(; j<nodesAvailable+1; j++) {
				commandResponse = new ArrayList<String>();
				if(browserName.equals(Constants.CHROME_BROWSER)){
					commandResponse = commands.executeProcessBuilder(commands.lauchChromeNode(Constants.CHROME_NODE+String.valueOf(j)));
				} else if(browserName.equals(Constants.FIREFOX_BROWSER)){
					commandResponse = commands.executeProcessBuilder(commands.lauchChromeNode(Constants.FIREFOX_NODE+String.valueOf(j)));
				}
				if(!commandResponse.get(0).contains("Error response from daemon")) {
					System.out.println(commandResponse);
					break;
				}
			}
			
			
		}
	}
	
	public static void main(String[] as) throws InterruptedException {
		DockerCommands commands = new DockerCommands();
		System.out.println(System.getProperty("os.name"));
		WebDriver driver1;
		WebDriver driver;
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		try {
			driver = new RemoteWebDriver(new URL("http://172.17.0.2:4444/wd/hub"), capabilities);
			driver.get("http://www.facebook.com");
			Thread.sleep(10000);
			SessionId sessionId = ((RemoteWebDriver)driver).getSessionId();
			System.out.println("SessionId: "+sessionId.toString());
			
			driver1 = new RemoteWebDriver(new URL("http://172.17.0.2:4444/wd/hub"), capabilities);
			driver1.get("http://www.facebook.com");
			Thread.sleep(10000);
			sessionId = ((RemoteWebDriver)driver1).getSessionId();
			System.out.println("SessionId: "+sessionId.toString());
			
			System.out.println("Closing browser");
			driver.quit();
			driver1.quit();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
