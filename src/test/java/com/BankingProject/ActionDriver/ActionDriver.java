/**
 * 
 */
package com.BankingProject.ActionDriver;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BankingProject.Base.BaseClass;
import com.BankingProject.Listeners.Listernes;

/**
 * @author Lerato.Motshabi
 *
 */
public class ActionDriver extends BaseClass {
	
	public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	public static WebElement element;

	

	public static String type(String locator, String value) {
		
		
		if(locator.endsWith("_xpath")) {
			
			 element =  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(lo.getProperty(locator)))));
			 element.click();
			 element.clear();
			 element.sendKeys(value);
		}else if(locator.endsWith("_id")) {
			
			 element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(lo.getProperty(locator)))));
			 element.click();
			 element.clear();
				element.sendKeys(value);
		}else if(locator.endsWith("_css")) {
			
			element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(lo.getProperty(locator)))));
			element.click();
			element.clear();
			element.sendKeys(value);
		}
		
		
		Listernes.test.get().info("Typed in: " + locator + " Entered value: " + value);
		
		return value;
	}

	public static void click(String locator) {
		
		
		if(locator.endsWith("_xpath")) {
			 
			 wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(lo.getProperty(locator))))).click();
			
		}else if(locator.endsWith("_id")) {
			
			 wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id(lo.getProperty(locator))))).click();
			
		}else if(locator.endsWith("_css")) {
			 wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(lo.getProperty(locator))))).click();
			
		}
		
		Listernes.test.get().info("Clicked on: " + locator);
	}
	
	public static String getText(String locator) {
		
		String value = null;
		if(locator.endsWith("_xpath")) {
			 
			value = driver.findElement(By.xpath(lo.getProperty(locator))).getText();
			
		}else if(locator.endsWith("_id")) {
			
			value = driver.findElement(By.id(lo.getProperty(locator))).getText();
			
		}else if(locator.endsWith("_css")) {
			value = driver.findElement(By.cssSelector(lo.getProperty(locator))).getText();
			
		}
		return value;
		
		
		
	}

	public static void selectByName(String locator, String value) {
		
		Select select;
		
		if(locator.endsWith("_xpath")) {
			
			select =new Select (driver.findElement(By.xpath(lo.getProperty(locator))));
			select.selectByVisibleText(value);
			
		}else if(locator.endsWith("_id")) {
			
			select =new Select (driver.findElement(By.id(lo.getProperty(locator))));
			select.selectByVisibleText(value);
			
		}else if(locator.endsWith("_css")) {
			
			select =new Select (driver.findElement(By.cssSelector(lo.getProperty(locator))));
			select.selectByVisibleText(value);
			
			Listernes.test.get().info("Clicked on: " + locator + " Selected value: " + value);
			
		}
		
		
	}

	public static void selectByIndex(String locator, int value) {
		
		Select select;
		if(locator.endsWith("_xpath")) {
			
			select =new Select ( driver.findElement(By.xpath(lo.getProperty(locator))));
			select.selectByIndex(value);
			
		}else if(locator.endsWith("_id")) {
			
			select =new Select ( driver.findElement(By.id(lo.getProperty(locator))));
			select.selectByIndex(value);
			
		}else if(locator.endsWith("_css")) {
			
			select =new Select ( driver.findElement(By.cssSelector(lo.getProperty(locator))));
			select.selectByIndex(value);
			
		}
		
		

	}
	
	public static boolean isAlertPresent()
	{
				
		boolean foundAlert = false;
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(0));
		try {
			
			w.until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
			driver.switchTo().alert().accept();
			
		} catch (TimeoutException e) {
			foundAlert = false;
		}
		
		return foundAlert;
		
	}
	
	public static boolean isElementPresent(String locator)
	{
		boolean element = false;
		try {
			if(locator.endsWith("_xpath")) {
				 
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(lo.getProperty(locator)))));
				
			}else if(locator.endsWith("_id")) {
				
				 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(lo.getProperty(locator)))));
				
			}else if(locator.endsWith("_css")) {
				 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(lo.getProperty(locator)))));
				
			}
			element = true;
		} catch (NoSuchElementException e) {
			element = false;
		}
		return element;
	}
	
	public static List<WebElement> WebElementList(String locator) {
		
		List<WebElement> list = null;
		if(locator.endsWith("_xpath")) {
			 
			list= wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(lo.getProperty(locator)))));
			
		}else if(locator.endsWith("_id")) {
			
			 list= wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.id(lo.getProperty(locator)))));
			
		}else if(locator.endsWith("_css")) {
			 list= wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(lo.getProperty(locator)))));
			
		}
		return list;
		
		
	}
	
	public static String Date()
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy h:mm:ss aa");
		Date date = new Date();
		String transactionsDate = simpleDateFormat.format(date);
		return transactionsDate;
	}
	
	public static String split(String value, String character,int part)
	{
		String[] parts = value.split(character);
		String part1 = parts[part];
		return part1;
		
	}

}
