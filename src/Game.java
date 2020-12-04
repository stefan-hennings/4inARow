import javax.swing.*;

public class Game extends JFrame {
    User user1;
    User user2;
    int tileCounter = 0;
    int [][] tileGrid = new int[6][7];
    boolean isRedTurn = true;

    public void placeTile (int column){
        for (int row = 0; row < 6; row++) {
            if (tileGrid[row][column] == Tile.WHITE.getI()) {
                tileGrid[row][column] = isRedTurn ? Tile.RED.getI() : Tile.YELLOW.getI();
                tileCounter++;
                if (hasWon(row, column)) {
                    processResult();
                    return;
                }
                isRedTurn = !isRedTurn;
                return;
            }
        }
    }

    public boolean hasWon(int row, int column){
        for ()
    }

    public void processResult(){

    }

    public void addUser(User user) {
        if ((user1 == null)) {
            user1 = user;
        } else {
            user2 = user;
            startGame();
        }
    }

    private void startGame() {

    }


}

