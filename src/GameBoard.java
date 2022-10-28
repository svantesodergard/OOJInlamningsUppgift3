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
        }
    }

    public List<String> createShuffledList() {
        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < squareCount; i++) {
            numbers.add(String.valueOf(i));
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    public int findSquare(String faceValue) {
        int index = -1;
        for(int i = 0; i < squareCount; i++){
            if (squares.get(i).getButton().getText().equals(faceValue)){
                index = i;
            }
        }
        return index;
    }

    public void moveSquare(int index) {
        int emptySquareIndex = findSquare("0");
        JButton buttonToMove = squares.get(index).getButton();
        JButton emptyButton = squares.get(emptySquareIndex).getButton();
        if (isSquareMovable(index)) {
            emptyButton.setVisible(true);
            emptyButton.setText(buttonToMove.getText());
            buttonToMove.setText("0");
            buttonToMove.setVisible(false);
        }
    }

    public boolean isSquareMovable(int index) {
        int emptySquareIndex = findSquare("0");
        BoardSquare squareToMove = squares.get(index);
        BoardSquare emptySquare = squares.get(emptySquareIndex);

        if (emptySquare.getX() != squareToMove.getX() && emptySquare.getY() != squareToMove.getY()) {
            return false;
        }
        if (Math.abs(emptySquare.getX() - squareToMove.getX()) == 1) {
            return true;
        }
        return Math.abs(emptySquare.getY() - squareToMove.getY()) == 1;
    }
}
