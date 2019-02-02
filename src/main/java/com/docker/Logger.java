package com.docker;

import java.io.File;

import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;


public class Logger {
	
	static String loggerProp = System.getProperty("user.dir") +
			File.separator + "src" +
			File.separator + "test" +
			File.separator + "resources" +
			File.separator + "logger" +
			File.separator + "log4j.properties";
	
	public synchronized static String getClassName() {
		try {
			return Thread.currentThread().getContextClassLoader().loadClass(Thread.currentThread().getStackTrace()[4].getClassName()).getName();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public synchronized static org.apache.log4j.Logger getLogger() {
		
		PropertyConfigurator.configure(loggerProp);
		org.apache.log4j.Logger.getRootLogger().setLevel(Level.INFO);
		return org.apache.log4j.Logger.getLogger(getClassName());
	}
	
	public synchronized static void info(String message) {
		getLogger().info(message);
		Reporter.log(message);
	}
	
	public synchronized static void debug(String message) {
		getLogger().debug(message);
		Reporter.log(message);
	}
	
	public synchronized static void warn(String message) {
		getLogger().warn(message);
		Reporter.log(message);
	}
	
	public synchronized static void error(String message) {
		getLogger().error(message);
		Reporter.log(message);
	}
	
	public synchronized static void fatal(String message) {
		getLogger().fatal(message);
		Reporter.log(message);
	}
}
