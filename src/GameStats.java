import java.io.Serializable;
import java.text.DecimalFormat;

public class GameStats implements Score, Serializable {

    private int wins;
    private int losses;
    private int ties;
    DecimalFormat d = new DecimalFormat("#.##");

    @Override
    public String calculateWinPercent() {
        return d.format((double)wins/(wins + losses + ties)*100)+"%";
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

    @Override
    public String toString() {
        return String.format("| Vinster: %d, förluster: %d, oavgjorda: %d, vinstprocent: %s", wins, losses, ties, calculateWinPercent());
    }
}
