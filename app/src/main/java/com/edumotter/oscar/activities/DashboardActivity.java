package com.edumotter.oscar.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.edumotter.oscar.R;

public class DashboardActivity extends AppCompatActivity {
    private Intent it;
    ImageView imageViewOscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        imageViewOscar = findViewById(R.id.imageViewOscar);
        imageViewOscar.setImageResource(R.drawable.oscar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.item1:
                it = new Intent(this, FilmsActivity.class);
                startActivity(it);
                return true;

            case R.id.item2:
                it = new Intent(this, DirectorsActivity.class);
                startActivity(it);
                return true;

            case R.id.item3:
                it = new Intent(this, ConfirmVoteActivity.class);
                startActivity(it);
                return true;

            case R.id.item4:
                finishAndRemoveTask();
                System.exit(0);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}