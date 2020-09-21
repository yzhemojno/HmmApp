package com.example.hmm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

public class FlashActivity extends AppCompatActivity {
    ImageButton switchBtnOn, switchBtnOff;

    private CameraManager cameraManager;
    private String camera;
    private AdView mAdView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            camera = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        switchBtnOn = findViewById(R.id.switchBtnOn);
        switchBtnOff = findViewById(R.id.switchBtnOff);

        switchBtnOn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                switchBtnOn.setVisibility(View.GONE);
                switchBtnOff.setVisibility(View.VISIBLE);

                try {
                    cameraManager.setTorchMode(camera, true);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        switchBtnOff.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                switchBtnOff.setVisibility(View.GONE);
                switchBtnOn.setVisibility(View.VISIBLE);

                try {
                    cameraManager.setTorchMode(camera, false);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

    }
}
