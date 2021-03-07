package com.edumotter.oscar.services;

import com.edumotter.oscar.models.Director;
import com.edumotter.oscar.models.Film;
import com.edumotter.oscar.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OscarService {
    @GET("directors")
    Call<List<Director>> getDirectors();

    @GET("films")
    Call<List<Film>> getFilms();

    @POST("users/login")
    Call<User> login(@Body User user);


}
