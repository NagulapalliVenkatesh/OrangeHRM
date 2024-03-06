package com.EckoEcko;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseEckoTest {
	
	WebDriver driver;
	String urlAddress="https://echoecho.com/htmlforms10.htm";
	
	
	@BeforeTest
	public void applicationLaunch()
	{
		System.setProperty("webdriver.chrome.driver", "./BrowserDriverFiles/chromedriver.exe");
		driver=new ChromeDriver();
		System.out.println("***Chrome launch successfull***");
		driver.navigate().to(urlAddress);
		System.out.println("***Application Launch Successfull***");
	}
	
	@AfterTest
	public void apllicationClose()
	{
		driver.close();
		System.out.println("***Application succesfully Closed***");
	}
}
