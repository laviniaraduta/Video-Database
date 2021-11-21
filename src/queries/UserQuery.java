package queries;

import databases.ActorDatabase;
import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;

public class UserQuery extends Query {
    public UserQuery(int actionId, String actionType, String objectType, int number, String username, String sortType, String criteria) {
        super(actionId, actionType, objectType, number, username, sortType, criteria);
    }

    @Override
    public String queryMethod(ActorDatabase ad, UserDatabase ud,
                              MovieDatabase md, SerialDatabase sd) {
        String message = null;
        return message;
    }
}
