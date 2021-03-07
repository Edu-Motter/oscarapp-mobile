package com.edumotter.oscar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.edumotter.oscar.R;
import com.edumotter.oscar.models.Film;
import com.edumotter.oscar.models.User;

public class DetailedFilmActivity extends AppCompatActivity {
    protected int position;
    private Film film;
    private User userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_film);

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

    public void onVoteClick(View view) {
        userSession.setFilm(film);

    }
}