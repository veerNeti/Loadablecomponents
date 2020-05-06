package com.loadablecomponents.driverhookup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-05-2020
 * Time: 10:57 PM
 */
public class BrowserFactory {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>( );

    private String browsername;
    private Logger logger;

    public BrowserFactory(String browsername, Logger logger) {
        this.browsername = browsername.toLowerCase( );
        this.logger = logger;
    }

    public WebDriver getWebDriver() {
        logger.info("Webdriver created: " + browsername);
        switch (browsername) {
            case "chrome":
                WebDriverManager.chromedriver( ).setup( );
                driver.set(new ChromeDriver( ));
                break;

            case "firefox":
                WebDriverManager.firefoxdriver( ).setup( );
                driver.set(new FirefoxDriver( ));
                break;

            case "opera":
                WebDriverManager.operadriver( ).setup( );
                driver.set(new OperaDriver( ));
                break;

            default:
                logger.info("Do not know how to start: " + browsername + ", starting chrome.");
                WebDriverManager.chromedriver( ).setup( );
                driver.set(new ChromeDriver( ));
                break;
        }
        return driver.get( );
    }

    public WebDriver createRemoteWebDriverOnGrid() {
        String hubUrl = "http://localhost:4444/wd/hub";
        DesiredCapabilities capability = new DesiredCapabilities( );
        // Creating driver
        switch (browsername) {
            case "chrome":
                capability.setBrowserName("chrome");
                capability.setPlatform(Platform.WINDOWS);
                ChromeOptions chromeOptions = new ChromeOptions( );
                chromeOptions.setCapability("video", "true"); // NOTE: case sensitive string, not boolean.
                chromeOptions.merge(capability);
                break;
            case "internet explorer":
                capability.setBrowserName("internet explorer");
                capability.setPlatform(Platform.WINDOWS);
                InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions( );
                internetExplorerOptions.setCapability("video", "TRUE");
                internetExplorerOptions.merge(capability);
                break;
            // MICROSOFT EDGE specifics
            case "MicrosoftEdge":
                EdgeOptions edgeOptions = new EdgeOptions( );
                capability.setPlatform(Platform.WINDOWS);
                edgeOptions.setCapability("video", "True"); // NOTE: case sensitive string, not boolean.
                break;
            case "firefox":
                capability.setBrowserName("firefox");
                capability.setPlatform(Platform.WINDOWS);
                FirefoxOptions firefoxOptions = new FirefoxOptions( );
                firefoxOptions.setCapability("video", "TRUE");
                firefoxOptions.merge(capability);
                break;
            case "opera":
                capability.setBrowserName("opera");
                capability.setPlatform(Platform.WINDOWS);
                OperaOptions operaOptions = new OperaOptions( );
                operaOptions.merge(capability);
                break;
        }
        try {
            driver.set(new RemoteWebDriver(new URL(hubUrl), capability));
        } catch (MalformedURLException e) {
            e.printStackTrace( );
        }
        return driver.get( );
    }
}
