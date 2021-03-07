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
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class DetailedFilmActivity extends AppCompatActivity {
    protected int position;
    private Film film;
    User userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_film);
        Session session = (Session) getApplicationContext();
        userSession = session.getUserSession();

        position = getIntent().getIntExtra("position", position);
        film = FilmsActivity.films.get(position);

        TextView textViewFilm = findViewById(R.id.textViewFilmDetailedName);
        TextView textViewFilmType = findViewById(R.id.textViewFilmDetailedGenre);
        ImageView imageViewFilm = findViewById(R.id.imageViewFilmDetailedImage);

        textViewFilm.setText(film.getName());
        textViewFilmType.setText(film.getGenre());
        Picasso.get().load(film.getPhoto())
                .placeholder(R.drawable.loading_film)
                .error(R.drawable.erro_film)
                .fit()
                .noFade()
                .into(imageViewFilm, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });

    }

    public void onVoteFilmClick(View view) {
        userSession.setFilm(film);
        Toast.makeText(this, "Parabéns, você acabou de votar no filme!", Toast.LENGTH_SHORT).show();
    }
}