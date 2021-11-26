package commands;

import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;
import user.User;

import java.util.Map;

public final class Favorite extends Command {
    public Favorite(final int actionId, final String actionType, final String type,
                    final String user, final String title) {
        super(actionId, actionType, type, user, title);
    }

    @Override
    public String commandMethod(final UserDatabase ud, final MovieDatabase md,
                                final SerialDatabase sd) {
        String message;
        User user = ud.getUserByUsername(this.getUser());
        Map<String, Integer> history = user.getHistory();
        if (history.containsKey(this.getTitle())) {
            Boolean isAlreadyFav = false;
            for (String title : user.getFavourite()) {
                if (title.equals(this.getTitle())) {
                    isAlreadyFav = true;
                }
            }
            if (!isAlreadyFav) {
                user.getFavourite().add(this.getTitle());
                message = "success -> " + this.getTitle() + " was added as favourite";
            } else {
                message = "error -> " + this.getTitle() + " is already in favourite list";
            }
        } else {
            message = "error -> " + this.getTitle() + " is not seen";
        }
        return message;
    }

}
