package queries;

import common.Constants;
import databases.ActorDatabase;
import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;
import entertainment.Movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.Utils.formNameList;
import static utils.Utils.formNameListInteger;

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
        Integer year = null;
        if (this.filters.get(0).get(0) != null) {
            year = Integer.valueOf(this.filters.get(0).get(0));
        }
        if (this.getCriteria().equals(Constants.RATINGS)) {
            Map<String, Double> moviesWithRatings = new HashMap<>();
            for (Movie m : md.getMovies()) {
                boolean condition;
                m.setRating();
                condition = m.getRating() != 0;
                if (this.filters.get(0).get(0) != null) {
                    condition = condition && (m.getYear().equals(year));
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
        } else if (this.getCriteria().equals(Constants.FAVORITE)) {
            Map<String, Integer> moviesWithLikes = new HashMap<>();
            for (Movie m : md.getMovies()) {
                boolean condition = true;
                m.setLikes(ud);
                if (m.getLikes() == 0) {
                    condition = false;
                }
                if (this.filters.get(0).get(0) != null) {
                    condition = condition && (m.getYear().equals(year));
                }
                if (this.filters.get(1).get(0) != null) {
                    condition = condition && m.getGenres().containsAll(this.filters.get(1));
                }
                if (condition) {
                    moviesWithLikes.put(m.getName(), m.getLikes());
                }
            }
            List<String> names = formNameListInteger(this.getSortType(),
                    moviesWithLikes, this.getNumber());
            message = message + names;
        } else if (this.getCriteria().equals(Constants.LONGEST)) {
            Map<String, Integer> moviesWithDuration = new HashMap<>();
            for (Movie m : md.getMovies()) {
                boolean condition = true;
                if (this.filters.get(0).get(0) != null) {
                    condition = (m.getYear().equals(year));
                }
                if (this.filters.get(1).get(0) != null) {
                    condition = condition && m.getGenres().containsAll(this.filters.get(1));
                }
                if (condition) {
                    moviesWithDuration.put(m.getName(), m.getDuration());
                }
            }
            List<String> names = formNameListInteger(this.getSortType(),
                    moviesWithDuration, this.getNumber());
            message = message + names;
        } else if (this.getCriteria().equals(Constants.MOST_VIEWED)) {
            Map<String, Integer> moviesWithViews = new HashMap<>();
            for (Movie m : md.getMovies()) {
                boolean condition = true;
                m.setViews(ud);
                if (m.getViews().equals(0)) {
                    condition = false;
                }
                if (this.filters.get(0).get(0) != null) {
                    condition = condition && (m.getYear().equals(year));
                }
                if (this.filters.get(1).get(0) != null) {
                    condition = condition && m.getGenres().containsAll(this.filters.get(1));
                }
                if (condition) {
                    moviesWithViews.put(m.getName(), m.getViews());
                }
            }
            List<String> names = formNameListInteger(this.getSortType(),
                    moviesWithViews, this.getNumber());
            message = message + names;
        }
        return message;
    }
}
