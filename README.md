# Loadablecomponents
a project to demo loadablecomponent pattern

- The LoadableComponent is a base class that aims to make writing PageObjects less painful. 
- It does this by providing a standard way of ensuring that pages are 
   loaded and providing hooks to make debugging the failure of a page to load easier. 
- You can use it to help reduce the amount of boilerplate code in your tests, which in turn make maintaining your tests less tiresome.
- LoadableComponent is another way to approach PageObjects. 
- LoadableComponent is a base class that all of the pages need to extend.
- The base class has the following methods on the interface:
    `get()
    isLoaded()
    load()`
- Instead of the usual public class PageObject, we change it:
    public class PageObject extends LoadableComponent<PageObject>
    Extending an abstract class is a contract to implement the methods in the parent class "load() and isLoaded()". 
    The load method will be designed to load the page; and the isLoaded() method should enable the validation if the page has been loaded correctly. 


There is currently an implementation in Java that ships as part of Selenium 2, but the approach used is simple enough to be implemented in any language.
## Reference :
 - https://github.com/SeleniumHQ/selenium/wiki/LoadableComponent
 
##Page locators if identified using the can be verified in the browser console
 1. cssSelector
 ``$$("div#content > .heading")``
 2. xpath
 ``$x("//h1[@class='heading']")``

#lowLoadableComponent and NoSuchElementException
 

 