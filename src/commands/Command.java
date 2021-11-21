package commands;

import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;

public abstract class Command {
    private int actionId;
    private String actionType;
    private String type;
    private String user;
    private String title;

    public Command(int actionId, String actionType, String type, String user, String title) {
        this.actionId = actionId;
        this.actionType = actionType;
        this.type = type;
        this.user = user;
        this.title = title;
    }
    public abstract String commandMethod(UserDatabase ud, MovieDatabase md, SerialDatabase sd);

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
