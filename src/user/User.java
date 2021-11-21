package user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private String subscription;
    private Map<String, Integer> history;
    private ArrayList<String> favourite;
    private Map<String, Double> moviesRated = new HashMap<>();
    private Map<String, Map<Integer, Double>> serialsRated = new HashMap<>();

    public User(String username, String subscription, Map<String, Integer> history, ArrayList<String> favourite) {
        this.username = username;
        this.subscription = subscription;
        this.history = history;
        this.favourite = favourite;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public void setHistory(Map<String, Integer> history) {
        this.history = history;
    }

    public ArrayList<String> getFavourite() {
        return favourite;
    }

    public void setFavourite(ArrayList<String> favourite) {
        this.favourite = favourite;
    }

    public Map<String, Double> getMoviesRated() {
        return moviesRated;
    }

    public void setMoviesRated(Map<String, Double> moviesRated) {
        this.moviesRated = moviesRated;
    }

    public Map<String, Map<Integer, Double>> getSerialsRated() {
        return serialsRated;
    }

    public void setSerialsRated(Map<String, Map<Integer, Double>> serialsRated) {
        this.serialsRated = serialsRated;
    }
}
