import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class GameBoardPanel extends JPanel implements ActionListener {
    ImageIcon imageIconWhiteTile;
    ImageIcon imageIconRedTile;
    ImageIcon imageIconYellowTile;
    JButton[][] buttons = new JButton[6][7];
    int currentPlayer = 1;
    Color player1 = new Color(255, 145, 143);
    Color player2 = new Color(251, 255, 125);
    Color currentColor = player1;

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
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        JButton jButton = (JButton) e.getSource();
                        jButton.setBackground(currentColor);
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        JButton jButton = (JButton) e.getSource();
                        jButton.setBackground(Color.WHITE);
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        JButton jButton = (JButton) e.getSource();
                        if (currentPlayer == 1){
                            jButton.setBackground(Color.RED);
                            currentColor = player2;
                            currentPlayer = 2;
                        }else{
                            jButton.setBackground(Color.YELLOW);
                            currentColor = player1;
                            currentPlayer = 1;
                        }
                        jButton.removeMouseListener(this);
                    }
                });
//                button.setEnabled(false);
                button.setBackground(Color.WHITE);
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
