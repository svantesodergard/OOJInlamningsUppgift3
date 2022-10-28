import javax.swing.*;

public class BoardSquare {
    JButton button;
    int x;
    int y;

    public BoardSquare(JButton button, int x, int y) {
        this.button = button;
        this.x = x;
        this.y = y;
    }

    public JButton getButton() {
        return button;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
