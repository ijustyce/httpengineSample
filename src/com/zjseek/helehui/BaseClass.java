/**
 * date:2013-04-20
 * @author yc
 * platform: ubuntu 14.04
 * encode: utf-8
 * base class for activity that need to send a get request,
 * for get data , please override onResponse method.
 */

package com.zjseek.helehui;

import org.gemini.httpengine.listener.OnResponseListener;
import org.gemini.httpengine.net.GMHttpParameters;
import org.gemini.httpengine.net.GMHttpRequest;
import org.gemini.httpengine.net.GMHttpResponse;
import org.gemini.httpengine.net.GMHttpService;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class BaseClass extends Activity implements OnResponseListener{

	private GMHttpRequest httpRequest;
	private GMHttpParameters httpParam;
	private GMHttpService httpService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		init();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		httpRequest = null;
		httpParam = null;
		httpService = null;
	}
	
	/**
	 * init some variable 
	 */
	private void init(){
		
		httpRequest = new GMHttpRequest(this.getBaseContext());
		httpParam = new GMHttpParameters();
		httpService = new GMHttpService();
	}
	
	/**
	 * send a get request
	 */
	public void sendGet(){
		
		httpParam.setIntParameter("" , 123);
		httpRequest.setUri("");
		httpRequest.setOnResponseListener(this);
		httpRequest.setHttpParameters(httpParam);
		
		excute();
	}
	
	/**
	 * excute a http request
	 */
	private void excute(){
		
		if(isConnected()){
			httpService.executeHttpMethod(httpRequest);
		}
		
		else{
			Toast.makeText(this, "please connect to net first", 
					Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * get response data , please override it in your activity
	 */
	public void onResponse(GMHttpResponse response, GMHttpRequest request) {
		
		if(!request.isFailed()){
			
			Log.i("---response---", response.parseAsString());
		}
		
		else{
			
			Log.i("---response---", "response failed , perhaps not connected to network");
		}
	}
	
	/**
	 * is phone connect to network
	 * @return true if connected to network or return false
	 */
	public boolean isConnected(){

		ConnectivityManager conManager = (ConnectivityManager)this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
		
		if (networkInfo != null){ 
			return networkInfo.isAvailable();
		}
		return false;
	}
}
