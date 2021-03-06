package com.edumotter.oscar.apiOscar;

import com.edumotter.oscar.models.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OscarService {
    @GET("films")
    Call<List<Film>> getFilms();
}
