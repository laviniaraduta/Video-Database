package commands;

import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;

/**
 * The parent class for all the 3 command actions
 */
public abstract class Command {
    private int actionId;
    private String actionType;
    private String type;
    private String user;
    private String title;

    public Command(final int actionId, final String actionType,
                   final String type, final String user, final String title) {
        this.actionId = actionId;
        this.actionType = actionType;
        this.type = type;
        this.user = user;
        this.title = title;
    }
    public final int getActionId() {
        return actionId;
    }

    public final void setActionId(final int actionId) {
        this.actionId = actionId;
    }

    public final String getActionType() {
        return actionType;
    }

    public final void setActionType(final String actionType) {
        this.actionType = actionType;
    }

    public final String getType() {
        return type;
    }

    public final void setType(final String type) {
        this.type = type;
    }

    public final String getUser() {
        return user;
    }

    public final void setUser(final String user) {
        this.user = user;
    }

    public final String getTitle() {
        return title;
    }

    public final void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Method that implements the command's action
     * @param ud the database containing all the users
     * @param md the database containing all the movies
     * @param sd the database containing all the shows
     * @return a string representing the final output of the command
     */
    public abstract String commandMethod(UserDatabase ud, MovieDatabase md, SerialDatabase sd);
}
