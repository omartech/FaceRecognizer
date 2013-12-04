package com.omar.hubino.bo;

public class OmarResponse {

	private boolean result = false;

	private String message;

	private String custEnrollStatus;
	
	private String galPath;
	
	private String imagePath;
	
	private String[] imageArr;
	
	private int trackingId;

	public void setCustEnrollStatus(String custEnrollStatus) {
		this.custEnrollStatus = custEnrollStatus;
	}

	public String getCustEnrollStatus() {
		return custEnrollStatus;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public boolean isResult() {
		return result;
	}

	public void setGalPath(String galPath) {
		this.galPath = galPath;
	}

	public String getGalPath() {
		return galPath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImageArr(String[] imageArr) {
		this.imageArr = imageArr;
	}

	public String[] getImageArr() {
		return imageArr;
	}

	public void setTrackingId(int trackingId) {
		this.trackingId = trackingId;
	}

	public int getTrackingId() {
		return trackingId;
	}

}
