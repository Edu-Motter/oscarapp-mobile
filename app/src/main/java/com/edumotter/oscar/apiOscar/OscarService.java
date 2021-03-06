package com.edumotter.oscar.apiOscar;

import com.edumotter.oscar.models.Film;
import com.edumotter.oscar.models.User;
import com.edumotter.oscar.models.UserLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OscarService {
    @GET("films")
    Call<List<Film>> getFilms();

    @POST("users/login")
    Call<UserLogin> login(@Body UserLogin userLogin);
}
