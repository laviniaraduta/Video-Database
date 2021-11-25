package commands;

import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;
import user.User;

import java.util.Map;

public final class View extends Command {
    public View(final int actionId, final String actionType, final String type,
                final String user, final String title) {
        super(actionId, actionType, type, user, title);
    }

    @Override
    public String commandMethod(final UserDatabase ud, final MovieDatabase md,
                                final SerialDatabase sd) {
        User user = ud.getUserByUsername(this.getUser());
        Map<String, Integer> history = user.getHistory();
        if (history.containsKey(this.getTitle())) {
            Integer views = history.get(this.getTitle()) + 1;
            history.put(this.getTitle(), views);
        } else {
            history.put(this.getTitle(), 1);
        }
        String message = "success -> " + this.getTitle()
                    + " was viewed with total views of "
                    + user.getHistory().get(this.getTitle());

        return message;
    }
}
