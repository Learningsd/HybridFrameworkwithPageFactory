package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;
import com.tutorialsninjs.qa.base.Base;

public class Search  extends Base
{
    public WebDriver driver;
     HomePage Hp;
 	 SearchPage Sp;
     
     public Search()
     {
    	 super();
     }
     
	@BeforeMethod
	public void setup()
	{
		driver=IntializeBrowserAndOPenApplicationUrl(prop.getProperty("browserName"));
		Hp=new HomePage(driver);
	}
	
	@AfterMethod
	public void teradown()
	{
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct()
	{
		
		
		Sp=Hp.EnterproductAndSearch(dataprop.getProperty("validProduct"));
		
		
		Assert.assertTrue(Sp.displayStatusOfHPValidProduct(),"the product is not displayed");
			
		
	}
	
	@Test(priority = 2)
	public void verifysearchWithInValidProduct()
	{
		Sp=Hp.EnterproductAndSearch(dataprop.getProperty("invalidProduct"));
		Assert.assertEquals(Sp.retrieveNoProductMessageText(), dataprop.getProperty("NoProductTextInSearchResults"));
		
		
	}
	
	@Test(priority = 3)
	public void verifysearchWithNoProduct()
	{
		Sp=Hp.clickOnSearchButton();
      
		Assert.assertEquals(Sp.retrieveNoProductMessageText(), dataprop.getProperty("NoProductTextInSearchResults"));
		
	}


}
