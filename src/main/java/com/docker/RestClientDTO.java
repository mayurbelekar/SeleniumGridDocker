package com.docker;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;

public class RestClientDTO {

	private Header[] reqHeader;
	private Header[] respHeader;
	private StatusLine statusLine;
	private RequestLine requestLine;
	
	public Header[] getReqHeader() {
		return reqHeader;
	}
	public void setReqHeader(Header[] reqHeader) {
		this.reqHeader = reqHeader;
	}
	public Header[] getRespHeader() {
		return respHeader;
	}
	public void setRespHeader(Header[] respHeader) {
		this.respHeader = respHeader;
	}
	public StatusLine getStatusLine() {
		return statusLine;
	}
	public void setStatusLine(StatusLine statusLine) {
		this.statusLine = statusLine;
	}
	public RequestLine getRequestLine() {
		return requestLine;
	}
	public void setRequestLine(RequestLine requestLine) {
		this.requestLine = requestLine;
	}
	public HttpEntity getEntity() {
		return entity;
	}
	public void setEntity(HttpEntity entity) {
		this.entity = entity;
	}
	private HttpEntity entity;
}
