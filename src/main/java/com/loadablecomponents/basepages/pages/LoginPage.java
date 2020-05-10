package com.loadablecomponents.basepages.pages;

import com.loadablecomponents.basepages.BasePage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-05-2020
 * Time: 10:21 PM
 */
public class LoginPage extends LoadableComponent<LoginPage> {
    LoadableComponent<WelcomePage> parentPage;
    //*********Page Variables*********
    private WebDriver driver;
    private BasePage basePage;
    private Logger logger;
    //*********Web Elements*********
    private By headerEle = By.tagName("h2");
    private By formFieldUserName = By.cssSelector("input#username");
    private By formFieldPassword = By.cssSelector("input#password");
    private By logInButton = By.cssSelector("button.radius");
    private By failuremessage = By.cssSelector("#flash.error");
    //*********Constructor*********
    public LoginPage(WebDriver driver, LoadableComponent<WelcomePage> welcomePageLoadableComponent,Logger logger) {
        this.driver = driver;
        this.logger=logger;
//        this.webDriverWait = new WebDriverWait(driver, 10L);
        this.basePage = new BasePage(this.driver, this.logger);
        this.parentPage = welcomePageLoadableComponent;
    }

    //*********Override LoadableComponent Methods*********
    //We need to go to the page at load method
    @Override
    protected void load() {
        this.logger.info("Loading LoginPage ");
        parentPage.get( ).loginaction( );
    }

    //We need to check that the page has been loaded.
    @Override
    protected void isLoaded() throws Error {
        this.logger.error("failed to load LoginPage ");
        assertTrue(this.driver.getCurrentUrl( ).contains("http://the-internet.herokuapp.com/login"), "Login Page is not loaded!");
        logger.throwing(Level.ERROR, new AssertionError( ));
    }


    /**
     * Execute log in
     */
    public SecureAreaPage logIn(String username, String password) {
        logger.info("Executing LogIn with username [" + username + "] and password [" + password + "]");
        basePage.writeText(formFieldUserName,username);
        basePage.writeText( formFieldPassword,password);
        basePage.click(logInButton);
        return new SecureAreaPage(this.driver,this, logger);
    }


    public void negativeLogIn(String username, String password) {
        logger.info("Executing Negative LogIn with username [" + username + "] and password [" + password + "]");
        this.basePage.writeText( formFieldUserName,username);
        this.basePage.writeText(formFieldPassword,password);
        this.basePage.click(logInButton);
    }

    public String pageHeaderText(){
        return this.basePage.readText(headerEle);
    }


}
