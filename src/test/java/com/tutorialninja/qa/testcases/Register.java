package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.utility;
import com.tutorialsninjs.qa.base.Base;

public class Register  extends Base
{
	public WebDriver driver;
	RegisterPage Rp;
	AccountSuccessPage Acp;
	
	public Register()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver=IntializeBrowserAndOPenApplicationUrl(prop.getProperty("browserName"));
		
		HomePage Hp=new HomePage(driver);
		Hp.ClickonAccount();
		Rp=Hp.ClickOnRegisterOption();
		
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	
	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandetoryFeilds()
	{
		
		Rp.enterFirstName(dataprop.getProperty("firstName"));
		Rp.enterLastName(dataprop.getProperty("lastName"));
		Rp.enterEmailAddress(utility.GenerateEmailTimeStamp());
		Rp.enterTelephoneNumber(dataprop.getProperty("telephoneNumber"));
		Rp.enterPassword(dataprop.getProperty("InputPassword"));
		Rp.enterConfirmPassword(dataprop.getProperty("InputPassword"));
		Rp.selectPrivacyPolicy();
		Acp=Rp.clickOnContinueButton();
		
		
		
		
		String Actualmessage=Acp.retrieveAccountSuccessPageHeading();
		
		Assert.assertEquals(Actualmessage, dataprop.getProperty("accountSuccessfullyCreatedHeading"),"Account is not created");
		
		
	}
	
	@Test(priority = 2)
	public void verifyRegisterAnAccountwithAllFields()
	{
		
		
		//Rp=new RegisterPage(driver);
		Rp.enterFirstName(dataprop.getProperty("firstName"));
		Rp.enterLastName(dataprop.getProperty("lastName"));
		Rp.enterEmailAddress(utility.GenerateEmailTimeStamp());
		Rp.enterTelephoneNumber(dataprop.getProperty("telephoneNumber"));
		Rp.enterPassword(dataprop.getProperty("InputPassword"));
		Rp.enterConfirmPassword(dataprop.getProperty("InputPassword"));
		Rp.selectYesNewsletterOption();
		Rp.selectPrivacyPolicy();
		Acp=Rp.clickOnContinueButton();
		
       
		
		String Actualmessage=Acp.retrieveAccountSuccessPageHeading();
		
		Assert.assertEquals(Actualmessage, dataprop.getProperty("accountSuccessfullyCreatedHeading"),"Account is not created");
		
		
	}
	
	@Test(priority = 3)
	public void verifyRegisteringAnAccountwithExistingEMailAddress()
	{

		//Rp=new RegisterPage(driver);
		Rp.enterFirstName(dataprop.getProperty("firstName"));
		Rp.enterLastName(dataprop.getProperty("lastName"));
		Rp.enterEmailAddress(prop.getProperty("ValidEmail"));
		Rp.enterTelephoneNumber(dataprop.getProperty("telephoneNumber"));
		Rp.enterPassword(dataprop.getProperty("InputPassword"));
		Rp.enterConfirmPassword(dataprop.getProperty("InputPassword"));
		Rp.selectYesNewsletterOption();
		Rp.selectPrivacyPolicy();
		Rp.clickOnContinueButton();
		
		String ActualWarningmessage = Rp.retrieveDuplicateEmailAddressWarning();
		
		Assert.assertTrue(ActualWarningmessage.contains(dataprop.getProperty("duplicateEmailWarning")),"Warningmessage for duplicateemail is not displayed");
		
		
		
	}
	
	@Test(priority = 4)
	public void verifyRegisteringAnAccountWithoutProvidingDetails()
	{
		
		Rp.clickOnContinueButton();
		
		String ActualPrivacyWarningMess=Rp.retrievePrivacyPolicyWarning();
		
		Assert.assertTrue(ActualPrivacyWarningMess.contains(dataprop.getProperty("privacyPolicyWarning")),"Privacy policy warning message is not displayed");
		
		String ActualFirstNameWarnMessage=Rp.retrieveFirstNameWarning();
		
		Assert.assertEquals(ActualFirstNameWarnMessage, dataprop.getProperty("firstNameWarning"),"FirstName warning message is not displayed");
		
         String ActualLastNameWarnMessage=Rp.retrieveLastNameWarning();
		
		Assert.assertEquals(ActualLastNameWarnMessage, dataprop.getProperty("lastNameWarning"),"LastName warning message is not displayed");
		
        String ActualEamilAddressWarnMessage=Rp.retrieveEmailWarning();
		
		Assert.assertEquals(ActualEamilAddressWarnMessage, dataprop.getProperty("emailWarning"),"EamilAddress warning message is not displayed");
		
		 String ActualTelephonenoWarnMessage=Rp.retrieveTelephoneWarning();
			
	     Assert.assertEquals(ActualTelephonenoWarnMessage, dataprop.getProperty("telephoneWarning"),"telephoneno warning message is not displayed");
		
        String ActualPasswordWarnMessage=Rp.retrievePasswordWarning();
			
	    Assert.assertEquals(ActualPasswordWarnMessage, dataprop.getProperty("passwordWarning"),"Password warning message is not displayed");
	    
	    
	}
	
	
	
	
}
