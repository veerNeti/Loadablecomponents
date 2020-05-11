package com.loadablecomponents.basepages.pages;

import com.loadablecomponents.basepages.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-05-2020
 * Time: 10:07 PM
 */


public class WelcomePage extends LoadableComponent<WelcomePage> {
    //Driver and Webdriver
    private final WebDriver driver;
    //*********Page Variables*********
    private String welcomepageUrl;
    private By formAuthenticationLinkLocator = By.linkText("Form Authentication");
    private By headerLocator = By.xpath("//h1[@class='heading']");
    private By subheaderLocator = By.tagName("h2");
    private By allAvilableOptionsLocator = By.cssSelector("ul>li>a");
    private By optionDropDownLocator = By.linkText("Dropdown");
    private By abtestLocator = By.linkText("A/B Testing");
    private By addremoveLocator = By.linkText("Add/Remove Elements");
    private By basicAuthLocator = By.linkText("Basic Auth");
    private By checkboxLocator = By.linkText("Checkboxes");
    private By contextMenuLocator = By.linkText("Context Menu");


    private Logger logger;

    //driver passed in from test will instatiate the driver
    private BasePage basePage;

    public WelcomePage(WebDriver driver, Logger logger, String aut) {
        this.driver = driver;
        this.logger = logger;
        this.welcomepageUrl = aut;
        this.basePage = new BasePage(this.driver, this.logger);
    }

    @Override
    public void load() {
        this.driver.get(welcomepageUrl);
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue("HomePage is not loaded!", driver.getTitle( ).contains("The Internet"));
    }

    //we can use this.driver.findelement.click
    //instead we are using basepage as we defined click action to avoid code duplication

    public LoginPage loginaction() {
        basePage.click(formAuthenticationLinkLocator);
        return new LoginPage(this.driver, this, logger);
    }

    public List<String> listAllOptionsInWelcomePage() {

        return this.basePage.getAllDropDownOptions(optionDropDownLocator);
    }


    public DropDownPage getDropDownPage() {
        this.driver.findElement(optionDropDownLocator).click( );
        return new DropDownPage(driver, this.logger, this);
    }


    public WelcomePage driveBackToWelcomePage() {
        this.driver.navigate( ).back( );
        return this;
    }

    public AbtestPage getAbTestPage() {
        this.driver.findElement(abtestLocator).click( );
        return new AbtestPage(driver, this.logger, this);
    }

    public AddremoveElementsPage addremoveElementsPage() {
        this.basePage.click(addremoveLocator);
        return new AddremoveElementsPage(driver, this.logger, this);
    }

    public BasicAuthPage loginToBasicAuthPage(String user, String pass) {
        this.basePage.click(basicAuthLocator);
        // create robot for keyboard operations
        Robot rb = null;
        try {
            rb = new Robot( );
        } catch (AWTException e) {
            e.printStackTrace( );
        }

// Enter user name in username field
        StringSelection username = new StringSelection("admin");
        Toolkit.getDefaultToolkit( ).getSystemClipboard( ).setContents(username, null);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);

// press tab to move to password field
        rb.keyPress(KeyEvent.VK_TAB);
        rb.keyRelease(KeyEvent.VK_TAB);

//Enter password in password fieldadmin
        StringSelection pwd = new StringSelection("admin");
        Toolkit.getDefaultToolkit( ).getSystemClipboard( ).setContents(pwd, null);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);

        // press tab to move to sigin field
        rb.keyPress(KeyEvent.VK_TAB);
        rb.keyRelease(KeyEvent.VK_TAB);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace( );
        }
        rb.keyPress(KeyEvent.VK_ENTER);
        return new BasicAuthPage(driver, logger, this);
    }

    public Checkboxes getCheckboxesPage() {
        this.basePage.click(checkboxLocator);
        return new Checkboxes(driver, logger, this);
    }

    public ContextMenuPage getContextMenyPage(){
        this.basePage.click(contextMenuLocator);
        return new ContextMenuPage(driver,logger,this);
    }

}

/*
|+---Welcome.Page
|+-----Login.page
|+-------SecureArea.page

|+---Welcome.Page
|+-----DropDown.page
|+-----Login.page


 */