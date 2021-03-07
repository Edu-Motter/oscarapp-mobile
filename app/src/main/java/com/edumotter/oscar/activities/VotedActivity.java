package com.edumotter.oscar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.edumotter.oscar.R;
import com.edumotter.oscar.utils.Session;

public class VotedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voted);

        Session session = (Session) getApplicationContext();

        System.out.println(session.getUserSession().getLogin());
        System.out.println(session.getUserSession().getDirector().getName());
        System.out.println(session.getUserSession().getFilm().getName());


        TextView textViewFilm = findViewById(R.id.textViewF);
        TextView textViewFilmType = findViewById(R.id.textViewD);

        textViewFilm.setText(session.getUserSession().getFilm().getName());
        textViewFilmType.setText(session.getUserSession().getDirector().getName());

    }

    public void onClickReturnLogin(View view){
        Intent it = new Intent(this, LoginActivity.class);
        startActivity(it);
        finish();

        //Criar toast caso o usuario errar o token:
    }


}