package com.OrangeHRMApplicationIntegrated;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.Utility.Log;

public class OrangeHRM_Base_Test 
{
	WebDriver driver;
	String Application_Url_Address="http://127.0.0.1/orangehrm-4.2.0.1/symfony/web/index.php/dashboard";
	
	@BeforeTest
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "./BrowserDriverFiles/chromedriver.exe");
		driver=new ChromeDriver();
		Log.info("***Chrome Launched Successfully***");
		//System.out.println("***Chrome Launched Successfully***");
		driver.navigate().to(Application_Url_Address);
		//System.out.println("***OrangeHRM Application Launched Successfully***");
		Log.info("***OrangeHRM Application Launched Successfully***");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.close();
		//System.out.println("***OrangeHRM Application Closed Successfully***");
		Log.info("***OrangeHRM Application Closed Successfully***");
	}

}
