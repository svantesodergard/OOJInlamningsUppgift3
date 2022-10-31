import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiHandler extends JFrame implements ActionListener {
    GameBoard gameBoard = new GameBoard(4, 4);
    JButton newGame = new JButton("Nytt Spel");
    GuiHandler() {
        JPanel game = new JPanel();
        gameBoard.createSquares();
        gameBoard.setNumbersOnSquares();

        for (BoardSquare square : gameBoard.getSquares()) {
            game.add(square.getButton());
            square.getButton().addActionListener(this);
        }

        game.setLayout(new GridLayout(4, 4));

        JPanel controls = new JPanel();
        newGame.addActionListener(this);
        controls.add(newGame);

        this.setLayout(new BorderLayout());
        this.add(controls, BorderLayout.NORTH);
        this.add(game);

        this.setVisible(true);
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void gameIsFinished(){
        JOptionPane.showMessageDialog(null,"Grattis, du vann!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {
            gameBoard.setNumbersOnSquares();
            return;
        } else {
            JButton button = (JButton) e.getSource();
            int squareIndex = gameBoard.findSquare(button.getText());
            gameBoard.moveSquare(squareIndex);
            if (gameBoard.isGameFinished()){
                gameIsFinished();
            }
        }
    }
}
