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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

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
	Context context;
	
	public HeLeHui(Context context , OnResponseListener responseListener){
		
		this.context = context;
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
		
		if(isConnected()){
			httpService.executeHttpMethod(request);
		}
		
		else{
			Toast.makeText(context, "please connect to net first", 
					Toast.LENGTH_LONG).show();
		}
	}
	
	public boolean isConnected(){

		ConnectivityManager conManager = (ConnectivityManager) 
				context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
		
		if (networkInfo != null){ 
			return networkInfo.isAvailable();
		}
		return false;
	}
}
