package queries;

import databases.ActorDatabase;
import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieQuery extends Query {
    private List<List<String>> filters;
    public MovieQuery(int actionId, String actionType, String objectType,
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
        String message = null;
        return message;
    }
}
