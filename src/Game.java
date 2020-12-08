import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame implements ActionListener {
    private User redPlayer;
    private User yellowPlayer;
    private int tileCounter = 0;
    private final int[][] tileGrid = new int[6][7];
    private boolean isRedTurn = true;

    private final LoginMenuPanel loginMenuPanel;
    private final GameBoardPanel gameBoardPanel = new GameBoardPanel(this);
    private final HighScorePanel highScorePanel = new HighScorePanel();

    public Game(LoginMenuPanel loginMenuPanel) {
        this.loginMenuPanel = loginMenuPanel;
        setLayout(new BorderLayout());
        add(BorderLayout.CENTER, loginMenuPanel);
        setTitle("Logga in spelare 1");
        setSize(new Dimension(1000, 800));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void placeTile(int column) {
        for (int row = 0; row < 6; row++) {
            if (tileGrid[row][column] == Tile.EMPTY.getI()) {
                tileGrid[row][column] = isRedTurn ? Tile.RED.getI() : Tile.YELLOW.getI();
                gameBoardPanel.getButtons()[row][column].setIcon(isRedTurn ? GameBoardPanel.RED_TILE : GameBoardPanel.YELLOW_TILE);

                tileCounter++;
                if (hasWon(row, column) || tileCounter == 42) {
                    processResult();
                    return;
                }
                isRedTurn = !isRedTurn;
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Kolumnen är redan full! Placera någon annanstans");
    }

    public boolean hasWon(int placedRow, int placedColumn) {
        int inARowCounter = 0;
        int correctColor = isRedTurn ? Tile.RED.getI() : Tile.YELLOW.getI();

        int startRow = Math.max(placedRow - 3, 0);
        int endRow = Math.min(placedRow + 3, 5);
        int startColumn = Math.max(placedColumn - 3, 0);
        int endColumn = Math.min(placedColumn + 3, 6);

        int currentRow;
        int currentColumn;

        //Horizontal win
        for (currentColumn = startColumn; currentColumn <= endColumn; currentColumn++) {
            //Reset counter to 0 if the row of correct colors is broken
            inARowCounter = tileGrid[placedRow][currentColumn] == correctColor ? inARowCounter + 1 : 0;
            if (inARowCounter == 4) {
                return true;
            }
        }
        inARowCounter = 0;

        //Vertical win
        for (currentRow = startRow; currentRow <= endRow; currentRow++) {
            inARowCounter = ((tileGrid[currentRow][placedColumn] == correctColor) ? inARowCounter + 1 : 0);
            if (inARowCounter == 4) {
                return true;
            }
        }
        inARowCounter = 0;

        //Bottom left to top right win
        currentColumn = startColumn;
        currentRow = startRow;
        while (currentRow <= endRow && currentColumn <= endColumn) {
            inARowCounter = ((tileGrid[currentRow][currentColumn] == correctColor) ? inARowCounter + 1 : 0);
            if (inARowCounter == 4) {
                return true;
            }
            currentColumn++;
            currentRow++;
        }
        inARowCounter = 0;

        //Top left to bottom right win
        currentColumn = Math.min(placedColumn + 3, 6);
        endColumn = Math.max(placedColumn - 3, 0);
        currentRow = startRow;
        while (currentRow <= endRow && currentColumn >= endColumn) {
            inARowCounter = ((tileGrid[currentRow][currentColumn] == correctColor) ? inARowCounter + 1 : 0);
            if (inARowCounter == 4) {
                return true;
            }
            currentColumn--;
            currentRow++;
        }
        return false;
    }

    public void processResult() {
        System.out.println("Player " + (isRedTurn ? "red" : "yellow") + " has won");

        if (tileCounter == 42) {
            redPlayer.getGameStats().addTie();
            yellowPlayer.getGameStats().addTie();
        } else if (isRedTurn) {
            redPlayer.getGameStats().addWin();
            yellowPlayer.getGameStats().addLoss();
        } else {
            yellowPlayer.getGameStats().addWin();
            redPlayer.getGameStats().addLoss();
        }
        gameBoardPanel.buttonList.forEach(e -> e.removeActionListener(this));
        UserDatabase.save();
        int choice = JOptionPane.showConfirmDialog(this, "Spelet är slut vill du se highscore?");
        if (choice == 0){
            remove(gameBoardPanel);
            highScorePanel.updateScorePanel();
            add(highScorePanel);
            repaint();
        }else {
            System.exit(0);
        }


        // TODO: 04-Dec-20 show results screen
    }

    public void addUser(User user) {
        if ((redPlayer == null)) {
            redPlayer = user;
            setTitle("Logga in spelare 2");
        } else {
            yellowPlayer = user;
            setTitle("4 i rad");
            startGame();
        }
    }

    private void startGame() {
        remove(loginMenuPanel);
        add(gameBoardPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 7; column++) {
                if (e.getSource() == gameBoardPanel.getButtons()[row][column]) {
                    placeTile(column);
                }
            }
        }
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public boolean isRedTurn() {
        return isRedTurn;
    }
}

