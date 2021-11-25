package recommendations;

import databases.UserDatabase;
import databases.VideoDatabase;

public class PopularRec extends Recommendation {

    public PopularRec(int actionId, String actionType, String type, String username) {
        super(actionId, actionType, type, username);
    }

    @Override
    public String recommendationMethod(UserDatabase ud, VideoDatabase vd) {
        return null;

    }
}
