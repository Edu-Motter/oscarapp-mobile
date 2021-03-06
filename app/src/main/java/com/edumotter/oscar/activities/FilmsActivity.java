package com.edumotter.oscar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.edumotter.oscar.R;
import com.edumotter.oscar.services.RetrofitConfig;
import com.edumotter.oscar.models.Director;
import com.edumotter.oscar.models.Film;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsActivity extends AppCompatActivity {
    private List<Film> films =  new ArrayList<>();
    private RecyclerView recyclerView;
    //private com.edumotter.oscar.adapters.FilmsAdapter filmsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);

        Call<List<Film>> call = new RetrofitConfig().getOscarService().getFilms();
        call.enqueue((new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if(response.isSuccessful()) {
                    films = response.body();
                    for (Film film : films)
                    {
                        System.out.println(film.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
            }
        }));
    }
}