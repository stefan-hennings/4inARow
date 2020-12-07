
import javax.swing.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HighScorePanel extends JPanel {

    // TestList
    List<User> users = new ArrayList<>();
    User user1 = new User().setUserName("Oscar");
    User user2 = new User().setUserName("Patrik");
    User user3 = new User().setUserName("Stefan");
    User user4 = new User().setUserName("Stefan");
    User user5 = new User().setUserName("Stefan");
    User user6 = new User().setUserName("Stefan");
    User user7 = new User().setUserName("Stefan");
    User user8 = new User().setUserName("Stefan");
    User user9 = new User().setUserName("Stefan");
    User user10 = new User().setUserName("Stefan");

    private final int witdh = 500;
    private final int higth = 800;


    public static void main(String[] args) {
        new HighScorePanel();
    }

    public HighScorePanel() {
        user1.getGameStats().addWin();
        user1.getGameStats().addWin();
        user1.getGameStats().addLoss();
        user2.getGameStats().addWin();
        user2.getGameStats().addWin();
        user2.getGameStats().addWin();
        user2.getGameStats().addLoss();
        user2.getGameStats().addWin();
        user2.getGameStats().addWin();
        user2.getGameStats().addWin();
        user3.getGameStats().addWin();
        user3.getGameStats().addWin();
        user3.getGameStats().addWin();
        user3.getGameStats().addWin();
        user3.getGameStats().addTie();
        user3.getGameStats().addLoss();
        user3.getGameStats().addWin();

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);

        sortListByWins(users);

        users.forEach(e -> System.out.println(e.getGameStats().toString()));

        JFrame jFrame = new JFrame();
        jFrame.add(this);
        jFrame.setResizable(false);
        jFrame.setSize(witdh, higth);
        jFrame.setVisible(true);
        int j = 1;
        for (User value : users) {
            StringBuilder sb = new StringBuilder();
            sb.append(j).append(": ").append(value.getUserName()).append(" ").append(value.getGameStats().toString()).append("\n");
            JLabel jLabel = new JLabel(sb.toString());
            add(jLabel);
            j++;
        }

    }

    private List<User> sortListByWins(List<User> users) {
        int j = 1;
        for (int i = 0; i < users.size() - 1; i++) {
            if (users.get(i).getGameStats().getWins() < users.get(j).getGameStats().getWins()) {
                Collections.swap(users, i, j);
            }
            j++;
        }
        return users;
    }


}
