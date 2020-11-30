import javax.management.StringValueExp;

public class User {

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
        user.gameStats.addWin(2);
        user.gameStats.addLosses(1);
        System.out.println(user.gameStats.getWins());

        System.out.println("" + user.gameStats.calculateWinPercent());

    }

}
