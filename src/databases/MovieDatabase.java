package databases;

import entertainment.Movie;
import fileio.MovieInputData;

import java.util.ArrayList;
import java.util.List;

public final class MovieDatabase {
    private ArrayList<Movie> movies = new ArrayList<Movie>();

    /**
     * Populates the movie database with movies read from a file
     * @param moviesList list of movies read from the file
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
     * Returns the movie that has the given title from the database
     * @param title the title of the movie
     * @return movie object found, null if not found
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
