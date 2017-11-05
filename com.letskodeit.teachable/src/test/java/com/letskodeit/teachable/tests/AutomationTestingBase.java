package com.letskodeit.teachable.tests;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.internal.TestResult;

import Util.UtilKit;

public class AutomationTestingBase {
	
	WebDriver driver = null;
	String project = "com.letskodeit.teachable";
	String application = project;
	String browser = "firefox";
	String className = this.getClass().getName();

	
	@BeforeClass (groups = {"grid"})
	@Parameters ("browser")
	public void startClass(@Optional("firefox") String browser){
		
		UtilKit.initTest(project, application, browser, className);
	}
	
	@BeforeMethod(groups = {"gred"})
		public void startMethod(Method method){
			UtilKit.initMethod(method);
	}
	
	@AfterMethod(groups = {"grid"})
		public void stopMetod(TestResult result){
		
			UtilKit.terminateMethod(driver, result);
	}
	

}
