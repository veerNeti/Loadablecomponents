package com.webdriversample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Veeresh Bikkaneti
 * Date: 05-07-2020
 * Time: 09:22 PM
 */
public class WelcomePage extends LoadableComponent<WelcomePage> {
    private final WebDriver driver;
    protected String baseurl;
    private By header = By.xpath("//h1[@class='heading']");
    private By subheader = By.tagName("h2");
    private By allAvilableOptions = By.cssSelector("ul>li>a");
    private By optionDropDown = By.linkText("Dropdown");
    private String expectedPagetitle;


    // FindBy annotation to tell the PageFactory how to locate the element.
/*
    @FindBy(tagName = "//h1[@class='heading']")
    private WebElement header;

    @FindBy(tagName = "h2")
    private WebElement subheader;

    @FindBy(css = "ul>li>a")
    private List<WebElement> allAvilableOptions;

    @FindBy(linkText = "Dropdown")
    private WebElement optionDropDown;*/


    public WelcomePage(WebDriver driver, String baseurl, String expectedPagetitle) {
        this.driver = driver;
        this.baseurl = baseurl;
        this.expectedPagetitle = expectedPagetitle;

    }


    public List<String> listAllAvailableOptions() {
        Iterator<WebElement> iterator = this.driver.findElements(allAvilableOptions).iterator( );
        List<String> optionsText = new ArrayList<>( );
        while (iterator.hasNext( )) {
            optionsText.add(iterator.next( ).getText( ));
        }
        return optionsText;
    }

    public boolean dropDownIsDisplayed() {
        return this.driver.findElement(optionDropDown).isDisplayed( );
    }

    public DropDownPage getDropDownPage() {
        assertTrue(dropDownIsDisplayed( ));
        this.driver.findElement(optionDropDown).click( );
        return new DropDownPage(driver, this);
    }

    @Override
    protected void load() {
        this.driver.get(baseurl);
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(this.driver.getTitle( ).contains(expectedPagetitle),"loading failed!!");
    }
}


 /*
    In order to turn this into a LoadableComponent, all we need to do is to set that as the base type:
      This signature looks a little unusual,
      but it all it means is that this class represents a LoadableComponent that loads the EditIssue page.
      By extending this base class, we need to implement two new methods:

One thing it has done is encapsulate the information about how to navigate to the page into the page itself,
meaning that this information's not scattered through the code base. It also means that we can do this in our tests:

DropDownPage dropdown = new DropDownPage(driver).get();
This call will cause the driver to navigate to the page if that's necessary.

Advanced Usage: Nested Components
LoadableComponents start to become more useful when they are used in conjunction with other LoadableComponents.
Using our example, we could view the "edit issue" page as a component within a project's website
(after all, we access it via a tab on that site).
You also need to be logged in to file an issue. We could model this as a tree of nested components:

 + WelcomePage
 +---+ DropDownPage
     +---+ options


 The "load" method in EditIssue now looks like:

  @Override
  protected void load() {
    securedPage.get();

    driver.get("");
  }
This shows that the components are all "nested" within each other.
A call to get() in EditIssue will cause all its dependencies to load too. The example usage:


 */