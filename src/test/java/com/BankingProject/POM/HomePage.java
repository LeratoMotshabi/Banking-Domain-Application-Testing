package com.BankingProject.POM;

import org.openqa.selenium.WebDriver;

import com.BankingProject.ActionDriver.ActionDriver;

public class HomePage {
	
	public WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void goToCustomerLogin()
	{
		ActionDriver.click("customerLogin_xpath");
	}

	public void goToBankManagerLogin()
	{
		ActionDriver.click("bankManagerLogin_xpath");
	}
	
	public void goToHomePage()
	{
		ActionDriver.click("home_xpath");
	}
}
