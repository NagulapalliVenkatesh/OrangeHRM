package com.TAB_ENTER_PICTURE;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Tab_Enter_Picture extends Base_Test
{
	@Test(priority=1,description="Getting Login With Valid Credentials")
	public void loginPage()
	{
		Actions actions=new Actions(driver);
		actions.sendKeys(Keys.TAB).build().perform();
		actions.sendKeys(Keys.TAB).build().perform();
		actions.sendKeys("venky");
		actions.sendKeys(Keys.TAB).build().perform();
		actions.sendKeys("Venky@123");
		actions.sendKeys(Keys.TAB.ENTER).build().perform();
	}
	@Test(priority=2,description="Clicking on PIM")
	public void PIM()
	{
		//<a href="/orangehrm-4.2.0.1/symfony/web/index.php/pim/viewPimModule" id="menu_pim_viewPimModule" 
		//class="firstLevelMenu"><b>PIM</b></a>
		//<a href="/orangehrm-4.2.0.1/symfony/web/index.php/pim/viewPimModule" id="menu_pim_viewPimModule" 
		//class="firstLevelMenu"><b>PIM</b></a>
		By PIMlinktextProperty=By.linkText("PIM");
		WebElement pimlinktextElement=driver.findElement(PIMlinktextProperty);
		
		Actions pim=new Actions(driver);
		pim.moveToElement(pimlinktextElement).build().perform();
		
	}
	@Test(priority=3,description="Clicking on Add Employee")
	public void addEmployee()
	{
	
	//<a href="/orangehrm-4.2.0.1/symfony/web/index.php/pim/addEmployee" id="menu_pim_addEmployee">Add Employee</a>
	By addemployeeProperty=By.linkText("Add Employee");
	WebElement addemployeeElement=driver.findElement(addemployeeProperty);
	addemployeeElement.click();
	}
	
	@Test(priority=4,description="Filling Add Employee Page Details")
	public void fillingDetails() throws IOException, InterruptedException
	{
		//<input class="formInputText" maxlength="30" type="text" name="firstName" id="firstName">
		By firstnameProperty=By.id("firstName");
		WebElement firstnameElement=driver.findElement(firstnameProperty);
		firstnameElement.sendKeys("Ram");
		
		Actions tab=new Actions(driver);
		tab.sendKeys(Keys.TAB).build().perform();
		tab.sendKeys("Charan");
		tab.sendKeys(Keys.TAB).build().perform();
		tab.sendKeys("Teja");
		tab.sendKeys(Keys.TAB).build().perform();
		tab.sendKeys(Keys.TAB).build().perform();
		tab.sendKeys(Keys.TAB.ENTER).build().perform();	
		Thread.sleep(3000);
		java.lang.Runtime.getRuntime().exec("./AddEmployeeCompliedScript/AddEmployeeScript.exe");
		Thread.sleep(3000);

		//<input type="button" class="" id="btnSave" value="Save">
		By saveButtonProperty=By.id("btnSave");
		WebElement saveButtonElement=driver.findElement(saveButtonProperty);
		saveButtonElement.click();
	}
	
	@Test(priority=5,description="Image Validation")
	public void imageValidation()
	{
		//<img alt="Employee Photo" src="/orangehrm-4.2.0.1/symfony/web/index.php/pim/viewPhoto/empNumber/0217" border="0" 
		//id="empPic" width="112" height="200">
		By empPicProperty=By.id("empPic");
		WebElement empPicElement=driver.findElement(empPicProperty);
		Boolean flag=empPicElement.isDisplayed();
		if(flag)
		{
			System.out.println("picture is displayed");
		}
		else
		{
			System.out.println("picture is not displayed");
		}
	}
}
