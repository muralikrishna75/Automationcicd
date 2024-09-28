package MuraliKrishnaSG.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	 public static ExtentReports  getReportObject() {
		 
		 String path=System.getProperty("user.dir")+"\\reports\\index.html";
		 ExtentSparkReporter reporter =new ExtentSparkReporter(path);
		 reporter.config().setReportName("My Web Automation ");
		 reporter.config().setDocumentTitle("Test Results");
		 ExtentReports extent =new  ExtentReports();
		 extent.attachReporter(reporter);
		 extent.setSystemInfo("Tester", " Krishna");
		 extent.createTest(path);
		 return extent;
		 
		 
		 
	 }

}
