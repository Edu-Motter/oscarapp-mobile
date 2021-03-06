package com.edumotter.oscar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.edumotter.oscar.R;
import com.edumotter.oscar.models.User;
import com.edumotter.oscar.controllers.LoginTask;


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

        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        User user = new User();
        user.setLogin(username);
        user.setPassword(password);

        String urlRequisition = "https://trabalho-android-oscar-app.herokuapp.com/users/login";
        System.out.println(urlRequisition);

        LoginTask task = new LoginTask(this, new ProgressDialog(this), user);
        task.execute(urlRequisition);

        it = new Intent(this, DashboardActivity.class);
        startActivity(it);
    }
}