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

    ExpectedCondition<WebElement> forEle(By by) {
        return ExpectedConditions.visibilityOfElementLocated(by);
    }

    public WebElement isElementLocated(By by) {
        return webDriverWait.until(forEle(by));
    }


}
