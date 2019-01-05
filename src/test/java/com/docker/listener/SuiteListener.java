package com.docker.listener;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import com.docker.commands.DockerCommands;

public class SuiteListener implements ISuiteListener {
	
	String hubContainer = "selenium-hub";
	DockerCommands command = new DockerCommands();
	
	/**
	 * Start Selenium Grid Hub
	 * Check the node count should less than 12
	 * Then create a erquired browser node
	 */
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
		System.out.println("Starting docker hub");
		command.executeProcessBuilder(command.startHub());
		
	}
	
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
	}
	

}
