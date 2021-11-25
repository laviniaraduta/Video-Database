package databases;

import fileio.ActionInputData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.List;
import fileio.Writer;

import commands.Favorite;
import commands.Rating;
import commands.View;

import queries.UserQuery;
import queries.ShowQuery;
import queries.ActorQuery;
import queries.MovieQuery;

import recommendations.SearchRec;
import recommendations.BestUnseenRec;
import recommendations.FavoriteRec;
import recommendations.PopularRec;
import recommendations.StandardRec;

import user.User;

public final  class ActionsDatabase {
    /**
     * @param actions
     * @param ad
     * @param md
     * @param sd
     * @param ud
     * @param writer
     * @param array
     * @throws IOException
     */
    public void addActions(final List<ActionInputData> actions, final ActorDatabase ad,
                           final MovieDatabase md, final SerialDatabase sd, final UserDatabase ud,
                           final Writer writer, final JSONArray array) throws IOException {
        for (ActionInputData a : actions) {
            int id = a.getActionId();
            String actionType = a.getActionType();
            String type = a.getType();
            String message = null;
            if (actionType.equals("command")) {
                String user = a.getUsername();
                String title = a.getTitle();
                Double grade = a.getGrade();
                int season = a.getSeasonNumber();
                if (type.equals("favorite")) {
                    Favorite f =  new Favorite(id, actionType, type, user, title);
                    message = f.commandMethod(ud, md, sd);
                } else if (type.equals("view")) {
                    View v = new View(id, actionType, type, user, title);
                    message = v.commandMethod(ud, md, sd);
                } else if (type.equals("rating")) {
                    Rating r = new Rating(id, actionType, type, user, title, grade, season);
                    message = r.commandMethod(ud, md, sd);
                }
            } else if (actionType.equals("query")) {
                String user = a.getUsername();
                String objectType = a.getObjectType();
                int number = a.getNumber();
                String sortType = a.getSortType();
                String criteria = a.getCriteria();
                String genre = a.getGenre();
                List<List<String>> filters = a.getFilters();
                if (objectType.equals("actors")) {
                    ActorQuery aq = new ActorQuery(id, actionType, objectType, number,
                            user, sortType, criteria, genre, filters);
                    message = aq.queryMethod(ad, ud, md, sd);
                } else if (objectType.equals("movies")) {
                    MovieQuery mq = new MovieQuery(id, actionType, objectType, number,
                            user, sortType, criteria, genre, filters);
                    message = mq.queryMethod(ad, ud, md, sd);
                } else if (objectType.equals("shows")) {
                    ShowQuery sq = new ShowQuery(id, actionType, objectType, number,
                            user, sortType, criteria, genre, filters);
                    message = sq.queryMethod(ad, ud, md, sd);
                } else if (objectType.equals("users")) {
                    UserQuery uq = new UserQuery(id, actionType, objectType, number,
                            user, sortType, criteria);
                    message = uq.queryMethod(ad, ud, md, sd);
                }
            } else if (actionType.equals("recommendation")) {
                String user = a.getUsername();
                String genre = a.getGenre();
                VideoDatabase vd = new VideoDatabase(md, sd, ud);
                if (type.equals("standard")) {
                    StandardRec sr = new StandardRec(id, actionType, type, user);
                    message = sr.recommendationMethod(ud, vd);
                } else if (type.equals("best_unseen")) {
                    BestUnseenRec br = new BestUnseenRec(id, actionType, type, user);
                    message = br.recommendationMethod(ud, vd);
                } else if (type.equals("popular")) {
                    User u = ud.getUserByUsername(user);
                    if (u.getSubscription().equals("PREMIUM")) {
                        PopularRec pr = new PopularRec(id, actionType, type, user);
                        message = pr.recommendationMethod(ud, vd);
                    } else {
                        message = "PopularRecommendation cannot be applied!";
                    }
                } else if (type.equals("favorite")) {
                    User u = ud.getUserByUsername(user);
                    if (u.getSubscription().equals("PREMIUM")) {
                        FavoriteRec fr = new FavoriteRec(id, actionType, type, user);
                        message = fr.recommendationMethod(ud, vd);
                    } else {
                        message = "FavoriteRecommendation cannot be applied!";
                    }
                } else if (type.equals("search")) {
                    User u = ud.getUserByUsername(user);
                    if (u.getSubscription().equals("PREMIUM")) {
                        SearchRec sr = new SearchRec(id, actionType, type, user, genre);
                        message = sr.recommendationMethod(ud, vd);
                    } else {
                        message = "SearchRecommendation cannot be applied!";
                    }
                }
            }
            JSONObject object = writer.writeFile(id, message);
            array.add(object);
        }
    }
}
