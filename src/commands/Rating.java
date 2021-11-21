package commands;

import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;

public class Rating extends Command {
    private Double rating;
    private int season;
    public Rating(int actionId, String actionType, String type,
                  String user, String title, Double rating, int season) {
        super(actionId, actionType, type, user, title);
        this.rating = rating;
        this.season = season;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    @Override
    public String commandMethod(UserDatabase ud, MovieDatabase md, SerialDatabase sd) {
        String message = null;
        return message;
    }
}
