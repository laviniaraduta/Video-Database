package queries;

import databases.ActorDatabase;
import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;

public abstract class Query {
    private int actionId;
    private String actionType;
    private String objectType;
    private int number;
    private String username;
    private String sortType;
    private String criteria;

    public Query(final int actionId, final String actionType, final String objectType,
                 final int number, final String username, final String sortType,
                 final String criteria) {
        this.actionId = actionId;
        this.actionType = actionType;
        this.objectType = objectType;
        this.number = number;
        this.username = username;
        this.sortType = sortType;
        this.criteria = criteria;
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

    public final String getObjectType() {
        return objectType;
    }

    public final void setObjectType(final String objectType) {
        this.objectType = objectType;
    }

    public final int getNumber() {
        return number;
    }

    public final void setNumber(final int number) {
        this.number = number;
    }

    public final String getUsername() {
        return username;
    }

    public final void setUsername(final String username) {
        this.username = username;
    }

    public final String getSortType() {
        return sortType;
    }

    public final void setSortType(final String sortType) {
        this.sortType = sortType;
    }

    public final String getCriteria() {
        return criteria;
    }

    public final void setCriteria(final String criteria) {
        this.criteria = criteria;
    }

    /**
     * The method that implements the query action
     * @param ad database containing all actors
     * @param ud database containing all users
     * @param md database containing all movies
     * @param sd database containing all serials
     * @return a string representing the output of the action
     */
    public abstract String queryMethod(ActorDatabase ad, UserDatabase ud,
                                       MovieDatabase md, SerialDatabase sd);
}
