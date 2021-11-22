package entertainment;

import java.util.ArrayList;

public class Movie extends Video {
    private int duration;
    private Double rating;
    private ArrayList<Double> ratings = new ArrayList<Double>();
    public Movie(String name, Integer year, int duration, ArrayList<String> genres, ArrayList<String> cast) {
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
    public void addRating(Double rating) {
        this.ratings.add(rating);
    }

    public ArrayList<Double> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Double> ratings) {
        this.ratings = ratings;
    }


}
