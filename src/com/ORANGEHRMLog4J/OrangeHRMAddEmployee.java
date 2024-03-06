package com.ORANGEHRMLog4J;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.Utility.Log;


public class OrangeHRMAddEmployee extends OrangeHRMLogin{
	
	FileInputStream fileinput;
	Properties properties;
	
	FileInputStream inputtestdata;
	XSSFWorkbook workBook;
	XSSFSheet sheetofworkbook;
	
	@Test(priority=7,description="getting actual add employee page text of the application and filling Excel")
	public void actual_AddEmployeePageText() throws IOException
	{
	fileinput=new FileInputStream("./configurationProperties/OrangeHRMLog4JProperties");
	properties=new Properties();
	properties.load(fileinput);
		
	inputtestdata= new FileInputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	workBook=new XSSFWorkbook(inputtestdata);
	sheetofworkbook=workBook.getSheet("testdata");

	By pimProperty=By.linkText(properties.getProperty("PIMlinktext"));
	WebElement pimElement=driver.findElement(pimProperty);
	Actions mouse=new Actions(driver);
	mouse.moveToElement(pimElement).build().perform();
	By addemployeeProperty=By.linkText(properties.getProperty("addEmployeelinktextProperty"));
	WebElement addemployeeElement=driver.findElement(addemployeeProperty);
	addemployeeElement.click();
	
	By addemployeeTextProperty=By.xpath(properties.getProperty("AddEmployeexpathproperty"));
	WebElement addemployeeTextElement=driver.findElement(addemployeeTextProperty);
	String actAddEmpPageText=addemployeeTextElement.getText();
	sheetofworkbook.getRow(1).createCell(13).setCellValue(actAddEmpPageText);
	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	workBook.write(outputdata);
	Log.info("getting actual add employee page text of the application and filling Excel");
	//System.out.println("7 getting actual add employee page text of the application and filling Excel");
	}

	@Test(priority=8,description="Validation of add employee page text of the application and filling add employee text test result in Excel")
	public void validation_addEmpPageText() throws IOException
	{
	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	XSSFWorkbook workBook=new XSSFWorkbook(inputtestdata);
	XSSFSheet sheetofworkbook=workBook.getSheet("testdata");
	String expAddEmpPageText=sheetofworkbook.getRow(1).getCell(12).getStringCellValue();
	String actAddEmpPageText=sheetofworkbook.getRow(1).getCell(13).getStringCellValue();
	if(actAddEmpPageText.equals(expAddEmpPageText))
	{
	sheetofworkbook.getRow(1).createCell(14).setCellValue("PASS");
	}
	else
	{
	sheetofworkbook.getRow(1).createCell(14).setCellValue("FAIL");
	}
	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	workBook.write(outputdata);
	Log.info("Validation of add employee page text of the application and filling add employee text test result in Excel");
	//System.out.println("8 Validation of add employee page text of the application and filling add employee text test result in Excel");
	}

	
	@Test(priority=9,description="Filling add employee in the application and saving it")
	public void addingEmployees() throws IOException, InterruptedException
	{
	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");

	XSSFWorkbook workBook=new XSSFWorkbook(inputtestdata);

	XSSFSheet sheetofworkbook=workBook.getSheet("testdata");
	
	String firstname=sheetofworkbook.getRow(1).getCell(15).getStringCellValue();

	String middlename=sheetofworkbook.getRow(1).getCell(16).getStringCellValue();

	String lastname=sheetofworkbook.getRow(1).getCell(17).getStringCellValue();
	
	By firstnameProperty=By.id(properties.getProperty("firstnameidProperty"));
	WebElement firstnameElement=driver.findElement(firstnameProperty);
	firstnameElement.sendKeys(firstname);
	
	Actions tab=new Actions(driver);
	tab.sendKeys(Keys.TAB).build().perform();
	tab.sendKeys(middlename);
	tab.sendKeys(Keys.TAB).build().perform();
	tab.sendKeys(lastname);
	tab.sendKeys(Keys.TAB).build().perform();
	tab.sendKeys(Keys.TAB).build().perform();
	tab.sendKeys(Keys.TAB.ENTER).build().perform();
	
	Thread.sleep(3000);
	Runtime.getRuntime().getRuntime().exec("./AddEmployeeCompliedScript/OrangeHRMscript.exe");
	Thread.sleep(3000);

	
	By empIdProperty=By.id(properties.getProperty("EmployeeIDidProperty"));
	WebElement empIdElement=driver.findElement(empIdProperty);
	String expEmpidvalue=empIdElement.getAttribute("value");
	sheetofworkbook.getRow(1).createCell(18).setCellValue(expEmpidvalue);
	
	
	By btnsaveProperty=By.id(properties.getProperty("savebuttonIdProperty"));
	WebElement btnsaveElement=driver.findElement(btnsaveProperty);
	btnsaveElement.click();
	
	By personaldetailsProperty=By.xpath(properties.getProperty("PersonalDetailsxpathPropery"));
	WebElement personaldetailsElement=driver.findElement(personaldetailsProperty);
	String personaldetailsTExt=personaldetailsElement.getText();
	sheetofworkbook.getRow(1).createCell(20).setCellValue(personaldetailsTExt);
	
	By actfirstnameProperty=By.id(properties.getProperty("ActualfirstnameidProperty"));
	WebElement actfirstnameWebElement=driver.findElement(actfirstnameProperty);
	String actualFirstname=actfirstnameWebElement.getAttribute("value");
	sheetofworkbook.getRow(1).createCell(22).setCellValue(actualFirstname);
	
	By actmiddlenameProperty=By.id(properties.getProperty("ActualmiddlenameidProperty"));
	WebElement actmiddlenameWebElement=driver.findElement(actmiddlenameProperty);
	String actualMiddlename=actmiddlenameWebElement.getAttribute("value");
	sheetofworkbook.getRow(1).createCell(24).setCellValue(actualMiddlename);
	
	By actlastnameProperty=By.id(properties.getProperty("ActuallastnameidProperty"));
	WebElement actlastnameWebElement=driver.findElement(actlastnameProperty);
	String actualLastname=actlastnameWebElement.getAttribute("value");
	sheetofworkbook.getRow(1).createCell(26).setCellValue(actualLastname);
	
	By actempidProperty=By.id(properties.getProperty("actempidProperty"));
	WebElement actempidElement=driver.findElement(actempidProperty);
	String actempid=actempidElement.getAttribute("value");
	sheetofworkbook.getRow(1).createCell(28).setCellValue(actempid);

	By empPicProperty=By.id(properties.getProperty("empPicid"));
	WebElement empPicElement=driver.findElement(empPicProperty);
	Boolean flag=empPicElement.isDisplayed();
	if(flag)
	{
		Log.info("Employee pic picture is displayed");
		//System.out.println("Employee pic picture is displayed");
	}
	else
	{
		Log.info("Employee pic picture is not displayed");
		//System.out.println("Employee pic picture is not displayed");
	}
	
	By welcomeProperty=By.id(properties.getProperty("welcomeadminid"));
	WebElement welcomeElement=driver.findElement(welcomeProperty);
	welcomeElement.click();
	
	By logoutProperty=By.linkText(properties.getProperty("logoutbuttonlinktext"));
	WebElement logoutElement=driver.findElement(logoutProperty);
	logoutElement.click();
	
	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	workBook.write(outputdata);
	Log.info("Filling add employee in the application and saving it");
	//System.out.println("9 Filling add employee in the application and saving it");
	}

	@Test(priority=10,description="Validations for all data")
	public void validations() throws IOException
	{
	int index=1;
	{	
	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	XSSFWorkbook workBook=new XSSFWorkbook(inputtestdata);
	XSSFSheet sheetofworkbook=workBook.getSheet("testdata");
	String expected_personal_detailsPagetext=sheetofworkbook.getRow(index).getCell(19).getStringCellValue();
	String actual_personal_detailsPagetext=sheetofworkbook.getRow(index).getCell(20).getStringCellValue();
	if(actual_personal_detailsPagetext.equals(expected_personal_detailsPagetext))
	{
	sheetofworkbook.getRow(index).createCell(21).setCellValue("PASS");
	}
	else
	{
	sheetofworkbook.getRow(index).createCell(21).setCellValue("FAIL");
	}
	String expfirstnameText=sheetofworkbook.getRow(index).getCell(15).getStringCellValue();
	String actfirstnameText=sheetofworkbook.getRow(index).getCell(22).getStringCellValue();
	if(actfirstnameText.equals(expfirstnameText))
	{
	sheetofworkbook.getRow(index).createCell(23).setCellValue("PASS");
	}
	else
	{
	sheetofworkbook.getRow(index).createCell(23).setCellValue("FAIL");
	}
	String expmiddlenameText=sheetofworkbook.getRow(index).getCell(16).getStringCellValue();
	String actmiddlenameText=sheetofworkbook.getRow(index).getCell(24).getStringCellValue();
	if(actmiddlenameText.equals(expmiddlenameText))
	{
	sheetofworkbook.getRow(index).createCell(25).setCellValue("PASS");
	}
	else
	{
	sheetofworkbook.getRow(index).createCell(25).setCellValue("FAIL");
	}
	String explastnameText=sheetofworkbook.getRow(index).getCell(17).getStringCellValue();
	String actlastnameText=sheetofworkbook.getRow(index).getCell(26).getStringCellValue();
	if(actlastnameText.equals(explastnameText))
	{
	sheetofworkbook.getRow(index).createCell(27).setCellValue("PASS");
	}
	else
	{
	sheetofworkbook.getRow(index).createCell(27).setCellValue("FAIL");
	}
	String expempidText=sheetofworkbook.getRow(index).getCell(18).getStringCellValue();
	String actempidText=sheetofworkbook.getRow(index).getCell(28).getStringCellValue();
	if(actempidText.equals(expempidText))
	{
	sheetofworkbook.getRow(index).createCell(29).setCellValue("PASS");
	}
	else
	{
	sheetofworkbook.getRow(index).createCell(29).setCellValue("Fail");
	}
	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	workBook.write(outputdata);
	Log.info("Validations for all data");
	//System.out.println("10 Validations for all data");
	}
	}

	
	@Test(priority=11,description="Capturing Actual login page text and validation of login page text")
	public void loginpage_validation() throws IOException
	{
	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	XSSFWorkbook workBook=new XSSFWorkbook(inputtestdata);
	XSSFSheet sheetofworkbook=workBook.getSheet("testdata");
	By loginpanelProperty=By.id(properties.getProperty("loginpanelnameidproperty"));
	WebElement loginpanelElement=driver.findElement(loginpanelProperty);
	String loginpageText=loginpanelElement.getText();
	sheetofworkbook.getRow(1).createCell(31).setCellValue(loginpageText);
	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	workBook.write(outputdata);
	Log.info("Capturing Actual login page text and validation of login page text");
	//System.out.println("11 Capturing Actual login page text and validation of login page text");
	}
	
	@Test(priority=12,description="Validation of login page text and filling login pgae text result in the Excel")
	public void loginPageText_Validation() throws IOException
	{
	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	XSSFWorkbook workBook=new XSSFWorkbook(inputtestdata);
	XSSFSheet sheetofworkbook=workBook.getSheet("testdata");
	String exploginpagetext=sheetofworkbook.getRow(1).getCell(30).getStringCellValue();
	String actloginpagetext=sheetofworkbook.getRow(1).getCell(31).getStringCellValue();
	if(actloginpagetext.equals(exploginpagetext))
	{
	sheetofworkbook.getRow(1).createCell(32).setCellValue("PASS");
	}
	else
	{
	sheetofworkbook.getRow(1).createCell(32).setCellValue("FAIL");
	}	
	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/OrangeHRMSheetLog4J.xlsx");
	workBook.write(outputdata);
	Log.info("Validation of login page text and filling login pgae text result in the Excel");
	//System.out.println("12 Validation of login page text and filling login pgae text result in the Excel");
}
}
