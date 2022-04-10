package com.example.olioharjoitusty;

public class Movie {
    String name;
    int length;
    int year;
    String genre;

    public Movie ( String name, int length, int year,String genre) {
        this.name=name;
        this.length=length;
        this.year=year;
        this.genre=genre;
    }

    public String getMovName() {
        return name;
    }

    public int getLength(){
        return length;
    }

    public int getYear(){
        return year;
    }

    public String getGenre(){
        return genre;
    }

}
