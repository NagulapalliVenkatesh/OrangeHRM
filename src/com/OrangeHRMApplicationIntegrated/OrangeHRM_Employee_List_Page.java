package com.OrangeHRMApplicationIntegrated;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.Utility.Log;

public class OrangeHRM_Employee_List_Page extends OrangeHRM_Base_Test
{
	FileInputStream inputtestdata;
	XSSFWorkbook workBook;
	XSSFSheet sheetOfWorkbook;
	
	FileInputStream input;
	Properties property;
	int rowsCount;
	
	@Test(priority=1,description="Identifying Employee List in OrangeHRM Application")
	public void employeeList() throws IOException
	{
		input=new FileInputStream("./configurationProperties/OrangeHRMApplicationIntegrated.Properties");
		property=new Properties();
		property.load(input);
		
		String username="venky";
		String password="Venky@123";
		//<input name="txtUsername" id="txtUsername" type="text">
		By usernameProperty=By.id(property.getProperty("usernameidProperty"));
		WebElement usernameElement=driver.findElement(usernameProperty);
		usernameElement.sendKeys(username);
		
		Actions tab=new Actions(driver);
		tab.sendKeys(Keys.TAB).build().perform();
		tab.sendKeys(password);
		tab.sendKeys(Keys.TAB).build().perform();
		tab.sendKeys(Keys.TAB.ENTER).build().perform();
		
		//<a href="/orangehrm-4.2.0.1/symfony/web/index.php/pim/viewPimModule" 
		//id="menu_pim_viewPimModule" class="firstLevelMenu"><b>PIM</b></a>
		By PIMProperty=By.linkText(property.getProperty("PIMlinktextProperty"));
		WebElement PIMElement=driver.findElement(PIMProperty);
		
		Actions pimMouseHovering=new Actions(driver);
		pimMouseHovering.moveToElement(PIMElement).build().perform();
		
		//<a href="/orangehrm-4.2.0.1/symfony/web/index.php/pim/viewEmployeeList/reset/1" 
		//id="menu_pim_viewEmployeeList">Employee List</a>
		By employeeListProperty=By.linkText(property.getProperty("employeeListProperty"));
		WebElement employeeListElement=driver.findElement(employeeListProperty);
		employeeListElement.click();
		Log.info("Identifying Employee List in OrangeHRM Application is Successfull");
		
	}
	@Test(priority=2,description="Filling Excel sheet with Employee List data")
	public void employeeListData() throws IOException
	{
		FileInputStream inputtestdata=new FileInputStream("./ExcelSheets/OrangeHRMApplicationEmployeeListPageSheet.xlsx");
		XSSFWorkbook workBook= new XSSFWorkbook(inputtestdata);
		XSSFSheet sheetOfWorkbook=workBook.getSheet("Sheet1");
		
		///html/body/div[1]/div[3]/div[2]/div/form/div[4]/table/tbody
		By EmplistBody=By.xpath(property.getProperty("employeelistBodyProperty"));
		WebElement emplistbodyElement=driver.findElement(EmplistBody);
		
		///html/body/div[1]/div[3]/div[2]/div/form/div[4]/table/tbody/tr[1]
		
		By rowsProperty=By.tagName(property.getProperty("employeelistrowsProperty"));
		List<WebElement> rowslist=emplistbodyElement.findElements(rowsProperty);
		int rowsSize=rowslist.size();
		
		for(int rowindex=0;rowindex<rowsSize;rowindex++)
		{
			Row rowOfSheet=sheetOfWorkbook.createRow(rowindex);
			
			WebElement rowElement=rowslist.get(rowindex);
			By cellProperty=By.tagName(property.getProperty("employeelistcellProperty"));
			List<WebElement> cellList=rowElement.findElements(cellProperty);
			int cellsSize=cellList.size();
			
			for(int cellindex=0;cellindex<cellsSize;cellindex++)
			{
				Cell cellOfRow=rowOfSheet.createCell(cellindex);
				WebElement cellElement=cellList.get(cellindex);
				String celltext=cellElement.getText();
				cellOfRow.setCellValue(celltext);
			}
		}
		FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/OrangeHRMApplicationEmployeeListPageSheetResult.xlsx");
		workBook.write(outputdata);
		Log.info("Filling Excel sheet with Employee List data is Successfull");
	}
}
