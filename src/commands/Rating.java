package commands;

import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;
import entertainment.Movie;
import entertainment.Serial;
import user.User;

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
    // true daca title e film fals daca e serial
    private Boolean isMovie(String title, MovieDatabase md, SerialDatabase sd) {
        for (Movie m : md.getMovies()) {
            if (m.getName().equals(title)) {
                return true;
            }
        }
        for (Serial s : sd.getSerials()) {
            if (s.getName().equals(title)) {
                return false;
            }
        }
        return false;
    }

    @Override
    public String commandMethod(UserDatabase ud, MovieDatabase md, SerialDatabase sd) {
        String message = null;
        User user = ud.getUserByUsername(this.getUser());
        Boolean typeMovie = isMovie(this.getTitle(), md, sd);
        if (user.getHistory().containsKey(this.getTitle()) == true) {
            if(typeMovie == false) {
                if (user.getSerialsRated().containsKey(this.getTitle()) &&
                        user.getSerialsRated().get(this.getTitle()).containsKey(this.season)) {
                        message = "error -> " + this.getTitle() + " has been already rated";
                } else {
                    Serial serial = sd.getSerialByTitle(this.getTitle());
                    serial.getSeasons().get(this.season - 1).getRatings().add(this.rating);
                    serial.setRating();
                    message = "success -> " + getTitle() +
                            " was rated with " + this.rating + " by " + this.getUser();
                }
            } else {
                if (user.getMoviesRated().containsKey(this.getTitle())) {
                    message = "error -> " + this.getTitle() + " has been already rated";
                } else {
                    Movie movie = md.getMovieByTitle(this.getTitle());
                    user.getMoviesRated().put(this.getTitle(), this.rating);
                    movie.addRating(this.rating);
                    movie.setRating();
                    message = "success -> " + getTitle() +
                            " was rated with " + this.rating + " by " + this.getUser();
                }
            }

        } else {
            message = "error -> " + this.getTitle() + " is not seen";
        }
//        System.out.println(message);
        return message;
    }
}
