package zalora.testCases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import zalora.base.TestBase;
import zalora.pages.HomePage;
import zalora.pages.MenCataLoguePage;
import zalora.utilities.Utilities;

public class selectBrandsForFilter extends TestBase{
	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void selectBrandsForFilter(Hashtable<String,String> data) throws InterruptedException {
		HomePage home = new HomePage();
		MenCataLoguePage menCatalogue = home.navigateToMenProductPage();
		menCatalogue.searchBrand("1", data.get("brands"));
		Thread.sleep(5000);
	}
	
	
}
