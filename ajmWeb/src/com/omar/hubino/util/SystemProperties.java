package com.omar.hubino.util;


import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * The Class SystemProperties.
 * 
 * @author Vijayaraja Gnansambandan
 * @version 1.0.0 - The Class SystemProperties Created
 * 
 */

public class SystemProperties {
	public static String osName = null;
	static Logger log = Logger.getLogger(SystemProperties.class);

	/**
	 * Gets operating system name.
	 * 
	 * @return String <code>osName</code>.
	 * @throws Exception
	 *             If any error occurs
	 */
	public static String getSystemProperties() throws Exception {
		try {

			// Get a list of all the system properties and their values
			Properties sysprops = System.getProperties();
			osName = sysprops.getProperty("os.name");
		} catch (Exception e) {
			log.info(e);
		}
		return osName;
	}

}