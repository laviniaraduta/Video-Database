package recommendations;

public class SearchRec extends Recommendation {
    private String genre;
    public SearchRec(int actionId, String actionType, String type, String username, String genre) {
        super(actionId, actionType, type, username);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    void recommendationMethod() {

    }
}
