package com.letskodeit.teachable.pages;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.letskodeit.teachable.util.UtilKit;

public class LetsKodeItSwitchPage {
	
	WebDriver driver = null;
	Alert LKSPAlert = null;
	
	Point switchCoordinates = new Point(182,28);

	By openWindowButtonL = UtilKit.UIMap("OPENWINDOW_BUTTON");
	By openTabButtonL = UtilKit.UIMap("OPENTAB_BUTTON");
	By nameTextboxL = UtilKit.UIMap("NAME_TEXTBOX");
	By alertButtonL = UtilKit.UIMap("ALERT_BUTTON");
	By confirmButtonL = UtilKit.UIMap("CONFIRM_BUTTON");
	
	By searchTextboxL = UtilKit.UIMap("SEARCH_TEXTBOX");
	
	public LetsKodeItSwitchPage(WebDriver driver){
		this.driver = driver;

		UtilKit.scrollToPoint(switchCoordinates, driver); // Make the switch area visible to the tester
	}

	public WebElement searchTextbox(){
		return driver.findElement(searchTextboxL);
	}
	
	public WebElement nameTextBox(){
		return driver.findElement(nameTextboxL);
	}
	public WebElement alertButton(){
		return driver.findElement(alertButtonL);
	}
	public WebElement confirmButton(){
		return driver.findElement(confirmButtonL);
	}
	
	public boolean verifyAlert(){

		long waitTotal = 0, waitMax = 5;

		while(waitTotal < waitMax){
			
			try {
				LKSPAlert = driver.switchTo().alert();
				return true;
			}catch(Exception e){
				UtilKit.suspendAction(250);
					waitTotal++;
			}
		}
		return false;
	}

	public void OKAlert(){
		
		LKSPAlert.accept();
	}
	public void cancelAlert(){

		LKSPAlert.dismiss();
	}
	public String alertText(){

		return LKSPAlert.getText();
	}
	
	
	public WebElement openWindowButton(){
		return driver.findElement(openWindowButtonL);
	}
	
	public WebElement openTabButton(){
		return driver.findElement(openTabButtonL);
	}

	public boolean verifyNewWindowURL(String inURL){
		
		if((driver.getCurrentUrl().compareToIgnoreCase(inURL)) == 0){
			UtilKit.logger.info(inURL + " AND " + driver.getCurrentUrl() + " MATCH !!!");
			return true;
		}
		UtilKit.logger.info(inURL + " AND " + driver.getCurrentUrl() + " DO NOT MATCH");
		return false;
	}
	
	public void switchToURL(String inURL){
		
		Set<String> curHandles = driver.getWindowHandles();
		
		for(String curHandle: curHandles){
			driver.switchTo().window(curHandle);
			UtilKit.suspendAction(2000);
			System.out.println("Switched to " + curHandle);
			if((driver.getCurrentUrl().compareToIgnoreCase(inURL)) == 0){
				System.out.println("Switch to " + driver.getTitle() + "  Completed...");
				return;
			}
		}
		UtilKit.logger.error("Switch to " + inURL + "Window was Never Completed...");

	}

	


}
