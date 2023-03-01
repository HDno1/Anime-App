package dz.phamtuanvan.techshopapp.Model;

import java.util.ArrayList;

public class MovieCategory {
    private String nameCategory;
    private ArrayList<Movie> movies;

    public MovieCategory() {
    }

    public MovieCategory(String nameCategory, ArrayList<Movie> movies) {
        this.nameCategory = nameCategory;
        this.movies = movies;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
