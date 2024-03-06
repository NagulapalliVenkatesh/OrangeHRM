package com.World_Clock_Webtable_Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Base_World_Clock {
	WebDriver driver;
	String applicationUrlAddress="https://www.timeanddate.com/worldclock/";

	@BeforeTest(description="World Clock Application Launched")
	public void application_Launch() throws IOException
	{
		FileInputStream inputdata=new FileInputStream("./configurationProperties/WorldClock.properties");
		Properties properties=new Properties();
		properties.load(inputdata);
		System.setProperty("webdriver.chrome.driver", "./BrowserDriverFiles/chromedriver.exe");
		driver=new ChromeDriver();
		driver.navigate().to(applicationUrlAddress);
		System.out.println("*****Applicatin Launch Succesfully*****");
	}
	@AfterTest(description="World Clock Application Closed")
	public void apllication_Closed()
	{
		driver.close();
		System.out.println("*****Applicatin Closed Succesfully*****");
	}
}
