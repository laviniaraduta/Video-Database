package recommendations;

import databases.UserDatabase;
import databases.VideoDatabase;
import entertainment.Video;
import user.User;

import java.util.ArrayList;

public final class SearchRec extends Recommendation {
    private String genre;
    public SearchRec(final int actionId, final String actionType, final String type,
                     final String username, final String genre) {
        super(actionId, actionType, type, username);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

    @Override
    public String recommendationMethod(final UserDatabase ud, final VideoDatabase vd) {
        String message =  "SearchRecommendation ";
        ArrayList<String> names = new ArrayList<>();
        User user = ud.getUserByUsername(this.getUsername());
        for (Video v : vd.getVideosByRatingByName()) {
            if (!user.getHistory().containsKey(v.getName())
                    && v.getGenres().contains(this.genre)) {
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
