package com.omar.hubino.bo;

public class FaceDistance {

	public String getSourceImage() {
		return sourceImage;
	}

	public void setSourceImage(String sourceImage) {
		this.sourceImage = sourceImage;
	}

	public String getDstImage() {
		return dstImage;
	}

	public void setDstImage(String dstImage) {
		this.dstImage = dstImage;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	private String sourceImage = null;
	
	private String dstImage = null;
	
	private double distance;
	
	public FaceDistance() {
		
	}
	
	public FaceDistance(String oSourceImage,String oDstImage,double oDistance) {
		this.sourceImage = oSourceImage;
		this.dstImage = oDstImage;
		this.distance = oDistance;
	}
}
