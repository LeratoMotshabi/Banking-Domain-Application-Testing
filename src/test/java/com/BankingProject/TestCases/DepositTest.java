package com.BankingProject.TestCases;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BankingProject.ActionDriver.ActionDriver;
import com.BankingProject.Base.BaseClass;
import com.BankingProject.POM.AccountPage;
import com.BankingProject.POM.BankManagerLoginPage;
import com.BankingProject.POM.CustomerLoginPage;
import com.BankingProject.POM.DepositForm;
import com.BankingProject.POM.HomePage;
import com.BankingProject.POM.OpenAccountForm;
import com.BankingProject.Utilities.ExcelDataProvider;

public class DepositTest extends BaseClass {

	OpenAccountForm inOpenAccount = new OpenAccountForm(driver);
	HomePage inHomePage = new HomePage(driver);
	BankManagerLoginPage inBankManagerLogin = new BankManagerLoginPage(driver);
	
	CustomerLoginPage inCustomerLogin = new CustomerLoginPage(driver);
	AccountPage inAccount = new AccountPage(driver);
	DepositForm inDeposit = new DepositForm(driver);
	@Test(priority=0,dataProvider = "verifyEmptyDepositField", dataProviderClass = ExcelDataProvider.class)
	public  void verifyEmptyDepositField(String amount) {

		SoftAssert soft = new SoftAssert();		
		
		inHomePage.goToHomePage();
		inHomePage.goToCustomerLogin();
		inCustomerLogin.selectYourName("Ron Weasly");
		inCustomerLogin.clickLoginButton();
		inAccount.goToDeposit();
		inDeposit.enterAmountToBeDeposited(amount);
		inDeposit.clickDepositButton();
		
		soft.assertFalse(ActionDriver.isElementPresent("depositSuccessful_xpath")==true, "unexpected message is showing");
		
		soft.assertAll();
	}
	
	@Test(priority=1,dataProvider = "verifyDepositedAmuntSuccessfully", dataProviderClass = ExcelDataProvider.class)
	public  void verifyDepositedAmountSuccessfullyMessage(String amount) {

		SoftAssert soft = new SoftAssert();		
		 
		inHomePage.goToHomePage();
		inHomePage.goToCustomerLogin();
		inCustomerLogin.selectYourName("Ron Weasly");
		inCustomerLogin.clickLoginButton();
		inAccount.goToDeposit();
		inDeposit.enterAmountToBeDeposited("100");
		inDeposit.clickDepositButton();
		
		soft.assertTrue(ActionDriver.isElementPresent("depositSuccessful_xpath")==true, "message is not showing");
		
		soft.assertAll();
	}
	
	@Test(priority=2)
	public  void verifyDepositIsAddedSuccessfully() throws InterruptedException {

		SoftAssert soft = new SoftAssert();
		
		String Deposit = "100";
		String transactionType = "credit";
		 
		inHomePage.goToHomePage();
		inHomePage.goToCustomerLogin();
		inCustomerLogin.selectYourName("Ron Weasly");
		inCustomerLogin.clickLoginButton();
		inAccount.goToDeposit();
		inDeposit.enterAmountToBeDeposited(Deposit);
		inDeposit.clickDepositButton();
		
		
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy h:mm:ss aa");
		Date date = new Date();
		String transactionsDate = simpleDateFormat.format(date);		
		System.out.println("transactions Date " + transactionsDate.toLowerCase());
		Thread.sleep(5000);
		ActionDriver.click("transactionsTab_xpath");
		
		List <WebElement>  transactions = driver.findElements(By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/table/tbody/tr"));
		
		
		boolean results = false;
		
		for(int i=1;i<transactions.size()+1;i++)
		{
			String transac = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/table/tbody/tr["+ i + "]")).getText();
			
			
			if(transac.toLowerCase().contains(transactionsDate.toLowerCase()) && transac.contains(Deposit) && transac.toLowerCase().contains(transactionType.toLowerCase()))
			{
				
				System.out.println("found date " + transac + " and " +transac);
				results = true;
				break;
			}else 
			{
				results = false;
			}
			
		}
		
		if (results == true) {

			soft.assertTrue(true);

		} else {
			soft.assertTrue(false, " cannot find transaction");
		}
		
				
		soft.assertAll();
	}

}
