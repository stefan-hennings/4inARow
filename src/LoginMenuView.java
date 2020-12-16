import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class LoginMenuView extends JPanel implements ActionListener {
    public static final Color FOREGROUND_COLOR = new Color(0x79AA9E);
    public static final Color BACKGROUND_COLOR = new Color(0x0123AA);
    private final JTextField userNameField = new JTextField("");
    private final JTextField passwordField = new JTextField("");
    private final JButton newUserButton = new JButton("Skapa ny användare");
    private final JButton confirmLoginButton = new JButton("Logga in");
    private final JButton removeUserButton = new JButton("Ta bort användare");


    private final JLabel outputLabel = new JLabel("<html>Välkommen till Best Company Ever AB's " +
            "<font color=red>4</font> i <font color=YELLOW>rad</font>-spel!</html>");

    private final GameController gameController;
    public LoginMenuView() {

        this.gameController = new GameController(this);
        setLayout(new BorderLayout());

        outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
        outputLabel.setPreferredSize(new Dimension(getParent().getWidth(), 300));
        outputLabel.setFont(new Font("Gabriola", Font.BOLD, 40));
        outputLabel.setForeground(FOREGROUND_COLOR);
        outputLabel.setBackground(BACKGROUND_COLOR);

        JLabel footerLabel = new JLabel("Logga in med befintlig användare för att spela, " +
                "alternativt skapa ny användare och logga sedan in.");
        footerLabel.setPreferredSize(new Dimension(getParent().getWidth(), 150));
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerLabel.setFont(new Font("Bell MT", Font.BOLD, 18));
        footerLabel.setBackground(BACKGROUND_COLOR);
        footerLabel.setForeground(FOREGROUND_COLOR);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(BACKGROUND_COLOR);
        topPanel.add(outputLabel, BorderLayout.CENTER);
        topPanel.add(footerLabel, BorderLayout.SOUTH);

        JLabel userNameLabel = new JLabel("Ange användarnamn:");
        userNameLabel.setForeground(FOREGROUND_COLOR);
        userNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel passwordLabel = new JLabel("Ange lösenord:");
        passwordLabel.setForeground(FOREGROUND_COLOR);
        passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel emptyColumnLabel = new JLabel();
        emptyColumnLabel.setBackground(BACKGROUND_COLOR);

        JPanel gridPanel = new JPanel(new GridLayout(2, 3, 30,10));
        gridPanel.add(userNameLabel);
        gridPanel.add(userNameField);
        gridPanel.add(emptyColumnLabel);
        gridPanel.add(passwordLabel);
        gridPanel.add(passwordField);
        gridPanel.setBackground(BACKGROUND_COLOR);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,0));

        add(topPanel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 25));
        bottomPanel.setPreferredSize(new Dimension(getParent().getWidth(), 200));
        bottomPanel.setBackground(BACKGROUND_COLOR);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(30,0,30,0));

        newUserButton.setPreferredSize(new Dimension(250, 50));
        confirmLoginButton.setPreferredSize(new Dimension(250, 50));
        removeUserButton.setPreferredSize(new Dimension(250, 50));

        bottomPanel.add(newUserButton);
        bottomPanel.add(confirmLoginButton);
        bottomPanel.add(removeUserButton);

        add(bottomPanel, BorderLayout.SOUTH);

        newUserButton.addActionListener(this);
        confirmLoginButton.addActionListener(this);
        removeUserButton.addActionListener(this);
        setBackground(BACKGROUND_COLOR);
    }

    void createUser() {
        User user = new User()
                .setUserName(userNameField.getText().trim())
                .setPassword(passwordField.getText().trim());

        try {
            UserDatabase.addUser(user);
            outputLabel.setText(user + " skapad");
        } catch (IllegalArgumentException e) {
            outputLabel.setText(e.getMessage());
        }
    }

    void attemptLogin() {
        Optional<User> userOptional;
        userOptional = UserDatabase.getUser(userNameField.getText(), passwordField.getText());
        userOptional.ifPresentOrElse(this::loginSuccessful, this::loginFail);
    }

    void loginSuccessful(User user) {
        outputLabel.setText( "<html><font color=RED>" + user + "</font> loggade in. <br/>" +
                "Logga in <font color=YELLOW>spelare 2</font>. </html>");
        gameController.addUser(user);
    }

    void loginFail() {
        outputLabel.setText("Felaktigt användarnamn eller lösenord, försök igen");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newUserButton) {
            createUser();
        } else if (e.getSource() == confirmLoginButton) {
            attemptLogin();

        } else if (e.getSource() == removeUserButton){
            String answer = JOptionPane.showInputDialog("Ange administratörslösenord");
            if (answer.equals("admin")){
                StringBuilder stringBuilder = new StringBuilder();
                UserDatabase.getUserList().forEach(user -> stringBuilder.append(user).append("\n"));
                answer = JOptionPane.showInputDialog("Ange användarnamn på användaren som ska tas bort:\n" + stringBuilder.toString());
                String output = UserDatabase.removeUser(answer);
                JOptionPane.showMessageDialog(null, output);
            }
            else
                JOptionPane.showMessageDialog(null, "Felaktigt lösenord");
        }
    }
}
