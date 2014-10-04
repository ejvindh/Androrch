package com.example.androrch;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class Androrch extends Activity {

	public static Camera cam = null;// has to be static, otherwise onDestroy() destroys it
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_androrch);
		flashLightOn(getCurrentFocus());		
	}

    @Override
    protected void onPause() {
    	super.onPause();
    	flashLightOff(getCurrentFocus());
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.androrch, menu);
		return true;
	}


	public void flashLightOn(View view) {

	    try {
	        if (getPackageManager().hasSystemFeature(
	                PackageManager.FEATURE_CAMERA_FLASH)) {
	            cam = Camera.open();
	            Parameters p = cam.getParameters();
	            p.setFlashMode(Parameters.FLASH_MODE_TORCH);
	            cam.setParameters(p);
	            cam.startPreview();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        Toast.makeText(getBaseContext(), "Exception flashLightOn()",
	                Toast.LENGTH_SHORT).show();
	    }
	}
	
	public void flashLightOff(View view) {
	    try {
	        if (getPackageManager().hasSystemFeature(
	                PackageManager.FEATURE_CAMERA_FLASH)) {
	            cam.stopPreview();
	            cam.release();
	            cam = null;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        Toast.makeText(getBaseContext(), "Exception flashLightOff",
	                Toast.LENGTH_SHORT).show();
	    }
	}	
}
