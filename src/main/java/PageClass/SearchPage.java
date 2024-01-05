package PageClass;

import java.io.FileInputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import BaseClass.BaseClass;

public class SearchPage extends BaseClass {

	String checkinDate;
	String checkoutDate;
	java.util.Date date;
	Format formatter;
	Calendar calendar = Calendar.getInstance();
	WebDriver driver;

	public SearchPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public HolidayHomesPage SortHomes() {

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
		js.executeScript("window.scrollBy(0,700)"); // Scrolling down

		elementClick("ShowMoreBtn_Xpath");
		elementClick("ElevatorBtn_Xpath");

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-500)"); // Scrolling up

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		elementClick("SortByBtn_Xpath");
		elementClick("TravellerRating_Xpath");

		calendar.add(Calendar.DATE, 1);
		date = calendar.getTime();
		formatter = new SimpleDateFormat("E MMM dd YYYY");
		checkinDate = formatter.format(date);
		System.out.println("The check-in date is: " + checkinDate);

		calendar.add(Calendar.DATE, 6);
		date = calendar.getTime();
		formatter = new SimpleDateFormat("E MMM dd YYYY");
		checkoutDate = formatter.format(date);
		System.out.println("The check-out date is: " + checkoutDate);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		elementClick("DatePickerBox_Xpath");

		driver.findElement(By.xpath("//div[@aria-label='" + checkinDate + "']")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.findElement(By.xpath("//div[@aria-label='" + checkoutDate + "']")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TakeScreenshot("C:\\Users\\Darshan Gowda\\workspace\\hackathon\\screenshots\\SelectedStayDates.PNG");

		return PageFactory.initElements(driver, HolidayHomesPage.class);

	}

}
