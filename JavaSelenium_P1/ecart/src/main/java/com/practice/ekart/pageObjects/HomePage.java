package com.practice.ekart.pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	

	@FindBy(xpath="//input[@type='search']")
	WebElement searchTxt;
	
	@FindBy(xpath="//h4[@class='product-name']")
	WebElement SearchedProductName;
	
	
	
	public void SearchProduct(String ProdName) throws InterruptedException
	{
		searchTxt.sendKeys(ProdName);	
		Thread.sleep(3000);
		String searchedProdResult = SearchedProductName.getText();
		String veg[] =searchedProdResult.split("-");
		String searchedProduct=veg[0].trim();
		
		if(searchedProduct.contentEquals(ProdName))
		{
			System.out.println("Searched product retrived");
		}
		else
		{
			System.out.println(searchedProduct + " is not searched product");
			Assert.fail();
			
		}
	}
	
	
	
}
