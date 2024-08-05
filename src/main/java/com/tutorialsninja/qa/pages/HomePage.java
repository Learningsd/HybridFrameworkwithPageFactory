package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	WebDriver driver;
	
	//Objects
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement AccountOption;
	
	@FindBy(linkText = "Login")
	private WebElement LoginOption;
	
	@FindBy(linkText = "Register")
	private WebElement RegisterOption;
	
	@FindBy(name="search")
	private WebElement searchBoxField;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchButton;
	
	//constructor
	public HomePage(WebDriver driver)
	{
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public void ClickonAccount()
	{
		AccountOption.click();
		
	}
	
	public LoginPage ClickonLoginOption()
	{
		LoginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage ClickOnRegisterOption()
	{
		RegisterOption.click();
		return new RegisterPage(driver);
		
	}
	
   public void enterProductIntoSearchBoxField(String productText) {
		
		searchBoxField.sendKeys(productText);
		
	}
	
    public SearchPage clickOnSearchButton() {
		
		searchButton.click();
		return new SearchPage(driver);
		
		
	}
    
    public SearchPage EnterproductAndSearch(String productText)
    {
    	searchBoxField.sendKeys(productText);
    	searchButton.click();
		return new SearchPage(driver);
    	
    }
	

}
