package recommendations;

import databases.UserDatabase;
import databases.VideoDatabase;
import entertainment.Video;
import user.User;

import java.util.ArrayList;

public class SearchRec extends Recommendation {
    private String genre;
    public SearchRec(int actionId, String actionType, String type, String username, String genre) {
        super(actionId, actionType, type, username);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String recommendationMethod(UserDatabase ud, VideoDatabase vd) {
        String message =  "SearchRecommendation ";
        String result = null;
        ArrayList<String > names = new ArrayList<>();
        User user = ud.getUserByUsername(this.getUsername());
        for (Video v : vd.getVideosByRatingByName()) {
            if (!user.getHistory().containsKey(v.getName()) && v.getGenres().contains(this.genre)){
                names.add(v.getName());
            }
        }
        if (names.size() != 0) {
            return message + "result: " +  names;
        } else {
            return message + "cannot be applied!";
        }
    }
}
