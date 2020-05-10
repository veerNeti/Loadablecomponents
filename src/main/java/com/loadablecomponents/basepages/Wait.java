package com.loadablecomponents.basepages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-10-2020
 * Time: 12:39 AM
 */
public class Wait {
    private final WebDriver driver;
    private WebDriverWait webDriverWait;
    private Long MAXTIMEOUT = 10l;

    public Wait(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(this.driver, MAXTIMEOUT);
    }

    /*alert is present
element exists
element is visible
title contains
title is
element staleness
visible text*/

    public WebElement elementRefreshed(By by) {
        return webDriverWait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(by)));
    }

    public Boolean isInvisiblility(By by) {
        return webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement isInDOM(By by) {
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement isVisible(By by) {
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

}
