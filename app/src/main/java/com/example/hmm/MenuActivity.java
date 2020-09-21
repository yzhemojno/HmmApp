package com.example.hmm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button MapsBtn, CamBtn, WebBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        MapsBtn = (Button)findViewById(R.id.MapsBtn);
        CamBtn = (Button)findViewById(R.id.CamBtn);
        WebBtn = (Button)findViewById(R.id.WebBtn);

        MapsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MapsIntent = new Intent( MenuActivity.this, MapsActivity.class);
                startActivity(MapsIntent);
            }
        });

        CamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent FlashIntent = new Intent( MenuActivity.this, FlashActivity.class);
                startActivity(FlashIntent);
            }
        });

        WebBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent WebBtnIntent = new Intent( MenuActivity.this, WebActivity.class);
                startActivity(WebBtnIntent);
            }
        });



    }
}