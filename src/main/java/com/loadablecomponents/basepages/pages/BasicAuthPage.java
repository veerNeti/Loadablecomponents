package com.loadablecomponents.basepages.pages;

import com.loadablecomponents.basepages.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-10-2020
 * Time: 03:03 PM
 */
public class BasicAuthPage extends LoadableComponent<BasicAuthPage> {
    private final LoadableComponent<WelcomePage> parentpage;
    //*********Web Elements*********
    private By header = By.tagName("h3");
    private By welcometext=By.cssSelector("p");
    //*********Page Variables*********
    private WebDriver driver;
    private BasePage basePage;
    private Logger logger;
    //*********Constructor*********

    public BasicAuthPage(WebDriver driver, Logger logger, LoadableComponent<WelcomePage> welcomePageLoadableComponent) {
        this.driver = driver;
        this.logger = logger;
        this.parentpage = welcomePageLoadableComponent;
        this.basePage = new BasePage(this.driver, this.logger);
    }

    @Override
    protected void load() {
        parentpage.get( ).loginToBasicAuthPage("admin", "admin");
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue(this.basePage.readText(header).equalsIgnoreCase("Basic Auth"), "Successfully authenticated");
    }

    public String readerwelcomeText(){
        return this.basePage.readText(welcometext);
    }


}
