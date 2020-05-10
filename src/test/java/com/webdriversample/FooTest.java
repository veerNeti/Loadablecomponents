package com.webdriversample;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-07-2020
 * Time: 09:44 PM
 */
public class FooTest {
    protected WebDriver driver = null;
    DropDownPage dropDownPage;
    WelcomePage welcomePage;
    String baseurl = "http://the-internet.herokuapp.com/";

    //create Weddrive and pass it to pages No reference of driver will be in tests
    @BeforeClass(alwaysRun = true)
    public void prepareComponents() {
        if (Objects.isNull(this.driver)) {
            WebDriverManager.chromedriver( ).setup( );
            this.driver = new ChromeDriver( );
        }
        welcomePage = new WelcomePage(this.driver, baseurl, "The Internet");
        welcomePage.get( );
        dropDownPage = welcomePage.getDropDownPage( );
        dropDownPage.get( );
    }

    @Test
    public void testDropDownOptions() {
        List<String> getdropdownoptions = dropDownPage.getdropdownoptions( );
        System.out.println("getdropdownoptions:" + getdropdownoptions);
        assertTrue(Objects.nonNull(getdropdownoptions), "Drop Down is Empty");
    }

    @Test
    public void testSelectOptionByIndex() {
        dropDownPage.selectOptionByIndex(1);
        System.out.println(dropDownPage.readSelectedOption( ) );
        assertTrue(dropDownPage.readSelectedOption( ).equalsIgnoreCase(dropDownPage.getdropdownoptions( ).get(1)), "Failed to Select Option 1");
    }



    @Test
    public void testSelectOptionByVisibleText() {
        dropDownPage.selectOptionByVisibletext("Option 2");
        System.out.println(dropDownPage.readSelectedOption( ) );
        assertTrue(dropDownPage.readSelectedOption( ).equalsIgnoreCase(dropDownPage.getdropdownoptions( ).get(2)), "Failed to Select Option 1");
    }





    @AfterClass(alwaysRun = true)
    void quit() {
        driver.quit( );
    }
}
