/**
 * 
 */
package com.base.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.base.pagefactory.ObjectRepo;
import com.base.utils.WaitUtil.Locator;

/**
 * @author Sindhu Koti
 *
 */
public class GenericUtil {

	private static final Logger logger = (Logger) LogManager.getLogger(GenericUtil.class.getName());
	
	public static String getConfigData(String parameter) throws IOException {
		try {
			File file = new File("./Config.properties");
			FileInputStream properties = new FileInputStream(file);
			Properties prop = new Properties();
			prop.load(properties);
			prop.getProperty(parameter);
			return prop.getProperty(parameter);

		} catch (IOException e) {
			return "";
		}

	}
	

	
}
