package databases;

import fileio.UserInputData;
import user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDatabase {
    private ArrayList<User> users = new ArrayList<User>();

    public void addUsers(List<UserInputData> users) {
        for (UserInputData u : users) {
            String username = u.getUsername();
            String subscription = u.getSubscriptionType();
            Map<String, Integer> history = u.getHistory();
            ArrayList<String> favoriteMovies = u.getFavoriteMovies();
            User user = new User(username, subscription, history, favoriteMovies);
            this.users.add(user);
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public User getUserByUsername(String username) {
        for (User u : this.users) {
            if(u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
}
