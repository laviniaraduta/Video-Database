package queries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActorQuery extends Query {
    private List<List<String>> filters;
    public ActorQuery(int actionId, String actionType, String objectType,
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
    void queryMethod() {

    }
}
