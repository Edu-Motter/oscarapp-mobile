package com.edumotter.oscar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edumotter.oscar.R;
import com.edumotter.oscar.models.User;
import com.edumotter.oscar.models.UserVote;
import com.edumotter.oscar.services.RetrofitConfig;
import com.edumotter.oscar.utils.Session;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmVoteActivity extends AppCompatActivity {
    TextView textViewFilmName, textViewDirectorName, textViewConfirmTitle;
    EditText editTextConfirmToken;
    ImageView imageViewConfirmFilm;
    User userSession;
    Button buttonConfirmVote;
    UserVote userVote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ProgressDialog progressDialogOnCreate = new ProgressDialog(this);
        progressDialogOnCreate.setMessage("Carregando..");
        progressDialogOnCreate.show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_vote);

        editTextConfirmToken = findViewById(R.id.editTextConfirmToken);
        imageViewConfirmFilm = findViewById(R.id.imageViewConfirmFilm);
        textViewConfirmTitle = findViewById(R.id.textViewConfirmTitle);
        textViewFilmName = findViewById(R.id.textViewFilmName);
        textViewDirectorName = findViewById(R.id.textViewDirectorName);
        buttonConfirmVote = findViewById(R.id.buttonConfirmVote);

        Session session = (Session) getApplicationContext();
        userSession = session.getUserSession();

        if (userSession.getFilm().getPhoto().contains("http")) {
            Picasso.get().load(userSession.getFilm().getPhoto())
                    .placeholder(R.drawable.loading_film)
                    .error(R.drawable.erro_film)
                    .fit()
                    .noFade()
                    .into(imageViewConfirmFilm, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });
        } else {
            imageViewConfirmFilm.setImageResource(R.drawable.film);
        }

        textViewFilmName.setText(userSession.getFilm().getName());
        textViewDirectorName.setText(userSession.getDirector().getName());

        if (userSession.getDirector().getId() == 0 && userSession.getFilm().getId() == 0) {
            buttonConfirmVote.setEnabled(false);
            textViewConfirmTitle.setText("Escolha o filme e diretor deseja votar");
        } else if (userSession.getFilm().getId() == 0) {
            buttonConfirmVote.setEnabled(false);
            textViewConfirmTitle.setText("Escolha o filme que deseja votar");
        } else if (userSession.getDirector().getId() == 0) {
            buttonConfirmVote.setEnabled(false);
            textViewConfirmTitle.setText("Escolha o diretor que deseja votar");
        }

        progressDialogOnCreate.dismiss();
    }

    public void onClickConfirm(View view) {
        ProgressDialog progressDialog = new ProgressDialog(ConfirmVoteActivity.this);
        progressDialog.setMessage("Confirmando voto..");
        progressDialog.show();

        String confirmTokenString = editTextConfirmToken.getText().toString();
        int confirmToken = Integer.parseInt(confirmTokenString);

        if (confirmToken == userSession.getToken()) {
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
                        if (response.code() >= 200 && response.code() <= 299) {
                            Intent it = new Intent(ConfirmVoteActivity.this, VotedActivity.class);
                            startActivity(it);

                            progressDialog.dismiss();
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(ConfirmVoteActivity.this, "Não foi possível registrar seu voto!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserVote> call, Throwable t) {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(ConfirmVoteActivity.this, "Token inválido!", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    }
}