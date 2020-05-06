package com.loadablecomponents.listerners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-05-2020
 * Time: 11:04 PM
 */
public class ExtentManager {
    static String projectName = "loadablecomponents";
    private static ExtentReports extent;
    private static String reportFileName = "ExtentReports.html";
    private static String extentReportPath = System.getProperty("user.dir") + "\\target\\TestReport\\";

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance( );

        return extent;
    }

    //Create an extent report instance
    public static ExtentReports createInstance() {
        createReportPath(extentReportPath);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(extentReportPath + reportFileName);
        htmlReporter.config( ).setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config( ).setChartVisibilityOnOpen(true);
        htmlReporter.config( ).setTheme(Theme.STANDARD);
        htmlReporter.config( ).setDocumentTitle(projectName);
        htmlReporter.config( ).setEncoding("utf-8");
        htmlReporter.config( ).setReportName(projectName);
        extent = new ExtentReports( );
        extent.attachReporter(htmlReporter);

        return extent;
    }


    //Create the report path if it does not exist
    private static void createReportPath(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists( )) {
            if (testDirectory.mkdir( )) {
                System.out.println("Directory: " + path + " is created!");
            } else {
                System.out.println("Failed to create directory: " + path);
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
    }
}
