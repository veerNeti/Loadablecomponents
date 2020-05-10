package com.loadablecomponents.tests;

import com.loadablecomponents.basepages.pages.*;
import com.loadablecomponents.driverhookup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-05-2020
 * Time: 11:13 PM
 */
public class LoginScenarios extends BaseTest {
    WelcomePage welcomePage;
    LoginPage loginPage;

    @Parameters("url")
    @BeforeMethod(alwaysRun = true)
    public void launchTestUrl(@Optional("https://the-internet.herokuapp.com") String aut) {
        this.welcomePage = new WelcomePage(driver, logger,aut);
        logger.info("launching url:" + aut);
        welcomePage.get( );
        logger.info("clicked on Authentication link locator");
    }

    @Test (dependsOnMethods = "testLogin")
    public void testHeaderText() {
        Assert.assertTrue(this.loginPage.pageHeaderText().equalsIgnoreCase("Login Page"),"Login page header not displayed!!");
    }

    @Parameters({"username", "password", "secure"})
    @Test
    public void testLogin(@Optional("tomsmith") String username, @Optional("SuperSecretPassword!") String password, @Optional("You logged into a secure area!") String textValidation) {
        loginPage = welcomePage.loginaction( );
        SecureAreaPage secureAreaPage = loginPage.logIn(username, password);
        assertTrue(secureAreaPage.verifyLogin(textValidation));
    }

    @AfterMethod
    public void resetWelcomePage() {
        welcomePage = this.welcomePage.driveBackToWelcomePage( );
    }

}
