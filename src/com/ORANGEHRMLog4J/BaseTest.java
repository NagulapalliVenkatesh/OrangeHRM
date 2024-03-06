package com.ORANGEHRMLog4J;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.Utility.Log;



public class BaseTest {
	WebDriver driver;
	String applicationUrlAddress="http://127.0.0.1/orangehrm-4.2.0.1/symfony/web/index.php/dashboard";
	
	@BeforeTest
	public void applicationLaunch()
	{
		System.setProperty("webdriver.chrome.driver", "./BrowserDriverFiles/chromedriver.exe");
		driver=new ChromeDriver();
		driver.navigate().to(applicationUrlAddress);
		Log.info("**********Application Launch Successfull**********");
		//System.out.println("**********Application Launch Successfull**********");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@AfterTest
	public void applicationClose()
	{
		driver.close();
		Log.info("**********Application Closed Successfully**********");
		//System.out.println("**********Application Closed Successfully**********");
	}
}
