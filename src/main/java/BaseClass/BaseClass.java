package BaseClass;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import PageClass.LoginPage;

public class BaseClass {

	static WebDriver driver;
	public Properties prop;

	// invoke the browsers
	public void invokeBrowser(String browserNameKey) {

		// Invoking chrome
		if (browserNameKey.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Darshan Gowda\\workspace\\hackathon\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();

		}

		// Invoking firefox
		else if (browserNameKey.equalsIgnoreCase("Mozilla")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\Darshan Gowda\\workspace\\hackathon\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		// Invoking Safari - ONLY works on MAC devices
		else {
			driver = new SafariDriver();
		}

		driver.manage().window().maximize();
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream file = new FileInputStream(
						"C:\\Users\\Darshan Gowda\\workspace\\hackathon\\resources\\ObjectRepository\\projectConfig.properties");

				try {
					prop.load(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// user.dir gives path of the project
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
   

	// Open website URL
	public LoginPage openURL(String websiteURLKey) {

		driver.get(prop.getProperty(websiteURLKey));
		return PageFactory.initElements(driver, LoginPage.class);

	}

	public void enterText(String xpathKey, String data) {

		getElement(xpathKey).sendKeys(data);

	}

	// TO CLICK
	public void elementClick(String xpathKey) {

		getElement(xpathKey).click();

	}

	public String gettingText(String xpathKey) {

		return getElement(xpathKey).getText();
	}

	// To indentify PAGE element
	public WebElement getElement(String locatorKey) { // Takes locator key and returns webelement

		WebElement element = null;

		try {

			if (locatorKey.endsWith("_Id")) {
				element = driver.findElement(By.id(prop.getProperty(locatorKey)));
			}

			else if (locatorKey.endsWith("_Xpath")) {
				element = driver.findElement(By.xpath(prop.getProperty(locatorKey)));

			}

			else if (locatorKey.endsWith("_CSS")) {
				element = driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
			}

			else if (locatorKey.endsWith("_LinkText")) {
				element = driver.findElement(By.linkText(prop.getProperty(locatorKey)));
			}

			else if (locatorKey.endsWith("_PartialLinkText")) {
				element = driver.findElement(By.partialLinkText(prop.getProperty(locatorKey)));
			}

			else if (locatorKey.endsWith("_Name")) {
				element = driver.findElement(By.name(prop.getProperty(locatorKey)));
			}

		} catch (Exception e) {

			// FAIL TEST CASE AND REPORT ERROR
			Assert.fail("Failing the test case:" + e.getMessage());
		}

		return element;

	}

	public void verifyPageTitle(String pageTitle) {

		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, pageTitle);

	}

	public boolean isElementDisplayed(String locatorKey) {

		if (getElement(locatorKey).isDisplayed()) {
			return true;

		}
		return false;

	}

	// TAKING SCREENSHOT OF THE END RESULT 
	public void TakeScreenshot(String fileName) {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile,new File(fileName));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Screenshot has been taken successfully!");

	}

	// CLOSE THE BROWSE
	public void tearDown() {

		driver.close();

	}

	// QUIT THE BROWSER
	public void quitBrowser() {

		driver.quit();

	}
}
