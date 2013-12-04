/**
 * OmerWebSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.omar.hubino.services;

public class OmerWebSoapBindingSkeleton implements com.omar.hubino.services.OmerWeb, org.apache.axis.wsdl.Skeleton {
    private com.omar.hubino.services.OmerWeb impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://services.hubino.omar.com", "dataSet"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://bo.hubino.omar.com", "DataSet"), com.omar.hubino.bo.DataSet[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("prepareMetadata", _params, new javax.xml.namespace.QName("http://services.hubino.omar.com", "prepareMetadataReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://bo.hubino.omar.com", "ServiceResponse"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://services.hubino.omar.com", "prepareMetadata"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("prepareMetadata") == null) {
            _myOperations.put("prepareMetadata", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("prepareMetadata")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://services.hubino.omar.com", "dataSet"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://bo.hubino.omar.com", "DataSet"), com.omar.hubino.bo.DataSet[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("validateFace", _params, new javax.xml.namespace.QName("http://services.hubino.omar.com", "validateFaceReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://bo.hubino.omar.com", "ServiceResponse"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://services.hubino.omar.com", "validateFace"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("validateFace") == null) {
            _myOperations.put("validateFace", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("validateFace")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://services.hubino.omar.com", "images"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://services.hubino.omar.com", "userId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://services.hubino.omar.com", "serviceType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("initiateFaceEngine", _params, new javax.xml.namespace.QName("http://services.hubino.omar.com", "initiateFaceEngineReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://bo.hubino.omar.com", "ServiceResponse"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://services.hubino.omar.com", "initiateFaceEngine"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("initiateFaceEngine") == null) {
            _myOperations.put("initiateFaceEngine", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("initiateFaceEngine")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://services.hubino.omar.com", "userId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://services.hubino.omar.com", "userStatus"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("customerStatusUpdate", _params, new javax.xml.namespace.QName("http://services.hubino.omar.com", "customerStatusUpdateReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://bo.hubino.omar.com", "ServiceResponse"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://services.hubino.omar.com", "customerStatusUpdate"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("customerStatusUpdate") == null) {
            _myOperations.put("customerStatusUpdate", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("customerStatusUpdate")).add(_oper);
    }

    public OmerWebSoapBindingSkeleton() {
        this.impl = new com.omar.hubino.services.OmerWebSoapBindingImpl();
    }

    public OmerWebSoapBindingSkeleton(com.omar.hubino.services.OmerWeb impl) {
        this.impl = impl;
    }
    public com.omar.hubino.bo.ServiceResponse prepareMetadata(com.omar.hubino.bo.DataSet[] dataSet) throws java.rmi.RemoteException
    {
        com.omar.hubino.bo.ServiceResponse ret = impl.prepareMetadata(dataSet);
        return ret;
    }

    public com.omar.hubino.bo.ServiceResponse validateFace(com.omar.hubino.bo.DataSet[] dataSet) throws java.rmi.RemoteException
    {
        com.omar.hubino.bo.ServiceResponse ret = impl.validateFace(dataSet);
        return ret;
    }

    public com.omar.hubino.bo.ServiceResponse initiateFaceEngine(java.lang.String[] images, java.lang.String userId, java.lang.String serviceType) throws java.rmi.RemoteException
    {
        com.omar.hubino.bo.ServiceResponse ret = impl.initiateFaceEngine(images, userId, serviceType);
        return ret;
    }

    public com.omar.hubino.bo.ServiceResponse customerStatusUpdate(java.lang.String userId, java.lang.String userStatus) throws java.rmi.RemoteException
    {
        com.omar.hubino.bo.ServiceResponse ret = impl.customerStatusUpdate(userId, userStatus);
        return ret;
    }

}
