package zalora.pages;

import org.openqa.selenium.By;

public class WomenCataloguePage extends Page{
	
	public ProductDetailPage searchKeyword(String searchKeyword) throws InterruptedException {
		By searchTxt = byLocator("SearchTxt");
		By searchIcon = byLocator("SearchIcon");
		By searchedResult = byLocator("searchedResult");
		
		findElement(searchTxt);
		
		sendValue(searchTxt, searchKeyword);
		Thread.sleep(3000);
		
		findElement(searchIcon);
		click(searchIcon);
		
		findElement(searchedResult);
		click(searchedResult);
		
		return new ProductDetailPage();
	}

}
