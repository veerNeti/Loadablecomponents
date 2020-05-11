package com.loadablecomponents.tests;

import com.loadablecomponents.basepages.pages.ContextMenuPage;
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
 * Time: 08:47 PM
 */
public class ContextMenuScenario extends BaseTest {
    private WelcomePage welcomePage;
    private ContextMenuPage contextMenuPage;

    @Parameters("url")
    @BeforeMethod(alwaysRun = true)
    public void launchTestUrl(@Optional("https://the-internet.herokuapp.com") String aut) {
        this.welcomePage = new WelcomePage(driver, logger, aut);
        logger.info("launching url:" + aut);
        welcomePage.get( );
        logger.info("clicked on Authentication link locator");
        contextMenuPage = welcomePage.getContextMenyPage( );
    }

    @Test
    public void textContextMenu(){
        assertTrue( contextMenuPage.rightclick( ),"Context Menu failed!");
    }

}
