import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameBoard {
    int squareCount = 16;
    List <BoardSquare> squares = new ArrayList<>();

    public List<BoardSquare> getSquares() {
        return squares;
    }

    public void createSquares() {

        for (int y = 0; y < 4; y++) {
            for(int x = 0; x < 4; x++){
                BoardSquare square = new BoardSquare(x,y);
                squares.add(square);
            }
        }
    }
    public void setNumbersOnSquares(){
        List<String> numbers = createShuffledList();

        for(int i = 0; i < squareCount; i++){
            if (numbers.get(i).equals("0")) {
                squares.get(i).getButton().setVisible(false);
            }
        squares.get(i).getButton().setText(numbers.get(i));
    }}

    public List<String> createShuffledList() {
        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < squareCount; i++) {
           numbers.add(String.valueOf(i));
        }
        Collections.shuffle(numbers);
        return numbers;
    }
}
