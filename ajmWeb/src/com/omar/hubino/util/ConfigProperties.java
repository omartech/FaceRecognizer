package com.omar.hubino.util;


import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import org.apache.log4j.Logger;

/**
 * The Class ConfigProperties.
 * 
 * @author Vijayaraja Gnansambandan
 * @version 1.0.0 - The Class ConfigProperties Created
 * 
 */

public class ConfigProperties {
	
	FileInputStream fis = null;
	
	String strPropertyFileName = null;
	
	Properties prop = null;
	
	private static String osName = null;
	
	static Logger log = Logger.getLogger(ConfigProperties.class);

	public ConfigProperties(String strPropertyFileName) {
		try {

			osName = SystemProperties.getSystemProperties();

			if (osName.startsWith("Windows")) {
				File root = new File(
						"E:\\Android\\ALDP\\conf\\"
								+ strPropertyFileName);
				root = root.getAbsoluteFile();
				fis = new FileInputStream(root); // windows
			} else {
				try {
					fis = new FileInputStream(/*"/home/hubino/Hubino/ark/conf/"*/"/home/ark/conf/"
							+ strPropertyFileName); // linux
				} catch (Exception e) {
					log.error(e);
					// TODO: handle exception
				}
				if (fis == null || fis.equals(null)) {
					log.error("Property file is null");
				}
			}

			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public String getPropertyValues(String strPropertyName) {
		if (prop != null) {
			return prop.getProperty(strPropertyName);
		}
		return null;
	}
}
