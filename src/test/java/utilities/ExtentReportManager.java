package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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

import testBase.baseClass;

public class ExtentReportManager implements ITestListener{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	
	public void onStart(ITestContext testContext) {
		/*SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt= new Date();
		String currentDateTimeStamp= df.format(dt); */
		
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName= "Test-Report"+ timeStamp+ ".html";
		sparkReporter= new ExtentSparkReporter(".\\reports\\"+ repName);
		sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
		sparkReporter.config().setReportName("OpenCart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent= new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("SubModule", "Cutomers");
		extent.setSystemInfo("UserName", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os= testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operation Syatem", os);
		
		String browser= testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups= testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+"got successfully executed");
	}
	
	public void onTestFailure(ITestResult result) {
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+"got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath= new baseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
		
		//to open report automatically
		String pathOfExtentReport= System.getProperty("user.dir")+"\\reports\\"+ repName;
		File extentReport= new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//send the report through email(don't try cuz it will block the account
		/*try {
			URL url= new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
			//create the email msg
			ImageHTMLEmail email= new ImageHTMLEmail();
			email.setDataSourceResolver(new DataSourceURLResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("vaibhav@gmail.com", "password"));
			email.setSSLOnConnect(true);
			email.setFrom("vaibhav@gmail.com"); //Sender
			email.setSubject("Test Report");
			email.setMsg("Please find the attached Report");
			email.addTo("vai@gmail.com"); //Receiver
			email.attach(url, "extent report", "Please check report");
			email.send(); //send the email
		}catch(Exception e) {
			e.printStackTrace();
		}*/
	}
}
