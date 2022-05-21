package four;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class ConnectFour extends JFrame {
    JPanel board = new JPanel(new GridLayout(6, 7, 0, 0));
    char currentTurn = 'X';
    ArrayList<ArrayList<JButton>> jButtonColumns = new ArrayList<>();
    private final Dimension buttonDimensions = new Dimension(98, 98);


    public ConnectFour() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700, 620);
        setLocationRelativeTo(null);
        setTitle("Connect 4");
        setResizable(false);

        addBoard();

        setVisible(true);
    }

    private void addBoard() {
        createButtons();
        add(board);
        addAction();
    }

    private void createButtons() {
        for (int i = 0; i < 7; i++) {
            jButtonColumns.add(new ArrayList<>());
        }

        for (int row = 6; row >= 1; row--) {
            for (char col = 'A'; col <= 'G'; col++) {
                JButton button = new JButton(" ");
                button.setFocusPainted(false);
                button.setName("Button" + col + "" + row);
                button.setBackground(Color.BLACK);
                button.setForeground(Color.CYAN);
                button.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
                button.setFont(new Font("Tahoma", Font.BOLD, 50));
                button.setPreferredSize(buttonDimensions);
                button.setMaximumSize(buttonDimensions);
                button.setMinimumSize(buttonDimensions);
                jButtonColumns.get(col - 65).add(button);
                board.add(button);
            }
        }

        for (var col : jButtonColumns) {
            col.sort(Comparator.comparing(Component::getName));
        }
    }

    private void addAction() {
        for (var col : jButtonColumns) {
            for (var button : col) {
                button.addActionListener(e -> {
                    System.out.println(button.getName());
                    var currentColumn = jButtonColumns.get(button.getName().charAt(6) - 65);
                    addTextToButtons(currentColumn);
                });
            }
        }
    }

    private void addTextToButtons(ArrayList<JButton> buttons) {
        for (var button : buttons) {
            if (button.getText().equals(" ")) {
                System.out.println(button.getName());
                button.setText(String.valueOf(currentTurn));
                if (currentTurn == 'X') {
                    currentTurn = 'O';
                } else {
                    currentTurn = 'X';
                }
                break;
            }
        }
    }
}