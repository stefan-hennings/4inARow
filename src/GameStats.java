import java.text.DecimalFormat;

public class GameStats implements Score {

    private double wins;
    private double losses;
    private double ties;
    DecimalFormat d = new DecimalFormat("#.##");

    @Override
    public String calculateWinPercent() {
        return d.format(wins/(wins + losses + ties)*100)+ "%";
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

    public double getWins() {
        return wins;
    }

    public double getLosses() {
        return losses;
    }

    public double getDraws() {
        return ties;
    }

}
