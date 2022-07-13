package com.BankingProject.TestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BankingProject.ActionDriver.ActionDriver;
import com.BankingProject.Base.BaseClass;
import com.BankingProject.POM.AddCustomerForm;
import com.BankingProject.POM.BankManagerLoginPage;
import com.BankingProject.POM.CustomerLoginPage;
import com.BankingProject.POM.HomePage;
import com.BankingProject.POM.OpenAccountForm;
import com.BankingProject.Utilities.ExcelDataProvider;

public class OpenAccountTest extends BaseClass {

	OpenAccountForm inOpenAccount = new OpenAccountForm(driver);
	HomePage inHomePage = new HomePage(driver);
	BankManagerLoginPage inBankManagerLogin = new BankManagerLoginPage(driver);
	AddCustomerForm inAddCustomerForm = new AddCustomerForm(driver);
	CustomerLoginPage inCustomerLogin = new CustomerLoginPage(driver);
	
	@Test(priority=0,dataProvider = "verifyEmptyOpenAccountField", dataProviderClass = ExcelDataProvider.class)
	public void verifyEmptyOpenAccountField(String customer, String currency) {

		SoftAssert soft = new SoftAssert();

		inHomePage.goToHomePage();
		inHomePage.goToBankManagerLogin();
		inBankManagerLogin.goToOpenAccount();
		inOpenAccount.createAccount(customer, currency);
		inOpenAccount.clickProcessButton();

		soft.assertFalse(ActionDriver.isAlertPresent() == true, "unexpected Alert dialog box was found");

		soft.assertAll();
	}

	@Test(priority=1,dataProvider = "verifyCanOpenAccountSuccessfully", dataProviderClass = ExcelDataProvider.class)
	public void verifyOpenAccountSccessfullyAlertBox(String customer, String currency) {

		SoftAssert soft = new SoftAssert();

		inHomePage.goToHomePage();
		inHomePage.goToBankManagerLogin();
		inBankManagerLogin.goToOpenAccount();
		inOpenAccount.createAccount(customer, currency);
		inOpenAccount.clickProcessButton();

		soft.assertTrue(ActionDriver.isAlertPresent() == true, "Alert dialog box was not found");

		soft.assertAll();
	}

	@Test(priority=2)
	public void verifyAccountIsOpenSuccessfully() throws InterruptedException {

		SoftAssert soft = new SoftAssert();
		String FirstName = "Lemo";
		String LastName = "Mots";
		String Pcode = "1234";

		inHomePage.goToHomePage();
		inHomePage.goToBankManagerLogin();
		inBankManagerLogin.goToAddCustomer();
		inAddCustomerForm.addCustomer(FirstName, LastName, Pcode);
		inAddCustomerForm.clickAddCustomerButton();

		ActionDriver.isAlertPresent();

		inBankManagerLogin.goToOpenAccount();
		inOpenAccount.createAccount(FirstName + " " + LastName, "Dollar");
		inOpenAccount.clickProcessButton();

		String getAlertText = driver.switchTo().alert().getText();

		String ActualaccountNumber = ActionDriver.split(getAlertText, ":", 1);

		ActionDriver.isAlertPresent();

		inHomePage.goToHomePage();
		inHomePage.goToCustomerLogin();
		inCustomerLogin.selectYourName(FirstName + " " + LastName);
		inCustomerLogin.clickLoginButton();
		String ExpectedaccountNumber = ActionDriver.getText("accountNumber_xpath");

		System.out.println(
				"actual account number " + ActualaccountNumber + " Expected account nunber " + ExpectedaccountNumber);
		soft.assertEquals(ActualaccountNumber, ExpectedaccountNumber, "Did not found account number " + ActualaccountNumber);

		soft.assertAll();
	}

}
