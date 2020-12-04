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

    JTextField output = new JTextField(20);

    Game game;


    public LoginMenuPanel() {
        this.game = new Game(this);
        setLayout(new GridLayout(5, 1));
        add(output);
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
        output.setText(user + " added");
    }

    void attemptLogin() {
        Optional<User> userOptional;
        userOptional = UserDatabase.getUser(userNameField.getText(), passwordField.getText());
        userOptional.ifPresentOrElse(this::loginSuccessful, this::loginFail);
    }

    void loginSuccessful(User user) {
        System.out.println(user + " logged in");
//        game.addUser(user);
    }

    void loginFail() {
        output.setText("Felaktigt användarnamn eller lösenord, försök igen");// fast i GUI
        //Skicka tillbaka till loginfönstret och be användaren försöka igen
    }

    public static void main(String[] args) {
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
