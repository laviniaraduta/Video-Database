package entertainment;

import java.util.ArrayList;

public abstract class Video {
    private String name;
    private int year;
    private ArrayList<String> cast;
    private ArrayList<String> genres;

    public Video(String name, int year, ArrayList<String> cast, ArrayList<String> genres) {
        this.name = name;
        this.year = year;
        this.genres = genres;
        this.cast = cast;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }
}
