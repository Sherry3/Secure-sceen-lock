package com.hrds.magentalockscreen;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class DisableKeygaurdService extends Service{

	BroadcastReceiver receiver;
	
	@Override
	public IBinder onBind(Intent intent) {
	return null;
	}
	
	@Override
	public void onCreate() {
		addR();
		
	super.onCreate();
	}
	
	@Override
	public void onDestroy() {
	unregisterReceiver(receiver);
	super.onDestroy();
	}
	
	public void addR()
	{
		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
		filter.addAction(Intent.ACTION_BOOT_COMPLETED);
		filter.addAction(Intent.ACTION_SCREEN_ON);
		
		ScreenLockerReciever receiver = new ScreenLockerReciever();
		registerReceiver(receiver, filter);
//		Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();	
	}
	
	
}
