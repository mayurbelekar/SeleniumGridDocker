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
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HTTPClient {
	HttpClient client;
	HttpResponse response;
	
	public HTTPClient() {
		client = new DefaultHttpClient();
	}

	public HttpResponse getRequest(URI uri, List<NameValuePair> headers) {
		HttpUriRequest request = new HttpGet(uri);
		if(headers != null){
			Iterator<NameValuePair> iterator = headers.iterator();
			while (iterator.hasNext()) {
				NameValuePair header = iterator.next();
				request.setHeader(header.getName(), header.getValue());
			}
		}
		try {
			response = client.execute(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		onRequestFail(response);
		return response;
	}
	
	public HttpResponse postRequest(URI uri, List<NameValuePair> headers, String payload) {
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
		try {
			response = client.execute(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		onRequestFail(response);
		return response;
	}
	
	public HttpResponse putRequest(URI uri, List<NameValuePair> headers, String payload) {
		StringEntity entity = new StringEntity(payload, "UTF-8");
		HttpPut httpPut = new HttpPut(uri);
		httpPut.setEntity(entity);
		HttpUriRequest request = httpPut;
		if(headers != null){
			Iterator<NameValuePair> iterator = headers.iterator();
			while (iterator.hasNext()) {
				NameValuePair header = iterator.next();
				request.setHeader(header.getName(), header.getValue());
			}
		}
		try {
			response = client.execute(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		onRequestFail(response);
		return response;
	}
	
	public HttpResponse deleteRequest(URI uri, List<NameValuePair> headers) {
		HttpUriRequest request = new HttpDelete(uri);
		if(headers != null){
			Iterator<NameValuePair> iterator = headers.iterator();
			while (iterator.hasNext()) {
				NameValuePair header = iterator.next();
				request.setHeader(header.getName(), header.getValue());
			}
		}
		try {
			response = client.execute(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		onRequestFail(response);
		return response;
 	}
	
	public URI buildURI(String schema, String host, String extendedUrl, List<NameValuePair> queryParams) throws URISyntaxException {
		if(queryParams != null) {
			return new URIBuilder().setScheme(schema).setHost(host).setPath(extendedUrl).setParameters(queryParams).build();
		} else {
			return new URIBuilder().setScheme(schema).setHost(host).setPath(extendedUrl).build();
		}
	}
	
	public void onRequestFail(HttpResponse response) {
		System.out.println("Response Code: "+ response.getStatusLine());
		if(response.getStatusLine().getStatusCode() >= HttpStatus.SC_BAD_REQUEST) {
			for(int i=0; i<= response.getAllHeaders().length; i++) {
				System.out.println(response.getAllHeaders()[i].getName()+": "+response.getAllHeaders()[i].getValue());
			}
			
		}
	}
	
	public String getResponseBody(HttpResponse response) {
		try {
			 return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public JSONObject getJSONObject(HttpResponse response) {
		String responseBody = getResponseBody(response);
		JSONObject object = new JSONObject(responseBody);
		return object;
	}
}
