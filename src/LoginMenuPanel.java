import javax.swing.*;
import java.util.Optional;

public class LoginMenuPanel extends JPanel {
    JTextField userNameField;
    JTextField passwordField;
    JButton newUserButton;
    JButton confirmLoginButton;


    public LoginMenuPanel() {

    }

    void createUser() {
        User user = new User();
        user.setUserName(userNameField.getText());
        user.setPassword(passwordField.getText());

        UserDatabase.addToUserList(user);

    }

    void attemptLogin() {
        Optional<User> userOptional;
        userOptional = UserDatabase.getUser(userNameField.getText(), passwordField.getText());
        userOptional.ifPresentOrElse(this::loginSuccessful, this::loginFail);
    }

    void loginSuccessful(User user) {
        // Kolla om det var första eller andra användaren som loggade in
        // Om bara en är inloggad gå tillbaka till login
        //  annars Ladda game panel
    }

    void loginFail() {
        System.out.println("Felaktigt användarnamn eller lösenord, försök igen");// fast i GUI
        //Skicka tillbaka till loginfönstret och be användaren försöka igen
    }
}
