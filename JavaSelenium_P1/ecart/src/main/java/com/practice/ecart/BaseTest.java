package com.practice.ecart;

import java.time.Duration;
import java.util.Base64;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;



public class BaseTest {

	public static WebDriver driver;
	Properties prop = new Properties();

	public void loadproperties() throws IOException {

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//properties//prop.properties");
		prop.load(fis);
	}

	public WebDriver invokeBrowser() throws IOException {

		loadproperties();
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
	options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}

	public void launchApplication() throws IOException {
		driver = invokeBrowser();
		String applicationURL = prop.getProperty("url");
		driver.get(applicationURL);
	}
	
	
	public static ExtentReports extentReport()
	{
		String path = System.getProperty("user.dir")+"//reports//extentReports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		ExtentReports extent =new ExtentReports();
		extent.attachReporter(reporter);
		return extent;
	}
	
	public  String getScreenshotFile(String testcaseName,WebDriver driver) throws IOException
	{
	TakesScreenshot  sc= (TakesScreenshot)driver;
	File source = sc.getScreenshotAs(OutputType.FILE);
	File dest = new File(System.getProperty("user.dir")+"//reports//screenshots//" + testcaseName+ ".png");
	FileUtils.copyFile(source,dest);
	return System.getProperty("user.dir")+"//reports//screenshots//" + testcaseName+ ".png";	
	}
	
	public  String getScreenshotBase64(String testcaseName,WebDriver driver) throws IOException
	{
//		File screenshot = new File(System.getProperty("user.dir")+"//reports//screenshots//" + testcaseName+ ".png");
//	    FileInputStream fileInputStream = new FileInputStream(screenshot);
//	    byte[] fileBytes = new byte[(int) screenshot.length()];
//	    fileInputStream.read(fileBytes);
//	    fileInputStream.close();
//	    
//	    return Base64.getEncoder().encodeToString(fileBytes); 
		TakesScreenshot sc = (TakesScreenshot) driver;
	    File source = sc.getScreenshotAs(OutputType.FILE);

	    FileInputStream fileInputStream = new FileInputStream(source);
	    byte[] fileBytes = new byte[(int) source.length()];
	    fileInputStream.read(fileBytes);
	    fileInputStream.close();
	    return Base64.getEncoder().encodeToString(fileBytes);
	}

}
