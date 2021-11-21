package queries;

public abstract class Query {
    private int actionId;
    private String actionType;
    private String objectType;
    private int number;
    private String username;
    private String sortType;
    private String criteria;

    public Query(int actionId, String actionType, String objectType,
                 int number, String username, String sortType, String criteria) {
        this.actionId = actionId;
        this.actionType = actionType;
        this.objectType = objectType;
        this.number = number;
        this.username = username;
        this.sortType = sortType;
        this.criteria = criteria;
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

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    abstract void queryMethod();
}
