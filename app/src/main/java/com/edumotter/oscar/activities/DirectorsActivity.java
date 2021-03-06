package com.edumotter.oscar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.edumotter.oscar.R;
import com.edumotter.oscar.services.RetrofitConfig;
import com.edumotter.oscar.models.Director;
import com.edumotter.oscar.models.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DirectorsActivity extends AppCompatActivity {
    private List<Director> directors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directors);

        Call<List<Director>> call = new RetrofitConfig().getOscarService().getDirectors();
        call.enqueue((new Callback<List<Director>>() {
            @Override
            public void onResponse(Call<List<Director>> call, Response<List<Director>> response) {
                if (response.isSuccessful()) {
                    directors = response.body();
                    for (Director director : directors) {
                        System.out.println(director.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Director>> call, Throwable t) {}
        }));
    }
}