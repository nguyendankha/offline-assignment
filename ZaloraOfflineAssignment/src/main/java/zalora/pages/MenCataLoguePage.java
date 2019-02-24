package zalora.pages;

import org.openqa.selenium.By;

public class MenCataLoguePage extends Page{
	
	public void searchBrand(String brandID, String brandName) {
		driver.findElement(By.xpath("//span[contains(@class,'js-brandName filter-brandName')][contains(text(),'" + brandName + "')]"));
		driver.findElement(By.xpath("//span[contains(@class,'js-brandName filter-brandName')][contains(text(),'" + brandName + "')]")).click();
	}

}
