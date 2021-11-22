package databases;

import entertainment.Movie;
import fileio.MovieInputData;

import java.util.ArrayList;
import java.util.List;

public final class MovieDatabase {
    private ArrayList<Movie> movies = new ArrayList<Movie>();

    /**
     * @param moviesList
     */
    public void addMovies(final List<MovieInputData> moviesList) {
        for (MovieInputData m : moviesList) {
            String title = m.getTitle();
            int year = m.getYear();
            ArrayList<String> cast = m.getCast();
            ArrayList<String> genres = m.getGenres();
            int duration = m.getDuration();
            this.movies.add(new Movie(title, year, duration, genres, cast));
        }
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }

    /**
     * @param title
     * @return
     */
    public Movie getMovieByTitle(final String title) {
        for (Movie m : this.movies) {
            if (m.getName().equals(title)) {
                return m;
            }
        }
        return null;
    }
}
