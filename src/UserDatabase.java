import java.util.List;
import java.util.Optional;

public class UserDatabase {
    private static List<User> userList;
    private static final String fileName = "database.ser";

    public static void load() {

    }

    public static void save() {

    }

    public static void addToUserList(User user) {
        userList.add(user);
        save();
    }

    public static Optional<User> getUser(String userName, String password) {
        return userList.stream()
                .filter(user -> user.getUserName().equals(userName))
                .filter (user -> user.getPassword().equals(password))
                .findFirst();
    }
}
