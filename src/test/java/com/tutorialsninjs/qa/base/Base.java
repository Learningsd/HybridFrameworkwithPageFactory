package com.tutorialsninjs.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.utility;

public class Base 
{
	WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	
	public Base() 
	{
		prop=new Properties();
		
		File file=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		dataprop=new Properties();
		
		File file1=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		
		try {
		FileInputStream fi1=new FileInputStream(file1);
		dataprop.load(fi1);
		}catch(Throwable e)
		{
			e.printStackTrace();
		}
		
		
		try {
			FileInputStream fi= new FileInputStream(file);
			prop.load(fi);
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		
	}

	public WebDriver IntializeBrowserAndOPenApplicationUrl(String browserName)
	{
	
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
			
		}else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(utility.Implicity_wait));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(utility.PageLoad_Timeout));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
}
