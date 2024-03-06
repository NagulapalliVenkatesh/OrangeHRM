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

public class OrangeHRM_Add_Employee_Page extends OrangeHRM_Base_Test
{
	FileInputStream inputtestdata;
	XSSFWorkbook workBook;
	XSSFSheet sheetOfWorkbook;
	
	FileInputStream input;
	Properties property;
	
	String UserName="venky";
	String PassWord="Venky@123";
	
	String Expected_LoginPageTextCell0="LOGIN Panel";
	String Expected_LoginPageTextCell1="Invalid credentials";
	String Expected_LoginPageTitle="orangeHRM";
	String Expected_HomePageText="Admin";
	String Expected_AddEmployeePageText="Add Employee";
	String Expected_PersonalDetailsPageText="Personal Details";
	String Expected_LogoutLoginPageText="LOGIN Panel";
	String Expected_FirstName1="Alluri";
	String Expected_MiddleName1="Seetha";
	String Expected_LastName1="Rama Raju";
	String Expected_FirstName2="Mahendra";
	String Expected_MiddleName2="Singh";
	String Expected_LastName2="Dhoni";
	String Expected_FirstName3="Yuva";
	String Expected_MiddleName3="Raj";
	String Expected_LastName3="Singh";
	
	int rowsCount;

	@Test(priority=1,description="Adding data to the Excel Sheet")
	public void excel_TestData() throws IOException
	{
		inputtestdata=new FileInputStream("./ExcelSheets/OrangeHRMApplicationAddEmployeePageSheet.xlsx");
			workBook=new XSSFWorkbook(inputtestdata);
				sheetOfWorkbook=workBook.getSheet("Sheet1");
		
		input=new FileInputStream("./configurationProperties/OrangeHRMApplicationIntegrated.Properties");
			property=new Properties();
				property.load(input);
		
		Row Row1OfSheet=sheetOfWorkbook.createRow(1);
		Row Row2OfSheet=sheetOfWorkbook.createRow(2);
		Row Row3OfSheet=sheetOfWorkbook.createRow(3);
		Row1OfSheet.createCell(0).setCellValue(Expected_LoginPageTextCell0);
		Row1OfSheet.createCell(1).setCellValue(Expected_LoginPageTextCell1);
		Row1OfSheet.createCell(4).setCellValue(Expected_LoginPageTitle);
		Row1OfSheet.createCell(9).setCellValue(Expected_HomePageText);
		Row1OfSheet.createCell(7).setCellValue(UserName);
		Row1OfSheet.createCell(8).setCellValue(PassWord);
		Row1OfSheet.createCell(15).setCellValue(Expected_FirstName1);
		Row1OfSheet.createCell(16).setCellValue(Expected_MiddleName1);
		Row1OfSheet.createCell(17).setCellValue(Expected_LastName1);
		Row2OfSheet.createCell(15).setCellValue(Expected_FirstName2);
		Row2OfSheet.createCell(16).setCellValue(Expected_MiddleName2);
		Row2OfSheet.createCell(17).setCellValue(Expected_LastName2);
		Row3OfSheet.createCell(15).setCellValue(Expected_FirstName3);
		Row3OfSheet.createCell(16).setCellValue(Expected_MiddleName3);
		Row3OfSheet.createCell(17).setCellValue(Expected_LastName3);
		Row1OfSheet.createCell(30).setCellValue(Expected_LogoutLoginPageText);
		
		rowsCount=sheetOfWorkbook.getLastRowNum();
		for(int rowindex=1;rowindex<=rowsCount;rowindex++)
		{
			Row RowOfSheet=sheetOfWorkbook.getRow(rowindex);
			RowOfSheet.createCell(12).setCellValue(Expected_AddEmployeePageText);
			RowOfSheet.createCell(19).setCellValue(Expected_PersonalDetailsPageText);			
		}
		//System.out.println("Adding data to the Excel Sheet is successfull");
		Log.info("Adding data to the Excel Sheet is successfull");
}
	@Test(priority=2,description="OrangeHRM Application Login Page Validation")
	public void login_PageValidation() throws IOException, InterruptedException
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
		Row row1OfSheet=sheetOfWorkbook.getRow(1);
		String username=row1OfSheet.getCell(7).getStringCellValue();
		String password=row1OfSheet.getCell(8).getStringCellValue();
		
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
					row1OfSheet.createCell(10).setCellValue(Actual_HomePagetext);
		
		String Actual_LoginPageTitle=driver.getTitle();
		row1OfSheet.createCell(5).setCellValue(Actual_LoginPageTitle);
		row1OfSheet.createCell(2).setCellValue("Valid credentials");
		Log.info("OrangeHRM Application Login Page Validation Is Successfull");
	}
	
	@Test(priority=3,description="Validation Of Login Page Text")
	public void loginPageText_Validation() throws IOException
	{
		Row row1OfSheet =sheetOfWorkbook.getRow(1);	
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
		//System.out.println("Validation Of Login Page Text");
		Log.info("Validation Of Login Page Text Is Successfull");
	}
	
	@Test(priority=4,description="Identifying PIM in OrangeHRM Application")
	public void PIM()
	{
		//<a href="/orangehrm-4.2.0.1/symfony/web/index.php/pim/viewPimModule" 
		//id="menu_pim_viewPimModule" class="firstLevelMenu"><b>PIM</b></a>
		By PIMProperty=By.linkText(property.getProperty("PIMlinktextProperty"));
			WebElement PIMElement=driver.findElement(PIMProperty);
		
		Actions pimMouseHovering=new Actions(driver);
			pimMouseHovering.moveToElement(PIMElement).build().perform();
			Log.info("Identifying PIM in OrangeHRM Application Is Successfull");
	}
	
	@Test(priority=5,description="Identifying Add Employee in OrangeHRM Application")
	public void addEmployee()
	{
		//<a href="/orangehrm-4.2.0.1/symfony/web/index.php/pim/addEmployee" 
		//id="menu_pim_addEmployee">Add Employee</a>
		By addEmployeeProperty=By.linkText(property.getProperty("AddEmployeeLinkTextProperty"));
			WebElement addEmployeeElement=driver.findElement(addEmployeeProperty);
				addEmployeeElement.click();
				Log.info("Identifying Add Employee in OrangeHRM Application Is Successfull");		
	}
	
	@Test(priority=6,description="Validation Of Login Page Title")
	public void loginPageTitle_Validation() throws IOException
	{
		Row row1OfSheet =sheetOfWorkbook.getRow(1);	
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
		//System.out.println("Validation Of Login Page Title");
		Log.info("Validation Of Login Page Title is Successfull");
	}
	
	@Test(priority=7,description="Validation Of Home Page Text")
	public void homePageText_Validation() throws IOException
	{
		Row row1OfSheet =sheetOfWorkbook.getRow(1);
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
		//System.out.println("Validation Of Home Page Text");
		Log.info("Validation Of Home Page Text is Successfull");
	}
	
	@Test(priority=8,description="Filling Excel Sheet with Actual Results Of Add Employee Page")
	public void addEmployeePage() throws IOException, InterruptedException
	{
		rowsCount=sheetOfWorkbook.getLastRowNum();
		for(int rowindex=1;rowindex<=rowsCount;rowindex++)
		{
		Row rowofsheet =sheetOfWorkbook.getRow(rowindex);
		///html/body/div[1]/div[3]/div/div[1]/h1
		By addEmployeePageTextProperty=By.xpath(property.getProperty("AddEmployeepageTextXpath"));
		WebElement addEmployeePageTextElement=driver.findElement(addEmployeePageTextProperty);
		String Actual_AddEmployeePageText=addEmployeePageTextElement.getText();
		sheetOfWorkbook.getRow(rowindex).createCell(13).setCellValue(Actual_AddEmployeePageText);

		
		//<input class="formInputText" maxlength="10" type="text" name="employeeId" value="0254" id="employeeId">
		By ExpectedEmployeeIdProperty=By.id(property.getProperty("ExpectedEmpidIDProperty"));
		WebElement empidElement=driver.findElement(ExpectedEmployeeIdProperty);
		String Expected_Employee_ID=empidElement.getAttribute("value");
		sheetOfWorkbook.getRow(rowindex).createCell(18).setCellValue(Expected_Employee_ID);
		
		
		//<input class="formInputText" maxlength="30" type="text" name="firstName" id="firstName">
		By firstNameProperty=By.id(property.getProperty("firstnametextboxIDProperty"));
		WebElement firstNameElement=driver.findElement(firstNameProperty);
		String Expected_FisrtName=rowofsheet.getCell(15).getStringCellValue();
		firstNameElement.sendKeys(Expected_FisrtName);
		
		Actions tab=new Actions(driver);
		
		tab.sendKeys(Keys.TAB).build().perform();
		String Expected_MiddleName=rowofsheet.getCell(16).getStringCellValue();
		tab.sendKeys(Expected_MiddleName);
		tab.sendKeys(Keys.TAB).build().perform();
		String Expected_LastName=rowofsheet.getCell(17).getStringCellValue();
		tab.sendKeys(Expected_LastName);
		tab.sendKeys(Keys.TAB).build().perform();
		tab.sendKeys(Keys.TAB).build().perform();
		tab.sendKeys(Keys.TAB.ENTER).build().perform();
		
		Thread.sleep(3000);
		Runtime.getRuntime().getRuntime().exec("./Scripts/AddEmployeeScript.exe");
		Thread.sleep(3000);
		
		//<input type="button" class="" id="btnSave" value="Save">
		By saveButtonProperty=By.id(property.getProperty("saveButtonIDProperty"));
		WebElement saveButtonElement=driver.findElement(saveButtonProperty);
		saveButtonElement.click();
		
		
		///html/body/div[1]/div[3]/div/div[2]/div[1]/h1
		By personalDetailsProperty=By.xpath(property.getProperty("personalDetailsxpathProperty"));
		WebElement personaleDetailsElement=driver.findElement(personalDetailsProperty);
		String Actual_PersonalDetailsPageText=personaleDetailsElement.getText();
		sheetOfWorkbook.getRow(rowindex).createCell(20).setCellValue(Actual_PersonalDetailsPageText);
		
		//<input value="sdgrg" type="text" name="personal[txtEmpFirstName]" class="block default editable" maxlength="30" title="First Name" 
		//id="personal_txtEmpFirstName" disabled="disabled">
		By actfirstNameProperty=By.id(property.getProperty("actualfirstnameidProperty"));
		WebElement actfirstNameElement=driver.findElement(actfirstNameProperty);
		String Actual_FirstName=actfirstNameElement.getAttribute("value");
		sheetOfWorkbook.getRow(rowindex).createCell(22).setCellValue(Actual_FirstName);

		//<input value="xbdzcvvb" type="text" name="personal[txtEmpMiddleName]" class="block default editable" maxlength="30" title="Middle Name" 
		//id="personal_txtEmpMiddleName" disabled="disabled">
		By actmiddleNameProperty=By.id(property.getProperty("actualmiddlenameidProperty"));
		WebElement actmiddleNameElement=driver.findElement(actmiddleNameProperty);
		String Actual_MiddleName=actmiddleNameElement.getAttribute("value");
		sheetOfWorkbook.getRow(rowindex).createCell(24).setCellValue(Actual_MiddleName);
		
		//<input value="gxf" type="text" name="personal[txtEmpLastName]" class="block default editable" maxlength="30" title="Last Name" 
		//id="personal_txtEmpLastName" disabled="disabled">
		By actlastNameProperty=By.id(property.getProperty("actuallastnameidProperty"));
		WebElement actlastNameElement=driver.findElement(actlastNameProperty);
		String Actual_LastName=actlastNameElement.getAttribute("value");
		sheetOfWorkbook.getRow(rowindex).createCell(26).setCellValue(Actual_LastName);
		
		//<input value="0254" type="text" name="personal[txtEmployeeId]" maxlength="10" class="editable" 
		//id="personal_txtEmployeeId" disabled="disabled">
		By actempProperty=By.id(property.getProperty("actualemployeeIdProperty"));
		WebElement actempidElement=driver.findElement(actempProperty);
		String Actual_Employeeid=actempidElement.getAttribute("value");
		sheetOfWorkbook.getRow(rowindex).createCell(28).setCellValue(Actual_Employeeid);
		
		//<img alt="Employee Photo" src="/orangehrm-4.2.0.1/symfony/web/index.php/pim/viewPhoto/empNumber/254" border="0" 
		//id="empPic" width="200" height="200">
		By empPicProperty=By.id(property.getProperty("ActulaemployeepictureIDProperty"));
		WebElement empPicElement=driver.findElement(empPicProperty);
		Boolean flagOfEmpPic=empPicElement.isDisplayed();
		if(flagOfEmpPic)
		{
			//System.out.println("OrangeHRM Application Add Employee Page Picture Is Dispalyed");
			Log.info("OrangeHRM Application Add Employee Page Picture Is Dispalyed");
		}
		else
		{
			//System.out.println("OrangeHRM Application Add Employee Page Picture Is Not Dispalyed");
			Log.info("OrangeHRM Application Add Employee Page Picture Is Not Dispalyed");

		}
		
		By PIMProperty=By.linkText(property.getProperty("PIMlinktextProperty"));
		WebElement PIMElement=driver.findElement(PIMProperty);
	
		Actions pimMouseHovering=new Actions(driver);
		pimMouseHovering.moveToElement(PIMElement).build().perform();
		
		By addEmployeeProperty=By.linkText(property.getProperty("AddEmployeeLinkTextProperty"));
		WebElement addEmployeeElement=driver.findElement(addEmployeeProperty);
		addEmployeeElement.click();
		
		FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/OrangeHRMAddEmployeeSheet.xlsx");
		workBook.write(outputdata);
		}
		By welcomeAdminProperty=By.linkText(property.getProperty("welcomeAdminLinkText"));
		WebElement welcomeAdminElement=driver.findElement(welcomeAdminProperty);
		welcomeAdminElement.click();
		
		//<a href="/orangehrm-4.2.0.1/symfony/web/index.php/auth/logout">Logout</a>
		By logoutbuttonProperty=By.linkText(property.getProperty("logoutLinkTextProperty"));
		WebElement logoutbuttonElement=driver.findElement(logoutbuttonProperty);
		logoutbuttonElement.click();
		
		//<div id="logInPanelHeading">LOGIN Panel</div>
		By loginpanelProperty=By.id(property.getProperty("loginpanelnameIdProperty"));
		WebElement loginpanelElement=driver.findElement(loginpanelProperty);
		String ActualHomePageText=loginpanelElement.getText();
		sheetOfWorkbook.getRow(1).createCell(31).setCellValue(ActualHomePageText);
		Log.info("Filling Excel Sheet with Actual Results Of Add Employee Page is Successfull");
	}
	@Test(priority=9,description="OrangeHRM Application Add Employee Page Text Validation")
	public void addEmployeeText_Validation() throws IOException
	{		
		for(int rowindex=1;rowindex<=rowsCount;rowindex++)
		{
		String Expected_AddEmployeePageText=sheetOfWorkbook.getRow(rowindex).getCell(12).getStringCellValue();
		String Actual_AddEmployeePageText=sheetOfWorkbook.getRow(rowindex).getCell(13).getStringCellValue();
		if(Actual_AddEmployeePageText.equals(Expected_AddEmployeePageText))
		{
			sheetOfWorkbook.getRow(rowindex).createCell(14).setCellValue("PASS");
		}
		else
		{
			sheetOfWorkbook.getRow(rowindex).createCell(14).setCellValue("FAIL");
		}
		}
		//System.out.println("OrangeHRM Application Add Employee Page Text Validation");
		Log.info("OrangeHRM Application Add Employee Page Text Validation is Successfull");
	}
	@Test(priority=10,description="OrangeHRM Application Personal Details Page Text Validation")
	public void PersonalDetailsPageText_Validation() throws IOException
	{
		for(int rowindex=1;rowindex<=rowsCount;rowindex++)
		{
		String Expected_PersonalDetailsPageText=sheetOfWorkbook.getRow(rowindex).getCell(19).getStringCellValue();
		String Actual_PersonalDetailsPageText=sheetOfWorkbook.getRow(rowindex).getCell(20).getStringCellValue();
		if(Actual_PersonalDetailsPageText.equals(Expected_PersonalDetailsPageText))
		{
			sheetOfWorkbook.getRow(rowindex).createCell(21).setCellValue("PASS");
		}
		else
		{
			sheetOfWorkbook.getRow(rowindex).createCell(21).setCellValue("FAIL");
		}
		}
		//System.out.println("OrangeHRM Application Personal Details Page Text Validation");
		Log.info("OrangeHRM Application Personal Details Page Text Validation is Successfull");
	}
	
	@Test(priority=11,description="OrangeHRM Application First Name Validation")
	public void firstName_Validation() throws IOException
	{
		for(int rowindex=1;rowindex<=rowsCount;rowindex++)
		{
			String Expected_FirstName=sheetOfWorkbook.getRow(rowindex).getCell(15).getStringCellValue();
			String Actual_FirstName=sheetOfWorkbook.getRow(rowindex).getCell(22).getStringCellValue();
			if(Actual_FirstName.equals(Expected_FirstName))
			{
				sheetOfWorkbook.getRow(rowindex).createCell(23).setCellValue("PASS");
			}
			else
			{
				sheetOfWorkbook.getRow(rowindex).createCell(23).setCellValue("FAIL");
			}
		}
		//System.out.println("OrangeHRM Application First Name Validation");
		Log.info("OrangeHRM Application First Name Validation is Successfull");

	}
	
	@Test(priority=12,description="OrangeHRM Application Middle Name Validation")
	public void middleName_Validation() throws IOException
	{
		for(int rowindex=1;rowindex<=rowsCount;rowindex++)
		{
			String Expected_MiddleName=sheetOfWorkbook.getRow(rowindex).getCell(16).getStringCellValue();
			String Actual_MiddleName=sheetOfWorkbook.getRow(rowindex).getCell(24).getStringCellValue();
			if(Actual_MiddleName.equals(Expected_MiddleName))
			{
				sheetOfWorkbook.getRow(rowindex).createCell(25).setCellValue("PASS");
			}
			else
			{
				sheetOfWorkbook.getRow(rowindex).createCell(25).setCellValue("FAIL");
			}
		}
		//System.out.println("OrangeHRM Application Middle Name Validation");
		Log.info("OrangeHRM Application Middle Name Validation");

	}
	
	@Test(priority=13,description="OrangeHRM Application Last Name Validation")
	public void lastName_Validation() throws IOException
	{
		for(int rowindex=1;rowindex<=rowsCount;rowindex++)
		{
			String Expected_LastName=sheetOfWorkbook.getRow(rowindex).getCell(17).getStringCellValue();
			String Actual_LastName=sheetOfWorkbook.getRow(rowindex).getCell(26).getStringCellValue();
			if(Actual_LastName.equals(Expected_LastName))
			{
				sheetOfWorkbook.getRow(rowindex).createCell(27).setCellValue("PASS");
			}
			else
			{
				sheetOfWorkbook.getRow(rowindex).createCell(27).setCellValue("FAIL");
			}
		}
		//System.out.println("OrangeHRM Application Last Name Validation");
		Log.info("OrangeHRM Application Last Name Validation");
	}
	
	@Test(priority=14,description="OrangeHRM Application Employee Id Validation")
	public void employeeID_Validation() throws IOException
	{
		for(int rowindex=1;rowindex<=rowsCount;rowindex++)
		{
			String Expected_EmployeeID=sheetOfWorkbook.getRow(rowindex).getCell(18).getStringCellValue();
			String Actual_EmployeeID=sheetOfWorkbook.getRow(rowindex).getCell(28).getStringCellValue();
			if(Actual_EmployeeID.equals(Expected_EmployeeID))
			{
				sheetOfWorkbook.getRow(rowindex).createCell(29).setCellValue("PASS");
			}
			else
			{
				sheetOfWorkbook.getRow(rowindex).createCell(29).setCellValue("FAIL");
			}
		}
		//System.out.println("OrangeHRM Application Last Name Validation");
		Log.info("OrangeHRM Application Last Name Validation");
	}
	
	@Test(priority=15,description="OrangeHRM Application Logout Login Panel Text Validation")
	public void loginPanelText_Validation() throws IOException
	{
		String Expected_LoginPageText=sheetOfWorkbook.getRow(1).getCell(30).getStringCellValue();
		String Actual_LoginPageText=sheetOfWorkbook.getRow(1).getCell(31).getStringCellValue();
			if(Actual_LoginPageText.equals(Expected_LoginPageText))
			{
				sheetOfWorkbook.getRow(1).createCell(32).setCellValue("PASS");
			}
			else
			{
				sheetOfWorkbook.getRow(1).createCell(32).setCellValue("FAIL");
			}
		FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/OrangeHRMApplicationAddEmployeePageSheetResult.xlsx");
		workBook.write(outputdata);
		//System.out.println("OrangeHRM Application Last Name Validation");
		Log.info("OrangeHRM Application Last Name Validation");
	}
}

