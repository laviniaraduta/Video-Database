package recommendations;

import databases.UserDatabase;
import databases.VideoDatabase;

public abstract class Recommendation {
    private int actionId;
    private String actionType;
    private String type;
    private String username;

    public Recommendation(final int actionId, final String actionType,
                          final String type, final String username) {
        this.actionId = actionId;
        this.actionType = actionType;
        this.type = type;
        this.username = username;
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

    public final String getUsername() {
        return username;
    }

    public final void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Method that implements the recommendation action
     * @param ud database containing all the users
     * @param vd database containing all the videos
     * @return
     */
    public abstract String recommendationMethod(UserDatabase ud, VideoDatabase vd);
}
