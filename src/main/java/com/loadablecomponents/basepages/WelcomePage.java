package com.loadablecomponents.basepages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.rmi.runtime.Log;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-05-2020
 * Time: 10:07 PM
 */


public class WelcomePage extends LoadableComponent<WelcomePage> {
    private String welcomepageUrl = "http://the-internet.herokuapp.com/";
    private By formAuthenticationLinkLocator = By.linkText("Form Authentication");

    //Driver and Webdriver
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private Logger logger;

    //driver passed in from test will instatiate the driver
    private BasePage basePage;

    public WelcomePage(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.logger=logger;
        this.webDriverWait = new WebDriverWait(driver, 10l);
        this.basePage = new BasePage(this.driver,this. logger);
    }

    @Override
    protected void load() {
        this.driver.get(welcomepageUrl);
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue("HomePage is not loaded!", driver.getCurrentUrl( ).contains(welcomepageUrl));
    }

    //we can use this.driver.findelement.click
    //instead we are using basepage as we defined click action to avoid code duplication

    public LoginPage loginaction() {
        basePage.click(formAuthenticationLinkLocator);
        return new LoginPage(this.driver, this,logger);
    }
}
