package com.example.hmm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                {
                    try
                    {
                        sleep(2000);

                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    finally {
                        Intent menuIntent = new Intent(MainActivity.this, LogRegActivity.class);
                        startActivity(menuIntent);
                    }
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }

}