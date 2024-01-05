package PageClass;


import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import BaseClass.BaseClass;

public class LoginPage extends BaseClass {

	WebDriver driver;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public TripAdvisorMainPage login() {
		
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

		elementClick("SignInBtn_Xpath");

		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement iframe = driver.findElement(By.xpath("//iframe[@title='regcontroller']"));
		
	
		driver.switchTo().frame(iframe);
		
		elementClick("UseEmail_Xpath");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	
		return PageFactory.initElements(driver, TripAdvisorMainPage.class);

	}
	
	public TripAdvisorMainPage OutOfFrame() {
		
		driver.switchTo().defaultContent();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return PageFactory.initElements(driver, TripAdvisorMainPage.class);

	}
	


}
