package com.letskodeit.teachable.tests;

import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.letskodeit.teachable.pages.LetsKodeItSelectsPage;
import com.letskodeit.teachable.pages.LetsKodeItSwitchPage;
import com.letskodeit.teachable.util.UtilKit;

public class LetsKodeItSwitch extends LetsKodeItPracticeBase {
	
	@Test(enabled=true, groups="switches")
	public void switchToWindowTest(){

		LetsKodeItSwitchPage LKSP = new LetsKodeItSwitchPage(driver);
		LetsKodeItSelectsPage LKSeP = new LetsKodeItSelectsPage(driver);
		
		LKSP.openWindowButton().click(); // Open a new window
		LKSP.switchToURL("https://letskodeit.teachable.com/courses"); // Switch to the new window
		UtilKit.suspendAction(3000);
		driver.manage().window().maximize();
		Assert.assertTrue(LKSP.verifyNewWindowURL("https://letskodeit.teachable.com/courses"), "Switch FAILED");
		LKSP.searchTextbox().sendKeys("selenium");
		UtilKit.suspendAction(5000);
		driver.close(); // Close the new window
		UtilKit.suspendAction(5000);
		LKSP.switchToURL("https://letskodeit.teachable.com/p/practice"); // Switch to the main window
		Assert.assertTrue(LKSP.verifyNewWindowURL("https://letskodeit.teachable.com/p/practice"), "Switch FAILED");
		UtilKit.suspendAction(5000);

		/*
		 * The next block  does a switch between the windows twice
		 */
		LKSP.openWindowButton().click(); 
		LKSP.switchToURL("https://letskodeit.teachable.com/courses");
		Assert.assertTrue(LKSP.verifyNewWindowURL("https://letskodeit.teachable.com/courses"), "Switch FAILED");
		driver.manage().window().maximize();
		LKSP.searchTextbox().sendKeys("selenium");
		UtilKit.suspendAction(5000);
		Dimension myDimension = new Dimension(200,200);
		driver.manage().window().setSize(myDimension);
		LKSP.switchToURL("https://letskodeit.teachable.com/p/practice"); // Switch to the main window
		Assert.assertTrue(LKSP.verifyNewWindowURL("https://letskodeit.teachable.com/p/practice"), "Switch FAILED");
		LKSeP.clickOnRadio("Benz");
		UtilKit.suspendAction(5000);

		// Again the same
		LKSP.switchToURL("https://letskodeit.teachable.com/courses");
		Assert.assertTrue(LKSP.verifyNewWindowURL("https://letskodeit.teachable.com/courses"), "Switch FAILED");
		driver.manage().window().maximize();
		UtilKit.suspendAction(5000);
		
		LKSP.switchToURL("https://letskodeit.teachable.com/p/practice"); // Switch to the main window
		Assert.assertTrue(LKSP.verifyNewWindowURL("https://letskodeit.teachable.com/p/practice"), "Switch FAILED");
		UtilKit.suspendAction(5000);
	}	
		
	@Test(enabled=false, groups="switches")
	public void switchToTabTest(){

		LetsKodeItSwitchPage LKSP = new LetsKodeItSwitchPage(driver);

		LKSP.openTabButton().click();
		UtilKit.suspendAction(5000);
		LKSP.switchToURL("https://letskodeit.teachable.com/courses");
		Assert.assertTrue(LKSP.verifyNewWindowURL("https://letskodeit.teachable.com/courses"), "Switch FAILED");
		driver.close();
		UtilKit.suspendAction(5000);
		LKSP.switchToURL("https://letskodeit.teachable.com/p/practice");
		Assert.assertTrue(LKSP.verifyNewWindowURL("https://letskodeit.teachable.com/p/practice"), "Switch FAILED");
		UtilKit.suspendAction(5000);
		
	}
	
	@Test(enabled = false, groups="switches")
		public void switchToAlertTest(){
			
			LetsKodeItSwitchPage LKSP = new LetsKodeItSwitchPage(driver);
			
			LKSP.nameTextBox().sendKeys("Efrain");
			LKSP.alertButton().click();
			Assert.assertTrue(LKSP.verifyAlert(), "Alert was Not Verified");
			UtilKit.logger.info("Alert Text :" + LKSP.alertText());
			UtilKit.suspendAction(3000);
			LKSP.OKAlert();
			
			LKSP.nameTextBox().sendKeys("Efrain");
			LKSP.confirmButton().click();
			Assert.assertTrue(LKSP.verifyAlert(), "Alert was Not Verified");
			UtilKit.logger.info("Alert Text :" + LKSP.alertText());
			UtilKit.suspendAction(3000);
			LKSP.OKAlert();

			LKSP.nameTextBox().sendKeys("Efrain");
			LKSP.confirmButton().click();
			Assert.assertTrue(LKSP.verifyAlert(), "Alert was Not Verified");
			UtilKit.logger.info("Alert Text :" + LKSP.alertText());
			UtilKit.suspendAction(3000);
			LKSP.cancelAlert();


		}

	}

