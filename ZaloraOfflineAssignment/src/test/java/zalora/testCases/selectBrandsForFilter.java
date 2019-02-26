package zalora.testCases;
import org.testng.annotations.Test;

import zalora.base.TestBase;
import zalora.pages.HomePage;
import zalora.pages.MenCataLoguePage;

public class selectBrandsForFilter extends TestBase{
	@Test
	public void selectBrandsForFiltering() throws InterruptedException {
		HomePage home = new HomePage();
		MenCataLoguePage menCatalogue = home.navigateToMenProductPage();
		menCatalogue.selectBrands();
	}
}
