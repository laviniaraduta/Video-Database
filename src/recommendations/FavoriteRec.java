package recommendations;

public class FavoriteRec extends Recommendation {
    public FavoriteRec(int actionId, String actionType, String type, String username) {
        super(actionId, actionType, type, username);
    }

    @Override
    void recommendationMethod() {

    }
}
