package com.loadablecomponents.basepages.pages;

import com.loadablecomponents.basepages.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-09-2020
 * Time: 10:55 AM
 */
public class DropDownPage extends LoadableComponent<DropDownPage> {
    private final LoadableComponent<WelcomePage> parentpage;
    //*********Page Variables*********
    private WebDriverWait webDriverWait;
    private WebDriver driver;
    private BasePage basePage;
    private Logger logger;
    //*********Web Elements*********
    private final String expectedheadertext = "Dropdown List";
    private Select selectDropDown;
    private By header = By.tagName("h3");
    private By dropdownEle = By.id("dropdown");
    //*********Constructor*********
    public DropDownPage(WebDriver driver, Logger logger, LoadableComponent<WelcomePage> parentpage) {
        this.driver = driver;
        this.logger=logger;
//        this.webDriverWait = new WebDriverWait(driver, 10L);
        this.parentpage = parentpage;
        this.basePage = new BasePage(this.driver, this.logger);
        this.selectDropDown = new Select(this.driver.findElement(dropdownEle));
    }


    @Override
    protected void load() {
        this.parentpage.get( ).getDropDownPage( );
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(this.driver.findElement(header).getText( ).equalsIgnoreCase(expectedheadertext), "Could not load dropdown page!!");
    }

    public List<String> getdropdownoptions() {
//        Select dropDownElem = new Select(this.driver.findElement(dropdownEle));
        List<String> options = new ArrayList<>( );
        List<WebElement> collectionofWebElements = selectDropDown.getOptions( ).stream( ).collect(Collectors.toList( ));
        for (WebElement ele : collectionofWebElements) {
            options.add(ele.getText( ));
        }
        logger.info("Get Available options:"+options);
        return options;
    }

    public String readSelectedOption() {
        String text = selectDropDown.getFirstSelectedOption( ).getText( );
        logger.info("getFirstSelectedOption:"+text);
        return text;
    }

    public void selectOptionByIndex(int index) {
//        Select select = new Select(this.driver.findElement(dropdownEle));
        selectDropDown.selectByIndex(index);
    }

    public void selectOptionByVisibletext(String visibleText) {
        selectDropDown.selectByVisibleText(visibleText);
        logger.info("selectByVisibleText:"+visibleText);
    }

    public void deSelectOptionBIdex(int index) {
        selectDropDown.deselectByIndex(index);
        logger.info("deselectByIndex:"+index);

    }

    //works only if DropDown Or Select element allows multiple selection
    public void deSelectOptionByVisibletext(String visibleText) {
        selectDropDown.deselectByVisibleText(visibleText);
        logger.info("deselectByVisibleText:"+visibleText);
    }
}
