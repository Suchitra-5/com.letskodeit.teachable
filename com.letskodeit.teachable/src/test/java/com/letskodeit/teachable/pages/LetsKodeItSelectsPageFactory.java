package com.letskodeit.teachable.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Util.UtilKit;

public class LetsKodeItSelectsPageFactory {
	
	WebDriver driver = null;

	/*
	 * Notice that to avoid the Java IllegalArgumentException
	 * declare this as List<> and initialized it as (ArrayList<>)null
	 */
	@FindBy(xpath=".//input[@type='radio']")
		List<WebElement> radioList = (ArrayList<WebElement>)null;
	
	@FindBy(xpath=".//input[@name='cars' and @type='checkbox']")
		List<WebElement> checkBoxList = (ArrayList<WebElement>)null;
	/*
	 * @FindAll() also works the same way as the above code 
	 */
//	@FindAll({
//		@FindBy(xpath=".//input[@type='radio']")
//	})
//		 List<WebElement> radioList = (ArrayList<WebElement>)null;
	
	@FindBy(xpath=".//select[@id='carselect']")
		WebElement simpleSelectElement;

	Select simpleSelect = null;
	
	@FindBy(xpath=".//select[@name='multiple-select-example']")
		WebElement multipleSelectElement;

	Select multipleSelect = null;
	public LetsKodeItSelectsPageFactory(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
		simpleSelect = new Select(simpleSelectElement);
		multipleSelect = new Select(multipleSelectElement);
	}
	
	public void printRadioList() {
		for (int i = 0; i < radioList.size(); i++) {
			UtilKit.waitForElement(radioList.get(i), "Displayed", 10);
			System.out.println("Radio Element : " + i + " HTML :" + radioList.get(i).getTagName()
					+ radioList.get(i).getAttribute("outerHTML") + " Text : " + radioList.get(i).getAttribute("value"));
		}
	}

	public void clickOnRadio(String auto) {
		for (int i = 0; i < radioList.size(); i++) {
			UtilKit.waitForElement(radioList.get(i), "Displayed", 10);
			System.out.println("Radio Element : " + i + " HTML :" + radioList.get(i).getTagName()
					+ radioList.get(i).getAttribute("outerHTML") + " Text : " + radioList.get(i).getAttribute("value"));
			if(radioList.get(i).getAttribute("value").equalsIgnoreCase(auto)){
				radioList.get(i).click();
			System.out.println("Selected Radio Element : " + i + radioList.get(i).getTagName() + " : " + radioList.get(i).getAttribute("value"));
			UtilKit.suspendAction(5000);
			}
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

	public void selectOnText(String inText){
		simpleSelect.selectByVisibleText(inText);
		System.out.println(inText + " Was Selected" + UtilKit.currentMethod());
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