package com.docker.action;

import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;

public class TestAction {

	@BeforeTest
	public void initializeTest(ITestContext context) {
		context.getCurrentXmlTest().getParameter("browser");
		context.getCurrentXmlTest().getParameter("execType");
		context.getCurrentXmlTest().getParameter("nodeCount");
	}
}
