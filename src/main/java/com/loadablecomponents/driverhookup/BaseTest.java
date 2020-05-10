package com.loadablecomponents.driverhookup;

import com.loadablecomponents.basepages.pages.WelcomePage;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-05-2020
 * Time: 11:20 PM
 */
public abstract class BaseTest {
    protected final long MAX_TIMEOUT = 20l;
    protected Logger logger;
    protected WebDriver driver = null;
    private WelcomePage welcomePage;
    @Parameters({"aut","browser", "environment"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("https://the-internet.herokuapp.com") String aut,@Optional("chrome") String browser, ITestContext ctx, @Optional("local") String environment) {
        if (Objects.isNull(this.driver)) {
            String testName = ctx.getCurrentXmlTest( ).getName( );
            logger = LogManager.getLogger(testName);
            // Create driver
            BrowserFactory browserFactory = new BrowserFactory(browser, logger);
            if (StringUtils.equalsIgnoreCase(environment, "grid")) {
                this.driver = browserFactory.createRemoteWebDriverOnGrid( );
            } else {
                this.driver = browserFactory.getWebDriver( );
            }
            this.driver.manage( ).timeouts( ).implicitlyWait(MAX_TIMEOUT, TimeUnit.SECONDS);
            logger.info("Env setup:" + environment + "\nWebDriver Setup:" + browser);
        } else {
            logger.throwing(Level.FATAL, new NullPointerException( ));
        }

        setCurrentThreadName( );
    }


    @AfterClass(alwaysRun = true)
    public void quitBrowser() {
        logger.info("Closing the WebDriver");
        driver.close( );
        logger.info("Quit the WebDriver");
        driver.quit( );
    }

    /**
     * Sets thread name which includes thread id
     */
    private void setCurrentThreadName() {
        Thread thread = Thread.currentThread( );
        String threadName = thread.getName( );
        String threadId = String.valueOf(thread.getId( ));
        if (!threadName.contains(threadId)) {
            thread.setName(threadName + " " + threadId);
        }
    }

}
