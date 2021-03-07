package com.edumotter.oscar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edumotter.oscar.R;
import com.edumotter.oscar.models.Film;
import com.edumotter.oscar.models.User;
import com.edumotter.oscar.utils.Session;

public class DetailedFilmActivity extends AppCompatActivity {
    protected int position;
    private Film film;
    private User userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_film);
        Session session = (Session) getApplicationContext();
        userSession = session.getUserSession();

        position = getIntent().getIntExtra("position", position);
        Film film = FilmsActivity.films.get(position);


        System.out.println(film.getName());

        TextView textViewFilm = findViewById(R.id.textViewFilm);
        TextView textViewFilmType = findViewById(R.id.textViewType);
//        ImageView imageViewImage = findViewById(R.id.imageViewDetailImage);
//
        textViewFilm.setText(film.getName());
        textViewFilmType.setText(film.getGenre());
//        textViewState.setText(club.getState());

    }

    public void onVoteFilmClick(View view) {
        userSession.setFilm(film);
        Toast.makeText(this, "Parabéns, você acabou de votar no filme!", Toast.LENGTH_SHORT).show();
    }
}