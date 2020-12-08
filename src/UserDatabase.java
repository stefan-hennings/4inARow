import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDatabase {
    private static List<User> userList = new ArrayList<>();
    private static final String fileName = "database.ser";

    @SuppressWarnings("unchecked")
    public static void load() {
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName))){
            userList = (ArrayList<User>)reader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName))){
            writer.writeObject(userList);
            System.out.println("User list saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(User recievedUser) {
        Optional<User> userOptional = userList.stream()
                .filter(user -> user.getUserName().equals(recievedUser.getUserName()))
                .findFirst();
        if (userOptional.isEmpty()) {
            userList.add(recievedUser);
            save();
        } else {
            throw new IllegalArgumentException("Username is already in use");
        }
    }

    public static Optional<User> getUser(String userName, String password) {
        return userList.stream()
                .filter(user -> user.getUserName().equals(userName))
                .filter (user -> user.getPassword().equals(password))
                .findFirst();
    }

    public static List<User> getUserList() {
        return userList;
    }
}
