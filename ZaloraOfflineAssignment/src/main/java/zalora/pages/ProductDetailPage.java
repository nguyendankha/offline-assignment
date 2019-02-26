package zalora.pages;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ProductDetailPage extends Page {
	List<WebElement> listOfSizes;

	public void addItemToCart() throws InterruptedException {
		By sizeDropdown = byLocator("sizeDropdown");
		HashMap<String, Integer> map = getTheSizeNameAndAmount();
		getTheMaxSizeValueInList(map);
		By sizeValueLocator = appendSizeNameToXpath(getTheMaxSizeValueInList(map));
		By addToCartLocator = byLocator("addToCartBtn");

		findElement(sizeDropdown);
		click(sizeDropdown);

		findElement(sizeValueLocator);
		click(sizeValueLocator);

		findElement(addToCartLocator);
		click(addToCartLocator);
	}
	
	public AddToCart navigateToCartPage() {
		By cartIconLocator = byLocator("cartIcon");
		By viewBagBtnLocator = byLocator("viewBagBtn");
		
		findElement(cartIconLocator);
		moveToElement(cartIconLocator);
		
		findElement(viewBagBtnLocator);
		moveToElement(viewBagBtnLocator);

		click(viewBagBtnLocator);
		
		return new AddToCart();
	}
	
	public Actions moveToElement(By by) {
		try {
			Actions moveToAction = new Actions(driver);
			moveToAction.moveToElement(findElement(by)).perform();
			return moveToAction;
		} catch (Exception e) {
			log.error("Cannot move to element " + by + "\n" + e);
			return null;
		}
	}

	public HashMap<String, Integer> getTheSizeNameAndAmount() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		By listOfSizeLocator = byLocator("ListOfSizes");
		By sizeDropdown = byLocator("sizeDropdown");

		listOfSizes = findElements(listOfSizeLocator);

		Iterator<WebElement> iter = listOfSizes.iterator();
		
		try {
			// iterate the sizeDropdown to get the HashMap for Size-Amount
			while (iter.hasNext()) {
				WebElement sizeValue = iter.next();
				// Locator of each Size value
				By sizeLocator = appendSizeNameToXpath(sizeValue.getText());

				// Click on each size value to get the size amount
				findElement(sizeDropdown);
				click(sizeDropdown);

				findElement(sizeLocator);
				click(sizeLocator);

				By availableAmountLocator = byLocator("availableAmount");
				String availableStock = findElement(availableAmountLocator).getText();

				// find number amount in string
				Pattern p = Pattern.compile("[0-9]+");
				int number = 0;
				Matcher m = p.matcher(availableStock);
				while (m.find()) {
					number = Integer.parseInt(m.group());
				}

				// add key-value to map
				map.put(sizeValue.getText(), number);
			}
			return map;
		} catch (Exception e) {
			log.error("Get Size value and amount error " + "\n" + e);
			return null;
		}
		
	}

	public String getTheMaxSizeValueInList(HashMap<String, Integer> map) {
		try {
			// find the maximum value and return the Size value
			int maxValueInMap = (Collections.max(map.values())); // This will return max value in the Hashmap
			String maxAvailableSize = null;
			for (Entry<String, Integer> entry : map.entrySet()) { // Itrate through hashmap
				if (entry.getValue() == maxValueInMap) {
					maxAvailableSize = entry.getKey();
				}
			}
			return maxAvailableSize;
		} catch (Exception e) {
			log.error("Cannot get the max size value " + "\n" + e);
			return null;
		}
		
	}

	public static By appendSizeNameToXpath(String sizeName) {
		try {
			return By.xpath("//option[@data-value-size='" + sizeName + "']");
		} catch (Exception e) {
			log.error("Cannot append the size name to xpath" + "\n" + e);
			return null;
		}
	}
}
