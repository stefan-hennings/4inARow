import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame implements ActionListener {
    User redPlayer;
    User yellowPlayer;
    int tileCounter = 0;
    int[][] tileGrid = new int[6][7];
    boolean isRedTurn = true;

    public Game(LoginMenuPanel loginMenuPanel) {
        setVisible(true);
        setLayout(new BorderLayout());
        add(BorderLayout.CENTER, loginMenuPanel);
//        setLocationRelativeTo(null);
        setSize(new Dimension(1000, 800));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        repaint();
    }

    public void placeTile(int column) {
        for (int row = 0; row < 6; row++) {
            if (tileGrid[row][column] == Tile.WHITE.getI()) {
                tileGrid[row][column] = isRedTurn ? Tile.RED.getI() : Tile.YELLOW.getI();
                tileCounter++;
                if (hasWon(row, column) || tileCounter == 42) {
                    processResult();
                    return;
                }
                isRedTurn = !isRedTurn;
                return;
            }
        }
    }

    public boolean hasWon(int placedRow, int placedColumn) {
        int inARowCounter = 0;
        int correctColor = isRedTurn ? Tile.RED.getI() : Tile.YELLOW.getI();

        int startRow = Math.max(placedRow - 3, 0);
        int endRow = Math.min(placedRow + 3, 7);
        int startColumn = Math.max(placedColumn - 3, 0);
        int endColumn = Math.min(placedColumn + 3, 7);

        int currentRow;
        int currentColumn;

        //Horizontal win
        for (currentColumn = startColumn; currentColumn < endColumn; currentColumn++) {
            //Reset counter to 0 if the row of correct colors is broken
            inARowCounter = tileGrid[placedRow][currentColumn] == correctColor ? inARowCounter + 1 : 0;
            if (inARowCounter == 4) {
                return true;
            }

        }
        inARowCounter = 0;

        //Vertical win
        for (currentRow = startRow; currentRow < endRow; currentRow++) {
            inARowCounter = ((tileGrid[currentRow][placedColumn] == correctColor) ? inARowCounter + 1 : 0);
            if (inARowCounter == 4) {
                return true;
            }
        }
        inARowCounter = 0;

        //Bottom left to top right win
        currentColumn = startColumn;
        currentRow = startRow;
        while (currentRow < endRow && currentColumn < endColumn) {
            inARowCounter = ((tileGrid[currentRow][currentColumn] == correctColor) ? inARowCounter + 1 : 0);
            if (inARowCounter == 4) {
                return true;
            }
            currentColumn++;
            currentRow++;
        }
        inARowCounter = 0;

        //Top left to bottom right win
        currentColumn = Math.min(placedColumn + 3, 7);
        endColumn = Math.max(placedColumn - 3, 0);
        currentRow = startRow;
        while (currentRow < endRow && currentColumn > endColumn) {
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
        // TODO: 04-Dec-20 show results screen
    }

    public void addUser(User user) {
        if ((redPlayer == null)) {
            redPlayer = user;
        } else {
            yellowPlayer = user;
            startGame();
        }
    }

    private void startGame() {

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

