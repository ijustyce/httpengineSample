/**
 * platform: ubuntu 14.04
 * encode: utf-8 
 */
package com.zjseek.net;

import org.gemini.httpengine.listener.OnResponseListener;
import org.gemini.httpengine.net.GMHttpParameters;
import org.gemini.httpengine.net.GMHttpRequest;
import org.gemini.httpengine.net.GMHttpResponse;
import org.gemini.httpengine.net.GMHttpService;

import android.content.Context;

/**
 * @author yc
 *
 */
public class HeLeHui {
	
	GMHttpRequest request;
	GMHttpParameters param;
	GMHttpService httpService;
	GMHttpResponse response;
	OnResponseListener responseListener;
	String result;
	
	public HeLeHui(Context context , OnResponseListener responseListener){
		
		request = new GMHttpRequest(context);
		param = new GMHttpParameters();
		httpService = new GMHttpService();
		
		this.responseListener = responseListener;
	}
	
	public void machineFriends(String url , String id){
		
		param.setParameter("uid", id);
		request.setUri(url);
		request.setOnResponseListener(responseListener);
		request.setHttpParameters(param);
		httpService.executeHttpMethod(request);
	}
}
