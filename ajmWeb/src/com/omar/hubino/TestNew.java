package com.omar.hubino;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class TestNew {
	public static void main(String args[]) throws ClientProtocolException,
			IOException, KeyManagementException, NoSuchAlgorithmException {
		
		String url = "https://alpd.net/AjmanLandProperty/index.php/MobileAPI/FaceAllow";
		
//		String url = "https://alpd.net/AjmanLandProperty/index.php/MobileAPI/FaceEnrollmentStatus";
		HttpPost httppost = new HttpPost(url);

		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
		parameters.add(new BasicNameValuePair("CustomerID", "1"));
		parameters.add(new BasicNameValuePair("APIKey", getHashKey()/*"4b4742995742b8949018d27eda5a3f2ed9344ce5"*/));
		parameters.add(new BasicNameValuePair("Action", "Authenticate"));
		
		/*parameters.add(new BasicNameValuePair("EnrollmentStatus", "Enrolled"));
		parameters.add(new BasicNameValuePair("AlgorithmScore", ""));*/

		httppost.setEntity(new UrlEncodedFormEntity(parameters));

		HttpClient httpclient = httpClientTrustingAllSSLCerts();
		HttpResponse httpResponse = httpclient.execute(httppost);
		HttpEntity resEntity = httpResponse.getEntity();

		// Get the HTTP Status Code
		int statusCode = httpResponse.getStatusLine().getStatusCode();

		// Get the contents of the response
		InputStream input = resEntity.getContent();
		String responseBody = IOUtils.toString(input);
		input.close();

		// Print the response code and message body
		System.out.println("HTTP Status Code: " + statusCode);
		System.out.println(responseBody);
	}

	public static String getHashKey() {
		StringBuffer hexString = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			FileInputStream fis = new FileInputStream("E://Android//ALDP//faceapikey");

			byte[] dataBytes = new byte[1024];

			int nread = 0;
			while ((nread = fis.read(dataBytes)) != -1) {
				md.update(dataBytes, 0, nread);
			}
			;
			byte[] mdbytes = md.digest();

			// convert the byte to hex format method 1
			
			for (int i = 0; i < mdbytes.length; i++) {
				sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}

			System.out.println("Hex format : " + sb.toString());

			// convert the byte to hex format method 2
			
			for (int i = 0; i < mdbytes.length; i++) {
				hexString.append(Integer.toHexString(0xFF & mdbytes[i]));
			}

			System.out.println("Hex format : " + hexString.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return hexString.toString();
	}
	
	private static DefaultHttpClient httpClientTrustingAllSSLCerts() throws NoSuchAlgorithmException, KeyManagementException {
        DefaultHttpClient httpclient = new DefaultHttpClient();

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, getTrustingManager(), new java.security.SecureRandom());

        SSLSocketFactory socketFactory = new SSLSocketFactory(sc);
        Scheme sch = new Scheme("https", 443, socketFactory);
        httpclient.getConnectionManager().getSchemeRegistry().register(sch);
        return httpclient;
    }

    private static TrustManager[] getTrustingManager() {
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
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
        return trustAllCerts;
    }
}
