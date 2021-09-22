/**
 * 
 */
package com.base.drivers.factory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;

import com.base.utils.ConfigConstants;
import com.base.utils.GenericUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * @author Sindhu Koti
 *
 */
public class DriverFactory {

	private static DriverFactory instance = null;
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(DriverFactory.class.getName());
	private static final ThreadLocal<AppiumDriver<MobileElement>> driver = new ThreadLocal<>();

	public static enum OS {
		IOS, Android
	};

	public static DriverFactory getInstance() {
		if (instance == null) {
			instance = new DriverFactory();
		}
		return instance;
	}

	/**
	 * Set the desired capability.
	 * 
	 * @throws Exception
	 */

	public static DesiredCapabilities getDesiredCapabilities() throws Exception {
		DesiredCapabilities desiredCapabilities = null;
		try {

			String os = GenericUtil.getConfigData(ConfigConstants.PLATFORM_NAME);

			switch (os.toUpperCase()) {
			case "IOS":
				desiredCapabilities = new DesiredCapabilities();
				break;

			default:
				File f = new File(GenericUtil.getConfigData(ConfigConstants.APK_PATH));
				desiredCapabilities = new DesiredCapabilities();
				desiredCapabilities.setCapability(MobileCapabilityType.APP, f.getAbsolutePath());
				
				desiredCapabilities.setCapability("noReset", GenericUtil.getConfigData(ConfigConstants.NO_RESET));
				desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS,
						GenericUtil.getConfigData(ConfigConstants.AUTO_GRANT_PERMISSIONS));			
				desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
						GenericUtil.getConfigData(ConfigConstants.AUTOMATION_NAME));
				desiredCapabilities.setCapability("appPackage", GenericUtil.getConfigData(ConfigConstants.APP_PACKAGE));
				desiredCapabilities.setCapability("appActivity",
						GenericUtil.getConfigData(ConfigConstants.MAIN_ACTIVITY));
				desiredCapabilities.setCapability("avdLaunchTimeout",
						GenericUtil.getConfigData(ConfigConstants.AVD_TIMEOUT));
				desiredCapabilities.setCapability("newCommandTimeout",
						GenericUtil.getConfigData(ConfigConstants.NEW_COMMAND_TIMEOUT));
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			if (driver != null) {
				driver.get().quit();
			}
			AppiumService.stopService();
		}
		return desiredCapabilities;
	}

	public static  AppiumDriver<MobileElement> getDriver() {
		return driver.get();
	}

	public static AppiumDriver<MobileElement> setDriver(int deviceSystemPort, String platformName, String deviceName) {
		try {

			String[] ports = GenericUtil.getConfigData(ConfigConstants.APPIUM_SERVER_PORTS).split(",");
			long appiumPort = Long.parseLong(ports[0]);
			DesiredCapabilities desiredCapabilities = getDesiredCapabilities();

			switch (platformName.toUpperCase()) {
			case "IOS":
				desiredCapabilities.setCapability("wdaLocalPort", deviceSystemPort);
				desiredCapabilities.setCapability("platformName", platformName);
				desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
				break;
			default:
				desiredCapabilities.setCapability("systemPort", deviceSystemPort);
				desiredCapabilities.setCapability("platformName", platformName);
				desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
				break;
			}
			AppiumDriver<MobileElement> customdriver = new AndroidDriver<MobileElement>(
					new URL("http://127.0.0.1:" + appiumPort + "/wd/hub/"), getDesiredCapabilities());
			driver.set(customdriver);
			boolean isResetApp = Boolean.getBoolean(GenericUtil.getConfigData(ConfigConstants.RESET_APP));
			if (isResetApp) {
				driver.get().resetApp();
			}
			driver.get().manage().timeouts().implicitlyWait(
					Long.parseLong(GenericUtil.getConfigData(ConfigConstants.WAIT_IMPLICIT_IN_SECONDS)),
					TimeUnit.SECONDS);
			driver.get().resetApp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.debug(e.getMessage());
		}

		return driver.get();
	}

	/**
	 * Closed the current opened driver and kills all the chromedriver.exe if open
	 * in system processes.
	 */
	public static void closeDriver() {
		driver.get().quit();
	}

}
