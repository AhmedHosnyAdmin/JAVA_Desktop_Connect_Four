package four;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardChecker {
    private BoardChecker() {
    }

    public static boolean checkRows(List<ArrayList<JButton>> checkRows) {
        for (var row : checkRows) {
            if (checkRow(row)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkRow(List<JButton> checkRow) {
        int streakCnt = 0;
        char streakSide = 0;
        var fstStreakBtnIndex = 0;
        for (int i = 0; i < checkRow.size(); i++) {
            char buttonTurn = checkRow.get(i).getText().charAt(0);
            if (streakCnt == 0 && (buttonTurn == 'X' || buttonTurn == 'O')) {
                streakSide = buttonTurn;
                streakCnt++;
            } else {
                if (streakSide == checkRow.get(i).getText().charAt(0)) {
                    streakCnt++;
                } else if (buttonTurn == 'X' || buttonTurn == 'O') {
                    fstStreakBtnIndex = i;
                    streakSide = buttonTurn;
                    streakCnt = 1;
                } else {
                    streakSide = 0;
                    streakCnt = 0;
                }
            }
            if (streakCnt == 4) {
                highlightWinner(checkRow, fstStreakBtnIndex);
                return true;
            }
        }
        return false;
    }

    public static void highlightWinner(List<JButton> checkRow, int fstStreakBtnIndex) {
        for (int i = fstStreakBtnIndex; i < fstStreakBtnIndex + 4; i++) {
            if (i < checkRow.size()) {
                checkRow.get(i).setBackground(Color.DARK_GRAY);
            }
        }
    }
}
