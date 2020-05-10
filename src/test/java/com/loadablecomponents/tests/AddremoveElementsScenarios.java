package com.loadablecomponents.tests;

import com.loadablecomponents.basepages.pages.AddremoveElementsPage;
import com.loadablecomponents.basepages.pages.WelcomePage;
import com.loadablecomponents.driverhookup.BaseTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-10-2020
 * Time: 12:58 PM
 */
public class AddremoveElementsScenarios extends BaseTest {
    private WelcomePage welcomePage;
    private AddremoveElementsPage addremoveElementsPage;

    @Parameters("url")
    @Test
    public void launchTestUrl(@Optional("https://the-internet.herokuapp.com") String aut) {
        this.welcomePage = new WelcomePage(driver, logger, aut);
        logger.info("launching url:" + aut);
        welcomePage.get( );
        logger.info("clicked on Authentication link locator");
        addremoveElementsPage = welcomePage.addremoveElementsPage( );
    }

    @Test(dependsOnMethods = "launchTestUrl")
    public void testAddButton() {
        addremoveElementsPage.clickAddButton( );
        assertTrue(addremoveElementsPage.isDeleteButtonVisible( ), "add button click functionality failed");
    }

    @Test(dependsOnMethods = "launchTestUrl")
    public void testDeletedButtonVisibility() {
        assertTrue(addremoveElementsPage.removeDeleteButtonDisplayed( ), "remove button is still on page");
    }

    @Test(dependsOnMethods = "testDeletedButtonVisibility")
    public void testDeleteButtonRemoved() {
        assertTrue(addremoveElementsPage.removeDeleteButton(addremoveElementsPage.numberOfDeleteButtonsDisplayed( )), "remove button is still on page");
    }

    @Test(dependsOnMethods = "testDeleteButtonRemoved")
    public void testAdd2Buttons() {
        int expectedSize = 2;
        for (int i = 0; i < expectedSize; i++) {
            logger.info("Adding :" + i);
            addremoveElementsPage.clickAddButton( );
        }
        assertTrue(addremoveElementsPage.numberOfDeleteButtonsDisplayed( ) == expectedSize, "added 2 add buttons successfully");
    }


    @Test(dependsOnMethods = "testAdd2Buttons")
    public void testAllDeleteButtonRemoved() {
        int numberOfDeleteButtons = addremoveElementsPage.numberOfDeleteButtonsDisplayed( );
        logger.info("Currently displayed " + numberOfDeleteButtons + "Remove buttons");
        assertTrue(addremoveElementsPage.removeDeleteButton(numberOfDeleteButtons), "remove button is still on page");
    }

}
