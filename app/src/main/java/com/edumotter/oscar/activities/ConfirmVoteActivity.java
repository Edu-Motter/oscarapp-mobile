package com.edumotter.oscar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.edumotter.oscar.R;
import com.edumotter.oscar.models.Director;
import com.edumotter.oscar.models.Film;
import com.edumotter.oscar.models.User;
import com.edumotter.oscar.utils.Session;

public class ConfirmVoteActivity extends AppCompatActivity {
    TextView textViewConfirmError, textViewFilmName, textViewDirectorName;
    ImageView imageViewConfirmFilm;
    EditText editTextConfirmToken;
    User userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_vote);

        imageViewConfirmFilm = findViewById(R.id.imageViewConfirmFilm);
        imageViewConfirmFilm.setImageResource(R.drawable.film);
        textViewFilmName = findViewById(R.id.textViewFilmName);
        textViewDirectorName = findViewById(R.id.textViewDirectorName);


//        Session session = (Session) getApplicationContext();
//        userSession = session.getUserSession();
//
//        Film film = new Film((long) 1,"La La Land", "Musical", "-");
//        Director director = new Director((long) 1,"James Cameron");
//        userSession.setFilm(film);
//        userSession.setDirector(director);
//
//        imageViewConfirmFilm = findViewById(R.id.imageViewConfirmFilm);
//        imageViewConfirmFilm.setImageResource(R.drawable.film);

        textViewFilmName.setText(userSession.getFilm().getName());
        textViewDirectorName.setText(userSession.getDirector().getName());
    }

    public void onClickConfirm(View view){
        Intent it = new Intent(this, LoginActivity.class);
        startActivity(it);
        finish();

        //Criar toast caso o usuario errar o token:
    }
}