

package com.beacons.railwayaid.raid;




import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.kontakt.sdk.android.ble.connection.OnServiceReadyListener;
import com.kontakt.sdk.android.ble.manager.ProximityManager;
import com.kontakt.sdk.android.ble.manager.ProximityManagerFactory;
import com.kontakt.sdk.android.ble.manager.listeners.EddystoneListener;
import com.kontakt.sdk.android.ble.manager.listeners.simple.SimpleEddystoneListener;
import com.kontakt.sdk.android.common.KontaktSDK;
import com.kontakt.sdk.android.common.profile.IEddystoneDevice;
import com.kontakt.sdk.android.common.profile.IEddystoneNamespace;

import android.support.v4.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.view.View;
import android.support.v4.content.PermissionChecker;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ProximityManager proximityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_tram));
        tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_location_on));
        tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_notifications_active));

        KontaktSDK.initialize("daPXGvgYkMWSgJlXprzrUfgnvYYGBCuG");

        proximityManager = ProximityManagerFactory.create(this);
        proximityManager.setEddystoneListener(createEddystoneListener());
    }

    @Override
    protected void onStart() {
        super.onStart();
        startScanning();
    }

    @Override
    protected void onStop() {
        proximityManager.stopScanning();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        proximityManager.disconnect();
        proximityManager = null;
        super.onDestroy();
    }





    private void startScanning() {
        proximityManager.connect(new OnServiceReadyListener() {
            @Override
            public void onServiceReady() {
                proximityManager.startScanning();
            }
        });
    }



    private EddystoneListener createEddystoneListener() {
        return new SimpleEddystoneListener() {
            @Override
            public void onEddystoneDiscovered(IEddystoneDevice eddystone, IEddystoneNamespace namespace) {
               String BeaconId = eddystone.getUniqueId();
                Intent intent = new Intent();
                PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);
                long when = System.currentTimeMillis();
                String contentTitle = "RAID";
                NotificationManager notificationManager =(NotificationManager) MainActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
                int smallIcon = R.drawable.ic_stat_name;
                if( BeaconId.equals("6Y5a")){

                  Notification.Builder notification = new Notification.Builder(MainActivity.this)
                            .setWhen(when)
                            .setTicker(contentTitle)
                            .setContentTitle(contentTitle)
                            .setContentText("Welcome to Bhavnagar Terminus")
                            .setSmallIcon(smallIcon)
                            .setAutoCancel(true)
                            .setContentIntent(pIntent);

                    notificationManager.notify((int) when, notification.build());

                }
            }
            public void onEddystoneLost(IEddystoneDevice eddystone, IEddystoneNamespace namespace) {
                String BeaconId = eddystone.getUniqueId();
                Intent intent = new Intent();
                PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);
                long when = System.currentTimeMillis();
                String contentTitle = "RAID";
                NotificationManager notificationManager =(NotificationManager) MainActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
                int smallIcon = R.drawable.ic_stat_name;
                if( BeaconId.equals("6Y5a")){

                    Notification.Builder notification = new Notification.Builder(MainActivity.this)
                            .setWhen(when)
                            .setTicker(contentTitle)
                            .setContentTitle(contentTitle)
                            .setContentText("You have now entered the gate")
                            .setSmallIcon(smallIcon)
                            .setAutoCancel(true)
                            .setContentIntent(pIntent);

                    notificationManager.notify((int) when, notification.build());

                }
            }

        };
    }
}
