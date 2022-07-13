package com.BankingProject.POM;

import org.openqa.selenium.WebDriver;

import com.BankingProject.ActionDriver.ActionDriver;

public class BankManagerLoginPage {
	
	WebDriver driver;
	public BankManagerLoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void goToAddCustomer()
	{
		ActionDriver.click("addCustomerTab_xpath");
	}

	public void goToOpenAccount()
	{
		ActionDriver.click("openAccountTab_xpath");
	}
	
	public void goToCustomers()
	{
		ActionDriver.click("customersTab_xpath");
	}

}
