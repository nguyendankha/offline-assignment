package zalora.pages;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static FileInputStream fileInputStream;
	public static Properties configProp = new Properties();
	public static Properties objectRepositoryProp = new Properties();
	public static Logger log = Logger.getLogger("logger");
	public static String workspaceDir = System.getProperty("user.dir");
	public static String chromeDriverPath = (workspaceDir + "\\src\\test\\resources\\drivers\\chromedriver.exe");
	public static String firefoxDriverPath = (workspaceDir + "\\src\\test\\resources\\drivers\\geckodriver.exe");
	public static String ieDriverPath = (workspaceDir + "\\src\\test\\resources\\drivers\\IEDriverServer.exe");
	public static String edgeDriverPath = (workspaceDir + "\\src\\test\\resources\\drivers\\MicrosoftWebDriver.exe");
	public static String configPropFilePath = (workspaceDir + "\\src\\test\\resources\\properties\\Config.properties");
	public static String objectRepoPropFilePath = (workspaceDir
			+ "\\src\\test\\resources\\properties\\ObjectRepository.properties");
	public static String expectedPageTitle;
	
	public Page() {
		if (driver == null) {
			log.info("Initiation starting.");
			// Loading Config properties file
			loadConfigFile(configPropFilePath);
			// Loading Object Repo properties file
			loadObjectFile(objectRepoPropFilePath);
			// Load browser driver
			loadBrowserDriver(configProp.getProperty("browser"));
			openURL(configProp.getProperty("homepageURL"));
			log.info("Initiation done, ready for running test.");
		} else {
			log.warn("Driver is running already.");
		}
	}

	public static void teardown() {
		if (driver != null) {
			driver.quit();
			log.info("Driver quit. Test ended.");
		} else {
			log.error("Cannot quit driver session.");
		}
	}

	public static void loadConfigFile(String filePath) {
		try {
			log.info("Loading " + filePath + " file");
			fileInputStream = new FileInputStream(filePath);
			configProp.load(fileInputStream);
			log.info(filePath + " loaded.");
		} catch (IOException e) {
			log.error("Loading " + filePath + " file failed." + "\n" + e);
		}
	}
	
	public static void loadObjectFile(String filePath) {
		try {
			log.info("Loading " + filePath + " file");
			fileInputStream = new FileInputStream(filePath);
			objectRepositoryProp.load(fileInputStream);
			log.info(filePath + " loaded.");
		} catch (IOException e) {
			log.error("Loading " + filePath + " file failed." + "\n" + e);
		}
	}
	
	public static void loadBrowserDriver(String browserType) {
		try {
			if (browserType.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",chromeDriverPath);
				driver = new ChromeDriver();
				log.info(browserType + " driver loaded.");
			} else if (browserType.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
				driver = new FirefoxDriver();
				log.info(browserType + " driver loaded.");
			} else if (browserType.equals("ie")) {
				System.setProperty("webdriver.ie.driver", ieDriverPath);
				driver = new InternetExplorerDriver();
				log.info(browserType + " driver loaded.");
			} else {
				System.setProperty("webdriver.edge.driver", edgeDriverPath);
				driver = new EdgeDriver();
				log.info(browserType + " driver loaded.");
			}
		} 
			catch (Exception e) {
			log.error("Loading " + browserType + " driver failed" + "\n" + e);
		}
	}

	public boolean verifyElementIsPresent(By by) {
		try {
			driver.findElement(by);
			log.info("Element " + by + " is presented");
			return true;
		} catch (NoSuchElementException e) {
			log.error("Element " + by + " not found.");
			return false;
		}
	}

	public static void openURL(String url) {
		try {
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(configProp.getProperty("pageload.wait")), TimeUnit.SECONDS);
			log.info("Navigated to " + url);
		} catch (Exception e) {
			log.error(url + " navigated failed" + "\n" + e);
		}
	}
	
	public static By byLocator(String locator) {
		return By.xpath(objectRepositoryProp.getProperty(locator));
	}

	public static void click(By by) {
		try {
			driver.findElement(by).click();
			log.info(by + "is clicked.");
		} catch (Exception e) {
			log.error(by + " cannot be clicked." + "\n" + e);
		}
	}

	public static WebElement findElement(By by) {
		try {
			wait = new WebDriverWait(driver, Integer.parseInt(configProp.getProperty("webdriver.wait")));
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			log.info("Element " + element + " found");
			return element;
		} catch (NoSuchElementException e) {
			log.error("Element " + by + " not found.");
			return null;
		}
	}

	public static void sendValue(By by, String value) {
		try {
			driver.findElement(by).sendKeys(value);
			log.info("Enter '" + value + "'" + " into " + by);
		} catch (Exception e) {
			log.error("Cannot enter value to " + by + "\n" + e);
		}
	}
	
	public static String getCurrentPageTitle() {
		try {
			String currentPageTitle = driver.getTitle();
			return currentPageTitle;
		} catch (Exception e) {
			log.error("Cannot get page title" + "\n" + e);
			return null;
		}
	}
	
	public static String getExpectedPageTitle(String pageTitle) {
		try {
			String expectedPage = objectRepositoryProp.getProperty(expectedPageTitle);
			return expectedPage;
		} catch (Exception e) {
			log.error("Cannot get expected page title" + "\n" + e);
			return null;
		}
	}
	
	public static boolean verifyPageTitle() {
		try {
			String actualPageTitle = getCurrentPageTitle();
			expectedPageTitle = getExpectedPageTitle(expectedPageTitle);
			assertEquals(actualPageTitle, expectedPageTitle);
			log.info("Page title '" + actualPageTitle + "'" + " is correct.");
			return true;
		} catch (Exception e) {
			log.error("The actual page title is not correct" + "\n" + e);
			return false;
		}
	}
	
	

}
