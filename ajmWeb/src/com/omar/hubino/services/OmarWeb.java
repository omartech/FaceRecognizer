package com.omar.hubino.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import sun.misc.BASE64Decoder;

import com.omar.hubino.bo.DataSet;
import com.omar.hubino.bo.FaceDistance;
import com.omar.hubino.bo.OmarResponse;
import com.omar.hubino.bo.OpenBrResponse;
import com.omar.hubino.bo.ServiceResponse;
import com.omar.hubino.model.OmarModel;

public class OmarWeb implements IomarWeb {

	private static final Logger LOGGER = Logger.getLogger(OmarWeb.class);

	@Override
	public ServiceResponse prepareMetadata(DataSet[] dataSet) {

		LOGGER.info("STARTING prepareMetadata");
		

		ServiceResponse response = new ServiceResponse();
		
		if(dataSet != null && dataSet.length > 0) {

			String reqType = "E";
			
			//List<BasicNameValuePair> parameters = null;
	
			response = imageProcess(dataSet, reqType, response);
			
			if (response.getMessage().equals("E00")) {
				LOGGER.info("Enrollment success");
				/*parameters = new ArrayList<BasicNameValuePair>();
				parameters.add(new BasicNameValuePair("CustomerID", dataSet[0].getUserId()));
				parameters.add(new BasicNameValuePair("APIKey", getHashKey()));
				parameters.add(new BasicNameValuePair("EnrollmentStatus", "Enrolled"));
				parameters.add(new BasicNameValuePair("AlgorithmScore", ""));*/
				
				LOGGER.info("Update user status to the client service");
				String[] reqString = new String[4];
				reqString[0] = dataSet[0].getUserId();
				reqString[1] = getHashKey();
				reqString[2] = "Enrolled";
				reqString[3] = "";
				String responseStr = getAuth("http://alpd.net/AjmanLandProperty/index.php/MobileAPI/FaceEnrollmentStatus", reqType, reqString);
				LOGGER.info("After getAuth::"+responseStr);
				String[] result = new String[3];
				try {
				JSONObject data = new JSONObject(responseStr);
				result[0] = data.getString("Status");
				result[1] = data.getString("StatusCode");
				result[2] = data.getString("Message");
				LOGGER.info("1:: "+result[0]+"::2:: "+result[1]+ "::3:: "+result[2]);
				} catch(Exception e) {
					LOGGER.error("Error getAuth::"+e.getMessage());
					e.printStackTrace();
				}
				
				if(result[0].equalsIgnoreCase("Error"))
				{
					LOGGER.info("Result::"+result[1]);
					if(result[1].equalsIgnoreCase("ManualCheckFailed"))
					{
						response.setMessage("Manual checking failed. Please contact land & property department to rectify the issue.");
						response.setResult(true);
					}
					else if(result[1].equalsIgnoreCase("InvalidAPICall"))
					{
						response.setMessage("Invalid API Call"); // No dataset
						response.setResult(true);
					}
					else if(result[1].equalsIgnoreCase("WrongAPIKey"))
					{
						response.setMessage("Wrong API Key"); // No dataset
						response.setResult(true);
					}
				}
				else
				{
					LOGGER.info("After successful Enrollment update to the databse");
				}
			}
			
		} else {

			LOGGER.info("   No dataset ");

			response.setMessage("No dataset"); // No dataset

			response.setResult(true);
		}
		
		return response;
	}

	@Override
	public ServiceResponse validateFace(DataSet[] dataSet) {

		LOGGER.info("STARTING validateFace");

		ServiceResponse response = new ServiceResponse();

		String reqType = "V";
		
		//List<BasicNameValuePair> parameters = null;
		
		if (dataSet != null && dataSet.length > 0) {
			LOGGER.info("Dataset not empty");
			/*parameters = new ArrayList<BasicNameValuePair>();
			parameters.add(new BasicNameValuePair("CustomerID", dataSet[0].getUserId()));
			parameters.add(new BasicNameValuePair("APIKey", getHashKey()));
			parameters.add(new BasicNameValuePair("Action", "Authenticate"));*/
			
			LOGGER.info("Get the Authkey before Validation for ::"+dataSet[0].getUserId());
			
			String[] reqString = new String[3];
			reqString[0] = dataSet[0].getUserId();
			reqString[1] = getHashKey();
			reqString[2] = "Authenticate";
			String responseStr = getAuth("http://alpd.net/AjmanLandProperty/index.php/MobileAPI/FaceAllow", "V", reqString);
			LOGGER.info("After getAuth::"+responseStr);
			String[] result = new String[4];
			try {
			JSONObject data = new JSONObject(responseStr);
			result[0] = data.getString("Status");
			result[1] = data.getString("StatusCode");
			if(result[0].equalsIgnoreCase("success"))
				result[2] = data.getString("FaceAuthKey");
			else
				result[2] = null;
			result[3] = data.getString("Message");
			LOGGER.info("1:: "+result[0]+"::2:: "+result[1]+ "::3:: "+result[2]+ "::4:: "+result[3]);
			} catch(Exception e) {
				LOGGER.error("Error getAuth::"+e.getMessage());
				e.printStackTrace();
			}
			
			if(result[0].equalsIgnoreCase("success"))
			{
				LOGGER.info("User status from client::"+result[0]);
				response = imageProcess(dataSet, reqType, response);
				response.setFaceAuthKey(result[2]);
			}
			else
			{
				LOGGER.info("Failed::"+result[1]);
				if(result[1].equalsIgnoreCase("NotAllowedToAuthenticate")) 
				{
					response.setMessage("You are not allowed to authenticate.  Please contact land & property department to rectify the issue."); // No dataset
					response.setResult(true);
				}
				else if(result[1].equalsIgnoreCase("WrongAPIKey"))
				{
					response.setMessage("Wrong API Key"); // No dataset
					response.setResult(true);
				}
			}
		} else {

			LOGGER.info("   No dataset ");
	
			response.setMessage("No dataset"); // No dataset
	
			response.setResult(true);
		}

		return response;
	}
	
/*	public static String[] parseJSON(String json) {
		String result[] = new String[4];
		try {
			if(json != null)
			{
				JSONObject data = new JSONObject(json);
				System.out.println(data.getString("Status"));
				System.out.println(data.getString("StatusCode"));
				System.out.println(data.getString("FaceAuthKey"));
				System.out.println(data.getString("Message"));
				LOGGER.info("1:: "+data.getString("Status")+"2:: "+data.getString("StatusCode")+ "3:: "+data.getString("FaceAuthKey")+ "4:: "+data.getString("Message"));
				
				result[0] = data.getString("Status");
				result[1] = data.getString("StatusCode");
				result[2] = data.getString("FaceAuthKey");
				result[3] = data.getString("Message");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	*/
	public static String getAuth(String url, String type, String[] reqStrings) {
		StringBuffer responseBody = new StringBuffer();
		try {
			LOGGER.info("STARTING getAuth");
			
	        URL urlValue = null;
	        try {
	        	urlValue = new URL(url);
	        } catch (MalformedURLException ex) {
	        	LOGGER.error("Error1::"+ex.getMessage());
	        }

	        HttpURLConnection urlConn = null;
	        try {
	        	LOGGER.info("STARTING urlConn");
	            // URL connection channel.
	            urlConn = (HttpURLConnection) urlValue.openConnection();
	        } catch (IOException ex) {
	        	LOGGER.error("Error2::"+ex.getMessage());
	        }

	        // Let the run-time system (RTS) know that we want input.
	        urlConn.setDoInput (true);

	        // Let the RTS know that we want to do output.
	        urlConn.setDoOutput (true);

	        // No caching, we want the real thing.
	        urlConn.setUseCaches (false);

	        try {
	            urlConn.setRequestMethod("POST");
	        } catch (ProtocolException ex) {
	        	LOGGER.error("Error3::"+ex.getMessage());
	        }

	        try {
	        	LOGGER.info("urlConn connect");
	            urlConn.connect();
	        } catch (IOException ex) {
	        	LOGGER.error("Error4::"+ex.getMessage());
	        }

	        DataOutputStream output = null;
	        DataInputStream input = null;

	        try {
	        	LOGGER.info("urlConn getOutputStream");
	            output = new DataOutputStream(urlConn.getOutputStream());
	        } catch (IOException ex) {
	        	LOGGER.error("Error5::"+ex.getMessage());
	        }

	        // Specify the content type if needed.
	        //urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

	        // Construct the POST data.
	        String content = null;
	        
	        LOGGER.info("type equals");
	        if(type.equals("E")) {
	        	content = "CustomerID=" + URLEncoder.encode(reqStrings[0]) +
	          "&APIKey=" + URLEncoder.encode(reqStrings[1]) +
	          "&EnrollmentStatus=" + URLEncoder.encode(reqStrings[2]) +
	          "&AlgorithmScore=" + URLEncoder.encode(reqStrings[3]);
	        } else {
	        	content = "CustomerID=" + URLEncoder.encode(reqStrings[0]) +
		          "&APIKey=" + URLEncoder.encode(reqStrings[1]) +
		          "&Action=" + URLEncoder.encode(reqStrings[2]);
	        }
	        
	        /*String content =
	            "name=" + URLEncoder.encode("Greg") +
	            "&email=" + URLEncoder.encode("greg at code dot geek dot sh");*/

	        // Send the request data.
	        try {
	        	LOGGER.info("write output");
	            output.writeBytes(content);
	            output.flush();
	            output.close();
	        } catch (IOException ex) {
	        	LOGGER.error("Error6::"+ex.getMessage());
	        }

	        // Get response data.
	        String str = null;
	        try {
	        	LOGGER.info("output string");
	            input = new DataInputStream (urlConn.getInputStream());
	            while (null != ((str = input.readLine()))) {
	                System.out.println(str);
	                responseBody.append(str);
	            }
	            input.close ();
	        } catch (IOException ex) {
	        	LOGGER.error("Error7::"+ex.getMessage());
	        }
			
			
			/*HttpPost httppost = new HttpPost(url);	
			
			httppost.setEntity(new UrlEncodedFormEntity(parameters));
			
			HttpClient httpclient = new DefaultHttpClient();
			
			LOGGER.info("Before execute httppost");
			HttpResponse httpResponse = httpclient.execute(httppost);
			LOGGER.info("After execute httppost");
			HttpEntity resEntity = httpResponse.getEntity();
			
			// Get the HTTP Status Code
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			
			// Get the contents of the response
			InputStream input = resEntity.getContent();
			responseBody = IOUtils.toString(input);
			input.close();
			
			// Print the response code and message body
			LOGGER.info("HTTP Status Code::"+statusCode);
			LOGGER.info("responseBody::"+responseBody);*/
			
			/*URL urlValue = new URL(url);
			HttpsURLConnection conn = (HttpsURLConnection) urlValue.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			//String jksLocation = "/home/exec/keystore.jsk"; // This line needs to be comment our when move the code to QA/UAT/PROD
			String jksLocation = "/home/exec/keystore.jsk";
			String jksPassword  = "ajmlpd";
			
			System.setProperty("javax.net.ssl.trustStore", jksLocation);
			System.setProperty("javax.net.ssl.trustStorePassword", jksPassword);
			System.setProperty("javax.net.ssl.keyStore", jksLocation);
			System.setProperty("javax.net.ssl.keyStorePassword", jksPassword);
			
			OutputStream os = conn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
			        new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(parameters));
			writer.flush();
			writer.close();
			os.close();

			conn.connect();*/
			
		} catch (Exception e) {
			LOGGER.info("Error:"+e.getMessage());
			e.printStackTrace();
		}
		return responseBody.toString();
	}
	
	/*private static String getQuery(List<BasicNameValuePair> parameters) throws UnsupportedEncodingException
	{
	    StringBuilder result = new StringBuilder();
	    boolean first = true;

	    for (BasicNameValuePair pair : parameters)
	    {
	        if (first)
	            first = false;
	        else
	            result.append("&");

	        result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
	        result.append("=");
	        result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
	    }

	    return result.toString();
	}*/
	
	public static String getHashKey() {
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			FileInputStream fis = new FileInputStream(/*"/home/hubino/Hubino/ark/faceapikey"*/"/etc/ssl/certs/faceapikey");

			byte[] dataBytes = new byte[1024];

			int nread = 0;
			while ((nread = fis.read(dataBytes)) != -1) {
				md.update(dataBytes, 0, nread);
			}
			;
			byte[] mdbytes = md.digest();
			
			// convert the byte to hex format method
			
			for (int i = 0; i < mdbytes.length; i++) {
				hexString.append(Integer.toHexString(0xFF & mdbytes[i]));
			}

			System.out.println("Hex format : " + hexString.toString());
		} catch (Exception e) {
			LOGGER.info("Error getHashKey::"+e.getMessage());
			e.printStackTrace();
		}		
		return hexString.toString();
	}
	
/*	private static DefaultHttpClient httpClientTrustingAllSSLCerts() {
		LOGGER.info("Inside httpClientTrustingAllSSLCerts");
		DefaultHttpClient httpclient = new DefaultHttpClient();
		LOGGER.info("httpclient1::"+httpclient);
		try {
	        SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, getTrustingManager(), new java.security.SecureRandom());
	        LOGGER.info("After getTrustingManager");
	        SSLSocketFactory socketFactory = new SSLSocketFactory(sc);
	        LOGGER.info("After SSLSocketFactory");
	        Scheme sch = new Scheme("https", 443, socketFactory);
	        LOGGER.info("After Scheme");
	        LOGGER.info("httpclient2::"+httpclient);
	        httpclient.getConnectionManager().getSchemeRegistry().register(sch);
	        LOGGER.info("After httpclient.getConnectionManager()");
		} catch (Exception e) {
			 LOGGER.error("Error::"+e.getMessage());
			 e.printStackTrace();
		} finally {
			
		}
	    LOGGER.info("End httpClientTrustingAllSSLCerts");
        return httpclient;
    }

    private static TrustManager[] getTrustingManager() {
    	LOGGER.info("Inside getTrustingManager");
    	TrustManager[] trustAllCerts = null;
    	try {
	        	trustAllCerts = new TrustManager[] { new X509TrustManager() {
	            @Override
	            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                return new X509Certificate[0];
	            }
	
	            @Override
	            public void checkClientTrusted(X509Certificate[] certs, String authType) {
	                // Do nothing
	            }
	
	            @Override
	            public void checkServerTrusted(X509Certificate[] certs, String authType) {
	                // Do nothing
	            }
	
	        } };
    	} catch (Exception e) {
    		LOGGER.error("Error::"+e.getMessage());
		}
        return trustAllCerts;
    }*/

	public ServiceResponse imageProcess(DataSet[] dataSet, String reqType, ServiceResponse response) {
		try {

			OmarResponse omarResponse = OmarModel.checkEnrollment(dataSet[0].getUserId(), reqType);

			if (omarResponse.isResult()) {

				omarResponse = OmarModel.storeImages(dataSet, reqType);

				File checkFile = new File(omarResponse.getImagePath());

				String[] dirFiles = checkFile.list();

				if (dirFiles != null && dirFiles.length > 0) {
					
					OpenBrResponse openBrResponse = null;
					if(reqType.equals("E"))
					{

						LOGGER.info("No of Training Images :: "	+ dirFiles.length);

						openBrResponse = OperBrNativePlugin.getInstance().brEnrollment(omarResponse.getImagePath(), omarResponse.getGalPath());
						
						if (openBrResponse.isResult()) {
							LOGGER.info(" Enrollment success ");
							
							response.setMessage("E00"); // Successfully completed

							response.setResult(true);
						} else {
							LOGGER.info(" Enrollment Failure ");
							
							response.setMessage("Enrollment Failed"); // Failure

							response.setResult(true);
						}
					}
					else
					{
						LOGGER.info("No of AuthenticationImages :: " + dirFiles.length);
						
						openBrResponse = OperBrNativePlugin.getInstance().brAthentication(omarResponse.getImagePath(), omarResponse.getGalPath(), 5, 3);
						
						boolean getStatus = getStatusFromScore(openBrResponse.getDistances());
						
						if (openBrResponse.isResult() && getStatus) {
							LOGGER.info(" Authentication success ");
							
							response.setMessage("E00"); // Successfully completed

							response.setResult(true);
						} else {
							LOGGER.info(" Authentication Failure ");
							
							response.setMessage("Authentication Failed"); // Failure

							response.setResult(true);
						}
					}

					OmarModel.updateImageStatus(openBrResponse,	dataSet[0].getUserId(), reqType, omarResponse.getTrackingId());

					LOGGER.info(" Successfully images updated ");

				} else {

					LOGGER.info("  No Image Found in source ");

					response.setMessage("No Image Found in source"); // No Image Found in source Folder
					response.setResult(true);
				}

			} else {
				LOGGER.info(omarResponse.getMessage());

				response.setMessage(omarResponse.getMessage());

				response.setResult(true);
			}

		} catch (Exception e) {

			response.setMessage("E01"); // Exception

			response.setResult(false);

			LOGGER.error("Error :: " + e.getMessage());

			e.printStackTrace();

		}

		LOGGER.info("Response Message :: " + response.getMessage());
		LOGGER.info("Response Result (bool) :: " + response.isResult());

		LOGGER.info("END");

		return response;
	}

	private boolean getStatusFromScore(FaceDistance[] faceDist) {
		LOGGER.info("Inside getStatusFromScore ");
		int countOne = 0;
		int countZero = 0;
		for(int i=0;i<faceDist.length;i++)
		{
			if(faceDist[i].getDistance() == 1)
				countOne+=1;
			else
				countZero+=1;
		}
		LOGGER.info(" Size : "+ faceDist.length + " : Count Ones : " + countOne + " : Count Zeros :" + countZero);
		System.out.println(" Size : "+ faceDist.length + " : Count Ones : " + countOne + " : Count Zeros :" + countZero);
		if(countOne >= 10)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public ServiceResponse initiateFaceEngine(String[] images, String userId,
			String serviceType) {
		LOGGER.info("initiateFaceEngine Starts");
		DataSet dataSet = null;
		int length = images.length;
		DataSet[] dataSetArray = new DataSet[length];
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			for (int i = 0; i < length; i++) {
				dataSet = new DataSet();
				BASE64Decoder decoder = new BASE64Decoder();
				try {
					dataSet.setData(decoder.decodeBuffer(images[i]));
				} catch (IOException e) {
					LOGGER.error("Error dataSet.setData::"+e.getMessage());
					e.printStackTrace();
				}
				dataSet.setUserId(userId);
				dataSetArray[i] = dataSet;
			}
			LOGGER.info("Before call main service");
			if (serviceType.equals("validateFace"))
				serviceResponse = validateFace(dataSetArray);
			else
				serviceResponse = prepareMetadata(dataSetArray);
			LOGGER.info("initiateFaceEngine end");
		} catch (Exception e) {
			LOGGER.error("initiateFaceEngine Error::"+e.getMessage());
			e.printStackTrace();
		}
		return serviceResponse;
	}
	
	public ServiceResponse customerStatusUpdate(String userId, String userStatus) {
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			serviceResponse = OmarModel.customerStatusUpdate(userId, userStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceResponse;
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * OmarWebService service = new OmarWebService();
	 * service.calculateResult("/home/viji/Documents/SoforOpenBr/FaceFaceRecog.csv"
	 * ); }
	 */

}
