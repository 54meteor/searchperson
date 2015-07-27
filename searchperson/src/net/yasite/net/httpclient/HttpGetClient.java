package net.yasite.net.httpclient;

import net.yasite.util.BaseAPI;

import org.apache.http.client.methods.HttpGet;

public class HttpGetClient extends AHttpClient {
	
	
	public HttpGetClient(BaseAPI param){
		super(param);
		request = new HttpGet(param.getMethod());
		request.addHeader("Pragma", "no-cache");
	    request.addHeader("Cache-Control", "no-cache");
	}

	@Override
	public void doRequest(BaseAPI param)  throws Exception {
		response = httpClient.execute(request);
		excute(response, content, param);
	}

}
