package databases;

import common.Constants;
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

/**
 * Class that creates instances for every action, runs their method
 * and creates the final text that will be written in the result file
 */
public final  class ActionsDatabase {
    /**
     * Decides the type of action and runs the corresponding methods
     * @param actions the list of parsed actions
     * @param ad the database containing all the actors
     * @param md the database containing all the movies
     * @param sd the database containing all the shows
     * @param ud the database containing all the users
     * @param writer object that writes the output to a file
     * @param array an array of JSONObjects
     * @throws IOException
     */
    public void addActions(final List<ActionInputData> actions, final ActorDatabase ad,
                           final MovieDatabase md, final SerialDatabase sd, final UserDatabase ud,
                           final Writer writer, final JSONArray array) throws IOException {
        for (ActionInputData a : actions) {
            String message = null;
            if (a.getActionType().equals(Constants.COMMAND)) {

                if (a.getType().equals(Constants.FAVORITE)) {
                    Favorite f =  new Favorite(a.getActionId(), a.getActionType(),
                            a.getType(), a.getUsername(), a.getTitle());
                    message = f.commandMethod(ud, md, sd);

                } else if (a.getType().equals(Constants.VIEW)) {
                    View v = new View(a.getActionId(), a.getActionType(),
                            a.getType(), a.getUsername(), a.getTitle());
                    message = v.commandMethod(ud, md, sd);

                } else if (a.getType().equals(Constants.RATING)) {
                    Rating r = new Rating(a.getActionId(), a.getActionType(),
                            a.getType(), a.getUsername(), a.getTitle(),
                            a.getGrade(), a.getSeasonNumber());
                    message = r.commandMethod(ud, md, sd);
                }
            } else if (a.getActionType().equals(Constants.QUERY)) {

                if (a.getObjectType().equals(Constants.ACTORS)) {
                    ActorQuery aq = new ActorQuery(a.getActionId(), a.getActionType(),
                            a.getObjectType(), a.getNumber(), a.getUsername(),
                            a.getSortType(), a.getCriteria(), a.getGenre(), a.getFilters());
                    message = aq.queryMethod(ad, ud, md, sd);

                } else if (a.getObjectType().equals(Constants.MOVIES)) {
                    MovieQuery mq = new MovieQuery(a.getActionId(), a.getActionType(),
                            a.getObjectType(), a.getNumber(), a.getUsername(),
                            a.getSortType(), a.getCriteria(), a.getGenre(), a.getFilters());
                    message = mq.queryMethod(ad, ud, md, sd);

                } else if (a.getObjectType().equals(Constants.SHOWS)) {
                    ShowQuery sq = new ShowQuery(a.getActionId(), a.getActionType(),
                            a.getObjectType(), a.getNumber(), a.getUsername(),
                            a.getSortType(), a.getCriteria(), a.getGenre(), a.getFilters());
                    message = sq.queryMethod(ad, ud, md, sd);

                } else if (a.getObjectType().equals(Constants.USERS)) {
                    UserQuery uq = new UserQuery(a.getActionId(), a.getActionType(),
                            a.getObjectType(), a.getNumber(), a.getUsername(),
                            a.getSortType(), a.getCriteria());
                    message = uq.queryMethod(ad, ud, md, sd);
                }
            } else if (a.getActionType().equals(Constants.RECOMMENDATION)) {

                VideoDatabase vd = new VideoDatabase(md, sd, ud);
                if (a.getType().equals(Constants.STANDARD)) {
                    StandardRec sr = new StandardRec(a.getActionId(), a.getActionType(),
                            a.getType(), a.getUsername());
                    message = sr.recommendationMethod(ud, vd);

                } else if (a.getType().equals(Constants.BEST_UNSEEN)) {
                    BestUnseenRec br = new BestUnseenRec(a.getActionId(), a.getActionType(),
                            a.getType(), a.getUsername());
                    message = br.recommendationMethod(ud, vd);

                } else if (a.getType().equals(Constants.POPULAR)) {
                    PopularRec pr = new PopularRec(a.getActionId(), a.getActionType(),
                            a.getType(), a.getUsername());
                    message = pr.recommendationMethod(ud, vd);

                } else if (a.getType().equals(Constants.FAVORITE)) {
                    FavoriteRec fr = new FavoriteRec(a.getActionId(), a.getActionType(),
                            a.getType(), a.getUsername());
                    message = fr.recommendationMethod(ud, vd);

                } else if (a.getType().equals(Constants.SEARCH)) {
                    SearchRec sr = new SearchRec(a.getActionId(), a.getActionType(),
                            a.getType(), a.getUsername(), a.getGenre());
                    message = sr.recommendationMethod(ud, vd);
                }
            }
            JSONObject object = writer.writeFile(a.getActionId(), message);
            array.add(object);
        }
    }
}
