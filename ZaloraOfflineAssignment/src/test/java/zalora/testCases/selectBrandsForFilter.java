package zalora.testCases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import zalora.base.TestBase;
import zalora.pages.HomePage;
import zalora.pages.MenCataLoguePage;

public class selectBrandsForFilter extends TestBase{
	
	@Parameters ({ "brand1", "brand2", "brand3",  "brand4", "brand5"})
	@Test
	public void selectBrandsForFiltering(String brand1, String brand2, String brand3, String brand4, String brand5) throws InterruptedException {
		HomePage home = new HomePage();
		MenCataLoguePage menCatalogue = home.navigateToMenProductPage();
		menCatalogue.searchBrand(brand1);
		Thread.sleep(5000);
		
		menCatalogue.searchBrand(brand2);
		Thread.sleep(5000);
		
		menCatalogue.searchBrand(brand3);
		Thread.sleep(5000);
		
		menCatalogue.searchBrand(brand4);
		Thread.sleep(5000);
		
		menCatalogue.searchBrand(brand5);
		Thread.sleep(5000);
	}
	
	
}
