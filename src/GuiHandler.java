import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiHandler extends JFrame implements ActionListener {
    private GameBoard gameBoard;
    private JLabel labelForGameSize = new JLabel("Spelstorlek");
    private JTextField gameSize = new JTextField("4", 2);
    private JButton newGame = new JButton("Nytt Spel");
    private JButton fastSolve = new JButton("Lös snabbt");
    private JPanel game;
    JPanel controls = new JPanel();
    GuiHandler() {

        newGame.addActionListener(this);
        fastSolve.addActionListener(this);
        controls.add(labelForGameSize);
        controls.add(gameSize);
        controls.add(newGame);
        controls.add(fastSolve);

        this.setLayout(new BorderLayout());
        this.add(controls, BorderLayout.NORTH);
        try {
            newGame();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Måste stå siffra i rutan");
        }


        this.setVisible(true);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    //Ruta kommer upp om spelaren klarar spelet.
    public void gameIsFinished(){
        JOptionPane.showMessageDialog(null,"Grattis, du vann!");
    }

    /*Spelaren skriver in hur stor spelplan hen önskar spela på. Felmeddelande om mindre än 0. 
    Kastar ett fel om användaren matar in andra tecken än siffror.
    Skapar en ny spelplan utifrån spelarens angivna storlek.  */
    public void newGame() throws NumberFormatException {
        int size = Integer.parseInt(gameSize.getText());
        if (size <= 0) {
            JOptionPane.showMessageDialog(null, "Du kan inte skriva värden < 0");
            return;
        }
        gameBoard = new GameBoard(size, size);
        gameBoard.createSquares();
        gameBoard.setNumbersOnSquares(false);

        game = new JPanel(new GridLayout(size, size));
        for (BoardSquare square : gameBoard.getSquares()) {
            game.add(square.getButton());
            square.getButton().addActionListener(this);
        }
        this.add(game);

        this.revalidate();
        this.repaint();
    }

    /*
    Metoden hanterar olika knapptryckningar, beroende på om spelaren trycker på "Nytt spel", 
    knapp med siffror eller "Lös snabbt".  */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {
            this.remove(game);
            try {
                newGame();
            } catch (NumberFormatException exception) { //Fångar upp fel som kastas av metoden newGame.
                JOptionPane.showMessageDialog(null, "Skriv in nummer!");
            }
        } else if (e.getSource() == fastSolve) {
            gameBoard.setNumbersOnSquares(true);
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
