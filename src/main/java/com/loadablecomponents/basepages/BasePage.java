package com.loadablecomponents.basepages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-05-2020
 * Time: 09:59 PM
 */
public class BasePage {
private WebDriver driver;

private Logger logger;

//constructor
    public BasePage(WebDriver driver,Logger logger) {
        this.driver = driver;
        this.logger=logger;
    }

    //Click Method
    public void click (By elementLocation) {
        logger.info("clicking ele:"+elementLocation);
        driver.findElement(elementLocation).click();
    }

    //Write Text
    public void writeText (By elementLocation, String text) {
        logger.info("writeText "+text+" to ele:"+elementLocation);
        driver.findElement(elementLocation).sendKeys(text);
    }

    //Read Text
    public String readText (By elementLocation) {
        logger.info("readText from ele:"+elementLocation);
        return driver.findElement(elementLocation).getText();
    }


}
