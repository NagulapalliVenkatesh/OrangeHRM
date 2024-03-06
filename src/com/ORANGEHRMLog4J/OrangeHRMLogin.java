package com.ORANGEHRMLog4J;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.Utility.Log;



public class OrangeHRMLogin extends BaseTest 
{
	String expectedLoginPagetext0="LOGIN Panel";
	String expectedLoginPagetext="Invalid credentials";
	String expectedLoginPagetitle="orangeHRM";
	String username="venky";
	String password="Venky@123";
	String expectedhomepagetext="Admin";
	String expectedaddemppagetext="Add Employee";
	String expectedpersonaldetailspagetext="Personal Details";
	String expectedlogoutloginpagetext="LOGIN Panel";
	String expectedFirstName1="Alluri";
	String expectedmiddleName1="Seetha";
	String expectedlastName1="Rama Raju";
	FileInputStream inputdata;
	Properties propertiesFile;

	@Test(priority=1,description="Filling excel sheet with expected Values")
	public void excel_filling() throws IOException

	{
	inputdata=new FileInputStream("./configurationProperties/OrangeHRMLog4JProperties");
	propertiesFile=new Properties();
	propertiesFile.load(inputdata);
	
	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	XSSFWorkbook workBook=new XSSFWorkbook(inputtestdata);

	XSSFSheet sheetofworkbook=workBook.getSheet("testdata");

	Row row1OfSheet=sheetofworkbook.createRow(1);
	row1OfSheet.createCell(15).setCellValue(expectedFirstName1);
	row1OfSheet.createCell(16).setCellValue(expectedmiddleName1);
	row1OfSheet.createCell(17).setCellValue(expectedlastName1);
	row1OfSheet.createCell(19).setCellValue(expectedpersonaldetailspagetext);

	row1OfSheet.createCell(0).setCellValue(expectedLoginPagetext0);
	row1OfSheet.createCell(1).setCellValue(expectedLoginPagetext);
	row1OfSheet.createCell(4).setCellValue(expectedLoginPagetitle);
	row1OfSheet.createCell(7).setCellValue(username);
	row1OfSheet.createCell(8).setCellValue(password);
	row1OfSheet.createCell(9).setCellValue(expectedhomepagetext);
	row1OfSheet.createCell(12).setCellValue(expectedaddemppagetext);
	row1OfSheet.createCell(30).setCellValue(expectedlogoutloginpagetext);

	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	workBook.write(outputdata);
	Log.info("Filling excel sheet with expected Values");
	//System.out.println("1 Filling excel sheet with expected Values");
	}

	
	@Test(priority=2,description="login page OrangeHRM image Validation")
	public void loginimageValidation()
	{
		By loginpageimageProperty=By.xpath(propertiesFile.getProperty("loginpageimagexpath"));
		WebElement loginpageimageElement=driver.findElement(loginpageimageProperty);
		Boolean flag=loginpageimageElement.isDisplayed();
		if(flag)
		{
			Log.info("Login page image is Displayed");
			//System.out.println("Login page image is Displayed");
		}
		else
		{
			Log.info("Login page image is Not Displayed");
			//System.out.println("Login page image NOT is Displayed");
		}
		Log.info("login page OrangeHRM image Validation");
		//System.out.println("2 login page OrangeHRM image Validation");
	}
	@Test(priority=3,description="getting actual Login Page Title Page Title Of the Application & filling the excel with actual login page title")
	public void actualLoginPageTitle() throws IOException
	{
	
	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	XSSFWorkbook workBook=new XSSFWorkbook(inputtestdata);
	XSSFSheet sheetofworkbook=workBook.getSheet("testdata");
	String actualLoginPageTitle=driver.getTitle();
	sheetofworkbook.getRow(1).createCell(5).setCellValue(actualLoginPageTitle);
	String exp_LoginPageTitle=sheetofworkbook.getRow(1).getCell(4).getStringCellValue();
	String act_LoginPageTitle=sheetofworkbook.getRow(1).getCell(5).getStringCellValue();
	if(act_LoginPageTitle.equals(exp_LoginPageTitle))
	{
	sheetofworkbook.getRow(1).createCell(6).setCellValue("PASS");
	}
	else
	{
	sheetofworkbook.getRow(1).createCell(6).setCellValue("FAIL");
	}
	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	workBook.write(outputdata);
	Log.info("getting actual Login Page Title Page Title Of the Application & filling the excel with actual login page title");
	//System.out.println("3 getting actual Login Page Title Page Title Of the Application & filling the excel with actual login page title");
	}
	
	@Test(priority=4,description="Getting actual home page text of the application and filling in the Excel")
	public void actualHomePageText() throws IOException
	{
	
	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	XSSFWorkbook workBook=new XSSFWorkbook(inputtestdata);
	XSSFSheet sheetofworkbook=workBook.getSheet("testdata");
	By username=By.id(propertiesFile.getProperty("usernameid"));
	WebElement usernameElement=driver.findElement(username);
	String usernametext=sheetofworkbook.getRow(1).getCell(7).getStringCellValue();
	usernameElement.sendKeys(usernametext);

	By password=By.id(propertiesFile.getProperty("passwordid"));
	WebElement passwordElement=driver.findElement(password);
	String passwordtext=sheetofworkbook.getRow(1).getCell(8).getStringCellValue();
	passwordElement.sendKeys(passwordtext);

	By button=By.id(propertiesFile.getProperty("loginbuttonid"));
	WebElement buttonElement=driver.findElement(button);
	buttonElement.click();
	
	String expUrl="http://127.0.0.1/orangehrm-4.2.0.1/symfony/web/index.php/dashboard";
	String actUrl=driver.getCurrentUrl();
	if(actUrl.equals(expUrl))
	{
	sheetofworkbook.getRow(1).createCell(2).setCellValue("Valid credentials");
	}
	else
	{
	sheetofworkbook.getRow(1).createCell(2).setCellValue("Invalid credentials");
	}
	By adminProperty=By.id(propertiesFile.getProperty("adminid"));
	WebElement adminElement=driver.findElement(adminProperty);
	String adminText=adminElement.getText();
	sheetofworkbook.getRow(1).createCell(10).setCellValue(adminText);
	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	workBook.write(outputdata);
	Log.info("Getting actual home page text of the application and filling in the Excel");
	//System.out.println("4 Getting actual home page text of the application and filling in the Excel");
	}

	@Test(priority=5,description="validation of login page text of the application and filling in the Excel")
	public void validation_login_page_text() throws IOException
	{
	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	XSSFWorkbook workBook=new XSSFWorkbook(inputtestdata);
	XSSFSheet sheetofworkbook=workBook.getSheet("testdata");
	String exploginpagetext=sheetofworkbook.getRow(1).getCell(1).getStringCellValue();
	String actloginpagetext=sheetofworkbook.getRow(1).getCell(2).getStringCellValue();
	if(actloginpagetext.equals(exploginpagetext))
	{
	sheetofworkbook.getRow(1).createCell(3).setCellValue("PASS");
	}
	else
	{
	sheetofworkbook.getRow(1).createCell(3).setCellValue("FAIL");
	}
	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	workBook.write(outputdata);
	Log.info("validation of login page text of the application and filling in the Excel");
	//System.out.println("5 validation of login page text of the application and filling in the Excel");
	}
	
	@Test(priority=6,description="validation of home page text of the application and filling Excel with home page test result")
	public void homePageText_validation() throws IOException
	{
	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	XSSFWorkbook workBook=new XSSFWorkbook(inputtestdata);
	XSSFSheet sheetofworkbook=workBook.getSheet("testdata");
	String expHomePageText=sheetofworkbook.getRow(1).getCell(9).getStringCellValue();
	String actHomePageText=sheetofworkbook.getRow(1).getCell(10).getStringCellValue();
	if(actHomePageText.equals(expHomePageText))
	{
	sheetofworkbook.getRow(1).createCell(11).setCellValue("PASS");
	}
	else
	{
	sheetofworkbook.getRow(1).createCell(11).setCellValue("FAIL");
	}
	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	workBook.write(outputdata);
	Log.info("validation of home page text of the application and filling Excel with home page test result");
	//System.out.println("6 validation of home page text of the application and filling Excel with home page test result");
	}
	
	
}
