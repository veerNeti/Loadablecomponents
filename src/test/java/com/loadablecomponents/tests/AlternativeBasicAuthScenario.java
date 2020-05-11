package com.loadablecomponents.tests;

import com.loadablecomponents.basepages.pages.BasicAuthPage;
import com.loadablecomponents.basepages.pages.WelcomePage;
import com.loadablecomponents.driverhookup.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-10-2020
 * Time: 03:14 PM
 */
public class AlternativeBasicAuthScenario extends BaseTest {
    private BasicAuthPage basicAuthPage;
    private WelcomePage welcomePage;
private LoadableComponent<WelcomePage> parent;
    @Test
    public void workWithBasicAuthTest() {
        this.welcomePage = new WelcomePage(driver, logger, "http://admin:admin@the-internet.herokuapp.com/basic_auth");
        welcomePage.get( );
        logger.info("clicked on Authentication link locator");
        basicAuthPage=new BasicAuthPage(this.driver,this.logger,this.welcomePage);
        assertTrue (basicAuthPage.readerwelcomeText().contains("Congratulations!"));
    }

}
