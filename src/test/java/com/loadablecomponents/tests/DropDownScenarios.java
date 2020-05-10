package com.loadablecomponents.tests;

import com.loadablecomponents.basepages.pages.DropDownPage;
import com.loadablecomponents.basepages.pages.WelcomePage;
import com.loadablecomponents.driverhookup.BaseTest;
import org.testng.annotations.*;

import java.util.List;
import java.util.Objects;

import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-05-2020
 * Time: 11:13 PM
 */
public class DropDownScenarios extends BaseTest {

    private DropDownPage dropDownPage;
    private WelcomePage welcomePage;

    @Parameters("url")
    @BeforeMethod
    public void launchTestUrl(@Optional("https://the-internet.herokuapp.com") String aut) {
        this.welcomePage = new WelcomePage(driver, logger, aut);
        logger.info("launching url:" + aut);
        welcomePage.get( );
        logger.info("clicked on Authentication link locator");
        dropDownPage = welcomePage.getDropDownPage( );
    }

    @Test
    public void testDropDownOptions() {
        List<String> getdropdownoptions = dropDownPage.getdropdownoptions( );
        System.out.println("getdropdownoptions:" + getdropdownoptions);
        assertTrue(Objects.nonNull(getdropdownoptions), "Drop Down is Empty");
    }

    @Test
    public void testSelectOptionByIndex() {
        dropDownPage.selectOptionByIndex(1);
        System.out.println(dropDownPage.readSelectedOption( ));
        assertTrue(dropDownPage.readSelectedOption( ).equalsIgnoreCase(dropDownPage.getdropdownoptions( ).get(1)), "Failed to Select Option 1");
    }

    @Test
    public void testSelectOptionByVisibleText1() {
        dropDownPage.selectOptionByVisibletext("Option 1");
        System.out.println(dropDownPage.readSelectedOption( ));
        assertTrue(dropDownPage.readSelectedOption( ).equalsIgnoreCase(dropDownPage.getdropdownoptions( ).get(1)), "Failed to Select Option 1");
    }


    @Test
    public void testSelectOptionByVisibleText() {
        dropDownPage.selectOptionByVisibletext("Option 2");
        System.out.println(dropDownPage.readSelectedOption( ));
        assertTrue(dropDownPage.readSelectedOption( ).equalsIgnoreCase(dropDownPage.getdropdownoptions( ).get(2)), "Failed to Select Option 1");
    }

    @AfterMethod
    public void resetWelcomePage() {
        welcomePage = this.welcomePage.driveBackToWelcomePage( );
    }


}
