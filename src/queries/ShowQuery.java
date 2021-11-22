package queries;

import databases.ActorDatabase;
import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;
import entertainment.Movie;
import entertainment.Serial;

import java.util.*;

import static utils.Utils.*;

public class ShowQuery extends Query {
    private List<List<String>> filters;
    public ShowQuery(int actionId, String actionType, String objectType,
                      int number, String username, String sortType,
                      String criteria, String genre, List<List<String>> filters) {
        super(actionId, actionType, objectType, number, username, sortType, criteria);
        this.filters = filters;
    }

    public List<List<String>> getFilters() {
        return filters;
    }

    public void setFilters(List<List<String>> filters) {
        this.filters = filters;
    }

    @Override
    public String queryMethod(ActorDatabase ad, UserDatabase ud,
                              MovieDatabase md, SerialDatabase sd) {
        String message = "Query result: ";
        if (this.getCriteria().equals("ratings")) {
            Map<String, Double> showsWithRatings = new HashMap<>();
            Integer year;
            boolean condition;
            List<String> genres = this.filters.get(1);
            for (Serial s : sd.getSerials()) {
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
            List<String> names = formNameList(this.getSortType(), showsWithRatings, this.getNumber());
            message = message + names;
        } else if (this.getCriteria().equals("favorite")) {

        } else if (this.getCriteria().equals("longest")) {

        } else if (this.getCriteria().equals("most_viewed")) {

        }
        return message;
    }
}
