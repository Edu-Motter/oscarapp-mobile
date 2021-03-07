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
import com.edumotter.oscar.models.UserVote;
import com.edumotter.oscar.services.RetrofitConfig;
import com.edumotter.oscar.utils.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmVoteActivity extends AppCompatActivity {
    TextView textViewFilmName, textViewDirectorName, textViewConfirmTitle;
    ImageView imageViewConfirmFilm;
    User userSession;
    Button buttonConfirmVote;
    UserVote userVote;

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
        userSession.setFilm(film);

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
    }

    public void onClickConfirm(View view){
        userVote = new UserVote();
        userVote.setIdDirector(userSession.getDirector().getId());
        userVote.setIdFilm(userSession.getFilm().getId());
        userVote.setToken(userSession.getToken());
        userVote.setIdUser(userSession.getId());

        try {
            Call<UserVote> call = new RetrofitConfig().getOscarService().userVote(userVote);
            call.enqueue(new Callback<UserVote>() {

                @Override
                public void onResponse(Call<UserVote> call, Response<UserVote> response) {
                }

                @Override
                public void onFailure(Call<UserVote> call, Throwable t) {
                }
            });

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        //Criar toast caso o usuario errar o token:
    }
}