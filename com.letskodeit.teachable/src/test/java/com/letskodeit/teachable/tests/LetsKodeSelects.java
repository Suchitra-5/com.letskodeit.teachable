package com.letskodeit.teachable.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import com.letskodeit.teachable.pages.LetsKodeItSelectsPage;
import com.letskodeit.teachable.pages.LetsKodeItSelectsPageFactory;
import Util.UtilKit;

public class LetsKodeSelects extends LetsKodeItPracticeBase {

	@Test(enabled = true, groups = { "selects" })
	public void radioPractice() {
		LetsKodeItSelectsPageFactory LKSP = new LetsKodeItSelectsPageFactory(driver);
		// LetsKodeItSelectsPage LKSP = new LetsKodeItSelectsPage(driver);
		LKSP.printRadioList();

	}

	@Test(enabled = true, dataProvider = "selectDataProvider", groups = { "selects" }, dependsOnMethods = {
			"radioPractice" })
	public void radioPracticeSelectAuto(String auto) {
		LetsKodeItSelectsPageFactory LKSP = new LetsKodeItSelectsPageFactory(driver);
		// LetsKodeItSelectsPage LKSP = new LetsKodeItSelectsPage(driver);
		LKSP.clickOnRadio(auto);
		UtilKit.suspendAction(2000);
		Assert.assertTrue(LKSP.verifyRadioSelect(auto), "Radio Select auto Failed");
	}

	@Test(enabled = true, dataProvider = "selectDataProvider", groups = { "selects" }, dependsOnMethods = {
			"radioPracticeSelectAuto" })
	public void selectPracticeSelectAuto(String auto) {
		LetsKodeItSelectsPageFactory LKSP = new LetsKodeItSelectsPageFactory(driver);
		// LetsKodeItSelectsPage LKSP = new LetsKodeItSelectsPage(driver);
		LKSP.selectOnText(auto);
		Assert.assertTrue(LKSP.verifySelectOnText(auto), "Single Select Failed");
	}

	@Test(enabled = true, dataProvider = "multipleColorsProvider", groups = { "selects" }, dependsOnMethods = {
			"selectPracticeSelectAuto" })
	public void multipleSelectTest(String colorsString) {

		// LetsKodeItSelectsPage LKSP = new LetsKodeItSelectsPage(driver);
		LetsKodeItSelectsPageFactory LKSP = new LetsKodeItSelectsPageFactory(driver);

		String[] colorsArray = colorsString.split(" ");
		for (String colorString : colorsArray) {
			LKSP.multipeSelectOnText(colorString);
		}

		LKSP.printMutipleSelected();

		for (String colorString : colorsArray) {
			Assert.assertTrue(LKSP.verifyMutipleSelected(colorString), "Multiple Colors Select Failed");
		}
	}

	@Test(enabled = true, dataProvider = "multipleAutosProvider", groups = "selects", dependsOnMethods = {"multipleSelectTest"})
	public void checkBoxCarsTest(String multipleAutosString) {

		// LetsKodeItSelectsPage LKSP = new LetsKodeItSelectsPage(driver);
		LetsKodeItSelectsPageFactory LKSP = new LetsKodeItSelectsPageFactory(driver);

		String[] autosArray = multipleAutosString.split(" ");

		for (String autoString : autosArray) {
			LKSP.checkBoxCar(autoString);
		}

		for (String autoString : autosArray) {
			Assert.assertTrue(LKSP.verifyCheckBoxCar(autoString),
					"Multiple autos Check Box Test Falied" + autosArray.toString() + " : " + autoString);
			LKSP.checkBoxCar(autoString); // De-select it now to clear it up for
											// the next iteration of this this
											// case
		}

	}

	@DataProvider
	public Object[][] selectDataProvider() {
		return UtilKit.getExcelTestData(application, "selectPractice");
	}

	@DataProvider
	public Object[][] multipleColorsProvider() {
		return UtilKit.getExcelTestData(application, "MultipleColors");
	}

	@DataProvider
	public Object[][] multipleAutosProvider() {
		return UtilKit.getExcelTestData(application, "MultipleAutos");
	}
}