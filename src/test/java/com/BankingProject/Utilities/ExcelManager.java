package com.BankingProject.Utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {

	public String excelPath;
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static FileInputStream fis;
	
	public ExcelManager(String excelPath, String sheetName)
	{
		this.excelPath=excelPath;
		
		try {
			fis = new FileInputStream(excelPath);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(sheetName);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public  int getRowCount()

	{
		int rowCount =0;
		
		try {
			rowCount = sheet.getLastRowNum();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public  int getColCount()
	{
		int colCount = 0;
		try {
			
			row = sheet.getRow(0);
			colCount = row.getLastCellNum();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colCount;
	}
	
	public  String getCellData(int rowCount, int colCount) {
		
		String data;
		row = sheet.getRow(rowCount);
		cell = row.getCell(colCount);
		
		try {
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		} catch (Exception e) {
			data="";
			
		}
		return data;
	}

}
