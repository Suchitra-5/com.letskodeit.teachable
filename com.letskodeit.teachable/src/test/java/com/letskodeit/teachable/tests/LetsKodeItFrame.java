package com.letskodeit.teachable.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.letskodeit.teachable.pages.LetsKodeItFramePage;
import com.letskodeit.teachable.pages.LetsKodeItFramePageFactory;
import Util.UtilKit;

public class LetsKodeItFrame extends LetsKodeItPracticeBase{

	@Test(enabled=false, dataProvider = "coursesProvider", groups="frame")
	public void coursesSearchTest(String course){
		
		//LetsKodeItFramePage LKFP = new LetsKodeItFramePage(driver); // This constructor will switch to the frame
		LetsKodeItFramePageFactory LKFP = new LetsKodeItFramePageFactory(driver); // This constructor will switch to the frame
		
		LKFP.coursesSearchTextbox().clear();
		LKFP.coursesSearchTextbox().sendKeys(course);
		
		LKFP.searchCourseButton().click();
		
		UtilKit.suspendAction(20000);
		Assert.assertTrue(LKFP.verifyCourseList(), "Course Search Failed");
		
	}
	
	@Test(enabled=true, dataProvider = "categoryProvider", groups="frame")
	public void categoryTest(String category){
		
		//LetsKodeItFramePage LKFP = new LetsKodeItFramePage(driver);
		LetsKodeItFramePageFactory LKFP = new LetsKodeItFramePageFactory(driver);
		
		LKFP.selectCategory(category);
		
		UtilKit.suspendAction(3000);
		
		Assert.assertTrue(LKFP.verifyCategory(category), "Category Selection Failed");
	}
	
	@DataProvider
	public Object[][] coursesProvider(){
		
			return UtilKit.getTestData(project, application, "coursesSearchTest" );
	}
	@DataProvider
	public Object[][] categoryProvider(){
		
			return UtilKit.getTestData(project, application, "categoryTest" );
	}

}
