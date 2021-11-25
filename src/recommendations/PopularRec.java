package recommendations;

import databases.UserDatabase;
import databases.VideoDatabase;
import entertainment.Genre;
import entertainment.Video;
import user.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.Utils.stringToGenre;
import static utils.Utils.sortByComparatorGenres;


public final class PopularRec extends Recommendation {

    public PopularRec(final int actionId, final String actionType,
                      final String type, final String username) {
        super(actionId, actionType, type, username);
    }

    @Override
    public String recommendationMethod(final UserDatabase ud, final VideoDatabase vd) {
        List<Genre> genres = List.of(Genre.values());
        Map<Genre, Integer> genresWithViews = new HashMap<>();
        for (Genre genre : genres) {
            int numOfViews = 0;
            for (Video v : vd.getVideoList()) {
                for (String g : v.getGenres()) {
                    Genre gen = stringToGenre(g);
                    if (gen.equals(genre)) {
                        numOfViews += v.getViews();
                    }
                }
            }
            genresWithViews.put(genre, numOfViews);
        }
        User user = ud.getUserByUsername(this.getUsername());
        List<Map.Entry<Genre, Integer>> sortedGenres =
                sortByComparatorGenres(genresWithViews, false);
        for (Map.Entry<Genre, Integer> entry : sortedGenres) {
            Genre genre = entry.getKey();
            for (Video v : vd.getVideoList()) {
                for (String g : v.getGenres()) {
                    Genre gen = stringToGenre(g);
                    if (gen.equals(genre) && !user.getHistory().containsKey(v.getName())) {
                        return "PopularRecommendation result: " + v.getName();
                    }
                }
            }
        }
        return "PopularRecommendation cannot be applied!";
    }
}
