import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;

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
        User user = new User();
        user = UserDatabase.getUser(userNameField.getText(), passwordField.getText());
         if (user == null)
             loginFail();
         else
             loginSuccessful(user);

    }

    void loginSuccessful(User user) {
        // Kolla om det var första eller andra användaren som loggade in
        //
        // Ladda game panel
    }

    void loginFail() {
        System.out.println("Felaktigt användarnamn eller lösenord, försök igen");// fast i GUI
        //Skicka tillbaka till loginfönstret och be användaren försöka igen
    }
}
