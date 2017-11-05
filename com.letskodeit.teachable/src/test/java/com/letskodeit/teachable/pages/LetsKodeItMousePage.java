package com.letskodeit.teachable.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Util.UtilKit;

public class LetsKodeItMousePage {
	
	WebDriver driver = null;
	Actions myActions = null;
	
	public LetsKodeItMousePage(WebDriver driver){
		this.driver = driver;
		myActions = new Actions(driver);
	}
	
	By mousehoverButtonL = UtilKit.UIMap("MOUSEHOVER_BUTTON");
	
	WebElement mousehoverButton(){
		return driver.findElement(mousehoverButtonL);
	}
	
	public void clickOnTop(){
		UtilKit.scrollToElement(mousehoverButton(), driver);
		myActions.moveToElement(mousehoverButton()).build().perform();
		UtilKit.suspendAction(1000);
		WebElement topOption = driver.findElement(By.partialLinkText("Top"));
		myActions.moveToElement(topOption).build().perform();
		UtilKit.suspendAction(1000);
		myActions.click().build().perform();
		UtilKit.suspendAction(5000);
	}
	public void clickOnReload(){
		UtilKit.scrollToElement(mousehoverButton(), driver);
		myActions.moveToElement(mousehoverButton()).build().perform();
		UtilKit.suspendAction(1000);
		WebElement topOption = driver.findElement(By.partialLinkText("Reload"));
		myActions.moveToElement(topOption).build().perform();
		UtilKit.suspendAction(1000);
		myActions.click().build().perform();
		UtilKit.suspendAction(5000);
	}

}
