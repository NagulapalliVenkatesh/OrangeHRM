package com.World_Clock_Webtable_Config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class World_Clock_WebTable_AllData extends Base_World_Clock{
	@Test(priority=1,description="Capturing World Clock Webtable Data of the World Clock Application")
	public void worldClock_WebtableData_Capturing() throws IOException
	{
		FileInputStream inputdata=new FileInputStream("./configurationProperties/WorldClock.properties");
		Properties properties=new Properties();
		properties.load(inputdata);
		FileInputStream inputdataexcel=new FileInputStream("./ExcelSheets/Worldclockdata_of_all_data.xlsx");
		XSSFWorkbook workBook=new XSSFWorkbook(inputdataexcel);
		XSSFSheet sheetofworkbook=workBook.getSheet(properties.getProperty("sheetname"));
		By worldclockBodyProperty=By.xpath(properties.getProperty("WorldClockWebtableDataBODYXpathProperty"));
		WebElement worldclockBodyElement=driver.findElement(worldclockBodyProperty);
		By worldclockRowProperty=By.tagName(properties.getProperty("WorldClockWebtableDataROWXpathProperty"));
		List<WebElement> rowslist=worldclockBodyElement.findElements(worldclockRowProperty);
		for(int rowindex=0;rowindex<rowslist.size();rowindex++)
		{
			Row rowOfSheet=sheetofworkbook.createRow(rowindex);
			WebElement webtablerow=rowslist.get(rowindex);
			By worldclockcellProperty=By.tagName(properties.getProperty("WorldClockWebtableDataCELLXpathProperty"));
			List<WebElement> celllist=webtablerow.findElements(worldclockcellProperty);
			for(int cellindex=0;cellindex<celllist.size();cellindex++)
			{
				Cell cellofrow=rowOfSheet.createCell(cellindex);
				WebElement webtablecell=celllist.get(cellindex);
				String celltext=webtablecell.getText();
				System.out.print(celltext+" ");
				cellofrow.setCellValue(celltext);
			}
			System.out.println();
		}
		FileOutputStream outputdata=new FileOutputStream("./ExcelSheets/Worldclockdata_of_all_data.xlsx");
		workBook.write(outputdata);

		

	}
}
