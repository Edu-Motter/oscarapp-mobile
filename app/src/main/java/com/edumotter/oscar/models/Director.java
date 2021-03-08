package com.edumotter.oscar.models;

public class Director {
    private Long id;
    private String name;

    public Director() {
    }

    public Director(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {return id;}
}
