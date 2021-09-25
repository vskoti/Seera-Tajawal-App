package com.base.pagefactory ;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;


/**
 * The base class for page objects that are used with the framework.  This class handles
 * construction of page objects and interfacing with the Selenium 2 page factory via its
 * constructors.
 */
public abstract class MobileDriverPage {

  /**
   * Initializes the page object against the current loaded page
   */
  public MobileDriverPage(AppiumDriver<MobileElement> driver) {
    this(null, LoadBehavior.ASSUME_PAGE_LOADED, driver);
  }

	/*
	 * public MobileDriverPage() { // default constructor this(null,
	 * LoadBehavior.ASSUME_PAGE_LOADED, DriverFactory.getInstance().getDriver()); //
	 * OICPageFactory.initializePage(this, Driver); }
	 */
  
  public MobileDriverPage(String location, LoadBehavior behavior, AppiumDriver<MobileElement> driver) {
    switch(behavior) {
      case ASSUME_PAGE_LOADED:
        break;
      case LOAD_PAGE:
    	  driver.get(location);
        break;
      case LOAD_PAGE_IF_BLANK:      
    	  driver.get(location);
        
        break;
    }
    MobilePageFactory.initializePage(this, driver);
  }

  /**
   * Forces loading of the specified URL before constructing the object to make sure all
   * bindings match content
   * @param location
   */
  public MobileDriverPage(String location, AppiumDriver<MobileElement> Driver) {
    this(location, LoadBehavior.LOAD_PAGE, Driver);
  }

  }
