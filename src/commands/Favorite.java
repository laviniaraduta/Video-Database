package commands;

import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;
import user.User;

import java.util.Map;

public class Favorite extends Command {
    public Favorite(int actionId, String actionType, String type, String user, String title) {
        super(actionId, actionType, type, user, title);
    }

    @Override
    public String commandMethod(UserDatabase ud, MovieDatabase md, SerialDatabase sd) {
        String message;
        User user = ud.getUserByUsername(this.getUser());
//        Movie movie = md.getMovieByTitle(this.getTitle());
//        Serial serial = sd.getSerialByTitle(this.getTitle());
        Map<String, Integer> history = user.getHistory();
        if (history.containsKey(this.getTitle())) {
            Boolean isAlreadyFav = false;
            for(String title : user.getFavourite()) {
                if(title.equals(this.getTitle())) {
                    isAlreadyFav = true;
                }
            }
            if(isAlreadyFav == false) {
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