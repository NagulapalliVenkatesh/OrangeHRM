package com.AddEmployee27;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class OrangeHRM_ADDEmployee_validation extends BaseAddEmployee{
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
	String expectedFirstName2="Subhash";
	String expectedFirstName3="Mahendra";
	String expectedmiddleName1="Seetha";
	String expectedmiddleName2="Chandra";
	String expectedmiddleName3="Singh";
	String expectedlastName1="Rama Raju";
	String expectedlastName2="Bose";
	String expectedlastName3="Dhoni";
	
	FileInputStream inputdata;
	Properties propertiesFile;

	@Test(priority=1,description="Filling excel sheet with expected Values")
	public void excel_filling() throws IOException

	{
	inputdata=new FileInputStream("./configurationProperties/OrangeHRM_AddEmployee.properties");
	propertiesFile=new Properties();
	propertiesFile.load(inputdata);
	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");
	XSSFWorkbook workBook=new XSSFWorkbook(inputtestdata);

	XSSFSheet sheetofworkbook=workBook.getSheet("testdata");

	Row row1OfSheet=sheetofworkbook.createRow(1);

	row1OfSheet.createCell(15).setCellValue(expectedFirstName1);

	row1OfSheet.createCell(16).setCellValue(expectedmiddleName1);

	row1OfSheet.createCell(17).setCellValue(expectedlastName1);

	row1OfSheet.createCell(19).setCellValue(expectedpersonaldetailspagetext);

	

	Row row2OfSheet=sheetofworkbook.createRow(2);

	row2OfSheet.createCell(15).setCellValue(expectedFirstName2);

	row2OfSheet.createCell(16).setCellValue(expectedmiddleName2);

	row2OfSheet.createCell(17).setCellValue(expectedlastName2);

	row2OfSheet.createCell(19).setCellValue(expectedpersonaldetailspagetext);

	

	Row row3OfSheet=sheetofworkbook.createRow(3);

	row3OfSheet.createCell(15).setCellValue(expectedFirstName3);

	row3OfSheet.createCell(16).setCellValue(expectedmiddleName3);

	row3OfSheet.createCell(17).setCellValue(expectedlastName3);

	row3OfSheet.createCell(19).setCellValue(expectedpersonaldetailspagetext);

	

	row1OfSheet.createCell(0).setCellValue(expectedLoginPagetext0);

	row1OfSheet.createCell(1).setCellValue(expectedLoginPagetext);

	row1OfSheet.createCell(4).setCellValue(expectedLoginPagetitle);

	row1OfSheet.createCell(7).setCellValue(username);

	row1OfSheet.createCell(8).setCellValue(password);

	row1OfSheet.createCell(9).setCellValue(expectedhomepagetext);

	row1OfSheet.createCell(12).setCellValue(expectedaddemppagetext);

	row1OfSheet.createCell(30).setCellValue(expectedlogoutloginpagetext);

	

	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");

	workBook.write(outputdata);

	}

	@Test(priority=2,description="getting actual Login Page Title Page Title Of the Application & filling the excel with actual login page title")
	public void actualLoginPageTitle() throws IOException

	{

	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");

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

	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");

	workBook.write(outputdata);

	}

	@Test(priority=3,description="Validation of Login Page Title Of the Application & filling the excel with result")
	public void validationOfLoginPageTitle() throws IOException

	{

	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");

	XSSFWorkbook workBook=new XSSFWorkbook(inputtestdata);

	XSSFSheet sheetofworkbook=workBook.getSheet("testdata");

	

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

	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");

	workBook.write(outputdata);

	}

	@Test(priority=4,description="Getting actual home page text of the application and filling in the Excel")
	public void actualHomePageText() throws IOException

	{
	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");
	XSSFWorkbook workBook=new XSSFWorkbook(inputtestdata);
	XSSFSheet sheetofworkbook=workBook.getSheet("testdata");
	By username=By.name(propertiesFile.getProperty("usernameNameProperty"));
	WebElement usernameElement=driver.findElement(username);
	String usernametext=sheetofworkbook.getRow(1).getCell(7).getStringCellValue();
	usernameElement.sendKeys(usernametext);

	By password=By.name(propertiesFile.getProperty("passswordNameProperty"));
	WebElement passwordElement=driver.findElement(password);
	String passwordtext=sheetofworkbook.getRow(1).getCell(8).getStringCellValue();
	passwordElement.sendKeys(passwordtext);

	By button=By.name(propertiesFile.getProperty("buttonNameProperty"));
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
	By adminProperty=By.linkText(propertiesFile.getProperty("adminLinkTextProperty"));
	WebElement adminElement=driver.findElement(adminProperty);
	String adminText=adminElement.getText();
	sheetofworkbook.getRow(1).createCell(10).setCellValue(adminText);
	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");
	workBook.write(outputdata);
	}

	@Test(priority=5,description="validation of login page text of the application and filling in the Excel")
	public void validation_login_page_text() throws IOException

	{

	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");

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

	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");

	workBook.write(outputdata);

	}

	@Test(priority=6,description="validation of home page text of the application and filling Excel with home page test result")
	public void homePageText_validation() throws IOException

	{

	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");

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

	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");

	workBook.write(outputdata);

	}

	@Test(priority=7,description="getting actual add employee page text of the application and filling Excel")
	public void actual_AddEmployeePageText() throws IOException

	{

	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");

	XSSFWorkbook workBook=new XSSFWorkbook(inputtestdata);

	XSSFSheet sheetofworkbook=workBook.getSheet("testdata");

	

	By pimProperty=By.linkText(propertiesFile.getProperty("PIMLinktextProperty"));

	WebElement pimElement=driver.findElement(pimProperty);

	

	Actions mouse=new Actions(driver);

	mouse.moveToElement(pimElement).build().perform();

	

	By addemployeeProperty=By.linkText(propertiesFile.getProperty("addEmployeelinktextProperty"));

	WebElement addemployeeElement=driver.findElement(addemployeeProperty);

	addemployeeElement.click();
	
	By addemployeeTextProperty=By.xpath(propertiesFile.getProperty("AddEmployeexpathproperty"));

	WebElement addemployeeTextElement=driver.findElement(addemployeeTextProperty);

	String actAddEmpPageText=addemployeeTextElement.getText();

	sheetofworkbook.getRow(1).createCell(13).setCellValue(actAddEmpPageText);

	

	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");

	workBook.write(outputdata);

	}

	@Test(priority=8,description="Validation of add employee page text of the application and filling add employee text test result in Excel")
	public void validation_addEmpPageText() throws IOException

	{

	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");

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
	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");
	workBook.write(outputdata);
	}

	
	@Test(priority=9,description="Filling add employee in the application and saving it")
	public void addingEmployees() throws IOException, InterruptedException
	{
	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");

	XSSFWorkbook workBook=new XSSFWorkbook(inputtestdata);

	XSSFSheet sheetofworkbook=workBook.getSheet("testdata");
	int rowsCount=sheetofworkbook.getLastRowNum();
	System.out.println("rowscount "+rowsCount);
	for(int index=1;index<=rowsCount;index++)
	{
	String firstname=sheetofworkbook.getRow(index).getCell(15).getStringCellValue();

	String middlename=sheetofworkbook.getRow(index).getCell(16).getStringCellValue();

	String lastname=sheetofworkbook.getRow(index).getCell(17).getStringCellValue();
	By firstnameProperty=By.id(propertiesFile.getProperty("firstnameidProperty"));
	WebElement firstnameElement=driver.findElement(firstnameProperty);
	firstnameElement.sendKeys(firstname);
	
	By middlenameProperty=By.id(propertiesFile.getProperty("middlenameidProperty"));
	WebElement middlenameElement=driver.findElement(middlenameProperty);
	middlenameElement.sendKeys(middlename);
	
	By lastnameProperty=By.id(propertiesFile.getProperty("lastnameidProperty"));
	WebElement lastnameElement=driver.findElement(lastnameProperty);
	lastnameElement.sendKeys(lastname);
	
	By empIdProperty=By.id(propertiesFile.getProperty("EmployeeIDidProperty"));
	WebElement empIdElement=driver.findElement(empIdProperty);
	String expEmpidvalue=empIdElement.getAttribute("value");
	sheetofworkbook.getRow(index).createCell(18).setCellValue(expEmpidvalue);
	
	
	By btnsaveProperty=By.id(propertiesFile.getProperty("savebuttonIdProperty"));
	WebElement btnsaveElement=driver.findElement(btnsaveProperty);
	btnsaveElement.click();
	
	By personaldetailsProperty=By.xpath(propertiesFile.getProperty("PersonalDetailsxpathPropery"));
	WebElement personaldetailsElement=driver.findElement(personaldetailsProperty);
	String personaldetailsTExt=personaldetailsElement.getText();
	sheetofworkbook.getRow(index).createCell(20).setCellValue(personaldetailsTExt);
	
	By actfirstnameProperty=By.id(propertiesFile.getProperty("ActualfirstnameidProperty"));
	WebElement actfirstnameWebElement=driver.findElement(actfirstnameProperty);
	String actualFirstname=actfirstnameWebElement.getAttribute("value");
	sheetofworkbook.getRow(index).createCell(22).setCellValue(actualFirstname);
	
	By actmiddlenameProperty=By.id(propertiesFile.getProperty("ActualmiddlenameidProperty"));
	WebElement actmiddlenameWebElement=driver.findElement(actmiddlenameProperty);
	String actualMiddlename=actmiddlenameWebElement.getAttribute("value");
	sheetofworkbook.getRow(index).createCell(24).setCellValue(actualMiddlename);
	
	By actlastnameProperty=By.id(propertiesFile.getProperty("ActuallastnameidProperty"));
	WebElement actlastnameWebElement=driver.findElement(actlastnameProperty);
	String actualLastname=actlastnameWebElement.getAttribute("value");
	sheetofworkbook.getRow(index).createCell(26).setCellValue(actualLastname);
	
	
	By actempidProperty=By.id(propertiesFile.getProperty("expectedempidProperty"));
	WebElement actempidElement=driver.findElement(actempidProperty);
	String actEmpIdValue=actempidElement.getAttribute("value");
	sheetofworkbook.getRow(index).createCell(28).setCellValue(actEmpIdValue);
	
	By pimProperty=By.linkText(propertiesFile.getProperty("PIMLinktextProperty"));
	WebElement pimElement=driver.findElement(pimProperty);
	Actions mouse=new Actions(driver);
	mouse.moveToElement(pimElement).build().perform();
	
	By addemployeeProperty=By.linkText(propertiesFile.getProperty("addEmployeelinktextProperty"));
	WebElement addemployeeElement=driver.findElement(addemployeeProperty);
	addemployeeElement.click();
	
	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");
	workBook.write(outputdata);
	}
	}

	@Test(priority=10,description="OrangeHRM Application getting logging out")
	public void logOut() throws InterruptedException
	{
	By welcomeadminProperty=By.linkText(propertiesFile.getProperty("welcomeadminlinktextProperty"));
	WebElement welcomeadminElement=driver.findElement(welcomeadminProperty);
	welcomeadminElement.click();
	By logoutProperty=By.linkText(propertiesFile.getProperty("logoutlinktextProperty"));

	WebElement logoutElement=driver.findElement(logoutProperty);
	logoutElement.click();
	}
	
	@Test(priority=11,description="Validations for all data")
	public void validations() throws IOException
	{	
	for(int index=1;index<=3;index++)
	{
	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");
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
	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");
	workBook.write(outputdata);
	}
	}

	
	@Test(priority=12,description="Capturing Actual login page text and validation of login page text")
	public void loginpage_validation() throws IOException
	{
	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");
	XSSFWorkbook workBook=new XSSFWorkbook(inputtestdata);
	XSSFSheet sheetofworkbook=workBook.getSheet("testdata");
	By loginpanelProperty=By.id(propertiesFile.getProperty("loginpanelnameidproperty"));
	WebElement loginpanelElement=driver.findElement(loginpanelProperty);
	String loginpageText=loginpanelElement.getText();
	sheetofworkbook.getRow(1).createCell(31).setCellValue(loginpageText);
	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");
	workBook.write(outputdata);
	}
	@Test(priority=13,description="Validation of login page text and filling login pgae text result in the Excel")
	public void loginPageText_Validation() throws IOException
	{
	FileInputStream inputtestdata= new FileInputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");
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
	FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/AddEmployeeExcelSheet.xlsx");
	workBook.write(outputdata);
	}
}
