package com.loadablecomponents.basepages.pages;

import com.loadablecomponents.basepages.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.Objects;

import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-10-2020
 * Time: 12:35 AM
 */
public class AddremoveElementsPage extends LoadableComponent<AddremoveElementsPage> {

    private final LoadableComponent<WelcomePage> parentpage;
    //*********Web Elements*********
    By headerText = By.tagName("h3");
    By buttonAddLocator = By.cssSelector(".example button");
    By buttonDeleteLocator = By.cssSelector("div#elements > .added-manually");

    //*********Page Variables*********
    private WebDriver driver;
    private BasePage basePage;
    private Logger logger;

    //*********Constructor*********


    public AddremoveElementsPage(WebDriver driver, Logger logger, LoadableComponent<WelcomePage> parentpage) {
        this.driver = driver;
        this.logger = logger;
        this.basePage = new BasePage(this.driver, this.logger);
        this.parentpage = parentpage;
    }

    @Override
    protected void load() {
        this.parentpage.get( ).addremoveElementsPage( );
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(this.basePage.readText(headerText).equalsIgnoreCase("Add/Remove Elements"), "Add/Remove Elements was not loaded!!");
    }

    public void clickAddButton() {
        this.basePage.click(buttonAddLocator);
    }

    public boolean isDeleteButtonVisible() {
        return this.basePage.isDisplayed(buttonDeleteLocator);
    }

    public boolean removeDeleteButtonDisplayed() {
        assertTrue(this.basePage.isDisplayed(buttonDeleteLocator), "Delete button is invisible");
        return this.basePage.isDisplayed(buttonDeleteLocator);
    }

    public Boolean removeDeleteButton(int numberOfDeleteButtons) {
        for (int i = 0; i < numberOfDeleteButtons; i++) {
            logger.info("removed :"+i );
            this.basePage.click( this.basePage.eleReloaded(buttonDeleteLocator));
        }
        return this.basePage.findListElements(buttonDeleteLocator).size()==0;
    }

    public int numberOfDeleteButtonsDisplayed(){
        return driver.findElements(buttonDeleteLocator).size();
    }



}

