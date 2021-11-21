package entertainment;

import java.util.ArrayList;

public class Movie extends Video {
    private int duration;
    private Double rating;
    public Movie(String name, int year, int duration, ArrayList<String> genres, ArrayList<String> cast) {
        super(name, year, cast, genres);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
