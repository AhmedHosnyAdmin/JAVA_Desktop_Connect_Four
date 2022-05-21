package four;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class ConnectFour extends JFrame {
    JPanel board = new JPanel(new GridLayout(6, 7, 0, 0));
    char currentTurn = 'X';
    ArrayList<JButton> buttons = new ArrayList<>();

    public ConnectFour() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setTitle("Connect 4");
        setResizable(false);

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
                JButton button = new JButton(" ");
                button.setFocusPainted(false);
                button.setName("Button" + column + "" + row);
                button.setBackground(Color.BLACK);
                button.setForeground(Color.CYAN);
                button.setBorder(new LineBorder(Color.DARK_GRAY,1, true));
                button.setFont(new Font("Tahoma", Font.BOLD, 50));
                button.addActionListener(e -> {
                    if (button.getText().equals(" ")) {
                        button.setText(String.valueOf(currentTurn));
                        if (currentTurn == 'X') {
                            currentTurn = 'O';
                        } else {
                            currentTurn = 'X';
                        }
                    }
                });
                board.add(button);
            }
        }
    }
}