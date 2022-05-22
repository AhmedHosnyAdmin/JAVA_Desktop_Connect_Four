package four;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

import static four.BoardChecker.checkRows;

public class ConnectFour extends JFrame {
    JPanel board = new JPanel(new GridLayout(6, 7, 0, 0));
    char currentTurn = 'X';
    int moves = 0;
    boolean isWinner = false;
    ArrayList<ArrayList<JButton>> columns = new ArrayList<>();
    ArrayList<ArrayList<JButton>> rows = new ArrayList<>();
    ArrayList<ArrayList<JButton>> mainDiagonals = new ArrayList<>();
    ArrayList<ArrayList<JButton>> sideDiagonals = new ArrayList<>();
    ArrayList<JButton> buttons = new ArrayList<>();
    private final Dimension buttonDimensions = new Dimension(98, 98);
    private static final String BUTTONS_PREFIX = "Button";


    public ConnectFour() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700, 690);
        setLocationRelativeTo(null);
        setTitle("Connect 4");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setResizable(false);
        setBackground(Color.BLACK);

        addBoard();
        addFooter();

        setVisible(true);
    }

    private void addBoard() {
        board.setPreferredSize(new Dimension(600, 600));
        board.setMinimumSize(new Dimension(600, 600));
        createButtons();
        createMainDiagonals();
        createSideDiagonals();
        add(board);
        addAction();
    }

    private void addFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JButton resetButton = new JButton("Reset");
        resetButton.setName("ButtonReset");
        resetButton.setPreferredSize(new Dimension(150, 50));
        resetButton.setMaximumSize(new Dimension(150, 50));
        resetButton.setBackground(Color.WHITE);
        resetButton.setFont(new Font("Arial", Font.BOLD, 40));
        resetButton.setFocusPainted(false);
        footer.add(resetButton);
        footer.setBackground(Color.BLACK);
        add(footer);

        resetButton.addActionListener(e -> {
            moves = 0;
            isWinner = false;
            for (var button : buttons) {
                button.setText(" ");
                button.setBackground(Color.BLACK);
                currentTurn = 'X';
            }
        });
    }

    private void createButtons() {
        for (int i = 0; i < 7; i++) {
            columns.add(new ArrayList<>());
        }

        for (int i = 0; i < 6; i++) {
            rows.add(new ArrayList<>());
        }
        int row = 0;
        for (int rowReversed = 6; rowReversed >= 1; rowReversed--) {
            for (char col = 'A'; col <= 'G'; col++) {
                JButton button = new JButton(" ");
                button.setFocusPainted(false);
                button.setName(BUTTONS_PREFIX + col + "" + rowReversed);
                button.setBackground(Color.BLACK);
                button.setForeground(Color.CYAN);
                button.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
                button.setFont(new Font("Tahoma", Font.BOLD, 50));
                button.setPreferredSize(buttonDimensions);
                button.setMaximumSize(buttonDimensions);
                button.setMinimumSize(buttonDimensions);
                buttons.add(button);
                rows.get(row).add(button);
                columns.get(col - 65).add(button);
                board.add(button);
            }
            row++;
        }

        for (var col : columns) {
            col.sort(Comparator.comparing(Component::getName));
        }
    }

    private void addAction() {
        for (var col : columns) {
            for (var button : col) {
                button.addActionListener(e -> {
                    System.out.println(button.getName());
                    var currentColumn = columns.get(button.getName().charAt(6) - 65);
                    if (!isWinner) {
                        addTextToButtons(currentColumn);
                    }
                    checkWinner();
                });
            }
        }
    }

    private void addTextToButtons(ArrayList<JButton> buttons) {
        for (var button : buttons) {
            if (button.getText().equals(" ")) {
                System.out.println(button.getName());
                button.setText(String.valueOf(currentTurn));
                moves++;
                if (currentTurn == 'X') {
                    currentTurn = 'O';
                } else {
                    currentTurn = 'X';
                }
                break;
            }
        }
    }

    private void createMainDiagonals() {
        for (var row : rows) {
            ArrayList<JButton> diagonal = new ArrayList<>();
            int rowNumReversed = row.get(0).getName().charAt(7) - 48;
            char col = 'A';
            if (rowNumReversed > 3) {
                int rowNum = rowNumReversed + (6 - 2 * rowNumReversed);
                while (rowNumReversed >= 1) {
                    int finalRowNumReversed = rowNumReversed;
                    char finalCol = col;
                    var optionalButton = rows.get(rowNum).stream().filter(b -> b.getName().equals(BUTTONS_PREFIX + finalCol + "" + finalRowNumReversed)).findFirst();
                    optionalButton.ifPresent(diagonal::add);
                    rowNumReversed--;
                    rowNum++;
                    col++;
                }
            }
            if (diagonal.size() >= 4) {
                mainDiagonals.add(diagonal);
            }
        }
    }

    private void createSideDiagonals() {
        for (var row : rows) {
            ArrayList<JButton> diagonal = new ArrayList<>();
            int rowNumReversed = row.get(row.size() - 1).getName().charAt(7) - 48;
            char col = 'G';
            if (rowNumReversed < 4) {
                int rowNum = rowNumReversed + (6 - 2 * rowNumReversed);
                while (rowNumReversed <= 6) {
                    int finalRowNumReversed = rowNumReversed;
                    char finalCol = col;
                    var optionalButton = rows.get(rowNum).stream().filter(b -> b.getName().equals(BUTTONS_PREFIX + finalCol + "" + finalRowNumReversed)).findFirst();
                    optionalButton.ifPresent(diagonal::add);
                    rowNumReversed++;
                    rowNum--;
                    col--;
                }
            }
            if (diagonal.size() >= 4) {
                sideDiagonals.add(diagonal);
            }
        }
    }

    private void checkWinner() {
        if ((moves >= 4) && (checkRows(rows) || checkRows(columns) || checkRows(mainDiagonals) || checkRows(sideDiagonals))) {
            isWinner = true;
        }
    }
}