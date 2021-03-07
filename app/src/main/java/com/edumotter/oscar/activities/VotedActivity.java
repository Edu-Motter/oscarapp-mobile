package com.edumotter.oscar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.edumotter.oscar.R;
import com.edumotter.oscar.utils.Session;

public class VotedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voted);

        Session session = (Session) getApplicationContext();

        System.out.println(session.getUserSession().getLogin());
        System.out.println(session.getUserSession().getDirector());
        System.out.println(session.getUserSession().getFilm());
    }
}