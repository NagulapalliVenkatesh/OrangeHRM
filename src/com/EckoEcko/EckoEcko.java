package com.EckoEcko;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class EckoEcko extends BaseEckoTest{

	@Test(priority=1,description="Ecko Ecko Application Radio Buttons")
	public void eckoecko() throws InterruptedException
	{
	By tableProperty=By.xpath("/html/body/div[2]/table[9]/tbody/tr/td[4]/table/tbody/tr/td/div/span/form/table[3]/tbody/tr/td/table/tbody/tr/td");
	WebElement tableElement=driver.findElement(tableProperty);
	
	By inputProperty=By.tagName("input");
	List<WebElement> tablelist=tableElement.findElements(inputProperty);
	int sizeOfYableList=tablelist.size();
	System.out.println(sizeOfYableList);
	
	for(int index=0;index<sizeOfYableList;index++)
	{
		Thread.sleep(3000);
		tablelist.get(index).click();
		Thread.sleep(3000);
		for(int Index=0;Index<sizeOfYableList;Index++)
		{
			WebElement element=tablelist.get(Index);
			String elementName=element.getAttribute("value");
			String elementName1=element.getAttribute("checked");
			System.out.println(elementName+"-"+elementName1);
		}
		System.out.println();
	}		
	}
}
