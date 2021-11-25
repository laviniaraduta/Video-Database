package recommendations;

import databases.UserDatabase;
import databases.VideoDatabase;
import entertainment.Video;
import user.User;

import java.util.List;

public class StandardRec extends Recommendation {

    public StandardRec(int actionId, String actionType, String type,
                       String username) {
        super(actionId, actionType, type, username);
    }


    @Override
    public String recommendationMethod(UserDatabase ud, VideoDatabase vd) {
        String message = "StandardRecommendation ";
        String result = null;
        User user = ud.getUserByUsername(this.getUsername());
        for (Video v : vd.getVideoList()) {
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
