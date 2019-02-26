package zalora.pages;

import static org.testng.Assert.*;
import org.openqa.selenium.By;

public class MenCataLoguePage extends Page {

	public void selectBrands() {
		Object[] data = getDataForFiltering();
		int numberOfBrands = data.length;
		for (int i = 0; i < numberOfBrands; i++) {
			// Search and select and brand
			searchBrand(data[i].toString());
			// Verify the brand is selected successfully
			verifyBrandIsSelected(data[i].toString());
			// Verify the selected brand is displayed in Your Choice section
			verifyBrandIsDisplayedInYourChoide(data[i].toString());
		}
	}
	
	public void searchBrand(String brandName) {
		By searchBrand = byLocator("BrandSearchTxt");
		findElement(searchBrand);
		sendValue(searchBrand, brandName);
		By searchedBrand = getTheBrandNameToXpath(brandName);
		click(searchedBrand);
	}

	public boolean verifyBrandIsSelected(String brandName) {
		try {
			By selectedBrand = getTheBrandNameToSelecedBrandXpath(brandName);
			findElement(selectedBrand);
			assertTrue(verifyElementIsPresent(selectedBrand));
			log.info(brandName + " is selected from brand list.");
			return true;
		} catch (Exception e) {
			log.error(brandName + " is not selected.");
			return false;
		}
	}
	
	public boolean verifyBrandIsDisplayedInYourChoide(String brandName) {
		try {
			By selectedBrand = getTheBrandNameToYourChoiceXpath(brandName);
			findElement(selectedBrand);
			assertTrue(verifyElementIsPresent(selectedBrand));
			log.info(brandName + " is displayed in Your Choice section.");
			return true;
		} catch (Exception e) {
			log.error(brandName + " is not displayed in Your Choice section.");
			return false;
		}
	}

	public static By getTheBrandNameToXpath(String brandName) {
		try {
			return By.xpath("//strong[contains(text(),'" + brandName + "')]/parent::span");
		} catch (Exception e) {
			log.error("Cannot get the brand name to xpath" + "\n" + e);
			return null;
		}
	}

	public static By getTheBrandNameToSelecedBrandXpath(String brandName) {
		try {
			return By.xpath("//ul[@id='selectedBrands']//span[contains(@class,'js-brandName')][contains(text(),'"
					+ brandName + "')]");
		} catch (Exception e) {
			log.error("Cannot get the brand name to selected brand xpath" + "\n" + e);
			return null;
		}
	}
	
	public static By getTheBrandNameToYourChoiceXpath(String brandName){
		try {
			return By.xpath("//div[div[contains(text(),'Your Choice')]]//a[contains(text(),'" + brandName + "')]");
		} catch (Exception e) {
			log.error("Cannot get the brand name to Your Choice xpath" + "\n" + e);
			return null;
		}
	}

	/**
	 * @return Object[] where the brand list is containing
	 */
	public Object[] getDataForFiltering() {
		return new Object[] { "adidas", "FILA", "Armani Exchange", "CR7", "BOSS" };
	}

}
