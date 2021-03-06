package com.loadablecomponents.basepages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-05-2020
 * Time: 09:59 PM
 */
public class BasePage {
    private final WebDriver driver;
    private final Wait wait;

    private Logger logger;

    //constructor
    public BasePage(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.logger = logger;
        this.wait = new Wait(this.driver);
    }

    //Click Method
    public void click(By elementLocation) {
        logger.info("clicking ele:" + elementLocation);
        isDisplayed(elementLocation);
        driver.findElement(elementLocation).click( );
    }

    //Click Method
    public void click(WebElement element) {
        isDisplayed(element);
        element.click( );
    }

    //isElement Displayed
    public Boolean isDisplayed(WebElement element) {
        return element.isDisplayed( );
    }

    //Write Text
    public void writeText(By elementLocation, String text) {
        logger.info("writeText " + text + " to ele:" + elementLocation);
        driver.findElement(elementLocation).sendKeys(text);
    }

    //Read Text
    public String readText(By elementLocation) {
        logger.info("readText from ele:" + elementLocation);
        return eleReloaded(elementLocation).getText( );
    }

    //isElement Displayed
    public Boolean isDisplayed(By elementLocation) {
        logger.info("isDisplayed:" + elementLocation);
        return wait.isVisible(elementLocation).isDisplayed( );
    }

    //Get list of all dropdownoptions
    public List<String> getAllDropDownOptions(By ele) {

        Iterator<WebElement> iterator = this.driver.findElements(ele).iterator( );
        List<String> optionsText = new ArrayList<>( );
        while (iterator.hasNext( )) {
            optionsText.add(iterator.next( ).getText( ));
        }
        return optionsText;
    }

    //isElement invisible
    public Boolean isInvisible(By elementLocation) {
        logger.info("is Invisible:" + elementLocation);
        return wait.isInvisiblility(elementLocation);
    }


    //isElement reloaded
    public WebElement eleReloaded(By elementLocation) {
        logger.info("is Invisible:" + elementLocation);
        return wait.elementRefreshed(elementLocation);
    }


    //find ele
    public WebElement find(By elementLocation) {
        return this.driver.findElement(elementLocation);
    }


    //find elements
    public List<WebElement> findListElements(By elementLocation) {
        return this.driver.findElements(elementLocation);
    }


}
