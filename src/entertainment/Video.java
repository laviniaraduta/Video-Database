package entertainment;

import databases.UserDatabase;
import user.User;

import java.util.ArrayList;

public class Video {
    private String name;
    private Integer year;
    private ArrayList<String> cast;
    private ArrayList<String> genres;
    private Integer likes;
    private Integer views;
    private Integer indexInVideoList;
    private Double rating;

    public Video(final Video v) {
        this.name = v.name;
        this.year = v.year;
        this.genres = v.genres;
        this.cast = v.cast;
        this.views = v.views;
        this.likes = v.likes;
    }
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

    public final Integer getLikes() {
        return this.likes;
    }

    /**
     * Computes the total number of likes for a video
     * @param ud the users database
     */
    public final void setLikes(final UserDatabase ud) {
        int numOfLikes = 0;
        for (User u : ud.getUsers()) {
            if (u.getFavourite().contains(this.getName())) {
                numOfLikes++;
            }
        }
        this.likes = numOfLikes;
    }

    public final Integer getViews() {
        return this.views;
    }

    /**
     * Computes the total number of views for a video
     * @param ud the users database
     */
    public final void setViews(final UserDatabase ud) {
        int numOfViews = 0;
        for (User u : ud.getUsers()) {
            if (u.getHistory().containsKey(this.getName())) {
                numOfViews += u.getHistory().get(this.getName());
            }
        }
        this.views = numOfViews;
    }

    public final Integer getIndexInVideoList() {
        return indexInVideoList;
    }

    public final void setIndexInVideoList(final Integer indexInVideoList) {
        this.indexInVideoList = indexInVideoList;
    }

    public final Double getRating() {
        return rating;
    }
    public final void setRating(final Double rating) {
        this.rating = rating;
    }
}
