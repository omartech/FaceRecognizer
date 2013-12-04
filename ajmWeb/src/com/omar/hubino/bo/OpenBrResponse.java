package com.omar.hubino.bo;

public class OpenBrResponse {

	private int errorCode = 0;

	private boolean result = false;

	private String[] faultImagePath;

	private int failCount = 0;

	private FaceDistance[] distances;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String[] getFaultImagePath() {
		return faultImagePath;
	}

	public void setFaultImagePath(String[] faultImagePath) {
		this.faultImagePath = faultImagePath;
	}

	public int getFailCount() {
		return failCount;
	}

	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}

	public OpenBrResponse() {
		// TODO Auto-generated constructor stub
	}

	public OpenBrResponse(int oErrorCode, boolean oResult,
			String[] oFaultImagePath, int oFailCount, FaceDistance[] oDistances) {

		this.errorCode = oErrorCode;
		this.result = oResult;
		this.faultImagePath = oFaultImagePath;
		this.failCount = oFailCount;
		this.distances = oDistances;
	}

	public OpenBrResponse(int oErrorCode, boolean oResult,
			String[] oFaultImagePath, int oFailCount) {

		this.errorCode = oErrorCode;
		this.result = oResult;
		this.faultImagePath = oFaultImagePath;
		this.failCount = oFailCount;
	}

	public FaceDistance[] getDistances() {
		return distances;
	}

	public void setDistances(FaceDistance[] distances) {
		this.distances = distances;
	}

}
