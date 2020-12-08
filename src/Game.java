import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class Game extends JFrame implements ActionListener {
    private User redPlayer;
    private User yellowPlayer;
    private int tileCounter = 0;
    private final int[][] tileGrid = new int[6][7];
    private boolean isRedTurn = true;

    private final LoginMenuPanel loginMenuPanel;
    private final GameBoardPanel gameBoardPanel = new GameBoardPanel(this);

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
        int correctColor = isRedTurn ? Tile.RED.getI() : Tile.YELLOW.getI();

        int startRow = Math.max(placedRow - 3, 0);
        int endRow = Math.min(placedRow + 3, 5);
        int startColumn = Math.max(placedColumn - 3, 0);
        int endColumn = Math.min(placedColumn + 3, 6);

        //Check left to right win
        if (checkHorizontalWin(startColumn, endColumn, placedRow, correctColor)) {
            return true;
        }

        //Check bottom to top win
        if (checkVerticalWin(startRow, endRow, placedColumn, correctColor)) {
            return true;
        }
/*
        System.out.println("\n\nStarting left-up check");
        currentColumn = startColumn;
        currentRow = startRow;

        int columnDifference = currentColumn - placedColumn;
        int rowDifference =  currentRow - placedRow;
        System.out.println("\nRow: " + rowDifference + ", column: " + columnDifference);

        int differenceDifference = columnDifference - rowDifference;
        System.out.println(differenceDifference);

        if (differenceDifference < 0) currentColumn -= differenceDifference;
*/
        //Check top left to bottom right win
        if (checkLeftUpWin(startRow, endRow, startColumn, endColumn,
                placedRow, placedColumn, correctColor)) {
            return true;
        }
        //Check bottom left to top right win
        if (checkLeftDownWin(startRow, endRow, startColumn, endColumn,
                placedRow, placedColumn, correctColor)) {
            return true;
        }
/*
        //Top left to bottom right win
        currentColumn = startColumn;
        currentRow = endRow;
        endRow = startRow;

        columnDifference = currentColumn - placedColumn;
        rowDifference =  placedRow - currentRow;
        System.out.println("\nRow: " + rowDifference + ", column: " + columnDifference);

        differenceDifference = columnDifference - rowDifference;
        System.out.println(differenceDifference);

        if (differenceDifference > 0) currentRow -= differenceDifference;
        else currentColumn -= differenceDifference;

        System.out.println("\n\nStarting left-down check");
        while (currentRow >= endRow && currentColumn <= endColumn) {
            inARowCounter = ((tileGrid[currentRow][currentColumn] == correctColor) ? inARowCounter + 1 : 0);
            System.out.printf("Checking tile %d, %d%n", currentRow, currentColumn);
            if (inARowCounter == 4) {
                return true;
            }
            currentColumn++;
            currentRow--;
        }*/
        return false;
    }

    private boolean checkHorizontalWin(int lowColumn, int highColumn, int placedRow, int correctColor) {
        int inARowCounter = 0;
        while (lowColumn <= highColumn) {
            inARowCounter = tileGrid[placedRow][lowColumn] == correctColor ? inARowCounter + 1 : 0;
            if (inARowCounter == 4) {
                return true;
            }
            lowColumn++;
        }
        return false;
    }

    private boolean checkVerticalWin(int lowRow, int highRow, int placedColumn, int correctColor) {
        int inARowCounter = 0;
        while (lowRow <= highRow) {
            inARowCounter = tileGrid[lowRow][placedColumn] == correctColor ? inARowCounter + 1 : 0;
            if (inARowCounter == 4) {
                return true;
            }
            lowRow++;
        }
        return false;
    }

    private boolean checkLeftUpWin(int lowRow, int highRow, int lowColumn, int highColumn,
                                   int placedRow, int placedColumn, int correctColor) {
        int inARowCounter = 0;

        System.out.println("\n\nStarting left-up check");

        int columnDifference = lowColumn - placedColumn;
        int rowDifference =  lowRow - placedRow;
        System.out.println("\nRow: " + rowDifference + ", column: " + columnDifference);

        int differenceDifference = columnDifference - rowDifference;
        System.out.println(differenceDifference);

        if (differenceDifference < 0) lowColumn -= differenceDifference;


        while (lowRow <= highRow && lowColumn <= highColumn) {
            System.out.printf("Checking tile %d, %d%n", lowRow, lowColumn);
            inARowCounter = ((tileGrid[lowRow][lowColumn] == correctColor) ? inARowCounter + 1 : 0);
            if (inARowCounter == 4) {
                return true;
            }
            lowColumn++;
            lowRow++;
        }
        return false;
    }

    private boolean checkLeftDownWin(int lowRow, int highRow, int lowColumn, int highColumn,
                                     int placedRow, int placedColumn, int correctColor) {
        int inARowCounter = 0;

        int columnDifference = lowColumn - placedColumn;
        int rowDifference = placedRow - highRow;
        System.out.println("\nRow: " + rowDifference + ", column: " + columnDifference);

        int differenceDifference = columnDifference - rowDifference;
        System.out.println(differenceDifference);

        if (differenceDifference > 0) highRow -= differenceDifference;
        else lowColumn -= differenceDifference;

        while (highRow >= lowRow && lowColumn <= highColumn) {
            inARowCounter = ((tileGrid[highRow][lowColumn] == correctColor) ? inARowCounter + 1 : 0);
            System.out.printf("Checking tile %d, %d%n", highRow, lowColumn);
            if (inARowCounter == 4) {
                return true;
            }
            lowColumn++;
            highRow--;
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
        gameBoardPanel.getButtonList().forEach(e -> e.removeActionListener(this));
        UserDatabase.save();
        JOptionPane.showMessageDialog(this, getHighScoreString(), "Highscore", JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);
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

    public boolean isRedTurn() {
        return isRedTurn;
    }

    public String getHighScoreString(){
        List<User> sortedUsers = UserDatabase.getUserList();
        sortedUsers.sort(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(sortedUsers.size(), 10); i++) {
            if (sortedUsers.get(i).getGameStats().getWins() == 0) {
                break;
            }
            sb.append(i+1).append(": ").append(sortedUsers.get(i).getUserName()).append(" ").append(sortedUsers.get(i).getGameStats().toString()).append("\n");
        }
        return sb.toString();
    }
}

