package com.zjseek.helehui;

import java.util.HashMap;
import java.util.Map;

import org.gemini.httpengine.net.GMHttpRequest;
import org.gemini.httpengine.net.GMHttpResponse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends BaseClass{

	private Map<String,String> map;
	private String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		init();
	}
	
	private void init(){
		
		url = "http://www.baidu.com";
		
		map = new HashMap<String,String>();
		map.put("uid", "ijustyce");
		map.put("net", "WiFi");
		map.put("sWidth", "480");
	}
	
	public void btClick(View v){
		
		switch(v.getId()){
		case R.id.get:
			sendGet(map , url);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	@Override
	public void onResponse(GMHttpResponse response, GMHttpRequest request) {
		// TODO Auto-generated method stub
		
		if(!request.isFailed()){
			
			Log.i("---response---", response.parseAsString());
		}
		
		else{
			
			Log.i("---response---", "response failed , perhaps not connected to network");
		}
	}
}
