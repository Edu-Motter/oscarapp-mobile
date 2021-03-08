package com.edumotter.oscar.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.edumotter.oscar.R;
import com.edumotter.oscar.models.User;
import com.edumotter.oscar.utils.Session;

public class DashboardActivity extends AppCompatActivity {
    private Intent it;
    ImageView imageViewOscar;
    TextView textViewDashboardToken, textViewDashboardTitle;
    User userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Session session = (Session) getApplicationContext();
        userSession = session.getUserSession();

        imageViewOscar = findViewById(R.id.imageViewFilmDetailedImage);
        imageViewOscar.setImageResource(R.drawable.oscar);

        textViewDashboardToken = findViewById(R.id.textViewFilmDetailedGenre);
        String token = String.valueOf(userSession.getToken());
        textViewDashboardToken.setText("Token recebido: " + token);

        textViewDashboardTitle = findViewById(R.id.textViewFilmDetailedName);
        String userName = String.valueOf(userSession.getLogin());
        textViewDashboardTitle.setText("Bem-vindo ao The Oscar App, " + userName);
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