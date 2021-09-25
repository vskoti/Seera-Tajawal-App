/**
 * 
 */
package com.base.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import io.appium.java_client.MobileElement;

/**
 * @author Sindhu Koti
 * @apiNote Mobile element actions concentration methods 
 */
public class ElementUtil {

	/**
	   * @description:Sets the input text after the element is displayed
	   */
	  public static void setInput(WebDriver driver, MobileElement element, String value) {
	    // This below line is added, moving to element using Actions class.
	    new Actions(driver).moveToElement(element).perform() ;
	    element.clear();	   
	    if (value != null) { 
	      element.sendKeys(value);
	    }
	  }

	  /**
	   * @description:Clicks on a web element after element is displayed
	   */
	  public static void clickElement(WebDriver driver,MobileElement element) {
		new Actions(driver).moveToElement(element).perform() ;
	    element.click();
	  }
	
}
