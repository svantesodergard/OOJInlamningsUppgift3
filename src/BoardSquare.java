import javax.swing.*;

public class BoardSquare {
    JButton button = new JButton();
    int x;
    int y;

    public BoardSquare(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setButton(JButton button) {
        this.button = button;
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
