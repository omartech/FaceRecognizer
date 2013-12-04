package com.omar.hubino.util;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;

import org.apache.log4j.Logger;


/**
 * This Class contains file manupulate methods
 * 
 * @author Vijayaraja
 * 
 */
public class FileUtil {
	static Logger log = Logger.getLogger(FileUtil.class);

	/**
	 * This method used to copy the one file in source directory to target
	 * directory
	 * 
	 * @param sourceDir
	 * @param fileName
	 * @param targetDir
	 * @return Boolean
	 * @throws IOException
	 */
	public static boolean copyFile(String sourceDir, String fileName,
			String targetDir) throws IOException {
		File source = new File(sourceDir, fileName);
		File target = new File(targetDir, fileName);
		boolean success = FileUtil.streamFileToFile(source, target);
		log.info("Copying File " + fileName + " to " + targetDir);
		return success;
	}

	/**
	 * 
	 * This static method used to change file name for add with current date and
	 * time.
	 * 
	 * @param sourceDir
	 * @param fileName
	 * @return boolean
	 * @throws IOException
	 */
	public static boolean fileRename(String sourceDir, String fileName)
			throws IOException {
		File source = new File(sourceDir, fileName);
		String changeName = FileNameFormatter.fileNameFormatter(fileName,
				sourceDir);
		File changedName = new File(sourceDir, changeName);
		boolean success = source.renameTo(changedName);
		log.info("File " + fileName + " renamed to " + changeName);
		return success;
	}

	/**
	 * This static method used to remove file in particular dirctory.
	 * 
	 * @param sourceDir
	 * @param fileName
	 * @return boolean
	 * @throws IOException
	 */
	public static boolean fileRemove(String sourceDir, String fileName)
			throws IOException {
		File source = new File(sourceDir, fileName);
		boolean success = source.delete();
		log.info("Removing File " + fileName + " form " + sourceDir);
		return success;
	}

	/**
	 * 
	 * This static method used to convert the stream file to file
	 * 
	 * @param source
	 * @param target
	 * @return boolean
	 * @throws IOException
	 */
	public static boolean streamFileToFile(File source, File target)
			throws IOException {
		return handlerStreamFileToFile(source, target);
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @return boolean
	 * @throws IOException
	 */
	public static boolean handlerStreamFileToFile(File source, File target)
			throws IOException {
		FileOutputStream stmOut = new FileOutputStream(target);
		try {
			handlerStreamFileToOutputStream(source, stmOut);
		} finally {
			stmOut.close();
		}
		boolean success = target.setLastModified(source.lastModified());
		success = target.setReadOnly();
		return success;
	}

	/**
	 * This static method used to convert the handler stream to output stream
	 * 
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	public static void handlerStreamFileToOutputStream(File source,
			OutputStream target) throws IOException {
		FileDataSource dataSource = new FileDataSource(source);
		DataHandler handler = new DataHandler(dataSource);
		handler.writeTo(target);
	}

	/**
	 * 
	 * This method used to list the files in particular directory
	 * 
	 * @param sourceDir
	 * @return List of Files
	 */
	public static List filesInDir(String sourceDir) throws Exception {
		List filelist = new ArrayList();
		File srcDir = new File(sourceDir);
		String[] fileNames = srcDir.list();
		for (int i = 0; i < fileNames.length; i++) {
			filelist.add(fileNames[i]);
		}
		return filelist;
	}

	/**
	 * This method used to write the file in specified path
	 * 
	 * @param data
	 * @param filePath
	 * @param encoding
	 * @throws IOException
	 */
	public static boolean writeToFile(String data, String filePath,
			String encoding) throws IOException {
		if (!"0".equalsIgnoreCase(data)) {
			FileOutputStream fos = new FileOutputStream(filePath);
			Writer out = new OutputStreamWriter(fos, encoding);
			out.write(data);
			out.flush();
			out.close();
			fos.close();
		}
		return true;
	}

	public static String writeToFile(byte[] data, String directory)
			throws IOException {
		String newFileName = "";
		if (data.length > 0) {
			makeDir(directory);
			newFileName = FileNameFormatter.getNewFileNameTime(directory);
			FileOutputStream fileOuputStream = new FileOutputStream(directory
					+ File.separator + newFileName);
			fileOuputStream.write(data);
			fileOuputStream.close();
		}
		return newFileName;
	}
	
	public static void writeToFile(byte[] data, String dir,String filename)
			throws IOException {
		if (data.length > 0) {
			makeDir(dir);
			FileOutputStream fileOuputStream = new FileOutputStream(
					dir + File.separator + filename);
			fileOuputStream.write(data);
			fileOuputStream.close();
		}
	}

	/**
	 * Print to terminal Window
	 */
	public static void printMesage(String message) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(new Date().toGMTString());
		sbf.append(" INFO ");
		sbf.append(message);

		System.out.println(sbf.toString());
	}

	public static byte[] getBytesOfFile(File file) throws IOException,
			FileNotFoundException {
		InputStream is = new FileInputStream(file);

		long length = file.length();
		byte[] bytes = new byte[(int) length];
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		is.close();
		return bytes;
	}

	public static void makeDir(String targetDir) {
		File dataFile = new File(targetDir);
		if (!dataFile.exists()) {
			dataFile.mkdirs();
		}

	}

	public static BufferedImage getBufferedImage(String filePath) throws IOException {
		BufferedImage bufferedImage = null;
		File f = new File(filePath);
		bufferedImage = ImageIO.read(f);
		return bufferedImage;
	}

}
