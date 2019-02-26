package zalora.base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import zalora.pages.Page;

public class TestBase {
	
	@BeforeTest
	public void init() {
		Page.init();
	}
	
	@AfterTest
	public void teardown() {
		Page.teardown();
	}
}
