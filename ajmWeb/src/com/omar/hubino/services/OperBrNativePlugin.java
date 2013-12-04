package com.omar.hubino.services;

import java.io.File;

import com.omar.hubino.Constants;
import com.omar.hubino.bo.OpenBrResponse;
import com.omar.hubino.util.ConfigProperties;

public class OperBrNativePlugin {

	private static OperBrNativePlugin instance = null;

	private OperBrNativePlugin() {
		loadLibs();
	}

	public synchronized static OperBrNativePlugin getInstance() {
		if (instance == null) {
			instance = new OperBrNativePlugin();
//			instance.brInitialize();
		}
		return instance;
	}

	/*@Override
	protected void finalize() throws Throwable {
		System.out.println(" BR finalize !! ");
		instance.finalize();
		super.finalize();
	}*/

	private static final String[] LIBRARIES = { "libopencv_core.so.2.4",
			"libopencv_flann.so.2.4", "libopencv_imgproc.so.2.4",
			"libopencv_highgui.so.2.4", "libopencv_features2d.so.2.4",
			"libopencv_calib3d.so.2.4", "libopencv_objdetect.so.2.4",
			"libopencv_ml.so.2.4", "libopencv_contrib.so.2.4",
			"libopenbr.so.0.5.0", "libFaceCompare_OmarTech_Latest.so", "libbrjniajm.so.1.0.0" };

	public void loadLibs() {

		ConfigProperties objProbs = new ConfigProperties(
				Constants.CONFIG_PROPERTIES);

		String localLibPath = objProbs.getPropertyValues(Constants.LIB_PATH);

		for (String lib : LIBRARIES) {
			String absolutePath = /*"/usr/local/lib"*/localLibPath + File.separator + lib;
			System.out.print("LIBS ::" + absolutePath);
			System.load(absolutePath);
			System.out.println(" :: Loaded");
		}

	}

	public native void brInitialize();

	public native void brCreateGal(String imagePath, String galPath);

	public native void brAuthenticate(String imagePath, String galPath);

	public native OpenBrResponse brEnrollment(String imagePath, String galPath);
	
	public native OpenBrResponse brAthentication(String imagePath, String galPath,int noOfqueryImages,int noOfTrainningImages);
	
	public native void brFinalize();
}
