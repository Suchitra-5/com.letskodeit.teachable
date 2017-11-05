package com.letskodeit.teachable.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.letskodeit.teachable.pages.LetsKodeItTablePage;
import Util.UtilKit;

public class LetsKodeitTable extends LetsKodeItPracticeBase {
	
	@Test(enabled=true, groups="table")
	public void tableTest(){
		
		LetsKodeItTablePage LKTP = new LetsKodeItTablePage(driver);
		
		LKTP.printTableContent();
			
	}
	
	@Test(enabled=true, groups="table")
		public void testHideAndShow() throws InterruptedException{

			LetsKodeItTablePage LKTP = new LetsKodeItTablePage(driver);
			
			LKTP.hideTestbox().click();
			UtilKit.suspendAction(2000);
			Assert.assertTrue(LKTP.verifyTextboxHiden(), "Hide Textbox Failed");
			
			UtilKit.suspendAction(5000);
			
			LKTP.showTextbox().click();
			Assert.assertTrue(LKTP.verifyTextboxShow(), "Show Textbox Failed");
			LKTP.displayedTextbox().sendKeys("Test Show");
			
			Thread.sleep(3000);
			
		}

}
