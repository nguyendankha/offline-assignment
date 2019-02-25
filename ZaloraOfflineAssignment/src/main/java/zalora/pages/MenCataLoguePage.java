package zalora.pages;

import org.openqa.selenium.By;

public class MenCataLoguePage extends Page {

	public void searchBrand(String brandName) throws InterruptedException {
		By searchBrand = byLocator("BrandSearchTxt");
		findElement(searchBrand);
		sendValue(searchBrand, brandName);
		By searchedBrand = getTheBrandNameToXpath(brandName);
		click(searchedBrand);
	}
	
	public static By getTheBrandNameToXpath(String brandName) {
		return By.xpath("//strong[contains(text(),'" + brandName + "')]/parent::span");
	}
}
