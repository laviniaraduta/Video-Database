package recommendations;

import databases.UserDatabase;
import databases.VideoDatabase;
import entertainment.Video;
import user.User;


public final class BestUnseenRec extends Recommendation {

    public BestUnseenRec(final int actionId, final String actionType,
                         final String type, final String username) {
        super(actionId, actionType, type, username);
    }

    @Override
    public String recommendationMethod(final UserDatabase ud, final VideoDatabase vd) {
        String message = "BestRatedUnseenRecommendation ";
        String result = null;
        User user = ud.getUserByUsername(this.getUsername());
        for (Video v : vd.getVideosByRating()) {
            if (!user.getHistory().containsKey(v.getName())) {
                result = v.getName();
                break;
            }
        }
        if (result != null) {
            return message + "result: " + result;
        } else {
            return message + "cannot be applied!";
        }
    }
}
