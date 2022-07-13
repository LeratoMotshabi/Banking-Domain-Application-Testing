package com.BankingProject.POM;

import org.openqa.selenium.WebDriver;

import com.BankingProject.ActionDriver.ActionDriver;

public class AddCustomerForm {
	
	WebDriver driver;
	public AddCustomerForm(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void enterFirstName(String value)
	{
		ActionDriver.type("fisrtName_xpath", value);
	}

	public void enterLastName(String value)
	{
		ActionDriver.type("lastName_xpath",value);
	}
	
	public void enterPostCode(String value)
	{
		ActionDriver.type("postCode_xpath",value);
	}
	
	public void clickAddCustomerButton()
	{
		ActionDriver.click("submitButton_xpath");
	}
	
	public void addCustomer(String fisrtName, String lastName, String postCode)
	{
		ActionDriver.type("fisrtName_xpath", fisrtName);
		ActionDriver.type("lastName_xpath",lastName);
		ActionDriver.type("postCode_xpath",postCode);
	}

}
