package com.edumotter.oscar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.edumotter.oscar.R;
import com.edumotter.oscar.services.RetrofitConfig;
import com.edumotter.oscar.models.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private Intent it;
    EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    public void onClick(View view){
        UserLogin userLogin = new UserLogin("Eduardo", "123");

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Realizando login");
        progressDialog.show();

        try {
            Call<UserLogin> call = new RetrofitConfig().getOscarService().login(userLogin);

            call.enqueue(new Callback<UserLogin>() {
                @Override
                public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                    if (response.code() >= 200 && response.code() <= 299) {
                        System.out.println("login");
                    } else {
                        System.out.println("fim on response");
                    }
                }

                @Override
                public void onFailure(Call<UserLogin> call, Throwable t) {
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        progressDialog.dismiss();

        it = new Intent(this, DashboardActivity.class);
        startActivity(it);
        finish();
    }
}