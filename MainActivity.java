package com.example.flashlightnotifier;

import java.util.logging.Logger;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


  public class MainActivity extends Activity 
   {  
    private boolean isFlashOn = false;                             //Set boolean flag when torch is turned on/off
    private Camera camera;                                         //Create camera object to access flashlight
    private Button button;                                         //Torch button

  @Override
   protected void onCreate(Bundle savedInstanceState) 
    {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
  
       button = (Button) findViewById(R.id.buttonFlashlight);      //Refer the button control
       Context context = this;                                     //Context object to refer context of the application
                                                                   //Retrieve application packages that are currently installed
       PackageManager pm = context.getPackageManager();            //on the device which includes camera, GPS etc.

                if(!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA))     // to check whether device have camera or not
                  {
                     Logger message;
   	                  Log.e("err" , "Device has no camera!");               //Toast a message to let the user know that camera is not installed in the device
   	                  Toast.makeText(getApplicationContext(),
   	                  "Your device doesn't have camera!",Toast.LENGTH_SHORT).show();
                       return;
                  }
   
      camera = Camera.open();                                      // creating a object camera to access the camera
      final Parameters p = camera.getParameters();                 // getting current settings of the camera
      button.setOnClickListener(new OnClickListener()              // creating on click listener 
        {
           public void onClick(View arg0) 
             {
                if (isFlashOn)                                     // to turn on/off logic
                  {
                      Log.i("info", "torch is turned off!");       //Set the flash mode to off      
                      p.setFlashMode(Parameters.FLASH_MODE_OFF);
                      camera.setParameters(p);                     //Pass the parameter ti camera object
                      isFlashOn = false;                           //Set flag to false     
                      button.setText("Torch-ON");                  //Set the button text to Torcn-ON
                  } 
               else                                                //If Flag is set to false
                  {
                      Log.i("info", "torch is turned on!");        //Set the flashmode to on
                      p.setFlashMode(Parameters.FLASH_MODE_TORCH);
                      camera.setParameters(p);                     //Pass the parameter ti camera object
                      isFlashOn = true;                            //Set flag to true
                      button.setText("Torch-OFF");                 //Set the button text to Torcn-OFF
                  }
             }
        });

    }

@Override
 public boolean onCreateOptionsMenu(Menu menu) 
 {
                                                                   // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
 }
    
}
