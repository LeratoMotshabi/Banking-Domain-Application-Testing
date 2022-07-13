package com.BankingProject.POM;

import org.openqa.selenium.WebDriver;

import com.BankingProject.ActionDriver.ActionDriver;

public class OpenAccountForm {
	
	WebDriver driver;
	public OpenAccountForm(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void createAccount(String customer, String currency)
	{
		ActionDriver.selectByName("customerName_id", customer);
		ActionDriver.selectByName("currency_id", currency);
	}
	
	public void clickProcessButton()
	{
		ActionDriver.click("processButton_xpath");
		
	}

}
