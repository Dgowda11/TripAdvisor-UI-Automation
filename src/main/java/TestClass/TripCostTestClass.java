package TestClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import BaseClass.BaseClass;
import PageClass.CruisesPage;
import PageClass.EndPage;
import PageClass.HolidayHomesPage;
import PageClass.LoginPage;
import PageClass.SearchPage;
import PageClass.TripAdvisorMainPage;
import utils.ReadExcelData;

public class TripCostTestClass extends BaseClass {

	LoginPage loginpage;
	TripAdvisorMainPage mainpage;
	SearchPage searchpage;
	HolidayHomesPage homes;
	CruisesPage cruise;
	EndPage endpage;
	
	BaseClass bclass = new BaseClass();

	@BeforeTest
	public void openingBrowser() {
		
		bclass.invokeBrowser("chrome");

		loginpage = bclass.openURL("websiteURL");

	}


	@Test(dataProvider = "TripAdvisorLoginDetails")
	public void TripCost(String username, String password) {

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

		// start reporters
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("HackathonReport.html");

		// create ExtentReports and attach reporter(s)
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		// creates a toggle for the given test, adds all log events under it
		ExtentTest test = extent.createTest("Hackathon project - Trip cost",
				"Task 1: Choose 3 Holiday Homes for 4 people for 6 nights. Task 2: Choose a cruise and display the details and the languages offered");

		
		test.log(Status.PASS, "Initialized the Browser");
		

		test.log(Status.PASS, "Opened the TripAdvisor website");
		
		test.log(Status.PASS, "Opened the log in box");
		mainpage = loginpage.login();


		test.log(Status.PASS, "Entering the TripAdvisor username from excel sheet");
		enterText("EmailTextBox_Xpath", username);

		test.log(Status.PASS, "Entering the TripAdvisor password from the excel sheet");
		enterText("PasswordTextBox_Xpath", password);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		test.log(Status.PASS, "Submitting the login details");
		elementClick("SubmitBtn_Xpath");

		mainpage = loginpage.OutOfFrame();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		test.log(Status.PASS, "Holiday Homes button has been clicked and Nairobi has been entered");
		searchpage = mainpage.SearchCountry();

		System.out.println("Details of the Holiday homes: ");

		test.log(Status.PASS, "Elevator access checkbox has been selected");
		test.log(Status.PASS, "Results have been sorted by Traveller rating");
		test.log(Status.PASS, "Tomorrow's date has been selected for the Check-in date");
		test.log(Status.PASS, "6 days from tomorrow has been selected for the Check-out date");
		test.log(Status.PASS, "Screenshot of the selected dates and the modified settings has been taken");
		homes = searchpage.SortHomes();

		test.log(Status.PASS,
				"Holiday home 1: Sunyee Cozy Studio Apartment has been chosen for 1 person for the next 6 days starting from tomorrow's date");
		test.log(Status.PASS,
				"Holiday home 3: Elegant Cosy Conquest has been chosen for 2 people for the next 6 days starting from tomorrow's date");
		test.log(Status.PASS,
				"Holiday home 3: Spacious one bedroom in Kileleshwa has been chosen for 1 person for the next 6 days starting from tomorrow's date");

		test.log(Status.INFO,
				"The details of all three holiday homes have been printed on the console and the screenshots have been taken");
		cruise = homes.CalculateCost();

		System.out.println("Details of the Cruises: ");

		test.log(Status.PASS, "The Ship Royal Caribbean Cruise has been clicked");
		test.log(Status.INFO,
				"The details of the cruise have been printed on the console and the screenshot has been taken");
		endpage = cruise.SelectCruise();

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		test.log(Status.PASS, "Browser has been closed successfully");
		

		extent.flush();

	}
	@AfterTest
	public void closingBrowser(){
		bclass.tearDown();
		
	}

	@DataProvider(name = "TripAdvisorLoginDetails")
	public Object[][] passData() {
		ReadExcelData stats = new ReadExcelData(
				"C:\\Users\\Darshan Gowda\\workspace\\hackathon\\src\\main\\java\\utils\\hackathonDatasheet.xlsx");

		int rows = stats.getRowCount(0);
		Object[][] data = new Object[rows][2];

		for (int i = 0; i < rows; i++) {
			data[i][0] = stats.getData(0, i, 0);
			data[i][1] = stats.getData(0, i, 1);
		}

		return data;

	}
}
