package recommendations;

public abstract class Recommendation {
    private int actionId;
    private String actionType;
    private String type;
    private String username;

    public Recommendation(int actionId, String actionType, String type, String username) {
        this.actionId = actionId;
        this.actionType = actionType;
        this.type = type;
        this.username = username;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    abstract void recommendationMethod();
}
