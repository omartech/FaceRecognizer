/**
 * OmerWebSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.omar.hubino.services;

public class OmerWebSoapBindingImpl implements com.omar.hubino.services.OmerWeb {
	public com.omar.hubino.bo.ServiceResponse prepareMetadata(
			com.omar.hubino.bo.DataSet[] dataSet)
			throws java.rmi.RemoteException {
		OmarWeb service = new OmarWeb();
		return service.prepareMetadata(dataSet);
	}

	public com.omar.hubino.bo.ServiceResponse validateFace(
			com.omar.hubino.bo.DataSet[] dataSet)
			throws java.rmi.RemoteException {
		OmarWeb service = new OmarWeb();
		return service.validateFace(dataSet);
	}

	public com.omar.hubino.bo.ServiceResponse initiateFaceEngine(
			java.lang.String[] images, java.lang.String userId,
			java.lang.String serviceType) throws java.rmi.RemoteException {
		OmarWeb service = new OmarWeb();
		return service.initiateFaceEngine(images, userId, serviceType);
	}

	public com.omar.hubino.bo.ServiceResponse customerStatusUpdate(
			java.lang.String userId, java.lang.String userStatus)
			throws java.rmi.RemoteException {
		OmarWeb service = new OmarWeb();
		return service.customerStatusUpdate(userId, userStatus);
	}

}