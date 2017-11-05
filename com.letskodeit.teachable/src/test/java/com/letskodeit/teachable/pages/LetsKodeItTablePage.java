package com.letskodeit.teachable.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Util.UtilKit;

public class LetsKodeItTablePage {
	
	WebDriver driver = null;
	
	public LetsKodeItTablePage(WebDriver driver){
		this.driver = driver;
		driver.manage().window().maximize();
		UtilKit.suspendAction(2000);
		//UtilKit.scrollToPoint(driver.findElement(displayedTextboxL).getLocation(), driver);
		UtilKit.scrollToPoint(new Point(50,350), driver);
		UtilKit.suspendAction(2000);

	}
	
	By productTableL = UtilKit.UIMap("PRODUCT_TABLE");
	By hideTextboxL = UtilKit.UIMap("HIDE_TEXTBOX");
	By showTextboxL = UtilKit.UIMap("SHOW_TEXTBOX");
	By displayedTextboxL = UtilKit.UIMap("DISPLAYED_TEXTBOX");
	
	public WebElement hideTestbox(){
		return driver.findElement(hideTextboxL);
	}
	public WebElement showTextbox(){
		return driver.findElement(showTextboxL);
	}
	public WebElement displayedTextbox(){
		return driver.findElement(displayedTextboxL);
	}
	
	public boolean verifyTextboxHiden(){
		return !(displayedTextbox().isDisplayed());
	}

	public boolean verifyTextboxShow(){
		return (displayedTextbox().isDisplayed());
	}

	public void printTableContent(){

		WebElement productTable = driver.findElement(productTableL);
		UtilKit.scrollToElement(productTable, driver);
		List<WebElement> tableRows = (ArrayList<WebElement>) productTable.findElements(By.tagName("tr"));
		for(int i = 0; i < tableRows.size(); i++){
			WebElement currRow = tableRows.get(i);
			System.out.println("tr Element :" + currRow.getAttribute("outerHTML"));
			if(i==0){
				List<WebElement> headerElements = currRow.findElements(By.tagName("th"));
				System.out.println("Header Elements size : " + headerElements.size());
				for(WebElement headerElement: headerElements){
					System.out.println("Header Element Outer: "  + i + " : " + headerElement.getAttribute("outerHTML"));
					System.out.println("Header Element Inner: "  + i + " : " + headerElement.getAttribute("innerHTML"));
				}
				continue;
			}
			List<WebElement> contentElements = currRow.findElements(By.tagName("td"));
				System.out.println("Content Elements size : " + contentElements.size());
			for(WebElement contentElement: contentElements){
				System.out.println("Content Element Outer: "  + i + " : " + contentElement.getAttribute("outerHTML"));
				System.out.println("Content Element Inner: "  + i + " : " + contentElement.getAttribute("innerHTML"));
			}
		}
		
	}

}
