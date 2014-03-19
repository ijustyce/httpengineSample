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
import android.util.Log;

/**
 * @author yc
 *
 */
public class HeLeHui implements OnResponseListener {
	
	GMHttpRequest request;
	GMHttpParameters param;
	GMHttpService httpService;
	GMHttpResponse response;
	String result;
	
	public HeLeHui(Context context){
		
		request = new GMHttpRequest(context);
		param = new GMHttpParameters();
		httpService = new GMHttpService();
	}
	
	public void machineFriends(String url , String id){
		
		param.setParameter("uid", id);
		request.setUri(url);
		request.setOnResponseListener(this);
		request.setHttpParameters(param);
		httpService.executeHttpMethod(request);
	}

	@Override
	public void onResponse(GMHttpResponse arg0, GMHttpRequest arg1) {
		// TODO Auto-generated method stub
		Log.i("---response---", arg0.parseAsString());
	}
}
