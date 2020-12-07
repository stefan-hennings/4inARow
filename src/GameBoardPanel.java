import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoardPanel extends JPanel implements ActionListener {
    private ImageIcon whiteTile = SetSizeForTile.setSize(new ImageIcon("src/theGame/images/whiteDot.png"), 100,100);
    private ImageIcon yellowTile = SetSizeForTile.setSize(new ImageIcon("src/theGame/images/yellowTile.png"), 100, 100);
    private ImageIcon redYellow = SetSizeForTile.setSize(new ImageIcon("src/theGame/images/redTile.png"), 100, 100);
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
        for (JButton[] buttonRow : buttons) {
            for (JButton button : buttonRow) {
                button = new JButton();
                add(button);

                button.addActionListener(game);
//                button.setEnabled(false);
                //button.setBackground(Color.WHITE);
                button.setIcon(whiteTile);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
