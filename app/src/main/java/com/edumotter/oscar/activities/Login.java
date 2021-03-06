package com.edumotter.oscar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.edumotter.oscar.R;
import com.edumotter.oscar.controllers.LoginTask;
import com.edumotter.oscar.models.User;

import org.json.JSONObject;


public class Login extends AppCompatActivity {
    private Intent it;
   EditText editTextUserName, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);

        setContentView(R.layout.activity_login);
    }

    public void onClickSingUp(View view){

        String username = "Eduardo";
        String password = "123";

        User user = new User();
        user.setLogin(username);
        user.setPassword(password);

        String urlRequisition = "https://trabalho-android-oscar-app.herokuapp.com/users/login";
        System.out.println(urlRequisition);

//        LoginTask task = new LoginTask(this, new ProgressDialog(this), user);
//        task.execute(urlRequisition);

        it = new Intent(this, Dashboard.class);
        startActivity(it);
    }
}