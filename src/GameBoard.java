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
    public int findSquare(String siffra) {
        int index = -1;
        for(int i = 0; i < squareCount; i++){
            if (squares.get(i).getButton().getText().equals(siffra)){
                index = i;
            }
        }
        return index;
    }

    public void moveSquare(int index) {
        int x = squares.get(index).getX();
        int y = squares.get(index).getY();
        int emptySquareIndex = findSquare("0");
        if (squares.get(emptySquareIndex).getX()==x && Math.abs(squares.get(emptySquareIndex).getY()-y)==1) {
            squares.get(emptySquareIndex).getButton().setVisible(true);
            squares.get(emptySquareIndex).getButton().setText(squares.get(index).button.getText());
            squares.get(index).getButton().setText("0");
            squares.get(index).getButton().setVisible(false);
        }
        if (squares.get(emptySquareIndex).getY()==y && Math.abs(squares.get(emptySquareIndex).getX()-x)==1) {
            squares.get(emptySquareIndex).getButton().setVisible(true);
            squares.get(emptySquareIndex).getButton().setText(squares.get(index).button.getText());
            squares.get(index).getButton().setText("0");
            squares.get(index).getButton().setVisible(false);
        }
    }
}
