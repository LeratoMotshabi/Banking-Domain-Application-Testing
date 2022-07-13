package com.BankingProject.TestCases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BankingProject.ActionDriver.ActionDriver;
import com.BankingProject.Base.BaseClass;
import com.BankingProject.POM.AddCustomerForm;
import com.BankingProject.POM.BankManagerLoginPage;
import com.BankingProject.POM.CustomersPage;
import com.BankingProject.POM.HomePage;
import com.BankingProject.Utilities.ExcelDataProvider;

public class AddCustomerTest extends BaseClass {

	HomePage homePage = new HomePage(driver);
	BankManagerLoginPage bankManagerLogin = new BankManagerLoginPage(driver);
	AddCustomerForm addCustomerForm = new AddCustomerForm(driver);
	CustomersPage customers = new CustomersPage(driver);
	
	@Test(priority=0,dataProvider = "BankData", dataProviderClass = ExcelDataProvider.class)
	public void verifyEmptyAddCustomerField(String firstName, String lastName, String postCode) {

		SoftAssert soft = new SoftAssert();
		
		homePage.goToHomePage();
		homePage.goToBankManagerLogin();
		bankManagerLogin.goToAddCustomer();
		addCustomerForm.addCustomer(firstName, lastName, postCode);	
		addCustomerForm.clickAddCustomerButton();

		soft.assertFalse(ActionDriver.isAlertPresent() == true, "unexpected Alert dialog box was found");
		soft.assertAll();
	}

	@Test(priority=1,dataProvider = "BankData", dataProviderClass = ExcelDataProvider.class)
	public void verifyAddCustomerAlertBox(String firstName, String lastName, String postCode) {

		SoftAssert soft = new SoftAssert();

		homePage.goToHomePage();
		homePage.goToBankManagerLogin();
		bankManagerLogin.goToAddCustomer();
		addCustomerForm.addCustomer(firstName, lastName, postCode);	
		addCustomerForm.clickAddCustomerButton();

		soft.assertTrue(ActionDriver.isAlertPresent() == true, "Alert dialog box was not found");
		soft.assertAll();
	}

	@Test(priority=2, dataProvider = "BankData", dataProviderClass = ExcelDataProvider.class)
	public void verifyCustomerIsAddedSuccefully(String firstName, String lastName, String postCode) {

		SoftAssert soft = new SoftAssert();

		homePage.goToHomePage();
		homePage.goToBankManagerLogin();
		bankManagerLogin.goToAddCustomer();
		addCustomerForm.addCustomer(firstName, lastName, postCode);	
		addCustomerForm.clickAddCustomerButton();
		
		ActionDriver.isAlertPresent();
		
		bankManagerLogin.goToCustomers();

		int FistName = ActionDriver.WebElementList("customersRowCount_xpath").size() + 1;

		boolean results = false;
		
		for (int i = 1; i < FistName; i++) {

			String cell = driver
					.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/div/div/table/tbody/tr[" + i + "]"))
					.getText();

			if (cell.contains(firstName) && cell.contains(lastName)) {
				
				results = true;
				break;

			} else {

				results = false;
			}

		}

		if (results == true) {

			soft.assertTrue(true, "not true");

		} else {
			soft.assertTrue(false, "Caanot find " + firstName + " " + lastName);
		}

		soft.assertAll();
	}
	
	
}
