package databases;

import entertainment.Movie;
import fileio.MovieInputData;

import java.util.ArrayList;

public class MovieDatabase {
    private ArrayList<Movie> movies = new ArrayList<Movie>();
//    static int numberOfMovies = 0;
//
//    // fac singleton
//    private static MovieDatabase MoviesDb = null;
//    private MovieDatabase() {}
//    public static MovieDatabase getFilmDb() {
//        if (MoviesDb == null) {
//            MoviesDb = new MovieDatabase();
//        }
//        numberOfMovies++;
//        return MoviesDb;
//    }
//    public static int getNumberOfActors() {
//        return numberOfMovies;
//    }
    public void addMovies(ArrayList<MovieInputData> movies) {
        for (MovieInputData m : movies) {
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

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public Movie getMovieByTitle(String title) {
        for(Movie m : this.movies) {
            if(m.getName().equals(title)) {
                return m;
            }
        }
        return null;
    }
}
