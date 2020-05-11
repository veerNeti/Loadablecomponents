package com.loadablecomponents.basepages.pages;

import com.loadablecomponents.basepages.BasePage;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-10-2020
 * Time: 06:15 PM
 */
public class Checkboxes extends LoadableComponent<Checkboxes> {
    private final LoadableComponent<WelcomePage> parentpage;
    //*********Web Elements*********
    private By headerLocator = By.tagName("h3");
    private By checkboxLocator = By.cssSelector("[type='checkbox']");

    //*********Page Variables*********
    private WebDriver driver;
    private BasePage basePage;
    private Logger logger;
    //*********Constructor*********

    public Checkboxes(WebDriver driver, Logger logger, LoadableComponent<WelcomePage> parentpage) {
        this.driver = driver;
        this.logger = logger;
        this.parentpage = parentpage;
        this.basePage = new BasePage(this.driver, this.logger);
    }

    @Override
    protected void load() {
        parentpage.get( ).getCheckboxesPage( );
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(this.basePage.readText(headerLocator).equalsIgnoreCase("Checkboxes"), "Checkboxes page is not visible");
    }


    public int numberOfCheckboxes() {
        return this.basePage.findListElements(checkboxLocator).size( );
    }

    public Boolean checktheboxes() {
        List<WebElement> checkboxWebElement = this.basePage.findListElements(checkboxLocator);
        for (WebElement check:checkboxWebElement) {
            boolean checked = Boolean.parseBoolean(check.getAttribute("checked"));
            logger.info("checkbox attribute:"+checked);
            if (!checked) {
                this.basePage.click(check);
            }
        }
        return checkboxWebElement.stream( ).filter(ele -> Boolean.parseBoolean(ele.getAttribute("checked"))).collect(Collectors.toList( )).size( )==checkboxWebElement.size();
    }


    public Boolean unCheckAllboxes() {
        List<WebElement> listOfCheckboxes = this.basePage.findListElements(checkboxLocator);
        for (WebElement ele : listOfCheckboxes) {
            String checked = ele.getAttribute("checked");
            logger.info("is checked:" + checked);
            if (Boolean.parseBoolean(checked)) {
                this.basePage.click(ele);
            }

        }
        return listOfCheckboxes.stream( ).filter(ele -> !Boolean.parseBoolean(ele.getAttribute("checked"))).collect(Collectors.toList( )).size( )==listOfCheckboxes.size();
    }

    public Boolean visibilityOfCheckboxes() {
     return this.basePage.findListElements(checkboxLocator).stream( ).filter(ele -> Boolean.parseBoolean(String.valueOf(ele.isDisplayed()))).collect(Collectors.toList( )).size( )>0;
    }
}
