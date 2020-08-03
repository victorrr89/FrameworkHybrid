package com.inetBanking.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporting extends TestListenerAdapter {

    // Listener class used to generate Extend reports

    public  ExtentHtmlReporter htmlReporter;
    public  ExtentReports extent;
    public  ExtentTest logger;


    public void onStart(ITestContext testContext){

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.mm.ss").format(new Date());
        String repName = "Test-Report"+timeStamp+".html";
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+repName);//specify location

        htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name","localHost");
        extent.setSystemInfo("Environment","QA");
        extent.setSystemInfo("user","pavan");

        htmlReporter.config().setDocumentTitle("InetBanking Test Project"); // Title of Report
        htmlReporter.config().setReportName("Functional Test Project");  // Name of Report
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // Location of the chart
        htmlReporter.config().setTheme(Theme.DARK);
    }


    public void onTestSuccess(ITestResult tr){

        logger = extent.createTest(tr.getName()); // create new entry in the report
        logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // Send the passed information
    }

    public void onTestFailure(ITestResult tr){
        logger = extent.createTest(tr.getName()); // create new entry in the report
        logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
        String screenShotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
        File file = new File(screenShotPath);

        if (file.exists()){
            try {
                logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenShotPath));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void onTestSkipped(ITestResult tr){

        logger = extent.createTest(tr.getName()); // create new entry in the report
        logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
    }

    public void onFinish(ITestResult tr){
        extent.flush();
    }







}
