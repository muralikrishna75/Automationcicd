package MuraliKrishnaSG.testcompnents;


import java.io.IOException;
import org.testng.ITestContext;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import MuraliKrishnaSG.resources.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
    ExtentReports extent = ExtentReportNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest =new ThreadLocal<ExtentTest>();
    
    @Override
    public void onTestStart(ITestResult result) {
        // Code to execute on test start
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }
    
    @Override  
    public void onTestSuccess(ITestResult result) {
        // Code to execute on test success
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Code to execute on test failure
    	extentTest.get().fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String filePath = null;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Ensure the ExtentReports object is flushed when all tests are done
        extent.flush();
    }
}
