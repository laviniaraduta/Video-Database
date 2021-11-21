package databases;

import commands.Command;
import commands.Favorite;
import commands.Rating;
import commands.View;
import fileio.ActionInputData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import queries.ActorQuery;
import queries.MovieQuery;
import queries.Query;
import queries.UserQuery;
import recommendations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import fileio.Writer;

public class ActionsDatabase {
    ArrayList<Command> commands = new ArrayList<Command>();
    ArrayList<Query> queries = new ArrayList<Query>();
    ArrayList<Recommendation> recommendations = new ArrayList<Recommendation>();
    JSONArray array = new JSONArray();

    public void addActions(ArrayList<ActionInputData> actions, ActorDatabase ad,
                           MovieDatabase md, SerialDatabase sd, UserDatabase ud, String path) throws IOException {
        Writer writer = new Writer(path);
        for (ActionInputData a : actions) {
            int id = a.getActionId();
            String actionType = a.getActionType();
            String type = a.getType();
            if(actionType.equals("command")) {
                String user = a.getUsername();
                String title = a.getTitle();
                Double grade = a.getGrade();
                int season = a.getSeasonNumber();
                if(type.equals("favorite")) {
                    Favorite f =  new Favorite(id, actionType, type, user, title);
                    String message = f.commandMethod(ud, md, sd);
                    JSONObject object = writer.writeFile(f.getActionId(), message);
                    array.add(object);
//                    this.commands.add(f);
                } else if(type.equals("view")) {
                    View v = new View(id, actionType, type, user, title);
                    String message = v.commandMethod(ud, md, sd);
                    JSONObject object = writer.writeFile(v.getActionId(), message);
                    array.add(object);
//                    this.commands.add(v);
                } else if(type.equals("rating")) {
                    Rating r = new Rating(id, actionType, type, user, title, grade, season);
                    r.commandMethod(ud, md, sd);
//                    this.commands.add(r);
                }
            } else if(actionType.equals("query")) {
                String user = a.getUsername();
                String objectType = a.getObjectType();
                int number = a.getNumber();
                String sortType = a.getSortType();
                String criteria = a.getCriteria();
                String genre = a.getGenre();
                List<List<String>> filters = a.getFilters();
                if(objectType.equals("actors")) {
                    this.queries.add(new ActorQuery(id, actionType, objectType, number,
                            user, sortType, criteria, genre, filters));
                } else if(objectType.equals("movies")) {
                    this.queries.add(new MovieQuery(id, actionType, objectType, number, user,
                            sortType, criteria, genre, filters));
                } else if(objectType.equals("users")) {
                    this.queries.add(new UserQuery(id, actionType, objectType, number,
                            user, sortType, criteria));
                }
            } else if(actionType.equals("recommendation")) {
                String user = a.getUsername();
                String genre = a.getGenre();
                if(type.equals("standard")) {
                    this.recommendations.add(new StandardRec(id, actionType, type, user));
                } else if(type.equals("best_unseen")) {
                    this.recommendations.add(new BestUnseenRec(id, actionType, type, user));
                } else if(type.equals("popular")) {
                    this.recommendations.add(new PopularRec(id, actionType, type, user));
                } else if(type.equals("favorite")) {
                    this.recommendations.add(new FavoriteRec(id, actionType, type, user));
                } else if(type.equals("search")) {
                    this.recommendations.add(new SearchRec(id, actionType, type, user, genre));
                }
            }
        }
    }
}
