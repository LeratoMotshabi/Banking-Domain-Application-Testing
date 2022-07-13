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

public class CustomersTest extends BaseClass{
	
	HomePage homePage = new HomePage(driver);
	BankManagerLoginPage bankManagerLogin = new BankManagerLoginPage(driver);
	AddCustomerForm addCustomerForm = new AddCustomerForm(driver);
	CustomersPage customers = new CustomersPage(driver);
	
	
	@Test(priority=0)
	public void verifyCanSearchCustomer() {

		SoftAssert soft = new SoftAssert();
		String firstName = "Ron";
		
		homePage.goToHomePage();
		homePage.goToBankManagerLogin();
		bankManagerLogin.goToCustomers();
		customers.search("Ron");
		
		int FistName = ActionDriver.WebElementList("customersRowCount_xpath").size() + 1;

		boolean results = false;
		
		for (int i = 1; i < FistName; i++) {

			String cell = driver
					.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/div/div/table/tbody/tr[" + i + "]"))
					.getText();

			if (cell.contains(firstName)) {
				
				results = true;
				break;

			} else {

				results = false;
			}

		}

		if (results == true) {

			soft.assertTrue(true, "not true");

		} else {
			soft.assertTrue(false, "Cannot find " + firstName );
		}

		
		

		soft.assertFalse(ActionDriver.isAlertPresent() == true, "unexpected Alert dialog box was found");
		soft.assertAll();
	}
	
	@Test(priority=1)
	public void verifyCanDeleteCustomer() {

		SoftAssert soft = new SoftAssert();
		String firstName = "Ron";
		
		homePage.goToHomePage();
		homePage.goToBankManagerLogin();
		bankManagerLogin.goToCustomers();
				
		int BeforeDeletecustomerList = ActionDriver.WebElementList("customersRowCount_xpath").size() + 1;		
		
		for (int i = 1; i < BeforeDeletecustomerList; i++) {

			String cell = driver
					.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/div/div/table/tbody/tr[" + i + "]"))
					.getText();

			if (cell.contains(firstName)) {
				
				driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[" + i + "]/td[5]/button[1]")).click();
				
				break;

			} 

		}
		

		int AfterDeletecustomerList = ActionDriver.WebElementList("customersRowCount_xpath").size() + 1;

		boolean results = false;
		
		for (int i = 1; i < AfterDeletecustomerList; i++) {

			String cell = driver
					.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/div/div/table/tbody/tr[" + i + "]"))
					.getText();
			

			if (cell.contains(firstName)) {				
			
				results = false;
				break;

			} else
			{
				results = true;
				
			}

		}

		
		if (results == true) {

			System.out.println("Test passed");
			soft.assertTrue(true);
			

		} else {
			
			System.out.println("Test failed");
			soft.assertTrue(false, " Name is not deleted");
		}
		
				
		soft.assertAll();
		
		soft.assertAll();
	}


}
