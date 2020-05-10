package com.loadablecomponents.basepages.pages;

import com.loadablecomponents.basepages.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-05-2020
 * Time: 10:07 PM
 */


public class WelcomePage extends LoadableComponent<WelcomePage> {
    //*********Page Variables*********
    private String welcomepageUrl;
    private By formAuthenticationLinkLocator = By.linkText("Form Authentication");
    private By header = By.xpath("//h1[@class='heading']");
    private By subheader = By.tagName("h2");
    private By allAvilableOptions = By.cssSelector("ul>li>a");
    private By optionDropDown = By.linkText("Dropdown");
    private By abtestele = By.linkText("A/B Testing");
    private By addremoveElements = By.linkText("Add/Remove Elements");

    //Driver and Webdriver
    private final WebDriver driver;
    private Logger logger;

    //driver passed in from test will instatiate the driver
    private BasePage basePage;

    public WelcomePage(WebDriver driver, Logger logger,String aut) {
        this.driver = driver;
        this.logger = logger;
        this.welcomepageUrl=aut;
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

        return this.basePage.getAllDropDownOptions(optionDropDown);
    }


    public DropDownPage getDropDownPage() {
        this.driver.findElement(optionDropDown).click( );
        return new DropDownPage(driver,this.logger, this);
    }


    public WelcomePage driveBackToWelcomePage() {
        this.driver.navigate( ).back( );
        return this;
    }

    public AbtestPage getAbTestPage() {
        this.driver.findElement(abtestele).click( );
        return new AbtestPage(driver,this.logger, this);
    }

    public AddremoveElementsPage addremoveElementsPage() {
        this.basePage.click(abtestele);
        return new AddremoveElementsPage(driver,this.logger, this);
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