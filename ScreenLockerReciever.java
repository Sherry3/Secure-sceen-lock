package com.hrds.magentalockscreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenLockerReciever extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		String action = intent.getAction();
		//If the screen was just turned on or it just booted up, start your Lock Activity
		if(action.equals(Intent.ACTION_SCREEN_OFF) || action.equals(Intent.ACTION_BOOT_COMPLETED))
		{
			new DBase(context).setNo();
			
			Intent i = new Intent(context, MainActivity.class);
			i.addCategory(Intent.CATEGORY_HOME);
			i.addCategory(Intent.CATEGORY_DEFAULT);
			
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);
		}
		
	}

}
