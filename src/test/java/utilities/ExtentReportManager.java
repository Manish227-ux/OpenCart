package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	ExtentSparkReporter sparkReporter;
	ExtentReports extent;
	ExtentTest test;

		
	public void onStart(ITestContext context) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.h.mm.ss a").format(new Date());
		String name = "Test_Report_" + timeStamp +".html";
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+name);
		sparkReporter.config().setDocumentTitle("Open Cart Automation Report");
		sparkReporter.config().setReportName("Open Cart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application name", "Open cart");
		extent.setSystemInfo("Tester name", "Manish Pandey");
		
		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> groups = context.getCurrentXmlTest().getIncludedGroups();
		
		if(!groups.isEmpty()) {
			
			extent.setSystemInfo("Groups", groups.toString());
		}
		
		
	}
	

	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName());
		
	}

	public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String path = new BaseClass().captureScreenShot(result.getName());
			
			test.addScreenCaptureFromPath(path);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public void onTestSkipped(ITestResult result) {
		
		test = extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());
		
	}

	public void onFinish(ITestContext context) {
		
		extent.flush();
	}

}
