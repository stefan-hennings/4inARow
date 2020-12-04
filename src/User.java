import java.io.Serializable;

public class User implements Serializable {

    // Design pattern Fluent interface

    private String userName;
    private String password;
    private final GameStats gameStats;


    public User() {
        this.gameStats = new GameStats();
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public static void main(String[] args) {
        User user = new User()
                .setUserName("Oscar")
                .setPassword("test");
        user.gameStats.addWin();
        user.gameStats.addLoss();
        user.gameStats.addTie();
        user.gameStats.addLoss();
        System.out.println(user.gameStats.getWins());
        System.out.println(user.gameStats.getLosses());

        System.out.println(user.getUserName() + " " + user.getPassword() + " " + user.gameStats.calculateWinPercent());

    }
}


