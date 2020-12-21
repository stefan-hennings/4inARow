package model;

import java.io.Serializable;

public class User implements Serializable, Comparable<User> {
    
    private String userName;
    private char[] password;
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
    
    @Override
    public String toString() {
        return userName;
    }
    
    public GameStats getGameStats() {
        return gameStats;
    }
    
    public char[] getPassword() {
        return password;
    }
    
    public User setPassword(char[] password) {
        this.password = password;
        return this;
    }
    
    @Override
    public int compareTo(User o) {
        return gameStats.getWins() - o.gameStats.getWins();
    }
}


