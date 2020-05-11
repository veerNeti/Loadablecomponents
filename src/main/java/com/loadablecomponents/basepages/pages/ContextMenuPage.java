package com.loadablecomponents.basepages.pages;

import com.loadablecomponents.basepages.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-10-2020
 * Time: 08:21 PM
 */
public class ContextMenuPage extends LoadableComponent<ContextMenuPage> {
    private LoadableComponent<WelcomePage> parentpage;
    //*********Page Variables*********
    private WebDriver driver;
    private BasePage basePage;
    private Logger logger;
    private Actions action;
    //*********Web Elements*********
    private By headerLocator = By.tagName("h3");
    private By formArea = By.cssSelector("#hot-spot");

    //*********Constructor*********
    public ContextMenuPage(WebDriver driver, Logger logger, LoadableComponent<WelcomePage> welcomePageLoadableComponent) {
        this.driver = driver;
        this.logger = logger;
        this.basePage = new BasePage(this.driver, this.logger);
        this.parentpage = welcomePageLoadableComponent;
        this.action = new Actions(this.driver);
    }

    @Override
    protected void load() {
        this.parentpage.get( ).getContextMenyPage( );
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(this.basePage.readText(headerLocator).equalsIgnoreCase("Context Menu"), "Context Menu is not loaded!!");
    }

    public boolean rightclick() {
        action.contextClick(this.basePage.find(formArea))
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform( );
        Alert alert = driver.switchTo( ).alert( );
        return Boolean.parseBoolean(String.valueOf(alert.getText( ).equalsIgnoreCase("You selected a context menu")));
    }

}
