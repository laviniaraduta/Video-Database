package commands;

import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;
import user.User;

import java.util.Map;

public class View extends Command {
    public View(int actionId, String actionType, String type, String user, String title) {
        super(actionId, actionType, type, user, title);
    }

    @Override
    public String commandMethod(UserDatabase ud, MovieDatabase md, SerialDatabase sd) {
        String message;
        User user = ud.getUserByUsername(this.getUser());
        Map<String, Integer> history = user.getHistory();
        if (history.containsKey(this.getTitle())) {
            Integer views = history.get(this.getTitle());
            history.put(this.getTitle(), views++);
        } else {
            history.put(this.getTitle(), 1);
        }
        message = "success -> " + this.getTitle() +
                " was viewed with total views of " +
                history.get(this.getTitle());
        return message;
    }
}
