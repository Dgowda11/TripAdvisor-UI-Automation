package PageClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import BaseClass.BaseClass;

public class CruisesPage extends BaseClass {

	WebDriver driver;

	public CruisesPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public EndPage SelectCruise() {

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

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-500)"); // Scrolling up

		elementClick("CruiseBtn_Xpath");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		elementClick("CruiseLineDrpdown_Xpath");
		elementClick("CruiseLineSelect_Xpath");
		elementClick("CruiseShipDrpdown_Xpath");
		elementClick("CruiseShipSelect_Xpath");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		elementClick("SearchBtn_Xpath");

		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollTo(0,800)");		//Scroll to Cruise Info 
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String PassengerNo = gettingText("PassengerNo_Xpath");
		System.out.println("Details of the Ship Royal Caribbean Allure of the Seas is " + PassengerNo);

		String LaunchDate = gettingText("Launched_Xpath");
		System.out.println("The ship Royal Caribbean Allure of the Seas was " + LaunchDate);

		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("window.scrollBy(0,1500)");		//Scroll to Languages
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<WebElement> languages = driver.findElements(By.xpath("//*[@class='fwSIg q']"));

		System.out.println("The languages offered are:");
		for (WebElement lang : languages) {
			String name = lang.getText();
			if (!(name.equalsIgnoreCase("All languages"))) {
				System.out.println(name);
			}

		}
		
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js3.executeScript("window.scrollTo(0,310)");	//Scroll back to top ;
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TakeScreenshot("C:\\Users\\Darshan Gowda\\workspace\\hackathon\\screenshots\\Cruise.PNG");
		
		return PageFactory.initElements(driver, EndPage.class);

	}

}
