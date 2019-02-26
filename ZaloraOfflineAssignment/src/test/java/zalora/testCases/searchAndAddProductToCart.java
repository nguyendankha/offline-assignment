package zalora.testCases;

import org.testng.annotations.Test;

import zalora.base.TestBase;
import zalora.pages.AddToCart;
import zalora.pages.HomePage;
import zalora.pages.ProductDetailPage;
import zalora.pages.WomenCataloguePage;

public class searchAndAddProductToCart extends TestBase{
	@Test
	public void searchAndAddProduct() throws InterruptedException {
		HomePage homepage = new HomePage();
		WomenCataloguePage womenCataloguePage = homepage.navigateToWomenProductPage();
		ProductDetailPage productDetailPage = womenCataloguePage.searchKeyword("dress");
		productDetailPage.addItemToCart();
		AddToCart cartPage = productDetailPage.navigateToCartPage();
		cartPage.increaseProductQuantity();
		cartPage.removeItemFromCart();
	}
}
