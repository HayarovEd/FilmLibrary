package com.edurda77.filmlibrary.data;

import com.edurda77.filmlibrary.domain.Movie;

import java.util.ArrayList;

public class MovieData {
    private String ganreTitle;
    private ArrayList<Movie> allMovieInSection;


    public MovieData() {
    }

    public MovieData(String ganreTitle, ArrayList<Movie> allMovieInSection) {
        this.ganreTitle = ganreTitle;
        this.allMovieInSection = allMovieInSection;
    }

    public String getGanreTitle() {
        return ganreTitle;
    }

    public ArrayList<Movie> getAllMovieInSection() {
        return allMovieInSection;
    }
}
