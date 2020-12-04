import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDatabase {
    private static List<User> userList;
    private static final String fileName = "database.ser";

    public static void load() {

    }

    public static void save() {
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName))){
            writer.writeObject(userList);
            System.out.println("User list saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
