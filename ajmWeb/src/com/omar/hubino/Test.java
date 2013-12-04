package com.omar.hubino;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
	
	public static void main(String[] args) {
			String path = "D:/Softwares/Sethu/csv-samples/yes.csv";
			boolean result = calculateResult(path);
			if (result) {
				System.out.println(true);
				System.out.println("E00");
			} else {
				System.out.println(true);
				System.out.println("E01");
			}
	}
	
	public static boolean calculateResult(String csvFilePath) {

		boolean result = false;

		try {
			File checkFile = new File(csvFilePath);
			if (checkFile.exists()) {
				FileInputStream fstream = new FileInputStream(csvFilePath);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						fstream));
				String strLine;
				int numerOfRows = 0;
				int countZero = 0;
				int countOne = 0;
				while ((strLine = br.readLine()) != null) {
					// System.out.println( numerOfRows +" :: "+strLine);
					if (numerOfRows != 0) {
						String[] faceCmpData = strLine.split(",");
						for (int i = 1; i < faceCmpData.length; i++) {
							String val = faceCmpData[i];
							Float fl = Float.parseFloat(val);
							if (fl == 1) {
								countOne++;
							} else if (fl < 1) {
								countZero++;
							}
						}
					}
					numerOfRows++;
				}
				br.close();

				int numberOfCols = (countOne + countZero) / --numerOfRows;

				System.out.println(" No of Rows : " + numerOfRows
						+ " : No of Cols : " + numberOfCols
						+ " : Count Ones : " + countOne + " : Count Zeros :"
						+ countZero);

				result = (countZero == 0);

			} else {
				result = false;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}
