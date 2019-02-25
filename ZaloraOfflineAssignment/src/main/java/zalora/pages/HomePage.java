package zalora.pages;

import org.openqa.selenium.By;

public class HomePage extends Page{

	public MenCataLoguePage navigateToMenProductPage() {
		By menTab = byLocator("MenTab"); 
		By clothingTab = byLocator("ClothingTab");
		
		// Click Men Tab
		findElement(menTab);
		click(menTab);
		expectedPageTitle = "menPagePageTitle";
		verifyPageTitle();
		
		// Click Clothing tab of Men Tab
		findElement(clothingTab);
		click(clothingTab);
		expectedPageTitle = "menClothingPageTitle";
		verifyPageTitle();
		
		return new MenCataLoguePage();
	}

	public void navigateToWomenProductPage() {
		
	}

}
