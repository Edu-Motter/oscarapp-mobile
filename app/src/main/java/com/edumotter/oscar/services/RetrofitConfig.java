package com.edumotter.oscar.services;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
            .baseUrl("https://trabalho-android-oscar-app.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    public OscarService getOscarService() {
        return this.retrofit.create(OscarService.class);
    }
}
