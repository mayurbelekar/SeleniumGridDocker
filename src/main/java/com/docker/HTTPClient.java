package com.docker;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HTTPClient {
	RestClientDTO clientData;
	public HTTPClient() {
		clientData = new RestClientDTO();
	}

	public RestClientDTO getRequest(URI uri, List<NameValuePair> headers) {
		HttpGet request = new HttpGet(uri);
		if(headers != null){
			Iterator<NameValuePair> iterator = headers.iterator();
			while (iterator.hasNext()) {
				NameValuePair header = iterator.next();
				request.setHeader(header.getName(), header.getValue());
			}
		}
		clientData.setRequestLine(request.getRequestLine());
		clientData.setReqHeader(request.getAllHeaders());
		try (CloseableHttpClient client = HttpClientBuilder.create().build();
				CloseableHttpResponse response = client.execute(request)){
			
			clientData.setStatusLine(response.getStatusLine());
			clientData.setRespHeader(response.getAllHeaders());
			clientData.setEntity(response.getEntity());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			onRequestFail(clientData);
		} 
		
		return clientData;
	}
	
	public RestClientDTO postRequest(URI uri, List<NameValuePair> headers, String payload) {
		StringEntity entity = new StringEntity(payload, "UTF-8");
		HttpPost post = new HttpPost(uri);
		post.setEntity(entity);
		HttpUriRequest request = post;
		if(headers != null){
			Iterator<NameValuePair> iterator = headers.iterator();
			while (iterator.hasNext()) {
				NameValuePair header = iterator.next();
				request.setHeader(header.getName(), header.getValue());
			}
		}
		clientData.setRequestLine(request.getRequestLine());
		clientData.setReqHeader(request.getAllHeaders());
		
		try (CloseableHttpClient client = HttpClientBuilder.create().build();
				CloseableHttpResponse response = client.execute(request)){
					clientData.setStatusLine(response.getStatusLine());
					clientData.setRespHeader(response.getAllHeaders());
					clientData.setEntity(response.getEntity());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			onRequestFail(clientData);
		}
		return clientData;
	}
	
	public void putRequest() {
		
	}
	
	public void deleteRequest() {
		
	}
	
	public URI buildURI(String schema, String host, String extendedUrl, List<NameValuePair> queryParams) throws URISyntaxException {
		if(queryParams != null) {
			return new URIBuilder().setScheme(schema).setHost(host).setPath(extendedUrl).setParameters(queryParams).build();
		} else {
			return new URIBuilder().setScheme(schema).setHost(host).setPath(extendedUrl).build();
		}
	}
	
	public void onRequestFail(RestClientDTO clientData) {
		System.out.println("Response Code: "+ clientData.getStatusLine().getStatusCode() +" "+ clientData.getStatusLine().getReasonPhrase());
		if(clientData.getStatusLine().getStatusCode() >= HttpStatus.SC_BAD_REQUEST) {
			for(int i=0; i<= clientData.getRespHeader().length; i++) {
				System.out.println(clientData.getRespHeader()[i].getName()+": "+clientData.getRespHeader()[i].getValue());
			}
			
		}
	}
	
	public String getResponseBody(RestClientDTO clientData) {
		try {
			 return EntityUtils.toString(clientData.getEntity(), "UTF-8");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public JSONObject getJSONObject(RestClientDTO clientData) {
		String responseBody = getResponseBody(clientData);
		JSONObject object = new JSONObject(responseBody);
		return object;
	}
}
