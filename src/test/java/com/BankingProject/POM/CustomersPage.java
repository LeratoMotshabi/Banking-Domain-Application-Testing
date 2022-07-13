package com.BankingProject.POM;

import org.openqa.selenium.WebDriver;

import com.BankingProject.ActionDriver.ActionDriver;

public class CustomersPage {
	
	WebDriver driver;
	public CustomersPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void clickOnDeleteButton()
	{
		ActionDriver.click("deleteButton_xpath");
	}
	
	public void search(String searchText)
	{
		ActionDriver.type("search_xpath", searchText);
	}

}
