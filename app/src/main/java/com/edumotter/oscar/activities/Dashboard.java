package com.edumotter.oscar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.edumotter.oscar.R;

public class Dashboard extends AppCompatActivity {
    private Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_dashboard);
    }


    public void onClickVoteMovie(View view){
        it = new Intent(this, VoteMovie.class);
        startActivity(it);
    }

    public void onClickVoteDirect(View view){
        it = new Intent(this, VoteDirect.class);
        startActivity(it);

    }

    public void onClickConfirmVote(View view){
        it = new Intent(this, ConfirmVote.class);
        startActivity(it);

    }

    public void onClickExit(View view){
        finishAndRemoveTask();
        System.exit(0);

    }


}