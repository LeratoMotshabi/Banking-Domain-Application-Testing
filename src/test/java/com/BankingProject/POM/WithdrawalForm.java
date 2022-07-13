package com.BankingProject.POM;

import org.openqa.selenium.WebDriver;

import com.BankingProject.ActionDriver.ActionDriver;

public class WithdrawalForm {
	
	WebDriver driver;
	public WithdrawalForm(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void enterAmountToBeWithdrawn( String amount)
	{
		ActionDriver.type("amount_xpath", amount);
	}
	
	public void clickWithdrawtButton()
	{
		ActionDriver.click("submitButton_xpath");
	}

}
