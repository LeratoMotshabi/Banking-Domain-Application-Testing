package com.BankingProject.Utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

	public static String path = System.getProperty("user.dir") + "/src/test/resources/testData/TestData.xlsx";

	@DataProvider(name = "BankData")
	public static Object[][] testData(Method m) {
		String sheetName = m.getName();
		ExcelManager excel = new ExcelManager(path, sheetName);

		int rowCount = excel.getRowCount();
		int cellCount = excel.getColCount();

		Object data[][] = new Object[rowCount][cellCount];

		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < cellCount; j++) {
				data[i - 1][j] = excel.getCellData(i, j);
			}

		}
		return data;

	}

//	@DataProvider(name="verifyCustomerAddSuccessfullyAlert")
//	public  Object[][] getDataCell()
//	{
//	 Object data[][] =	testData(path, "verifyCustomerAddSuccessfullyAl");
//	return data;
//	}

	@DataProvider(name = "verifyEmptyOpenAccountField")
	public Object[][] verifyEmptyOpenAccountField() {
		return new Object[][] { 
			{ "---Customer Name---", "Dollar" }, 
			{ "Harry Potter", "---Currency---" },
				

		};
	}

	@DataProvider(name = "verifyCanOpenAccountSuccessfully")
	public Object[][] verifyCanOpenAccountSuccessfully() {
		return new Object[][] {

				{ "Harry Potter", "Dollar" }, 
				

		};
	}
	
	@DataProvider(name = "verifyEmptyDepositField")
	public Object[][] verifyEmptyDepositField() {
		return new Object[][] {

				{ ""},
				

		};
	}
	
	@DataProvider(name = "verifyDepositedAmuntSuccessfully")
	public Object[][] verifyDepositedAmuntSuccessfully() {
		return new Object[][] {

				{"100"},
				

		};
	}
	

}
