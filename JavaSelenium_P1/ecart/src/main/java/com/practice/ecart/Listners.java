package com.practice.ecart;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listners extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentReports extent = BaseTest.extentReport();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
//		String filePath = null;
//		try {
//			// filePath = getScreenshotFile(result.getMethod().getMethodName(), driver); >>
//			// for filetype output
//			filePath = getScreenshotBase64(result.getMethod().getMethodName(), driver);
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
		test.fail(result.getThrowable());
	    String base64Screenshot = null;
	    try {
	        base64Screenshot = getScreenshotBase64(result.getMethod().getMethodName(), driver);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    if (base64Screenshot != null) {
	        String imgTag = "<img src='data:image/png;base64," + base64Screenshot + "' />";
	        test.fail("Screenshot of failure: " + imgTag);  // Add the image to the Extent report
	    }
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		ITestListener.super.onFinish(context);
	}

}
