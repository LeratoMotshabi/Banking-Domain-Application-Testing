package com.BankingProject.POM;

import org.openqa.selenium.WebDriver;

import com.BankingProject.ActionDriver.ActionDriver;

public class AccountPage {
	
	WebDriver driver;
	public AccountPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void goToTransactions()
	{
		ActionDriver.click("transactionsTab_xpath");
	}

	public void goToDeposit()
	{
		ActionDriver.click("depositTab_xpath");
	}
	
	public void goToWithdrawal()
	{
		ActionDriver.click("withdrawlTab_xpath");
	}
	
	public void selecteAccountNumber()
	{
		ActionDriver.click("selectAccountNumber_id");
	}

}
