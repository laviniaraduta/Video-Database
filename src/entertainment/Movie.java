package entertainment;

import databases.UserDatabase;
import user.User;

import java.util.ArrayList;

public final class Movie extends Video {
    private int duration;
    private Double rating;
    private ArrayList<Double> ratings = new ArrayList<Double>();
    public Movie(final String name, final Integer year, final int duration,
                 final ArrayList<String> genres, final ArrayList<String> cast) {
        super(name, year, cast, genres);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public Double getRating() {
        return rating;
    }

    /**
     *
     */
    public void setRating() {
        Double sum = 0d;
        for (Double i : this.ratings) {
            sum = sum + i;
        }
        if (this.ratings.size() == 0) {
            this.rating = 0d;
        } else {
            this.rating = (Double) (sum / this.ratings.size());
        }
    }
    public ArrayList<Double> getRatings() {
        return ratings;
    }

    public void setRatings(final ArrayList<Double> ratings) {
        this.ratings = ratings;
    }



    /**
     * @param newRating
     */
    public void addRating(final Double newRating) {
        this.ratings.add(newRating);
    }


}
