package com.letskodeit.teachable.pages;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Util.UtilKit;

public class LetsKodeItSelectsPage {
	
	WebDriver driver = null;

	ArrayList<WebElement> radioList = (ArrayList<WebElement> )null;
	ArrayList<WebElement> checkBoxList = (ArrayList<WebElement> )null;
	
	By radioListL = By.xpath(".//input[@type='radio']");
	By simpleSelectElementL = By.xpath(".//select[@id='carselect']");
	Select simpleSelect = null;
	By multipleSelectElementL = UtilKit.UIMap("MULTIPLE_SELECT");
	Select multipleSelect = null;
	By checkBoxListL = UtilKit.UIMap("CHECKBOX_LIST");

	public LetsKodeItSelectsPage(WebDriver driver){
		this.driver = driver;
		
		radioList = (ArrayList<WebElement>) driver.findElements(radioListL);
		simpleSelect = new Select(driver.findElement(simpleSelectElementL));
		multipleSelect = new Select(driver.findElement(multipleSelectElementL));
		checkBoxList = (ArrayList<WebElement>) driver.findElements(checkBoxListL);
	}
	
	public void printRadioList() {
		for (int i = 0; i < radioList.size(); i++) {
			UtilKit.waitForElement(radioList.get(i), "Displayed", 10);
			System.out.println("Radio Element : " + i + " HTML :" + radioList.get(i).getTagName()
					+ radioList.get(i).getAttribute("outerHTML") + " Text : " + radioList.get(i).getAttribute("value"));
		}
	}

	public boolean verifyRadioSelect(String auto) {
		for (int i = 0; i < radioList.size(); i++) {
			/* Next 3 lines Added for debugging only 
			String myValue = radioList.get(i).getAttribute("value");
			boolean selectedStatus = radioList.get(i).isSelected();
			System.out.println("Value :" + myValue + "  Selected : " + selectedStatus);
			*/

			if( radioList.get(i).getAttribute("value").equalsIgnoreCase(auto) && radioList.get(i).isSelected() )
				return true;
		}
		
		return false;
	}

	public void clickOnRadio(String auto) {
		for (int i = 0; i < radioList.size(); i++) {
			UtilKit.waitForElement(radioList.get(i), "Displayed", 10);
			System.out.println("Radio Element : " + i + " HTML :" + radioList.get(i).getTagName()
					+ radioList.get(i).getAttribute("outerHTML") + " Text : " + radioList.get(i).getAttribute("value"));
			if(radioList.get(i).getAttribute("value").equalsIgnoreCase(auto)){
				radioList.get(i).click();
			System.out.println("Selected Radio Element : " + i + radioList.get(i).getTagName() + " : " + radioList.get(i).getAttribute("value"));
			UtilKit.suspendAction(2000);
			}
		}
	}
	
	public void selectOnText(String inText){
		simpleSelect.selectByVisibleText(inText);
		System.out.println(inText + " Was Selected in :" +  UtilKit.currentMethod());
		UtilKit.suspendAction(2000);
	}

	public boolean verifySelectOnText(String inText){
		return simpleSelect.getFirstSelectedOption().getText().equals(inText);
	}
	
	public void multipeSelectOnText(String inText){
		multipleSelect.selectByVisibleText(inText);
		System.out.println(inText + " Was Selected in :" +  UtilKit.currentMethod());
		UtilKit.suspendAction(2000);
	}
	
	public void printMutipleSelected(){
		List<WebElement> selectedOptions = (ArrayList<WebElement>)multipleSelect.getAllSelectedOptions();
		for(WebElement currOption: selectedOptions){
			UtilKit.logger.info(currOption.getText() + " Selected !!!");
		}
	}

	public boolean verifyMutipleSelected(String inText){
		List<WebElement> selectedOptions = (ArrayList<WebElement>)multipleSelect.getAllSelectedOptions();
		for(WebElement currOption: selectedOptions){
			if(currOption.getText().equals(inText))
				return true;
		}
		
		return false;
	}

	public void checkBoxCar(String inCar){

		for(WebElement carElement: checkBoxList){
			//System.out.println("CheckBox Element : " + carElement.getAttribute("value") + " outerHTML" + carElement.getAttribute("outerHTML"));
			if(carElement.getAttribute("value").equalsIgnoreCase(inCar)){
				carElement.click();
				UtilKit.suspendAction(1000);
				return;
			}
		}
	}
	
	public boolean verifyCheckBoxCar(String inCar){
		for(int i =0; i < checkBoxList.size(); i++){
			if((checkBoxList.get(i).getAttribute("value").equalsIgnoreCase(inCar)) &&
			   (checkBoxList.get(i).isSelected())){
					return true;
			}
		}
		return false;
	}
	
	

}
