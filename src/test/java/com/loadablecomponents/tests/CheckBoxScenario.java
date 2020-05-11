package com.loadablecomponents.tests;

import com.loadablecomponents.basepages.pages.Checkboxes;
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
 * Time: 06:33 PM
 */
public class CheckBoxScenario extends BaseTest {
    private WelcomePage welcomePage;
    private Checkboxes checkboxesPage;

    @Parameters("url")
    @Test
    public void launchTestUrl(@Optional("https://the-internet.herokuapp.com") String aut) {
        this.welcomePage = new WelcomePage(driver, logger, aut);
        logger.info("launching url:" + aut);
        welcomePage.get( );
        logger.info("clicked on Authentication link locator");
        checkboxesPage = welcomePage.getCheckboxesPage( );
    }

    @Test(dependsOnMethods = "launchTestUrl")
    public void testVisibilityOfCheckboxes() {
       assertTrue( checkboxesPage.visibilityOfCheckboxes(),"checkboxes are not displayed!!");
    }

    @Test(dependsOnMethods = "testVisibilityOfCheckboxes")
    public void testChecktheboxes() {
        assertTrue(checkboxesPage.checktheboxes(),"all check boxes test failed");
    }

    @Test(dependsOnMethods = "testChecktheboxes")
    public void testunCheckAllboxes() {
        assertTrue(checkboxesPage.unCheckAllboxes(),"failed testunCheckAllboxes");
    }



}
