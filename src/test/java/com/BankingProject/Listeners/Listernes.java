package com.BankingProject.Listeners;

import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.BankingProject.Utilities.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Listernes implements ITestListener  {
	
	//public static DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	public static Date date = new Date();
	static String dateF = date.toString().replace(":", "_").toString().replace(" ", "_");
	public static String file = System.getProperty("user.dir")+"/src/test/resources/extentReports/" +"extent.html"; 
	public static ExtentReports extent = ExtentManager.createInstance(file);
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		
		ExtentTest testReport = extent.createTest(result.getClass() + " @TEST CASE " + result.getMethod().getMethodName());
		test.set(testReport);
		
	}

	public void onTestSuccess(ITestResult result) {
		
		Markup m = MarkupHelper.createLabel(result.getName() + ": PASSES ", ExtentColor.GREEN);
		test.get().pass(m);
		
	}

	public void onTestFailure(ITestResult result) {
	
		String detatils = result.getThrowable().getMessage();
		String getThrowable = "<details><summary><font color=red>" + "Exception occured: Click to see more"
				+ "</font></summary> <p>" + detatils.replaceAll(",", "<br>") + "</p></details>";
		
		Markup m = MarkupHelper.createLabel(result.getName(), ExtentColor.RED);
		test.get().fail(m);
		test.get().fail(getThrowable);
		
		
	}

	public void onTestSkipped(ITestResult result) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	
	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}
	

}
