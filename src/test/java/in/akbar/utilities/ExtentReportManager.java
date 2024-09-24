package in.akbar.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
//import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import in.akbar.testbase.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	public String repname;
	
	@Override
	public void onStart(ITestContext context) {
		/*
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date date = new Date();
		String dateformate = sdf.format(date);*/
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp
		
		repname = "Test-Report-"+timeStamp+".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repname); // specify location of the report
		sparkReporter.config().setDocumentTitle("Opencart - Automation"); // Title of report
		sparkReporter.config().setReportName("Opencart - Functional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Project", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		
		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // TO DISPLAY GROUPS IN REPORT
		test.log(Status.PASS, result.getName()+": got successfully executed");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // TO DISPLAY GROUPS IN REPORT
		test.log(Status.FAIL, result.getName()+": got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreenshot(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // TO DISPLAY GROUPS IN REPORT
		test.log(Status.SKIP, result.getName()+": got successfully skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	    
	    String pathOfExtentReport = System.getProperty("user.dir") + File.separator + "reports" + File.separator + repname;
	    File extentReport = new File(pathOfExtentReport);
	    
	    // Check if the report file exists
	    if (extentReport.exists()) {
	        try {
	        	try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	            Desktop.getDesktop().browse(extentReport.toURI());
	        } catch (IOException | UnsupportedOperationException e) {
	            e.printStackTrace();
	        }
	    } else {
	        System.out.println("Report file does not exist: " + pathOfExtentReport);
	    }
		
		/*
		try {
			URL url = new  URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repname);
			// Create the email message 
			  ImageHtmlEmail email = new ImageHtmlEmail();
			  email.setDataSourceResolver(new DataSourceUrlResolver(url));
			  email.setHostName("smtp.googlemail.com"); 
			  email.setSmtpPort(465);
			  email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com","password")); 
			  email.setSSLOnConnect(true);
			  email.setFrom("pavanoltraining@gmail.com"); //Sender
			  email.setSubject("Test Results");
			  email.setMsg("Please find Attached Report....");
			  email.addTo("pavankumar.busyqa@gmail.com"); //Receiver 
			  email.attach(url, "extent report", "please check report..."); 
			  email.send(); // send the email 
		} catch(Exception e) {
			e.printStackTrace();
		}
		*/
	}
}