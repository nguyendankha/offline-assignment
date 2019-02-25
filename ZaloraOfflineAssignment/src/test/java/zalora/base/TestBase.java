package zalora.base;

import org.testng.annotations.AfterSuite;

import zalora.pages.Page;

public class TestBase {
	
	@AfterSuite
	public void teardown() {
		Page.teardown();
	}
}
