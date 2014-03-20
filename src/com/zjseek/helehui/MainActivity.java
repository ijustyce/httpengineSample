package com.zjseek.helehui;

import org.gemini.httpengine.listener.OnResponseListener;
import org.gemini.httpengine.net.GMHttpRequest;
import org.gemini.httpengine.net.GMHttpResponse;

import com.zjseek.net.HeLeHui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends ActionBarActivity 
               implements OnResponseListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	public void btClick(View v){
		
		switch(v.getId()){
		case R.id.get:
			get();
			break;
		default:
			break;
		}
	}
	
	private void get(){
		
		HeLeHui hlh = new HeLeHui(this.getBaseContext() , this);
		hlh.machineFriends("http://www.baidu.com", "ijustyce" , 480,
				800, "WiFi");
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
	public void onResponse(GMHttpResponse arg0, GMHttpRequest arg1) {
		// TODO Auto-generated method stub
		Log.i("---response---", arg0.parseAsString());
	}
}
