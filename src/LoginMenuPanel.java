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
    }

    void loginSuccessful() {

    }

    void loginFail() {

    }
}
