package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	WebDriver driver;
	
	@FindBy(id = "input-email")
	private WebElement EmailField;
	
	@FindBy(id="input-password")
	private WebElement PasswdField;
	
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement LoginButton;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement LoginErrorMessage;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public void EnterEmailid(String emaildata)
	{
		EmailField.sendKeys(emaildata);
	}
	
	public void EnterPassword(String Pword)
	{
		PasswdField.sendKeys(Pword);
	}
	
	public AccountPage ClickonLoginButton()
	{
		LoginButton.click();
		return new AccountPage(driver);
	}
	
	public String LoginErrorMessagedisplay()
	{
		String errordata=LoginErrorMessage.getText();
		return errordata;
	}

}
