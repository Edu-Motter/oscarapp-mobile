package com.edumotter.oscar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.edumotter.oscar.R;
import com.edumotter.oscar.adapters.FilmAdapter;
import com.edumotter.oscar.services.RetrofitConfig;
import com.edumotter.oscar.models.Film;
import com.edumotter.oscar.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsActivity extends AppCompatActivity {
    public static List<Film> films = new ArrayList<>();
    private RecyclerView recyclerView;
    private FilmAdapter FilmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando filmes..");
        progressDialog.show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);

        Call<List<Film>> call = new RetrofitConfig().getOscarService().getFilms();
        call.enqueue((new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if (response.isSuccessful()) {
                    films = response.body();
                    films.remove(0);
                    FilmAdapter = new FilmAdapter(films);

                    recyclerView = findViewById(R.id.recyclerViewFilms);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.addItemDecoration(new
                            DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
                    recyclerView.setAdapter(FilmAdapter);
                    recyclerView.addOnItemTouchListener(
                            new RecyclerItemClickListener(
                                    getApplicationContext(),
                                    new RecyclerItemClickListener.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {

                                            Intent intent = new Intent(FilmsActivity.this, DetailedFilmActivity.class);
                                            intent.putExtra("position", position);
                                            startActivity(intent);
                                        }

                                    }
                            )
                    );
                } else {
                    System.out.println("Request ERROR: \n" + response.toString());
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
            }
        }));
    }

}