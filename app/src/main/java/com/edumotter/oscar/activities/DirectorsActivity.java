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
    private RadioButton radio_director_one, radio_director_two, radio_director_three, radio_director_four;
    User userSession;
    Director director;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ProgressDialog progressDialog = new ProgressDialog(DirectorsActivity.this);
        progressDialog.setMessage("Buscando Diretores..");
        progressDialog.show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directors);

        radioGroup = findViewById(R.id.radioGroup);
        radio_director_one = findViewById(R.id.radio_director_one);
        radio_director_two = findViewById(R.id.radio_director_two);
        radio_director_three = findViewById(R.id.radio_director_three);
        radio_director_four = findViewById(R.id.radio_director_four);

        Call<List<Director>> call = new RetrofitConfig().getOscarService().getDirectors();
        call.enqueue((new Callback<List<Director>>() {
            @Override
            public void onResponse(Call<List<Director>> call, Response<List<Director>> response) {
                if (response.isSuccessful()) {
                    directors = response.body();
                    for (Director director : directors) {
                        int i = director.getId().intValue();
                        switch (i) {
                            case 1:
                                radio_director_one.setText(director.getName());
                                break;
                            case 2:
                                radio_director_two.setText(director.getName());
                                break;
                            case 3:
                                radio_director_three.setText(director.getName());
                                break;
                            case 4:
                                radio_director_four.setText(director.getName());
                                break;
                            default:
                                radio_director_one.setText("Não encontrado");
                                break;
                        }
                    }
                } else {
                    System.out.println("Request ERROR: \n" + response.toString());
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

            int radioButtonID = radioGroup.getCheckedRadioButtonId();
            View radioButton = radioGroup.findViewById(radioButtonID);
            int idx = radioGroup.indexOfChild(radioButton);
            director = directors.get(idx + 1);
            userSession.setDirector(director);
            Toast.makeText(this, "Parabéns, você acabou de votar no diretor!", Toast.LENGTH_SHORT).show();
        }
    }
}