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


    }

    void attemptLogin() {

    }

    void loginSuccessful() {

    }

    void loginFail() {

    }
}
