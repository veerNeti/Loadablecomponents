package com.loadablecomponents.basepages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-05-2020
 * Time: 10:33 PM
 */
public class SecureAreaPage extends LoadableComponent<SecureAreaPage> {
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private BasePage basePage;
    private Logger logger;

    private String username = "tomsmith";
    private String password = "SuperSecretPassword!";

    private LoadableComponent<LoginPage> parentpage;
    private By headerSecure=By.tagName("h2");
    private By secureSubheader=By.className(".subheader");
    private By flashMessage=By.id("flash");



    public SecureAreaPage(WebDriver driver, LoadableComponent<LoginPage> loginPageLoadableComponent, Logger logger) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(this.driver, 10L);
        this.parentpage = loginPageLoadableComponent;
        this.logger = logger;
        this.basePage = new BasePage(this.driver, this.logger);
    }


    @Override
    protected void load() {
        logger.info("Performing Login operation");
        this.parentpage.get( ).logIn(username, password);
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(this.driver.getCurrentUrl( ).contains("secure"), "We were unable to authenticate you!!");
    }

    public boolean verifyLogin(String validationText) {
        boolean contains = this.basePage.readText(flashMessage).trim( ).contains(validationText);
        logger.info("verifyLogin:"+contains);
        return contains;
    }
}
