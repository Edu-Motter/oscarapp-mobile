package com.edumotter.oscar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.edumotter.oscar.R;
import com.edumotter.oscar.models.User;
import com.edumotter.oscar.services.RetrofitConfig;
import com.edumotter.oscar.models.Director;
import com.edumotter.oscar.utils.Session;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DirectorsActivity extends AppCompatActivity {
    private List<Director> directors;
    private RadioGroup radioGroup;
    User userSession;
    Director director;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ProgressDialog progressDialog = new ProgressDialog(DirectorsActivity.this);
        radioGroup = findViewById(R.id.radioGroup);
        progressDialog.setMessage("Buscando Diretores..");
        progressDialog.show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directors);

        radioGroup = findViewById(R.id.radioGroup);

        Call<List<Director>> call = new RetrofitConfig().getOscarService().getDirectors();
        call.enqueue((new Callback<List<Director>>() {
            @Override
            public void onResponse(Call<List<Director>> call, Response<List<Director>> response) {
                if (response.isSuccessful()) {
                    directors = response.body();
                    for (Director director : directors) {
                        int directorRadioId = director.getId().intValue();
                        if (directorRadioId != 0) {
                            RadioButton radioButton = new RadioButton(DirectorsActivity.this);
                            radioButton.setId(directorRadioId);
                            radioButton.setText(director.getName());
                            radioGroup.addView(radioButton);
                        }
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Director>> call, Throwable t) {
            }
        }));
    }

    public void onVoteDirectorClick(View view) {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Por favor, selecione um diretor!", Toast.LENGTH_SHORT).show();
        } else {
            Session session = (Session) getApplicationContext();
            userSession = session.getUserSession();

            int voteId = radioGroup.getCheckedRadioButtonId();
            director = directors.get(voteId);
            userSession.setDirector(director);
            Toast.makeText(this, "Parabéns, você acabou de votar no diretor!", Toast.LENGTH_SHORT).show();
        }
    }
}