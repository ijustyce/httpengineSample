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
	
	/**
	 * machine friends main page
	 * @param url url of machine friends page
	 * @param uid user id
	 * @param width screen width
	 * @param height screen height
	 * @param net network type , WiFi or 3G ?
	 */
	public void machineFriends(String url , String uid , int width , 
			int height , String net){
		
		param.setIntParameter("sWidth", width);
		param.setIntParameter("sHeight", height);
		param.setParameter("uid", uid);
		param.setParameter("network", net);
		
		request.setUri(url);
		request.setOnResponseListener(responseListener);
		request.setHttpParameters(param);
		
		excute();
	}
	
	/**
	 * excute a http request
	 */
	private void excute(){
		
		if(isConnected()){
			httpService.executeHttpMethod(request);
		}
		
		else{
			Toast.makeText(context, "please connect to net first", 
					Toast.LENGTH_LONG).show();
		}
	}
	
	/**
	 * is phone connect to network
	 * @return
	 */
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
