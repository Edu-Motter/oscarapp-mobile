package com.edumotter.oscar.models;

import android.media.Image;
import android.widget.ImageView;

public class Film {
    private Long id;
    private String name;
    private String genre;
    private String photo;
    private ImageView image;

    public Film(Long id, String name, String genre, String photo, ImageView image) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.photo = photo;
        this.image = image;
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

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
