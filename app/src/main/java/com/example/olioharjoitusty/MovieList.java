package com.example.olioharjoitusty;

import java.util.ArrayList;

public class MovieList {

    ArrayList<Movie> movieList;
    int llength;

    public MovieList() {
        movieList = new ArrayList<Movie>();

    }

    public void addMovie(String name, int length, int year,String genre) {
        movieList.add(new Movie(name,length,year,genre));
        llength++;
    }

    public ArrayList getNames() {
        ArrayList names = new ArrayList();
        for (int position = 0; position < llength; position++) {
            String a = movieList.get(position).getMovName();
            names.add(a);
        }
        return names;
    }

    public int getLength(String movie) {
        int length = 0;
        for (int i = 0; i < llength; i++) {
            if (movie.contains(movieList.get(i).getMovName())) {
                length = movieList.get(i).length;
            }
        }
        return length;
    }

    public int getYear(String movie) {
        int year = 0;
        for (int i = 0; i < llength; i++) {
            if (movie.contains(movieList.get(i).getMovName())) {
                year = movieList.get(i).year;
            }
        }
        return year;
    }

    public String  getGenre(String movie) {
        String genre = null;
        for (int i = 0; i < llength; i++) {
            if (movie.contains(movieList.get(i).getMovName())) {
                genre = movieList.get(i).genre;
            }
        }
        return genre;
    }
}


