package com.base.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.drivers.factory.DriverFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;


/**
 * @author Sindhu Koti
 * This just has a wait method
 * to wait for an element to be clickable before continuing.
 */
public class WaitUtil  {
	
	/**
	 * @description : Waits for the element using Selenium 3 expectedConditions.
	 * @param locator
	 * @param type
	 */
	public static void waitForElementToBeClickable(AppiumDriver<MobileElement> driver,String locator, Locator type, Long timeout) {

		switch (type) {
		case Xpath:
			new WebDriverWait(driver, timeout)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
			break;

		case Id:
			new WebDriverWait(driver, timeout)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
			break;

		default:
			break;
		}
	}

	/**
	 * @description : Enum representing the types of locator.
	 * @author SindhuKoti
	 */
	public static enum Locator {
		Xpath, Id, MobileElement;
	}
	
	public static void waitForElementToDisplay(AppiumDriver<MobileElement> driver, String locator, Locator type, Long timeout) {

		switch (type) {
		case Xpath:
			new WebDriverWait(driver, timeout)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
			break;


		case Id:
			new WebDriverWait(driver, timeout)
					.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
			break;
      

		default:
			break;
		}
	}
	
	
	public static void waitForElementToDisappear(AppiumDriver<MobileElement> driver,String locator, Locator type, Long timeout) {

		switch (type) {
		case Xpath:
			new WebDriverWait(driver, timeout)
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
			break;

		case Id:
			new WebDriverWait(driver, timeout)
					.until(ExpectedConditions.invisibilityOfElementLocated(By.id(locator)));
			break;

		default:
			break;
		}
	}
	
	
}
