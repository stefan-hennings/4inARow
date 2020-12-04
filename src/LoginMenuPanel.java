import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class LoginMenuPanel extends JPanel implements ActionListener {
    JTextField userNameField = new JTextField("Enter username");
    JTextField passwordField = new JTextField("Enter password");
    JButton newUserButton = new JButton("Create new user");
    JButton confirmLoginButton = new JButton("Log in");

    JLabel outputLabel = new JLabel("Välkommen till världens bästa 4-i-rad spel!");

    Game game;


    public LoginMenuPanel() {
        this.game = new Game(this);
        setLayout(new GridLayout(5, 1));
        add(outputLabel);
        add(userNameField);
        add(passwordField);
        add(newUserButton);
        newUserButton.addActionListener(this);
        add(confirmLoginButton);
        confirmLoginButton.addActionListener(this);
    }

    void createUser() {
        User user = new User();
        user.setUserName(userNameField.getText());
        user.setPassword(passwordField.getText());

        UserDatabase.addUser(user);
        outputLabel.setText(user + " added");
    }

    void attemptLogin() {
        Optional<User> userOptional;
        userOptional = UserDatabase.getUser(userNameField.getText(), passwordField.getText());
        userOptional.ifPresentOrElse(this::loginSuccessful, this::loginFail);
    }

    void loginSuccessful(User user) {
        outputLabel.setText(user + " logged in");
//        game.addUser(user);
    }

    void loginFail() {
        outputLabel.setText("Felaktigt användarnamn eller lösenord, försök igen");// fast i GUI
        //Skicka tillbaka till loginfönstret och be användaren försöka igen
    }

    public static void main(String[] args) {
        UserDatabase.load();
        new LoginMenuPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newUserButton) {
            createUser();
        } else if (e.getSource() == confirmLoginButton) {
            attemptLogin();
        }
    }
}
