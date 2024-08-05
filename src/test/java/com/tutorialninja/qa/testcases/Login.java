package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.utility;
import com.tutorialsninjs.qa.base.Base;

public class Login extends Base
{
	public WebDriver driver;
	LoginPage Lp;
	 AccountPage Ap;
	
	
	public Login()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
	
		driver=IntializeBrowserAndOPenApplicationUrl(prop.getProperty("browserName"));
		
		HomePage Hp=new HomePage(driver);
		Hp.ClickonAccount();
		Lp=Hp.ClickonLoginOption();
		
		
		
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}
	
	
    @Test(priority = 1,dataProvider = "testLogindata")
	public void verifyLoginWithValidCredentials(String email,String password)
	{
    	 
         Lp.EnterEmailid(email);
         Lp.EnterPassword(password);
         Ap=Lp.ClickonLoginButton();
		
		
			
		Assert.assertTrue(Ap.EditAccountOptionsDisplay());
		
		
	}
    
    @DataProvider(name="testLogindata")
    public Object[][] supplyLogindata()
    {
    	Object[][] data= utility.getTestDataFromExcel("Login");
    	return data;
    	
    }
    
    @Test()
    public void verifyLoginWithInValidCredentials()
    {
    	 Lp.EnterEmailid(utility.GenerateEmailTimeStamp());
         Lp.EnterPassword(dataprop.getProperty("invalidPassword"));
         Ap=Lp.ClickonLoginButton();
    			
        
		
		String ActualWarningmess = Lp.LoginErrorMessagedisplay();
		
		String ExpectedWarningmess=dataprop.getProperty("emailPasswordNoMatchWarning");
		
		Assert.assertTrue(ActualWarningmess.contains(ExpectedWarningmess), "message is not diplayed");
		
		
    	
    }
    
    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailidAndValidPassword()
    {
    	Lp.EnterEmailid(utility.GenerateEmailTimeStamp());
        Lp.EnterPassword(prop.getProperty("ValidPassword"));
        Lp.ClickonLoginButton();
    	
        String ActualWarningmess = Lp.LoginErrorMessagedisplay();
		
		String ExpectedWarningmess=dataprop.getProperty("emailPasswordNoMatchWarning");
		
		Assert.assertTrue(ActualWarningmess.contains(ExpectedWarningmess), "message is not diplayed");
		
		
    	
    }
    
    @Test(priority = 4)
    public void verifyLoginWithValidEmailAndInvalidPassword()
    {
        
    	Lp.EnterEmailid(prop.getProperty("ValidEmail"));
        Lp.EnterPassword(dataprop.getProperty("invalidPassword"));
        Lp.ClickonLoginButton();
		
        String ActualWarningmess = Lp.LoginErrorMessagedisplay();
		
		String ExpectedWarningmess=dataprop.getProperty("emailPasswordNoMatchWarning");
		
		Assert.assertTrue(ActualWarningmess.contains(ExpectedWarningmess), "message is not diplayed");
		
	
    	
    	
    }
    
    
    @Test(priority = 5)
    public void verifyLoginWithoutProvidingCredentials()
    {
    	
        Lp.ClickonLoginButton();
		
		
		Assert.assertTrue(Lp.LoginErrorMessagedisplay().contains(dataprop.getProperty("emailPasswordNoMatchWarning")), "message is not diplayed");
		
		
    }
    
    
	
}
