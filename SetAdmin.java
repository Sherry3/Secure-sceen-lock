package com.hrds.magentalockscreen;

import android.app.Activity;  
import android.app.ActivityManager;  
import android.app.admin.DevicePolicyManager;  
import android.content.ComponentName;  
import android.content.Context;  
import android.content.Intent;  
import android.os.Bundle;  
import android.util.Log;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.widget.Button; 
import android.widget.Toast;

public class SetAdmin extends Activity implements OnClickListener {  
	 private Button disable;  
	 private Button enable;  
	 static final int RESULT_ENABLE = 1;  
	  
	     DevicePolicyManager deviceManger;  
	     ActivityManager activityManager;  
	     ComponentName compName;  
	       
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	     
	        deviceManger =(DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
			compName = new ComponentName(this, MyAdmin.class);
			 
	        setContentView(R.layout.adminlayout);  
	        
	        disable = (Button)findViewById(R.id.btnDisable);  
	        enable =(Button)findViewById(R.id.btnEnable);  
	        disable.setOnClickListener(this);  
	        enable.setOnClickListener(this);  
	    }  
	    
	    
	    public void onme(View v)
	    {
	    	 deviceManger = (DevicePolicyManager)getSystemService(  
  			          Context.DEVICE_POLICY_SERVICE);
  			
             deviceManger.lockNow();
	    }
	  
	 @Override  
	 public void onClick(View v) {     
	  if(v == enable){  

		  	  
		  provisionOwner();	
		  
	  }  
	    
	  if(v == disable){  
	     deviceManger.removeActiveAdmin(compName);  
	                
	  }    
	  updateButtonStates();
	 }  
	 
	 private void provisionOwner() 
	 {
		 
//		 if(!deviceManger.isAdminActive(compName)) 
//		 {
		 try{
			 Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
			 intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName);
			 startActivityForResult(intent, 1);
			 return;
		 }catch (Exception e) {
			 Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
			// TODO: handle exception
		}
//		 }
//		 else
//		 {
//			 
//			 Toast.makeText(getApplicationContext(), "its lollipop dude :P", Toast.LENGTH_SHORT).show();
//		 }
		 
//		 if (manager.isDeviceOwnerApp(getPackageName()))
//			 manager.setLockTaskPackages(componentName, new String [] {getPackageName()});
		 
	 }
	 
	  
	 private void updateButtonStates() 
	 {  
	    
        boolean active = deviceManger.isAdminActive(compName);  
        if (active) {  
            enable.setEnabled(false);  
            disable.setEnabled(true);  
              
        } else {  
            enable.setEnabled(true);  
            disable.setEnabled(false);  
        }      
	 }  
	   
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	         switch (requestCode) {  
	             case RESULT_ENABLE:  
	                 if (resultCode == Activity.RESULT_OK) {  
	                     Log.i("DeviceAdminSample", "Admin enabled!");
	                     startService(new Intent(this,DisableKeygaurdService.class));
	                     Toast.makeText(getApplicationContext(), "Enabled", Toast.LENGTH_SHORT).show();                    
	                 } else {  
	                     Log.i("DeviceAdminSample", "Admin enable FAILED!");  
	                     Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
	                 }  
	                 return;  
	         }  
	         super.onActivityResult(requestCode, resultCode, data);  
	     }  
	  }
