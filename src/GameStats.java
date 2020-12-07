import java.io.Serializable;

public class GameStats implements Score, Serializable {

    private int wins = 0;
    private int losses = 0;
    private int ties = 0;

    @Override
    public double calculateWinPercentage() {
        return 100.0 * wins / (wins + losses);
    }

    @Override
    public void addWin() {
        this.wins++;
    }

    @Override
    public void addLoss() {
        this.losses++;
    }

    @Override
    public void addTie() {
        this.ties++;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return ties;
    }

}
