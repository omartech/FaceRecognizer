package com.omar.hubino.services;


public class NativeService {
	
//	private static NativeService instance = null;

	/*private NativeService() {
		loadLibs();
	}*/
	
	public NativeService() {
		
	}
	
	static{
		System.load("/usr/local/lib/libopencv_core.so.2.4");
		System.load("/usr/local/lib/libopencv_flann.so.2.4");
		System.load("/usr/local/lib/libopencv_imgproc.so.2.4"); 
		System.load("/usr/local/lib/libopencv_highgui.so.2.4");
		System.load("/usr/local/lib/libopencv_features2d.so.2.4");
		System.load("/usr/local/lib/libopencv_calib3d.so.2.4"); 
		System.load("/usr/local/lib/libopencv_objdetect.so.2.4");
		System.load("/usr/local/lib/libopencv_ml.so.2.4"); 
		System.load("/usr/local/lib/libopencv_contrib.so.2.4");
		System.load("/usr/local/lib/libopenbr.so.0.5.0");
		System.load("/usr/local/lib/libbrface.so.1.0.0");
	}

/*	public synchronized static NativeService getInstance() {
		if (instance == null) {
			instance = new NativeService();
		}
		return instance;
	}
*/
	/*private static final String[] LIBRARIES = { 
		"libopencv_core.so.2.4",
		"libopencv_flann.so.2.4",
		"libopencv_imgproc.so.2.4", 
		"libopencv_highgui.so.2.4",
		"libopencv_features2d.so.2.4",
		"libopencv_calib3d.so.2.4", 
		"libopencv_objdetect.so.2.4",
		"libopencv_ml.so.2.4", 
		"libopencv_contrib.so.2.4",
		"libopenbr.so.0.5.0",
		"libbrface.so.1.0.0"
		};*/

	/*public void loadLibs() {
		
		ConfigProperties objProbs = new ConfigProperties(
				Constants.CONFIG_PROPERTIES);
		
		String localLibPath = objProbs.getPropertyValues(Constants.LIB_PATH);
		
		for (String lib : LIBRARIES) {
			String absolutePath = localLibPath + File.separator +lib;
			System.out.print("LIBS ::" + absolutePath);
			System.load(absolutePath);
			System.out.println(lib + " :: Loaded");
		}
		
		for (String lib : LIBRARIES) {
			System.load(lib.trim());
			System.out.println(lib+" :: Loaded");
		}
	}
	*/
	public native void createGal(String imagePath,String galPath);
	public native void authenticate(String imagePath,String galPath);
}
