package queries;

public class UserQuery extends Query {
    public UserQuery(int actionId, String actionType, String objectType, int number, String username, String sortType, String criteria) {
        super(actionId, actionType, objectType, number, username, sortType, criteria);
    }

    @Override
    void queryMethod() {

    }
}
