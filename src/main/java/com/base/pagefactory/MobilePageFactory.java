package com.base.pagefactory ;

import java.lang.reflect.Field;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


/**
 * Handles interactions with the Selenium 2 {@link PageFactory} and also handles interations with
 * the custom annotations {@link HandlesLoginUsing} and {@link GetsElementsFrom}
 */
public final class MobilePageFactory  {
	
  private MobilePageFactory() {}

  /**
   * Constructs a new instance of type T and handles all Selenium 2 related annotation binding.
   * @param <T>
   * @param clazz
   * @return the fully bootstrapped page object
   */
  public static <T extends MobileDriverPage> T initializePage(Class<T> clazz, AppiumDriver<MobileElement> driver) {

    if (clazz == null) {
      throw new InvalidPageObjectException("Cannot create an instance of the class type null");
    }

    T page = null;

    try {
      page = clazz.newInstance();
    }
    catch (Exception e) {
      throw new MissingDefaultConstructorException("Cannot construct " + clazz.getSimpleName()
        + ", no default constructor", e);
    }
    initializePage(page, driver);
    return page;
  }

  /**
   * Initializes the page object passed in by loading all of the annotated fields that deal with
   * Selenium 2 functionality
   * @param <T>
   * @param page
   */
  @SuppressWarnings("unchecked")
  public static <T extends MobileDriverPage> void initializePage(T page, AppiumDriver<MobileElement> driver) {
    if (page == null) {
      throw new InvalidPageObjectException("Cannot initialize a page instance that is null");
    }
    PageFactory.initElements(new AppiumFieldDecorator(driver), page);    
    for (Class<? extends MobileDriverPage> pageClass = page.getClass();
        pageClass != null && !pageClass.equals(MobileDriverPage.class);
        pageClass = (Class<? extends MobileDriverPage>) pageClass.getSuperclass()) {

    for (Field field : pageClass.getDeclaredFields()) {
      field.setAccessible(true);
    }
    }
  }
        
}
  
  
  
  
