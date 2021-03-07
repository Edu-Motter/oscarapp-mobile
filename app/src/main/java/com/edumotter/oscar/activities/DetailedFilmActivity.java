package com.edumotter.oscar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.edumotter.oscar.R;
import com.edumotter.oscar.models.Film;

public class DetailedFilmActivity extends AppCompatActivity {
    protected int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_film);

        position = getIntent().getIntExtra("position", position);
        Film film = FilmsActivity.films.get(position);

        System.out.println(film.getName());

//        TextView textViewFilm = findViewById(R.id.textViewFilm);
//        TextView textViewFilmType = findViewById(R.id.textViewFilmsTitle);
//        ImageView imageViewImage = findViewById(R.id.imageViewDetailImage);

//        textViewFilm.setText(film.getName());
//        textViewFilmType.setText(film.getGenre());
//        textViewState.setText(club.getState());




    }
}