package com.edumotter.oscar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.edumotter.oscar.R;
import com.edumotter.oscar.RecyclerItemClickListener;
import com.edumotter.oscar.adapters.FilmAdapter;
import com.edumotter.oscar.services.RetrofitConfig;
import com.edumotter.oscar.models.Film;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsActivity extends AppCompatActivity {
    public static List<Film> films =  new ArrayList<>();
    private RecyclerView recyclerView;
    private FilmAdapter FilmAdapter;
    //private com.edumotter.oscar.adapters.FilmsAdapter filmsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);

        recyclerView = findViewById(R.id.recyclerViewFilms);



        Call<List<Film>> call = new RetrofitConfig().getOscarService().getFilms();
        call.enqueue((new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if(response.isSuccessful()) {
                    films = response.body();
                    for (Film film : films)
                    {
                        FilmAdapter = new FilmAdapter(films);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.addItemDecoration(new
                                DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
                        recyclerView.setAdapter(FilmAdapter);
                        System.out.println(film.getName());

                        recyclerView.addOnItemTouchListener(
                                new RecyclerItemClickListener(
                                        getApplicationContext(),
                                        new RecyclerItemClickListener.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(View view, int position) {
                                                Film film = films.get(position);
                                                Intent intent = new Intent(FilmsActivity.this, DetailedFilmActivity.class);
                                                intent.putExtra("position", position);
                                                startActivity(intent);
                                            }

                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                            }
                                        }
                                )
                        );


                    }
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
            }
        }));




    }

}