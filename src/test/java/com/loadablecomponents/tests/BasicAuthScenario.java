package com.loadablecomponents.tests;

import com.loadablecomponents.basepages.pages.BasicAuthPage;
import com.loadablecomponents.basepages.pages.WelcomePage;
import com.loadablecomponents.driverhookup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-10-2020
 * Time: 03:14 PM
 */
public class BasicAuthScenario extends BaseTest {
    private BasicAuthPage basicAuthPage;
    private WelcomePage welcomePage;

    @Parameters("url")
    @Test
    public void launchTestUrl(@Optional("https://the-internet.herokuapp.com") String aut) {
        this.welcomePage = new WelcomePage(driver, logger, aut);
        logger.info("launching url:" + aut);
        welcomePage.get( );
        logger.info("clicked on Authentication link locator");
        basicAuthPage = welcomePage.loginToBasicAuthPage("admin", "admin");
    }

    @Test(dependsOnMethods = "launchTestUrl")
    public void testWelcomeText() {
        Assert.assertTrue(basicAuthPage.readerwelcomeText( ).equalsIgnoreCase("Congratulations! You must have the proper credentials."), "Successfully logged-in");

    }
}
