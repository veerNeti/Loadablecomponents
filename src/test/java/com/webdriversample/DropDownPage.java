package com.webdriversample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

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

    private final WebDriver driver;
    private final String expectedheadertext = "Dropdown List";
    private final LoadableComponent<WelcomePage> parentpage;
    private Select selectDropDown;
    private By header = By.tagName("h3");
    private By dropdownEle = By.id("dropdown");

    public DropDownPage(WebDriver driver, LoadableComponent<WelcomePage> parentpage) {
        this.driver = driver;
        this.parentpage = parentpage;
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
        return options;
    }

    public String readSelectedOption() {
//        Select select = new Select(this.driver.findElement(dropdownEle));
        return selectDropDown.getFirstSelectedOption( ).getText( );
    }

    public void selectOptionByIndex(int index) {
//        Select select = new Select(this.driver.findElement(dropdownEle));
        selectDropDown.selectByIndex(index);
    }

    public void selectOptionByVisibletext(String visibleText) {
//        Select select = new Select(this.driver.findElement(dropdownEle));
        selectDropDown.selectByVisibleText(visibleText);
    }

    public void deSelectOptionBIdex(int index) {
//        Select select = new Select(this.driver.findElement(dropdownEle));
        selectDropDown.deselectByIndex(index);

    }

    //works only if DropDown Or Select element allows multiple selection
    public void deSelectOptionByVisibletext(String visibleText) {
//        Select select = new Select(this.driver.findElement(dropdownEle));
        selectDropDown.deselectByVisibleText(visibleText);
    }
}
