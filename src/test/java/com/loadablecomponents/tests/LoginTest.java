package com.loadablecomponents.tests;

import com.loadablecomponents.basepages.LoginPage;
import com.loadablecomponents.basepages.SecureAreaPage;
import com.loadablecomponents.basepages.WelcomePage;
import com.loadablecomponents.driverhookup.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-05-2020
 * Time: 11:13 PM
 */
public class LoginTest extends BaseTest {
    WelcomePage welcomePage;
    @Parameters("url")
    @BeforeMethod(alwaysRun = true)
    public void launchTestUrl(@Optional("https://the-internet.herokuapp.com") String aut) {
        this.welcomePage = new WelcomePage(driver, logger);
        logger.info("launching url:" + aut);
        welcomePage.get( );
        logger.info("clicked on Authentication link locator");
    }


    @Parameters({"username", "password","secure"})
    @Test
    public void testLogin(@Optional("tomsmith") String username, @Optional("SuperSecretPassword!") String password,@Optional("You logged into a secure area!") String textValidation) {
        LoginPage loginPage = welcomePage.loginaction( );
        SecureAreaPage secureAreaPage = loginPage.logIn(username, password);
        assertTrue(secureAreaPage.verifyLogin(textValidation));
    }

}
