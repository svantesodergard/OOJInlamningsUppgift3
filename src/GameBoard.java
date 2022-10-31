import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameBoard {
    private int width, height;
    private int squareCount;
    private List <BoardSquare> squares = new ArrayList<>();
    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.squareCount = width*height;
    }
    public List<BoardSquare> getSquares() {
        return squares;
    }

    //Skapar rutor utifrån spelarens valda storlek
    public void createSquares() {
        for (int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++){
                BoardSquare square = new BoardSquare(x,y);
                squares.add(square);
            }
        }
    }

    //Sätter nummer på knapparna. Inparameter bestämmer om spelet redan ska vara löst (quickSolve) eller ej.
    public void setNumbersOnSquares(boolean quickSolve){
        List<String> numbers = createShuffledList(quickSolve);

        for(int i = 0; i < squareCount; i++){
            if (numbers.get(i).equals("0")) {
                squares.get(i).getButton().setVisible(false);
            } else {
                squares.get(i).getButton().setVisible(true);
            }
            squares.get(i).getButton().setText(numbers.get(i));
        }
    }

    //Blandar siffror när spelaren trycker "Nytt spel".
    public List<String> createShuffledList(boolean quickSolve) {
        List<String> numbers = new ArrayList<>();
        for (int i = 1; i < squareCount; i++) {
            numbers.add(String.valueOf(i));
        }
        numbers.add("0");
        if (!quickSolve) {
            Collections.shuffle(numbers);
        }
        return numbers;
    }

    //Hämtar index för rutan med samma text som inparametern
    public int findSquare(String faceValue) {
        int index = -1;
        for(int i = 0; i < squareCount; i++){
            if (squares.get(i).getButton().getText().equals(faceValue)){
                index = i;
            }
        }
        return index;
    }

    /*
    Byter text och synlighet på knapparna om metoden isSquareMovable är true. 
    Inparameter är knappen spelaren trycker på.
     */
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
    /*
    Kollar om den tomma rutan är bredvid den knapp spelaren trycker på. Returnerar boolean true om så är fallet.    
     */
    public boolean isSquareMovable(int index) {
        int emptySquareIndex = findSquare("0");
        BoardSquare squareToMove = squares.get(index);
        BoardSquare emptySquare = squares.get(emptySquareIndex);

        if (emptySquare.getX() != squareToMove.getX() && emptySquare.getY() != squareToMove.getY()) {
            return false; //Undersöker om knappen inte är på samma rad eller kolumn som den tomma rutan
        }
        if (Math.abs(emptySquare.getX() - squareToMove.getX()) == 1) {
            return true; //Om skillnaden i x-värdet mellan tomma rutan och knappen är 1 betyder det att de är bredvid varandra
        }
        return Math.abs(emptySquare.getY() - squareToMove.getY()) == 1;
        //Om skillnaden i y-värdet mellan tomma rutan och knappen är 1 betyder det att de är över/under varandra
    }

    //Undersöker om spelaren har klarat spelet.
    public boolean isGameFinished(){
        for (int i=0; i<squareCount; i++){
            if (squares.get(i).getNumberOfButton() != i+1 && squares.get(i).getNumberOfButton() != 0) {
                return false;
            }
        }
        return true;

    }

}
