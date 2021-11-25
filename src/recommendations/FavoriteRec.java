package recommendations;

import databases.UserDatabase;
import databases.VideoDatabase;
import entertainment.Video;
import user.User;

public class FavoriteRec extends Recommendation {
    public FavoriteRec(int actionId, String actionType, String type, String username) {
        super(actionId, actionType, type, username);
    }

    @Override
    public String recommendationMethod(UserDatabase ud, VideoDatabase vd) {
        String message =  "FavoriteRecommendation ";
        String result = null;
        User user = ud.getUserByUsername(this.getUsername());
        for (Video v : vd.getVideosByLikes()) {
            if (!user.getHistory().containsKey(v.getName()) && v.getLikes() != 0){
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
