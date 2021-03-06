package com.edumotter.oscar.models;

public class Film {
    private Long id;
    private String name;
    private String genre;
    private String photo;

    public Film(Long id, String name, String genre, String photo) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
