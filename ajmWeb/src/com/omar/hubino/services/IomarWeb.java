package com.omar.hubino.services;

import com.omar.hubino.bo.DataSet;
import com.omar.hubino.bo.ServiceResponse;

public interface IomarWeb {

	public ServiceResponse prepareMetadata(DataSet[] dataSet);
	public ServiceResponse validateFace(DataSet[] dataSet);
	public ServiceResponse initiateFaceEngine(String[] images, String userId, String serviceType);
	
}
