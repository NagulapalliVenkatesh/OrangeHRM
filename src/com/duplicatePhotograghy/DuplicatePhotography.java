package com.duplicatePhotograghy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DuplicatePhotography {
	public static void main(String[] args) {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "./BrowserDriverFiles/chromedriver.exe");
		driver=new ChromeDriver();
		String Url="http://127.0.0.1/orangehrm-4.2.0.1/symfony/web/index.php/pim/viewEmployee/empNumber/158";
		driver.navigate().to(Url);
		//<input name="txtUsername" id="txtUsername" type="text">
		By usernameProperty=By.id("txtUsername");
		WebElement usernameElement=driver.findElement(usernameProperty);
		usernameElement.sendKeys("venky");
		
		//<input name="txtPassword" id="txtPassword" autocomplete="off" type="password">
		By passwordProperty=By.id("txtPassword");
		WebElement passwordElement=driver.findElement(passwordProperty);
		passwordElement.sendKeys("Venky@123");
		
		//<input type="submit" name="Submit" class="button" id="btnLogin" value="LOGIN">
		By loginButtonProperty=By.id("btnLogin");
		WebElement loginbuttonElement=driver.findElement(loginButtonProperty);
		loginbuttonElement.click();
		
		//<a href="/orangehrm-4.2.0.1/symfony/web/index.php/pim/viewPimModule" 
		//id="menu_pim_viewPimModule" class="firstLevelMenu"><b>PIM</b></a>
		By pimProperty=By.linkText("PIM");
		WebElement pimElement=driver.findElement(pimProperty);
		
		Actions mouse=new Actions(driver);
		mouse.moveToElement(pimElement).build().perform();
		
		//<a href="/orangehrm-4.2.0.1/symfony/web/index.php/pim/addEmployee" id="menu_pim_addEmployee">Add Employee</a>
		By employeelistProperty=By.linkText("Employee List");
		WebElement addemployeeElement=driver.findElement(employeelistProperty);
		addemployeeElement.click();
		
		By subProperty=By.linkText("Subhash Chandra");
		WebElement subElement=driver.findElement(subProperty);
		subElement.click();
		
		//id="empPic"
		By empPicProperty=By.id("empPic");
		WebElement empPicElement=driver.findElement(empPicProperty);
		Boolean flag=empPicElement.isDisplayed();
		System.out.println("flag: "+flag);
	}
}
