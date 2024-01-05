package PageClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import BaseClass.BaseClass;

public class HolidayHomesPage extends BaseClass {

	WebDriver driver;

	public HolidayHomesPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public CruisesPage CalculateCost() {

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

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		elementClick("HolidayHome1_Xpath");

		String originalWindow = driver.getWindowHandle();

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JavascriptExecutor home1 = (JavascriptExecutor) driver;
		home1.executeScript("window.scrollBy(0,300)");
		
		

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		elementClick("PeopleDrpdown1_Xpath");
		elementClick("PeopleNo1_Xpath");
		elementClick("ApplyBtn1_Xpath");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String Name1 = gettingText("Home1Name_Xpath");
		String Price1 = gettingText("Home1Price_Xpath");
		System.out.println("The price of the first Holiday Home for 1 person: " + Name1 + " is " + Price1);

		TakeScreenshot("C:\\Users\\Darshan Gowda\\workspace\\hackathon\\screenshots\\HolidayHome1.PNG");

		driver.switchTo().window(originalWindow);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		elementClick("HolidayHome2_Xpath");

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JavascriptExecutor home2 = (JavascriptExecutor) driver;
		home2.executeScript("window.scrollBy(0,300)");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		elementClick("PeopleDrpdown2_Xpath");
		elementClick("PeopleNo2_Xpath");
		elementClick("ApplyBtn2_Xpath");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String Name2 = gettingText("Home2Name_Xpath");
		String Price2 = gettingText("Home2Price_Xpath");
		System.out.println("The price of the second Holiday Home for 2 people: " + Name2 + " is " + Price2);

		TakeScreenshot("C:\\Users\\Darshan Gowda\\workspace\\hackathon\\screenshots\\HolidayHome2.PNG");

		driver.switchTo().window(originalWindow);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		elementClick("HolidayHome3_Xpath");

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JavascriptExecutor home3 = (JavascriptExecutor) driver;
		home3.executeScript("window.scrollBy(0,300)");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		elementClick("PeopleDrpdown3_Xpath");
		elementClick("PeopleNo3_Xpath");
		elementClick("ApplyBtn3_Xpath");

		String Name3 = gettingText("Home3Name_Xpath");
		String Price3 = gettingText("Home3Price_Xpath");
		System.out.println("The price of the third Holiday Home for 1 person: " + Name3 + " is " + Price3);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TakeScreenshot("C:\\Users\\Darshan Gowda\\workspace\\hackathon\\screenshots\\HolidayHome3.PNG");

		driver.switchTo().window(originalWindow);

		return PageFactory.initElements(driver, CruisesPage.class);

	}

}
