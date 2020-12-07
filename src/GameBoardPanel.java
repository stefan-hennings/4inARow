import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoardPanel extends JPanel implements ActionListener {
    public static final ImageIcon WHITE_TILE = SetSizeForTile.setSize(new ImageIcon("src\\images\\whiteDot.png"), 100,100);
    public static final ImageIcon YELLOW_TILE = SetSizeForTile.setSize(new ImageIcon("src\\images\\yellowTile.png"), 100, 100);
    public static final ImageIcon RED_TILE = SetSizeForTile.setSize(new ImageIcon("src\\images\\redTile.png"), 100, 100);
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
                buttons[row][column].setBackground(Color.BLUE);
//                button.setEnabled(false);
                buttons[row][column].setIcon(WHITE_TILE);
                buttons[row][column].setFocusPainted(false);
                buttons[row][column].setBorder(BorderFactory.createLineBorder(Color.BLUE));
            }
        }
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static ImageIcon formatIcon(ImageIcon oldImageIcon, int width,
                                int height) {
        Image oldImage = oldImageIcon.getImage();
        Image newImg = oldImage.getScaledInstance(width, height,
                java.awt.Image.SCALE_SMOOTH);

        return new ImageIcon(newImg);
    }
}
