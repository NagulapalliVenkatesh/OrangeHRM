package com.OrangeHRMApplicationIntegrated;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
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

public class OrangeHRM_Login_Page extends OrangeHRM_Base_Test{
	
	FileInputStream inputtestdata;
	XSSFWorkbook workBook;
	XSSFSheet sheetOfWorkbook;
	
	FileInputStream input;
	Properties property;
	
	String UserName="venky";
	String PassWord="Venky@123";

	String UserName2="venky";
	String PassWord2="admin";

	String UserName3="admin";
	String PassWord3="Venky@123";

	String UserName4="admin";
	String PassWord4="Admin@123";
	
	String Expected_LoginPageTextCell0="LOGIN Panel";
	String Expected_LoginPageTextCell1="Invalid credentials";
	String Expected_LoginPageTitle="orangeHRM";
	String Expected_HomePageText="Admin";


	@Test(priority=1,description="Adding data to the Excel Sheet")
	public void excel_TestData() throws IOException
	{
		inputtestdata=new FileInputStream("./ExcelSheets/OrangeHRMApplicationLoginPageSheet.xlsx");
		workBook=new XSSFWorkbook(inputtestdata);
		sheetOfWorkbook=workBook.getSheet("Sheet1");
		input=new FileInputStream("./configurationProperties/OrangeHRMApplicationIntegrated.Properties");
		property=new Properties();
		property.load(input);
		
		for(int rowIndex=1;rowIndex<6;rowIndex++)
		{
				Row Row1OfSheet=sheetOfWorkbook.createRow(rowIndex);
				Row1OfSheet.createCell(0).setCellValue(Expected_LoginPageTextCell0);
				Row1OfSheet.createCell(1).setCellValue(Expected_LoginPageTextCell1);
				Row1OfSheet.createCell(4).setCellValue(Expected_LoginPageTitle);
				Row1OfSheet.createCell(9).setCellValue(Expected_HomePageText);
		}
		Row row1OfSheet=sheetOfWorkbook.getRow(1);
		row1OfSheet.createCell(7).setCellValue(UserName);
		row1OfSheet.createCell(8).setCellValue(PassWord);
		
		Row row2OfSheet=sheetOfWorkbook.getRow(2);
		row2OfSheet.createCell(7).setCellValue(UserName2);
		row2OfSheet.createCell(8).setCellValue(PassWord2);
		
		Row row3OfSheet=sheetOfWorkbook.getRow(3);
		row3OfSheet.createCell(7).setCellValue(UserName3);
		row3OfSheet.createCell(8).setCellValue(PassWord3);
		
		Row row4OfSheet=sheetOfWorkbook.getRow(4);
		row4OfSheet.createCell(7).setCellValue(UserName4);
		row4OfSheet.createCell(8).setCellValue(PassWord4);
		
		Row row5OfSheet=sheetOfWorkbook.getRow(5);
		row5OfSheet.createCell(7).setCellValue(UserName);
		row5OfSheet.createCell(8).setCellValue(PassWord);
		
		//System.out.println("Adding data to the Excel Sheet is successfull");
		Log.info("Adding data to the Excel Sheet is successfull");
}
	@Test(priority=2,description="OrangeHRM Application Login Page Validation")
	public void login_PageValidation() throws IOException
	{
		///html/body/div[1]/div/div[2]/div/img
		By loginPageImageProperty=By.xpath(property.getProperty("loginPageImagexpathProperty"));
		WebElement loginPageImageElement=driver.findElement(loginPageImageProperty);
		Boolean flagOfLoginPage=loginPageImageElement.isDisplayed();
		if(flagOfLoginPage)
		{
			//System.out.println("OrangeHRM Application Login Page Image is Displayed");
			Log.info("OrangeHRM Application Login Page Image is Displayed");
		}
		else
		{
			//System.out.println("OrangeHRM Application Login Page Image is Not Displayed");
			Log.info("OrangeHRM Application Login Page Image is Not Displayed");

		}
		int rowsCount=sheetOfWorkbook.getLastRowNum();
		System.out.println("rowsCount"+rowsCount);
		for(int rowIndex=1;rowIndex<=rowsCount;rowIndex++)
		{
			Row row1OfSheet=sheetOfWorkbook.getRow(rowIndex);
			for(int cellIndex=7;cellIndex<8;cellIndex++)
			{
				String username=row1OfSheet.getCell(7).getStringCellValue();
				String password=row1OfSheet.getCell(8).getStringCellValue();
				try
				{
				//<input name="txtUsername" id="txtUsername" type="text">
				By usernameProperty=By.id(property.getProperty("usernameidProperty"));
				WebElement usernameElement=driver.findElement(usernameProperty);
				usernameElement.sendKeys(username);
				
				Actions tab=new Actions(driver);
				tab.sendKeys(Keys.TAB).build().perform();
				tab.sendKeys(password);
				tab.sendKeys(Keys.TAB).build().perform();
				tab.sendKeys(Keys.TAB.ENTER).build().perform();
				
				//<a href="/orangehrm-4.2.0.1/symfony/web/index.php/admin/viewAdminModule" 
				//id="menu_admin_viewAdminModule" class="firstLevelMenu"><b>Admin</b></a>
				By adminProperty=By.id(property.getProperty("adminidProperty"));
				WebElement adminElement=driver.findElement(adminProperty);
				String Actual_HomePagetext=adminElement.getText();
				sheetOfWorkbook.getRow(rowIndex).createCell(10).setCellValue(Actual_HomePagetext);
				
				String Actual_LoginPageTitle=driver.getTitle();
				sheetOfWorkbook.getRow(rowIndex).createCell(5).setCellValue(Actual_LoginPageTitle);
				
				sheetOfWorkbook.getRow(rowIndex).createCell(2).setCellValue("Valid credentials");
				
				//<a href="#" id="welcome" class="panelTrigger">Welcome Admin</a>
				By welcomeAdminProperty=By.linkText(property.getProperty("welcomeAdminLinkText"));
				WebElement welcomeAdminElement=driver.findElement(welcomeAdminProperty);
				welcomeAdminElement.click();
				
				//<a href="/orangehrm-4.2.0.1/symfony/web/index.php/auth/logout">Logout</a>
				By logoutbuttonProperty=By.linkText(property.getProperty("logoutLinkTextProperty"));
				WebElement logoutbuttonElement=driver.findElement(logoutbuttonProperty);
				logoutbuttonElement.click();

				}
				catch(Exception exceptionName)
				{
					//<span id="spanMessage">Invalid credentials</span>
					By invalidcredentialsProperty=By.id(property.getProperty("invalidcredentialsidProperty"));
					WebElement invalidcredentialsElement=driver.findElement(invalidcredentialsProperty);
					String Actual_LoginPageText=invalidcredentialsElement.getText();
					sheetOfWorkbook.getRow(rowIndex).createCell(2).setCellValue(Actual_LoginPageText);
					
					String Actual_LoginPageTitle=driver.getTitle();
					sheetOfWorkbook.getRow(rowIndex).createCell(5).setCellValue(Actual_LoginPageTitle);
					
					//<div id="logInPanelHeading">LOGIN Panel</div>
					By loginpanelProperty=By.id(property.getProperty("loginpanelnameIdProperty"));
					WebElement loginpanelElement=driver.findElement(loginpanelProperty);
					String ActualHomePageText=loginpanelElement.getText();
					sheetOfWorkbook.getRow(rowIndex).createCell(10).setCellValue(ActualHomePageText);

					
					File linkScreenShot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(linkScreenShot, new File("./ApplicationScreenshots/OrangeHRM_Login_Page "+" username "+ username +","+" password "+ password +" .png"));
				}
			}
		}
		//System.out.println("OrangeHRM Application Login Page Validation");
		Log.info("OrangeHRM Application Login Page Validation Successfull");
	}
	
	@Test(priority=3,description="Validation Of Login Page Text")
	public void loginPageText_Validation() throws IOException
	{
		int rowsCount=sheetOfWorkbook.getLastRowNum();
		for(int rowindex=1;rowindex<=rowsCount;rowindex++)
		{
			Row row1OfSheet=sheetOfWorkbook.getRow(rowindex);
			String Expected_LoginPageText=row1OfSheet.getCell(1).getStringCellValue();
			String Actual_LoginPageText=row1OfSheet.getCell(2).getStringCellValue();
				
			if(Actual_LoginPageText.equals(Expected_LoginPageText))
			{
				row1OfSheet.createCell(3).setCellValue("PASS");
			}
			else
			{
				row1OfSheet.createCell(3).setCellValue("FAIL");
			}
		}
		//System.out.println("Validation Of Login Page Text");
		Log.info("Validation Of Login Page Text is Successfull");
	}
	@Test(priority=4,description="Validation Of Login Page Title")
	public void loginPageTitle_Validation() throws IOException
	{
		int rowsCount=sheetOfWorkbook.getLastRowNum();
		for(int rowindex=1;rowindex<=rowsCount;rowindex++)
		{
			Row row1OfSheet=sheetOfWorkbook.getRow(rowindex);
			String Expected_LoginPageTitle=row1OfSheet.getCell(4).getStringCellValue();
			String Actual_LoginPageTitle=row1OfSheet.getCell(5).getStringCellValue();
				
			if(Actual_LoginPageTitle.equals(Expected_LoginPageTitle))
			{
				row1OfSheet.createCell(6).setCellValue("PASS");
			}
			else
			{
				row1OfSheet.createCell(6).setCellValue("FAIL");
			}
		}
		//System.out.println("Validation Of Login Page Title");
		Log.info("Validation Of Login Page Title is Successfull");
	}
	
	@Test(priority=5,description="Validation Of Home Page Text")
	public void homePageText_Validation() throws IOException
	{
		int rowsCount=sheetOfWorkbook.getLastRowNum();
		for(int rowindex=1;rowindex<=rowsCount;rowindex++)
		{
			Row row1OfSheet=sheetOfWorkbook.getRow(rowindex);
			String Expected_HomePageText=row1OfSheet.getCell(9).getStringCellValue();
			String Actual_HomePageText=row1OfSheet.getCell(10).getStringCellValue();
				
			if(Actual_HomePageText.equals(Expected_HomePageText))
			{
				row1OfSheet.createCell(11).setCellValue("PASS");
			}
			else
			{
				row1OfSheet.createCell(11).setCellValue("FAIL");
			}
		}
		FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/OrangeHRMApplicationLoginPageSheetResult.xlsx");
		workBook.write(outputdata);
		//System.out.println("Validation Of Home Page Text");
		Log.info("Validation Of Home Page Text is Successfull");
	}
	
}
