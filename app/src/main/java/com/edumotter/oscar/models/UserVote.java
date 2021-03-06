package com.edumotter.oscar.models;

public class UserVote {
    private Long idUser;
    private Long idFilm;
    private Long idDirector;
    private int token;

    public UserVote(Long idUser, Long idFilm, Long idDirector, int token) {
        this.idUser = idUser;
        this.idFilm = idFilm;
        this.idDirector = idDirector;
        this.token = token;
    }

    public UserVote() {
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(Long idFilm) {
        this.idFilm = idFilm;
    }

    public Long getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(Long idDirector) {
        this.idDirector = idDirector;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }
}
