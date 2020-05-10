package com.loadablecomponents.tests;

import com.loadablecomponents.basepages.pages.AbtestPage;
import com.loadablecomponents.basepages.pages.WelcomePage;
import com.loadablecomponents.driverhookup.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-10-2020
 * Time: 12:20 AM
 */
public class AbPageScenarios extends BaseTest {
    private WelcomePage welcomePage;
    private AbtestPage abtestPage;
    private String expectedParagraph = "Also known as split testing. This is a way in which businesses are able to simultaneously test and learn different versions of a page to see which text and/or functionality works best towards a desired outcome (e.g. a user action such as a click-through).";

    @Parameters("url")
    @BeforeMethod(alwaysRun = true)
    public void launchTestUrl(@Optional("https://the-internet.herokuapp.com") String aut) {
        this.welcomePage = new WelcomePage(driver, logger, aut);
        logger.info("launching url:" + aut);
        welcomePage.get( );
        logger.info("clicked on Authentication link locator");
        abtestPage = welcomePage.getAbTestPage( );
    }

    @Test
    public void testParagraph() {
        assertTrue(abtestPage.readParagraph( ).equalsIgnoreCase(expectedParagraph));
    }

}
