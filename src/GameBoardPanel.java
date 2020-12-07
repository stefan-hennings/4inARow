import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoardPanel extends JPanel implements ActionListener {
    ImageIcon imageIconWhiteTile;
    ImageIcon imageIconRedTile;
    ImageIcon imageIconYellowTile;
    JButton[][] buttons = new JButton[6][7];

    Game game;

    public GameBoardPanel(Game game) {
        this.game = game;
        setBackground(Color.BLUE);
        setLayout(new GridLayout(6, 7));
        createButtons();
        repaint();
    }

    private void createButtons() {
        for (int row = 5; row >= 0; row--) {
            for (int column = 0; column < 7; column++) {
                buttons[row][column] = new JButton();
                add(buttons[row][column]);
                buttons[row][column].addActionListener(game);
//                button.setEnabled(false);
                buttons[row][column].setBackground(Color.WHITE);
                buttons[row][column].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        }
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
