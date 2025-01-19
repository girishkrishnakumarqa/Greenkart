package com.practice.ecart;

import java.io.IOException;
import org.testng.annotations.Test;
import com.practice.ekart.pageObjects.HomePage;


public class TC01_Search_Product_in_GreenKart extends BaseTest {

	@Test
	public void SearchProduct() throws InterruptedException, IOException {
	
		
		
		
		
		
		
		
		launchApplication();
		HomePage hp = new HomePage(driver);
		String Product = "Cucumber";

		hp.SearchProduct(Product);

		driver.quit();

	}

}
