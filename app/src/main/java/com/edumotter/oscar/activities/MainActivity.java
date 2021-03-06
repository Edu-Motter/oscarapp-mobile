package com.edumotter.oscar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.edumotter.oscar.R;

public class MainActivity extends AppCompatActivity {
    private Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override public void run() {
                showDashboard();
            }
        }, 3000);
    }

    private void showDashboard() {
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        finish();
    }

}
