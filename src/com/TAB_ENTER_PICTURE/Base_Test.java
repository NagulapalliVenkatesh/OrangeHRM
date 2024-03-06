package com.TAB_ENTER_PICTURE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Base_Test 
{
	WebDriver driver;
	String UrlApplication="http://127.0.0.1/orangehrm-4.2.0.1/symfony/web/index.php/dashboard";
	
	@BeforeTest
	public void applicationLaunch()
	{
		System.setProperty("webdriver.chrome.driver", "./BrowserDriverFiles/chromedriver.exe");
		driver=new ChromeDriver();
		driver.navigate().to(UrlApplication);
		System.out.println("*****Application Launch Successfull*****");
	}
	
	@AfterTest
	public void applicationClose()
	{
		driver.close();
		System.out.println("*****Application Closed Succesfully*****");
	}
}
