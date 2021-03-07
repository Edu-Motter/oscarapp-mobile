package com.edumotter.oscar.models;

import java.io.Serializable;

public class User implements Serializable {

    private Long id;
    private String login;
    private String password;
    private int token;
    private Director director;
    private Film film;

    public User(){

    }

    public User(Long id, String login, String password, int token, Director director, Film film) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.token = token;
        this.director = director;
        this.film = film;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public String toString(){
        return "{\"login\":"+ login +",\"password\":" + password +"}";
    }

}
