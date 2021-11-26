package entertainment;

import java.util.ArrayList;

public final class Movie extends Video {
    private int duration;
    private ArrayList<Double> ratings = new ArrayList<Double>();
    public Movie(final String name, final Integer year, final int duration,
                 final ArrayList<String> genres, final ArrayList<String> cast) {
        super(name, year, cast, genres);
        this.duration = duration;
    }

    public Movie(final Movie m) {
        super(m.getName(), m.getYear(), m.getCast(), m.getGenres());
        this.setRating(m.getRating());
        this.ratings = m.getRatings();
        this.duration = m.getDuration();

    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /**
     * Computes the average rating for the movie from the list of ratings
     */
    public void setRating() {
        Double sum = 0d;
        for (Double i : this.ratings) {
            sum = sum + i;
        }
        if (this.ratings.size() == 0) {
            this.setRating(0d);
        } else {
            this.setRating((Double) (sum / this.ratings.size()));
        }
    }
    public ArrayList<Double> getRatings() {
        return ratings;
    }

    public void setRatings(final ArrayList<Double> ratings) {
        this.ratings = ratings;
    }

    /**
     * Adds a rating to the ratings list of the movie
     * @param newRating
     */
    public void addRating(final Double newRating) {
        this.ratings.add(newRating);
    }


}
