package com.BankingProject.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	public static ExtentSparkReporter HtmlReporter;
	
	public static ExtentReports createInstance(String fileName)
	{
		HtmlReporter = new ExtentSparkReporter(fileName);
		HtmlReporter.config().setDocumentTitle(fileName);
		HtmlReporter.config().setReportName(fileName);
		HtmlReporter.config().setTheme(Theme.STANDARD);
		HtmlReporter.config().setEncoding("uft-8");
		
		ExtentReports extent = new ExtentReports();
		extent.setSystemInfo("Tester: ", "Lerato Motshabi");
		extent.setSystemInfo("Enviroment: ", "Windows 10");		
		extent.setSystemInfo("Project: ", "Banking Domain Application Testing");	
		extent.attachReporter(HtmlReporter);
		return extent;
		
		
	}

}
