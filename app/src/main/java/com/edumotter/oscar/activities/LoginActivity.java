package com.edumotter.oscar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edumotter.oscar.R;
import com.edumotter.oscar.models.User;
import com.edumotter.oscar.services.RetrofitConfig;
import com.edumotter.oscar.utils.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private Intent it;
    EditText editTextUsername, editTextPassword;
    TextView textViewError;
    User userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewError = findViewById(R.id.textViewError);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        Session session = (Session) getApplicationContext();
        userSession = session.getUserSession();
    }

    public void onClick(View view){
        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Realizando login");
        progressDialog.show();

        userSession.setLogin("Carlos");
        userSession.setPassword("123");
        try {
            Call<User> call = new RetrofitConfig().getOscarService().login(userSession);
            call.enqueue(new Callback<User>() {

                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.code() >= 200 && response.code() <= 299) {
                        userSession.setId(response.body().getId());
                        userSession.setLogin(response.body().getLogin());
                        userSession.setPassword("****");
                        userSession.setToken(response.body().getToken());
                        if (response.body().getFilm() != null)
                            userSession.setFilm(response.body().getFilm());
                        if (response.body().getDirector() != null)
                            userSession.setDirector(response.body().getDirector());

                        if(userSession.getFilm().getId() != 0 && userSession.getDirector().getId() != 0){
                            progressDialog.dismiss();
                            it = new Intent(LoginActivity.this, VotedActivity.class);
                            startActivity(it);
                            finish();
                        } else{
                            progressDialog.dismiss();
                            it = new Intent(LoginActivity.this, DashboardActivity.class);
                            startActivity(it);
                            finish();
                        }

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Login ou Senha incorreto(s)!", Toast.LENGTH_SHORT).show();


                    }

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                }
            });

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}