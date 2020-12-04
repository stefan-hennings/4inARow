import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreenPanel extends JPanel implements ActionListener {
    ImageIcon imageIconWhiteTile;
    ImageIcon imageIconRedTile;
    ImageIcon imageIconYellowTile;
    JButton[][] buttons = new JButton[6][7];

    Game game;

    public GameScreenPanel(Game game) {
        this.game = game;
        setBackground(Color.BLUE);
        createButtons();
        repaint();
    }

    private void createButtons() {
        for (JButton[] buttonRow : buttons) {
            for (JButton button : buttonRow) {
                button = new JButton();
                button.addActionListener(game);
                button.setEnabled(false);
                button.setBackground(Color.WHITE);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
