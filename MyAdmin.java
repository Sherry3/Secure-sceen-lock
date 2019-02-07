package com.hrds.magentalockscreen;

import android.app.admin.DeviceAdminReceiver;
import android.content.ComponentName;
import android.content.Context;
  
public class MyAdmin extends DeviceAdminReceiver {

	public static ComponentName getComponentName(Context context) {
		return new ComponentName(context.getApplicationContext(), MyAdmin.class);
		}
		
} 