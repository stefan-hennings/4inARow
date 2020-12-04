import javax.swing.*;

public class Game extends JFrame {
    User user1;
    User user2;
    int tileCounter;
    int [][] tileGrid;

    public boolean checkWinCondition(){
        return  true;
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

