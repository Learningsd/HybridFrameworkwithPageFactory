package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MyExtentReporter {

	public static ExtentReports generateExtentReport()
	{
		ExtentReports ER=new ExtentReports();
		
		File ExtentFilepath=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\ExtentRp.html");
		
		ExtentSparkReporter spark = new ExtentSparkReporter(ExtentFilepath);
		
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("TutorialsNinjaResultsReport");
		spark.config().setDocumentTitle("TnAutomationTitle");
		spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		ER.attachReporter(spark);
		
		Properties prop=new Properties();
		File file=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		try {
		FileInputStream fi=new FileInputStream(file);
		prop.load(fi);
		}catch(Throwable e)
		{
			e.printStackTrace();
		}
		
		ER.setSystemInfo("ApplicationUrl", prop.getProperty("url"));
		ER.setSystemInfo("browserName", prop.getProperty("browserName"));
		ER.setSystemInfo("email", prop.getProperty("ValidEmail"));
		ER.setSystemInfo("passwordofapplication", prop.getProperty("ValidPassword"));
		ER.setSystemInfo("osVersion", System.getProperty("os.version"));
		ER.setSystemInfo("operatingSystem", System.getProperty("os.name"));
		ER.setSystemInfo("UserName", System.getProperty("user.name"));
		
		return ER;
		
	}
}
