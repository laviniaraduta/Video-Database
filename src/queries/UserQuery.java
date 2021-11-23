package queries;

import databases.ActorDatabase;
import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;
import user.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.Utils.formNameList;
import static utils.Utils.formNameListInteger;

public final class UserQuery extends Query {
    public UserQuery(final int actionId, final String actionType, final String objectType,
                     final int number, final String username, final String sortType,
                     final String criteria) {
        super(actionId, actionType, objectType, number, username, sortType, criteria);
    }

    @Override
    public String queryMethod(final ActorDatabase ad, final UserDatabase ud,
                              final MovieDatabase md, final SerialDatabase sd) {
        String message = "Query result: ";
        Map<String, Integer> userWithRatings = new HashMap<>();
        for (User u : ud.getUsers()) {
            u.setNumberOfRatings();
            if (!u.getNumberOfRatings().equals(0)) {
                userWithRatings.put(u.getUsername(), u.getNumberOfRatings());
            }
        }
        List<String> names = formNameListInteger(this.getSortType(),
                userWithRatings, this.getNumber());
        message = message + names;
        return message;
    }
}
