package com.letskodeit.teachable.tests;

import org.testng.annotations.Test;

import com.letskodeit.teachable.pages.LetsKodeItMousePage;

public class LetsKodeItMouse  extends LetsKodeItPracticeBase{
	
	@Test(enabled=true, groups="actions")
	public void jumpToTopTest(){
		
		LetsKodeItMousePage LKMP = new LetsKodeItMousePage(driver);
		
		LKMP.clickOnTop();
	}
	@Test(enabled=true, groups="actions")
	public void reloadTest(){
		
		LetsKodeItMousePage LKMP = new LetsKodeItMousePage(driver);
		
		LKMP.clickOnReload();
	}
	

}
