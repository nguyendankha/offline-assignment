package zalora.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

public class HomePage extends Page{

	public MenCataLoguePage navigateToMenProductPage() {
		bypassAdPopup();
		
		By menTab = byLocator("MenTab"); 
		By clothingTab = byLocator("ClothingTab");
		
		// Click Men Tab
		findElement(menTab);
		click(menTab);
		expectedPageTitle = "menPagePageTitle";
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(configProp.getProperty("pageload.wait")), TimeUnit.SECONDS);
		verifyPageTitle();
		
		// Click Clothing tab of Men Tab
		findElement(clothingTab);
		click(clothingTab);
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(configProp.getProperty("pageload.wait")), TimeUnit.SECONDS);
		expectedPageTitle = "menClothingPageTitle";
		verifyPageTitle();
		
		return new MenCataLoguePage();
	}

	public WomenCataloguePage navigateToWomenProductPage() {
		bypassAdPopup();
		
		By womenTab = byLocator("WomenTab"); 
		By clothingTab = byLocator("ClothingTab");
		
		// Click Men Tab
		findElement(womenTab);
		click(womenTab);
		expectedPageTitle = "womenPagePageTitle";
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(configProp.getProperty("pageload.wait")), TimeUnit.SECONDS);
		verifyPageTitle();
		
		// Click Clothing tab of Men Tab
		findElement(clothingTab);
		click(clothingTab);
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(configProp.getProperty("pageload.wait")), TimeUnit.SECONDS);
		expectedPageTitle = "womenClothingPageTitle";
		verifyPageTitle();
		
		return new WomenCataloguePage();
	}
	
	public void bypassAdPopup() {
		By adPopup = byLocator("AdPopup");
		findElement(adPopup);
		click(adPopup);
	}

}
