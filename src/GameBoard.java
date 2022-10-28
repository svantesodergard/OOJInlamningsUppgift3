import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameBoard {
    int buttonCount = 16;
    JButton[] buttons = new JButton[buttonCount];

    public JButton[] getButtons() {
        return buttons;
    }

    public void createButtons() {
        List<String> numbers = createShuffledList();

        for (int i = 0; i < buttonCount; i++) {
           buttons[i] = new JButton();
           if (numbers.get(i).equals("0")) {
               buttons[i].setVisible(false);
           }
           buttons[i].setText(numbers.get(i));
        }//test
    }

    public List<String> createShuffledList() {
        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < buttonCount; i++) {
           numbers.add(String.valueOf(i));
        }
        Collections.shuffle(numbers);
        return numbers;
    }
}
