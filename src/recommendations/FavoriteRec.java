package recommendations;

import common.Constants;
import databases.UserDatabase;
import databases.VideoDatabase;
import entertainment.Video;
import user.User;

public final class FavoriteRec extends Recommendation {
    public FavoriteRec(final int actionId, final String actionType,
                       final String type, final String username) {
        super(actionId, actionType, type, username);
    }

    @Override
    public String recommendationMethod(final UserDatabase ud, final VideoDatabase vd) {
        User u = ud.getUserByUsername(this.getUsername());
        if (u.getSubscription().equals(Constants.PREMIUM)) {
            String message = "FavoriteRecommendation ";
            String result = null;
            User user = ud.getUserByUsername(this.getUsername());
            for (Video v : vd.getVideosByLikes()) {
                if (!user.getHistory().containsKey(v.getName()) && v.getLikes() != 0) {
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
        return "FavoriteRecommendation cannot be applied!";
    }
}
