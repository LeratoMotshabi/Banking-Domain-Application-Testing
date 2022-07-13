package com.BankingProject.POM;

import org.openqa.selenium.WebDriver;

import com.BankingProject.ActionDriver.ActionDriver;

public class CustomerLoginPage {
	
	WebDriver driver;
	public CustomerLoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void selectYourName(String yourName)
	{
		ActionDriver.selectByName("yourName_xpath", yourName);
	}
	
	public void clickLoginButton()
	{
		ActionDriver.click("loginButton_xpath");
	}


}
