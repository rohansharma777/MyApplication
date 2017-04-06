package com.beacons.railwayaid.raid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class StartScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
