package com.letskodeit.teachable.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.letskodeit.teachable.util.UtilKit;

public class LetsKodeItFramePage {
	
	WebDriver driver = null;
	Actions myActions = null;
	
	List<WebElement> framesList = null;
	
	public LetsKodeItFramePage(WebDriver driver){
		
		this.driver = driver;
		driver.switchTo().defaultContent();// With every test case ensure you start at default content
		UtilKit.suspendAction(1000);
		driver.manage().window().maximize();
		//UtilKit.scrollDown(driver);
		UtilKit.scrollToPoint(new Point(50,1250), driver);
		UtilKit.suspendAction(1000);
		driver.switchTo().frame(coursesIframe());
		UtilKit.suspendAction(1000);
		//framesList = UtilKit.findFrames(driver);
		//System.out.println("Frame Index :" + UtilKit.findFrameIndex(driver, framesList, By.xpath(".//h2[text() = 'Software Testing']")));
		myActions = new Actions(driver);
	}
	
	By coursesIframeL = UtilKit.UIMap("COURSES_IFRAME");
	By searchTextboxL = UtilKit.UIMap("SEARCH_TEXTBOX");
	By categoryButtonL = UtilKit.UIMap("CATEGORY_BUTTON");
	By authorButtonL = UtilKit.UIMap("AUTHOR_BUTTON");
	By categoryMenuL = UtilKit.UIMap("CATEGORY_DROPDOWN_MENU");
	By searchCourseButtonL = UtilKit.UIMap("SEARCHCOURSE_BUTTON");
	By courseListL = UtilKit.UIMap("COURSE_LIST");
	
	public WebElement coursesIframe(){
		
		return driver.findElement(coursesIframeL);
		
	}
	
	public WebElement coursesSearchTextbox(){
		
		// WebElement searchTextbox = driver.findElement(".//input[@id='search-courses' and @name = 'query']"));
		 return driver.findElement(searchTextboxL);
	}
	
	public WebElement searchCourseButton(){
		
		return driver.findElement(searchCourseButtonL);
	}
	public boolean verifyCourseList(){
		
		UtilKit.waitForElement(courseListL, driver, "Exists", 3); // Wait for the search to return some courses
		List<WebElement> courseList = driver.findElements(courseListL);	
		if(courseList.size() == 0){
			UtilKit.logger.error("Course Search returned Empty List ");
			return false;
		}
		else{
			for(int i =0; i < courseList.size(); i++){
				System.out.println(i + " Of " + courseList.size() + " Course : "  + courseList.get(i).getAttribute("data-course-url"));
			}
			return true;
		}
	}
	public void selectCategory(String inCategory){
		myActions.moveToElement(driver.findElement(categoryButtonL)).build().perform();;
		myActions.click().build().perform();
		UtilKit.suspendAction(2000);
		UtilKit.waitForElement(categoryMenuL, driver, "Exists", 3);
		WebElement categoryMenu = driver.findElement(categoryMenuL);
		List<WebElement> categoryList = (ArrayList<WebElement>) categoryMenu.findElements(By.tagName("a")); 
		for(WebElement currCategory: categoryList){
			UtilKit.logger.info("Course Category : " + currCategory.getAttribute("outerHTML") + ": " + currCategory.getAttribute("href"));
			if(currCategory.getAttribute("outerHTML").contains(inCategory)){
				System.out.println(inCategory + " Category Selected !!!");
				myActions.click(currCategory).build().perform();
				break;
			}
		}	
		UtilKit.suspendAction(2000);	
	}
	
	public boolean verifyCategory(String inCategory){
		
		String h2XpathString = ".//h2[text()='"+ inCategory + "']";
		boolean inChildFrame = false;

		// Find all frames And then look for the expected Element in the current content or in one of the frames
		framesList = UtilKit.findFrames(driver);
		int frameIndex = UtilKit.findFrameIndex(driver, framesList, By.xpath(h2XpathString));

		// If found in one of the frames the switch to that frame Otherwise verify it outerHTML in the current context
		if(frameIndex > 0){
			driver.switchTo().frame(framesList.get(frameIndex -1));
			inChildFrame = true;
		}
		// Verify the outer HTML contains the given Category
		List<WebElement> h2List = (ArrayList<WebElement>) driver.findElements(By.tagName("h2"));
		System.out.println("h2List size :" + h2List.size());
		for(WebElement myElement : h2List){
			System.out.println("h2 Elementb Html : " + myElement.getAttribute("outerHTML"));
			if(myElement.getText().contains(inCategory)){
				System.out.println(inCategory + " Category was Verified");
				if(inChildFrame == true)// if in a frame then go back to default content before returning
					driver.switchTo().defaultContent();
				return true;
			}
		}
		System.out.println(inCategory + " Category was NOT Verified");
		if(inChildFrame == true) // if in a frame then go back to default content before returning
			driver.switchTo().defaultContent();
		return false;
	}

	public void selectAuthor(String inAuthor){
		myActions.moveToElement(driver.findElement(authorButtonL)).build().perform();;
		myActions.click().build().perform();
	}

}
