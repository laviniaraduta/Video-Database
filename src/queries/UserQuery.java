package queries;

import databases.ActorDatabase;
import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;

public final class UserQuery extends Query {
    public UserQuery(final int actionId, final String actionType, final String objectType,
                     final int number, final String username, final String sortType,
                     final String criteria) {
        super(actionId, actionType, objectType, number, username, sortType, criteria);
    }

    @Override
    public String queryMethod(final ActorDatabase ad, final UserDatabase ud,
                              final MovieDatabase md, final SerialDatabase sd) {
        String message = null;
        return message;
    }
}
