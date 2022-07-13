package com.BankingProject.POM;

import org.openqa.selenium.WebDriver;

import com.BankingProject.ActionDriver.ActionDriver;

public class DepositForm {
	
	WebDriver driver;
	public DepositForm(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void enterAmountToBeDeposited( String amount)
	{
		ActionDriver.type("amount_xpath", amount);
	}
	
	public void clickDepositButton()
	{
		ActionDriver.click("submitButton_xpath");
	}

}
