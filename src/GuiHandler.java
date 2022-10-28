import javax.swing.*;
import java.awt.*;

public class GuiHandler extends JFrame {
   GuiHandler() {
       JPanel game = new JPanel();
       GameBoard gameBoard = new GameBoard();
       gameBoard.createButtons();

       for (JButton button : gameBoard.getButtons()) {
           game.add(button);
       }

       game.setLayout(new GridLayout(4, 4));

       JPanel controls = new JPanel();
       JButton newGame = new JButton("Nytt Spel");
       controls.add(newGame);

       this.setLayout(new BorderLayout());
       this.add(controls, BorderLayout.NORTH);
       this.add(game);

       this.setVisible(true);
       this.setSize(300, 300);
       this.setLocationRelativeTo(null);
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
}
