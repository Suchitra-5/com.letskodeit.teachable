package com.letskodeit.teachable.tests;

import com.letskodeit.teachable.util.UtilKit;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class LetsKodeItPracticeBase {
	public WebDriver driver;
	String myBrowser = "firefox";
	String application = "com.letskodeit.teachable";
	String project = application; // In this case the project is the same name as the application
	String thisClassName = this.getClass().getName();
	
	
	//The Browser parameter is optional and is specified in the testng suite .xml file 
	@Parameters("browser")
	@BeforeClass(groups = {"selects","table","switches","actions","frame"})
	public void startClass(@Optional("firefox") String browser){
		if(!browser.isEmpty())
			myBrowser = browser;
		driver = UtilKit.initTest(project, application, myBrowser, thisClassName);
		System.out.println("Initialization completed");
		
	}
	
	@BeforeMethod(groups = {"selects","table","switches","actions","frame"})
	public void startMethod(Method method) {
		UtilKit.initMethod(method.getName());
	}
	
	@AfterMethod(groups = {"selects","table","switches","actions","frame"})
	public void stopMethod(ITestResult result){
		UtilKit.terminateMethod(driver, result);
		
	}
	
	@AfterClass(groups = {"selects","table","switches","actions","frame"})
	public void stopClass(){
		UtilKit.terminateTest(driver);
	}
	

}
