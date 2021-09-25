/**
 * 
 */
package com.base.utils;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.drivers.factory.DriverFactory;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

/**
 * @author Sindhu Koti
 *
 */
public class ScreenUtil {
	/**
	 * Scrolls down the page as per the hard coded coordinates 
	 */
	public static void scrollDown() {	
		new TouchAction(DriverFactory.getDriver()).press(PointOption.point(550, 600)).waitAction().moveTo(PointOption.point(500, 10)).release().perform();			     
	}
	
    /**
     * 
     * @param driver
     * @param direction
     */
	public static void scroll(WebDriver driver , String direction) {
	    HashMap<String, String> scrollObject = new HashMap<String, String>();
	    scrollObject.put("direction", direction);
	    ((RemoteWebDriver) driver).executeScript("mobile:scroll", scrollObject);
	}
	
	/**
	 * 
	 * @param driver
	 * @param text
	 */
	public static void scrollToText(WebDriver driver , String text) {
		driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + text + "\").instance(0));"));
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='"+text+"']")));
	}
	
	
}
