package entertainment;

import java.util.ArrayList;

public class Video {
    private String name;
    private Integer year;
    private ArrayList<String> cast;
    private ArrayList<String> genres;

    public Video(final String name, final int year,
                 final ArrayList<String> cast, final ArrayList<String> genres) {
        this.name = name;
        this.year = year;
        this.genres = genres;
        this.cast = cast;
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final Integer getYear() {
        return year;
    }

    public final void setYear(final int year) {
        this.year = year;
    }

    public final ArrayList<String> getCast() {
        return cast;
    }

    public final void setCast(final ArrayList<String> cast) {
        this.cast = cast;
    }

    public final ArrayList<String> getGenres() {
        return genres;
    }

    public final void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }
}
