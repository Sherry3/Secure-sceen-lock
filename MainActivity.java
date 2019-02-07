package com.hrds.magentalockscreen;

import java.util.Random;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{

	DevicePolicyManager deviceManager;
	String pass;
	String input;
	TextView t;
	int x=0,ans,userans;
	Animation anim;
	int must;
	boolean yes=false;
	
	//pass 123
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	try{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
		
		setContentView(R.layout.activity_main);
		
		Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
		
		DBase db=new DBase(getApplicationContext());
		if(db.readFile().equalsIgnoreCase("yes"))
		{
			unlockScreen();
		}
			
		pass="123";
		
		anim= AnimationUtils.loadAnimation(getBaseContext(),R.anim.slide_up);

		x=0;
		ans=0;
		userans=0;
		
		Random r=new Random();
		must=r.nextInt(pass.length())+1;
		
		input=randomisedString();
		draw(input, Color.GREEN);
				
		for(int i=0;i<9;i++)
		{
			Button t=(Button) findViewById(R.id.t1+i);
			t.setOnClickListener(cl);
		}

		}catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), "error",	Toast.LENGTH_SHORT).show();
		}

	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//Performing actions according to various button down events
		if (keyCode == KeyEvent.KEYCODE_HEADSETHOOK)//Button in head phone is pressed
		{	
			x=pass.length()+1;	
			finish();
			return true; 
		}
		else if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			deviceManager = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
			deviceManager.lockNow();
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
//	@Override
//	public void onAttachedToWindow() {
//		// TODO Auto-generated method stub
//
//		super.onAttachedToWindow();
//		
//		try{
//				this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);   
//				Toast.makeText(getApplicationContext(), "error in attach",	Toast.LENGTH_SHORT).show();
//		}catch(Exception e)
//		{
//			Toast.makeText(getApplicationContext(), ""+e.getMessage(),	Toast.LENGTH_SHORT).show();	
//		}
//	}
	
//	@Override
//	public void onWindowFocusChanged(boolean hasFocus) {
//		// TODO Auto-generated method stub
//		
//		if(!hasFocus && x<2)
//		{
//			deviceManager = (DevicePolicyManager)getSystemService(  
//			          Context.DEVICE_POLICY_SERVICE);
//			
//			deviceManager.lockNow();
//		}
//		
//		super.onWindowFocusChanged(hasFocus);
//	}
	
	public void yes(View v) {
		//Instead of using finish(), this totally destroys the process
		
		if(x>=2)
		{
			unlockScreen();
			return;
		}
		
		if(userans==ans)
		{
			x++;
			input=randomisedString();
			draw(input, Color.GREEN);
			userans=0;
		}
		else
		{
			startOver();			
		}
	
	}

	public void no(View v) {
		//Instead of using finish(), this totally destroys the process
		
		userans=0;

	}
	
	public void unlockScreen() {
		new DBase(getApplicationContext()).setYes();
		
		Intent i=new Intent(this, Launchalot.class);
		startActivity(i);
		
		finish();
//		android.os.Process.killProcess(android.os.Process.myPid());
	}
	
	String randomisedString()
	{
		String a="";
		Random r=new Random();
		int y;
		ans=0;
		
		for(int i=0;i<9;i++)
		{
			y=r.nextInt(9);
			
			char tempchar=(char)('1'+y);
			a+=""+tempchar;
			ans+=pass.indexOf(tempchar)+1;	
		}
			
		return a;
	}
	
	void draw(String s, int color)
	{
		int i;
		for(i=0;i<9;i++)
		{
			Button t=(Button) findViewById(R.id.t1+i);
			t.setText(" "+s.charAt(i));
			t.setTextColor(color);
			t.startAnimation(anim);
		}
		
	}
	
	void startOver()
	{
		x=0;
		ans=0;
		userans=0;
		
		Random r=new Random();
		must=r.nextInt(pass.length())+1;
		
		input=randomisedString();
		draw(input, Color.RED);
	}
	
	OnClickListener cl = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			userans+=Integer.parseInt(v.getTag().toString());
		}
		
	};
	 
}
