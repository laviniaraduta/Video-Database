package queries;

import databases.ActorDatabase;
import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;
import entertainment.Movie;
import entertainment.Serial;

import java.util.*;

import static utils.Utils.*;

public final class ShowQuery extends Query {
    private List<List<String>> filters;
    public ShowQuery(final int actionId, final String actionType, final String objectType,
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
            Map<String, Double> showsWithRatings = new HashMap<>();
            Integer year;
            List<String> genres = this.filters.get(1);
            for (Serial s : sd.getSerials()) {
                boolean condition;
                s.setRating();
                condition = s.getRating() != 0;
                if (this.filters.get(0).get(0) != null) {
                    year = Integer.valueOf(this.filters.get(0).get(0));
                    condition = condition && (s.getYear() == year);
                }
                if (this.filters.get(1).get(0) != null) {
                    condition = condition && s.getGenres().containsAll(this.filters.get(1));
                }
                if (condition) {
                    showsWithRatings.put(s.getName(), s.getRating());
                }
            }
            List<String> names = formNameList(this.getSortType(),
                    showsWithRatings, this.getNumber());
            message = message + names;
        } else if (this.getCriteria().equals("favorite")) {
            Map<String, Integer> showsWithLikes = new HashMap<>();
            Integer year;

            List<String> genres = this.filters.get(1);
            for (Serial s : sd.getSerials()) {
                boolean condition = true;
                s.setLikes(ud);
                if (s.getLikes() == 0) {
                    condition = false;
                }
                if (this.filters.get(0).get(0) != null) {
                    year = Integer.valueOf(this.filters.get(0).get(0));
                    condition = condition && (s.getYear().equals(year));
                }
                if (this.filters.get(1).get(0) != null) {
                    condition = condition && s.getGenres().containsAll(this.filters.get(1));
                }
                if (condition) {
                    showsWithLikes.put(s.getName(), s.getLikes());
                }
            }
            List<String> names = formNameListInteger(this.getSortType(),
                    showsWithLikes, this.getNumber());
            message = message + names;
//            System.out.println(message);
        } else if (this.getCriteria().equals("longest")) {
            Map<String, Integer> showsWithDuration = new HashMap<>();
            Integer year;
            List<String> genres = this.filters.get(1);
            for (Serial s : sd.getSerials()) {
                boolean condition = true;
                s.setTotalDuration();
                if (this.filters.get(0).get(0) != null) {
                    year = Integer.valueOf(this.filters.get(0).get(0));
                    condition = (s.getYear().equals(year));
                }
                if (this.filters.get(1).get(0) != null) {
                    condition = condition && s.getGenres().containsAll(this.filters.get(1));
                }
                if (condition) {
                    showsWithDuration.put(s.getName(), s.getTotalDuration());
                }
            }
            List<String> names = formNameListInteger(this.getSortType(),
                    showsWithDuration, this.getNumber());
            message = message + names;

        } else if (this.getCriteria().equals("most_viewed")) {
            Map<String, Integer> showsWithViews = new HashMap<>();
            Integer year;
            List<String> genres = this.filters.get(1);
            for (Serial s : sd.getSerials()) {
                boolean condition = true;
                s.setViews(ud);
                if (s.getViews().equals(0)) {
                    condition = false;
                }
                if (this.filters.get(0).get(0) != null) {
                    year = Integer.valueOf(this.filters.get(0).get(0));
                    condition = condition && (s.getYear().equals(year));
                }
                if (this.filters.get(1).get(0) != null) {
                    condition = condition && s.getGenres().containsAll(this.filters.get(1));
                }
                if (condition) {
                    showsWithViews.put(s.getName(), s.getViews());
                }
            }
            List<String> names = formNameListInteger(this.getSortType(),
                    showsWithViews, this.getNumber());
            message = message + names;
        }
        return message;
    }
}
