package graphics;

import controller.GameController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.Field;

public class GamePanel extends JPanel {

    private final JButton _return;
    private final GameController _controller;
    private int _cols = 9;
    private int _rows = 20;
    private Field[][] _field;

    public GamePanel(MainWindow w) {
        _return = new JButton("Return");
        _controller = new GameController(this, _rows, _cols);
        _field = _controller.getField();

        _return.addActionListener((ActionEvent e) -> {
            w.changePanel("menu");
        });

        super.addKeyListener(_controller);
        super.setLayout(new BorderLayout());
        super.add(_return, BorderLayout.PAGE_END);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < _rows; i++) {
            for (int m = 0; m < _cols; m++) {
                switch (_field[i][m].getColor()) {
                    case CYAN:
                        g.setColor(Color.CYAN);
                        break;
                    case BLUE:
                        g.setColor(Color.BLUE);
                        break;
                    case RED:
                        System.out.println("I erteke: " + i + "# M erteke: " + m);
                        g.setColor(Color.RED);
                        break;
                    case YELLOW:
                        g.setColor(Color.YELLOW);
                        break;
                    case ORANGE:
                        g.setColor(Color.ORANGE);
                        break;
                    case PURPLE:
                        g.setColor(new Color(128, 0, 128));
                        break;
                    case GREEN:
                        g.setColor(Color.GREEN);
                        break;                                               
                    case DEFAULT:
                        g.setColor(Color.BLACK);
                        break;
                }
                g.fillRect(m * 12, i * 12, 10, 10);
            }
        }

    }

    public void startGame(MainWindow w, Color[] colors) {

    }

}
