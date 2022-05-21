package four;

import javax.swing.*;
import java.awt.*;

public class ConnectFour extends JFrame {
    JPanel board = new JPanel(new GridLayout(6, 7, 0, 0));

    public ConnectFour() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setTitle("Connect 4");

        addBoard();

        setVisible(true);
    }

    private void addBoard() {
        addBoardButtons();
        add(board);
    }

    private void addBoardButtons() {
        for (int row = 6; row > 0; row--) {
            for (char column = 'A'; column <= 'G'; column++) {
                JButton button = new JButton(column + "" + row);
                button.setFocusPainted(false);
                button.setName("Button" + column + "" + row);
                board.add(button);
            }
        }
    }
}