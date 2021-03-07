package com.edumotter.oscar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.edumotter.oscar.R;
import com.edumotter.oscar.models.Director;
import com.edumotter.oscar.models.Film;
import com.edumotter.oscar.models.User;
import com.edumotter.oscar.utils.Session;

public class ConfirmVoteActivity extends AppCompatActivity {
    TextView textViewConfirmError, textViewFilmName, textViewDirectorName, textViewConfirmTitle;
    ImageView imageViewConfirmFilm;
    EditText editTextConfirmToken;
    User userSession;
    Button buttonConfirmVote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_vote);

        imageViewConfirmFilm = findViewById(R.id.imageViewConfirmFilm);
        textViewConfirmTitle = findViewById(R.id.textViewConfirmTitle);
        textViewFilmName = findViewById(R.id.textViewFilmName);
        textViewDirectorName = findViewById(R.id.textViewDirectorName);
        buttonConfirmVote = findViewById(R.id.buttonConfirmVote);

        Session session = (Session) getApplicationContext();
        userSession = session.getUserSession();

        Film film = new Film((long) 1,"La La Land", "Musical", "-", null);
        Director director = new Director((long) 1,"James Cameron");
        userSession.setFilm(film);
        //userSession.setDirector(director);

        imageViewConfirmFilm.setImageResource(R.drawable.film);
        textViewFilmName.setText(userSession.getFilm().getName());
        textViewDirectorName.setText(userSession.getDirector().getName());

        if(userSession.getDirector().getId() == 0 && userSession.getFilm().getId() == 0){
            buttonConfirmVote.setEnabled(false);
            textViewConfirmTitle.setText("Escolha o filme e diretor deseja votar");
        } else if (userSession.getFilm().getId() == 0){
            buttonConfirmVote.setEnabled(false);
            textViewConfirmTitle.setText("Escolha o filme que deseja votar");
        } else if (userSession.getDirector().getId() == 0) {
            buttonConfirmVote.setEnabled(false);
            textViewConfirmTitle.setText("Escolha o diretor que deseja votar");
        }

        System.out.println(userSession.getFilm());
    }

    public void onClickConfirm(View view){
        Intent it = new Intent(this, LoginActivity.class);
        startActivity(it);
        finish();

        //Criar toast caso o usuario errar o token:
    }
}