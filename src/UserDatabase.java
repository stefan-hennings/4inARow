import java.util.List;
import java.util.Optional;

public class UserDatabase {
    private static List<User> userList;
    private static final String fileName = "database.ser";

    public static void load() {

    }

    public static void save() {

    }

    public static User getUser(String username, String password) {
        Optional<User> collect = userList.stream()
                .findFirst();
    }
}
