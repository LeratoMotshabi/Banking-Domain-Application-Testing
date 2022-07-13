package com.BankingProject.TestCases;

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
import com.BankingProject.POM.WithdrawalForm;

public class WithdrawlTest extends BaseClass{
	
	OpenAccountForm inOpenAccount = new OpenAccountForm(driver);
	HomePage inHomePage = new HomePage(driver);
	BankManagerLoginPage inBankManagerLogin = new BankManagerLoginPage(driver);
	DepositForm inDeposit = new DepositForm(driver);	
	CustomerLoginPage inCustomerLogin = new CustomerLoginPage(driver);
	AccountPage inAccount = new AccountPage(driver);
	WithdrawalForm inWithdrawal = new WithdrawalForm(driver);
	
	
	@Test(priority=0)
	public  void verifyEmptyWithdrawlField() {

		SoftAssert soft = new SoftAssert();
		
		inHomePage.goToHomePage();
		inHomePage.goToCustomerLogin();
		inCustomerLogin.selectYourName("Ron Weasly");
		inCustomerLogin.clickLoginButton();		
		inAccount.goToWithdrawal();
		inWithdrawal.clickWithdrawtButton();		
		soft.assertFalse(ActionDriver.isElementPresent("withdrawnSuccessfully_xpath")==true, "unexpected message is showing");
		
		soft.assertAll();
	}
	
	@Test(priority=1)
	public  void verifyWhenWithdrawAmountMoreThanTheBalance() {

		SoftAssert soft = new SoftAssert();
		
		inHomePage.goToHomePage();
		inHomePage.goToCustomerLogin();
		inCustomerLogin.selectYourName("Ron Weasly");
		inCustomerLogin.clickLoginButton();
		inAccount.goToWithdrawal();
		inWithdrawal.enterAmountToBeWithdrawn("50");
		inWithdrawal.clickWithdrawtButton();		
		soft.assertFalse(ActionDriver.isElementPresent("withdrawnSuccessfully_xpath")==true, "unexpected message is showing");
		
		soft.assertAll();
	}
	
	@Test(priority=2)
	public  void verifyWithdrawnAmountSuccessfullyMessage() {

		SoftAssert soft = new SoftAssert();			
		 
		inHomePage.goToHomePage();
		inHomePage.goToCustomerLogin();
		inCustomerLogin.selectYourName("Ron Weasly");
		inCustomerLogin.clickLoginButton();
		
		inAccount.goToDeposit();
		inDeposit.enterAmountToBeDeposited("100");
		inDeposit.clickDepositButton();
		
		inAccount.goToWithdrawal();
		inWithdrawal.enterAmountToBeWithdrawn("50");
		inWithdrawal.clickWithdrawtButton();		
		
		soft.assertTrue(ActionDriver.isElementPresent("withdrawnSuccessfully_xpath")==true, "\"Transaction Failed. You can not withdraw amount more than the balance \"message is not showing");
		
		soft.assertAll();
	}
	
	@Test(priority=3)
	public  void verifyWithdrawnSuccessfully() throws InterruptedException {

		SoftAssert soft = new SoftAssert();
		
		String WithdrawAmount = "50";
		String transactionType = "Debit";
		 
		inHomePage.goToHomePage();
		inHomePage.goToCustomerLogin();
		inCustomerLogin.selectYourName("Ron Weasly");
		inCustomerLogin.clickLoginButton();
		
		inAccount.goToDeposit();
		inDeposit.enterAmountToBeDeposited("100");
		inDeposit.clickDepositButton();
		
		inAccount.goToWithdrawal();
		inWithdrawal.enterAmountToBeWithdrawn(WithdrawAmount);
		inWithdrawal.clickWithdrawtButton();		
		
		
		
		String DateOfTransaction = ActionDriver.Date();
		
		System.out.println("transactions Date " + DateOfTransaction);
		Thread.sleep(7000);
		ActionDriver.click("transactionsTab_xpath");
		
		List <WebElement>  transactions = driver.findElements(By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/table/tbody/tr"));
		
		
		boolean results = false;
		
		for(int i=1;i<transactions.size()+1;i++)
		{
			String cells = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/table/tbody/tr["+ i + "]")).getText();
			
			
			if(cells.toLowerCase().contains(DateOfTransaction.toLowerCase()) && cells.toLowerCase().contains(WithdrawAmount) && cells.toLowerCase().contains(transactionType.toLowerCase()))
			{
				System.out.println("found date " + cells );
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
