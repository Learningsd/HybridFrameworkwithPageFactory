package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.MyExtentReporter;
import com.tutorialsninja.qa.utils.utility;

public class MyListeners implements ITestListener
{
	ExtentReports extentreport;
	ExtentTest Extenttest;
	
	
	@Override
	public void onStart(ITestContext context) 
	{
		
		extentreport = MyExtentReporter.generateExtentReport();
		
	}

	@Override
	public void onTestStart(ITestResult result) 
	{
		
		Extenttest = extentreport.createTest(result.getName());
		Extenttest.log(Status.INFO, result.getName()+" excueting started");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		
		
		Extenttest.log(Status.INFO, result.getName()+"  sucessfully test method is passed ");
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		
		
		WebDriver driver=null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Throwable e) {
			
			e.printStackTrace();
		} 
		  
		String Destfile=utility.CaptureofScreenshot(driver, result.getName());
		
		Extenttest.addScreenCaptureFromPath(Destfile);
		
		Extenttest.log(Status.INFO, result.getThrowable());
		Extenttest.log(Status.FAIL, result.getName()+"   test method is failed");
		
	
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		
		Extenttest.log(Status.INFO, result.getThrowable());
		Extenttest.log(Status.SKIP, result.getName()+" test method is skipped ");
		
	}

	

	@Override
	public void onFinish(ITestContext context) 
	{
		extentreport.flush();
		
		String ExtentReportFilelocation=System.getProperty("user.dir")+"\\test-output\\ExtentReports\\ExtentRp.html";
		File file=new File(ExtentReportFilelocation);
		try {
			Desktop.getDesktop().browse(file.toURI());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	
}
