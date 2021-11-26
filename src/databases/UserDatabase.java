package databases;

import fileio.UserInputData;
import user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class UserDatabase {
    private ArrayList<User> users = new ArrayList<User>();

    /**
     * Populates the user database with users read from a file
     * @param usersList the list of users read from the file
     */
    public void addUsers(final List<UserInputData> usersList) {
        for (UserInputData u : usersList) {
            String username = u.getUsername();
            String subscription = u.getSubscriptionType();
            Map<String, Integer> history = u.getHistory();
            ArrayList<String> favoriteMovies = u.getFavoriteMovies();
            this.users.add(new User(username, subscription, history, favoriteMovies));
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(final ArrayList<User> users) {
        this.users = users;
    }

    /**
     * Returns the user with the username given from the database
     * @param username the username
     * @return the user found, null otherwise
     */
    public User getUserByUsername(final String username) {
        for (User u : this.users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
}
