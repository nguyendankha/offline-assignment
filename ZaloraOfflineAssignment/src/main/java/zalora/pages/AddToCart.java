package zalora.pages;

import static org.testng.Assert.*;

import org.openqa.selenium.By;

public class AddToCart extends Page{
	
	public void increaseProductQuantity() {
		By quantityDropdownLocator = byLocator("quantityDropdown");
		By currentOptionValueLocator = byLocator("currentOptionValue");
		
		String currentQuantityValue;
		String newQuantityValue;
		String afterIncreasedQuantityValue;
		
		findElement(quantityDropdownLocator);
		currentQuantityValue = findElement(currentOptionValueLocator).getText().trim();
		click(quantityDropdownLocator);
		By newOptionValueLocator = appendNewQuantityValueOptionToXpath(currentQuantityValue);
		findElement(newOptionValueLocator);
		newQuantityValue = findElement(newOptionValueLocator).getText().trim();
		click(newOptionValueLocator);
		
		findElement(quantityDropdownLocator);
		click(quantityDropdownLocator);
		afterIncreasedQuantityValue = findElement(currentOptionValueLocator).getText().trim();
		
		assertEquals(newQuantityValue, afterIncreasedQuantityValue);
	}
	
	public void removeItemFromCart() {
		By removeItemIconLocator = byLocator("removeItemIcon");
	
		findElement(removeItemIconLocator);
		click(removeItemIconLocator);
		
		By yesBtnLocator = byLocator("yesBtn");
		
		findElement(yesBtnLocator);
		click(yesBtnLocator);
		
		By emptyBagMessageLocator = byLocator("emptyBagMessage");
		
		verifyElementIsPresent(emptyBagMessageLocator);
	}
	
	public By appendNewQuantityValueOptionToXpath(String oldValue) {
		try {
			return By.xpath("//select[@title='Quantity']/option[contains(text(),'"
					+ (Integer.parseInt(oldValue) + 1) + "')]");
		} catch (Exception e) {
			log.error("Cannot append new value to Xpath " + "\n" + e);
			return null;
		}
		
	}

}
