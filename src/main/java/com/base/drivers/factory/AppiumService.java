/**
 * 
 */
package com.base.drivers.factory;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import com.base.utils.ConfigConstants;
import com.base.utils.GenericDataUtil;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

/**
 * @author Sindhu Koti
 * @apiNote To start and stop the appium server
 */
public class AppiumService {
	
	private static final org.apache.logging.log4j.Logger logger = LogManager
			.getLogger(AppiumService.class.getName());

	public static AppiumDriverLocalService service = null ;
	
	/**
	 * @apiNote Start the appium service. this will be called from onStart of the test suite. 
	 */
	public static void startService() {
		
		try {
			File nodeExe =  new File(GenericDataUtil.getConfigData(ConfigConstants.NODE_HOME_PATH));
			File appiumJs = new File(GenericDataUtil.getConfigData(ConfigConstants.APPIUM_SERVER_JS_PATH));
			File logFile = new File(System.getProperty("user.dir") + "/AppiumLog.txt"); //new File(GenericUtil.getConfigData(ConfigConstants.APPIUM_LOG_FILE_PATH)).get;		    
			String[] ports = GenericDataUtil.getConfigData(ConfigConstants.APPIUM_SERVER_PORTS).split(",");  
			
				service = 
					    AppiumDriverLocalService.			
						buildService(new AppiumServiceBuilder().withIPAddress("127.0.0.1").usingPort(4723).withArgument(GeneralServerFlag.SESSION_OVERRIDE)); 			
						service.start();
			
			
		} catch (Exception e) {        
          logger.debug(e.getMessage());         
		}
	}

	public static void stopService() {		
	 service.stop();
	}
	
}
