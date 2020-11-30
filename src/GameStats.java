public class GameStats implements Score {

    private int wins;
    private int losses;
    private int ties;

    @Override
    public double calculateWinPercent() {
        return wins / (wins + losses);
    }

    @Override
    public void addWin(int i) {
        this.wins++;
    }

    @Override
    public void addLosses(int i) {
        this.losses++;
    }

    @Override
    public void addTie(int i) {
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
