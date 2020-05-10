package com.loadablecomponents.basepages.pages;

import com.loadablecomponents.basepages.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-09-2020
 * Time: 11:59 PM
 */
public class AbtestPage extends LoadableComponent<AbtestPage> {
    private final LoadableComponent<WelcomePage> parentpage;
    //*********Web Elements*********
    private By header = By.tagName("h3");
    private By dropdownEle = By.id("dropdown");
    private By paragraph = By.tagName("p");
    //*********Page Variables*********
    private WebDriver driver;
    private BasePage basePage;
    private Logger logger;
    //*********Constructor*********

    public AbtestPage(WebDriver driver, Logger logger, LoadableComponent<WelcomePage> welcomePageLoadableComponent) {
        this.logger = logger;
        this.driver = driver;
//        this.webDriverWait = new WebDriverWait(this.driver, 10L);
        this.basePage = new BasePage(this.driver, this.logger);
        this.parentpage = welcomePageLoadableComponent;
    }

    @Override
    protected void load() {
        this.parentpage.get( ).load( );
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(this.basePage.readText(header).equalsIgnoreCase("A/B Test Control"), "A/B Test Control Page is not loaded!!");
    }

    public String readParagraph() {
        return this.basePage.readText(paragraph);
    }
}
