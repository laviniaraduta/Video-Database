package queries;

import databases.ActorDatabase;
import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;
import entertainment.Movie;

import java.util.*;

import static utils.Utils.*;

public final class MovieQuery extends Query {
    private List<List<String>> filters;
    public MovieQuery(final int actionId, final String actionType, final String objectType,
                      final int number, final String username, final String sortType,
                      final String criteria, final String genre, final List<List<String>> filters) {
        super(actionId, actionType, objectType, number, username, sortType, criteria);
        this.filters = filters;
    }

    public List<List<String>> getFilters() {
        return filters;
    }

    public void setFilters(final List<List<String>> filters) {
        this.filters = filters;
    }



    @Override
    public String queryMethod(final ActorDatabase ad, final UserDatabase ud,
                              final MovieDatabase md, final SerialDatabase sd) {
        String message = "Query result: ";
        if (this.getCriteria().equals("ratings")) {
            Map<String, Double> moviesWithRatings = new HashMap<>();
            Integer year;
            boolean condition;
            List<String> genres = this.filters.get(1);
            for (Movie m : md.getMovies()) {
                m.setRating();
                condition = m.getRating() != 0;
                if (this.filters.get(0).get(0) != null) {
                    year = Integer.valueOf(this.filters.get(0).get(0));
                    condition = condition && (m.getYear() == year);
                }
                if (this.filters.get(1).get(0) != null) {
                    condition = condition && m.getGenres().containsAll(this.filters.get(1));
                }
                if (condition) {
                    moviesWithRatings.put(m.getName(), m.getRating());
                }
            }
            List<String> names = formNameList(this.getSortType(),
                    moviesWithRatings, this.getNumber());
            message = message + names;
        } else if (this.getCriteria().equals("favorite")) {

        } else if (this.getCriteria().equals("longest")) {

        } else if (this.getCriteria().equals("most_viewed")) {

        }
        return message;
    }
}
