package graphics;

import controller.GameController;
import controller.TopListController;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.Field;

/**
 * A játék grafikus felülete. Null layoutot alkalmazunk, és a graphicssal
 * rajzoljuk ki az Scoreboardot illetve a játékmezőt
 */
public class GamePanel extends JPanel {

    private final JButton _return;
    private final GameController _controller;
    private final TopListDialogBox _topListDB;
    private final int _cols = 10;
    private final int _rows = 22;
    private final Field[][] _field;
    private boolean _gameOver = false;

    /**
     * Egyszerű konstruktor
     *
     * @param w A főablak referenciája
     * @param controllerConn A topList kontrollerének referenciája
     */
    public GamePanel(MainWindow w, TopListController controllerConn) {
        _topListDB = new TopListDialogBox();
        _controller = new GameController(this, controllerConn, _topListDB, _rows, _cols);
        _field = _controller.getField();
        _topListDB.setButtonListener(_controller);
        _return = new JButton("Return");
        _return.addActionListener((ActionEvent e) -> {
            w.changePanel("menu");
        });

        super.addKeyListener(_controller);
        super.addFocusListener(_controller);
        super.setLayout(null);
        super.add(_return);
    }

    /**
     * A gameOver-t igazzá teszi és megjeleníti a TopListDialogBox-ot
     */
    public void gameOver() {
        _gameOver = true;
        this.repaint();
        _topListDB.setVisible(true);
    }

    /**
     * Ezzel a függvénnyel rajzoljuk ki a táblát és a scoreBoardot
     *
     * @param g A paintComponent viselkedéséből jövő Graphics. Ezáltal rajzolunk
     * az ablakra
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!_gameOver) {
            // A pályát rajzoló rész
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
                            g.setColor(Color.RED);
                            break;
                        case YELLOW:
                            g.setColor(Color.YELLOW);
                            break;
                        case ORANGE:
                            g.setColor(Color.ORANGE);
                            break;
                        case PURPLE:
                            g.setColor(new Color(160, 0, 160));
                            break;
                        case GREEN:
                            g.setColor(Color.GREEN);
                            break;
                        case DEFAULT:
                            g.setColor(new Color(222, 222, 222));
                            break;
                    }
                    g.fillRect(((_cols - m - 1) * (this.getWidth() - 100)) / _cols,
                            (_rows - i - 1) * this.getHeight() / _rows,
                            ((this.getWidth() - 100) / _cols) - 1,
                            (this.getHeight() / _rows) - 1);

                }
            }
            _return.setBounds(this.getWidth() - 100, this.getHeight() - 20, 100, 20);

            // A következő Piece-t rajzoló rész
            g.setColor(Color.ORANGE);
            g.fillRect(this.getWidth() - 100, 0, 100, this.getHeight());
            g.setColor(Color.BLACK);
            g.setFont(new Font("Times New Roman", Font.BOLD, 16));
            g.drawString("Next piece", this.getWidth() - 90, this.getHeight() - 175);
            Field[][] nextPieceGraphics = _controller.getNextPieceGraphics();
            for (int i = 0; i < 4; i++) {
                for (int m = 0; m < 4; m++) {
                    switch (nextPieceGraphics[i][m].getColor()) {
                        case CYAN:
                            g.setColor(Color.CYAN);
                            break;
                        case BLUE:
                            g.setColor(Color.BLUE);
                            break;
                        case RED:
                            g.setColor(Color.RED);
                            break;
                        case YELLOW:
                            g.setColor(Color.YELLOW);
                            break;
                        case ORANGE:
                            g.setColor(Color.ORANGE);
                            break;
                        case PURPLE:
                            g.setColor(new Color(160, 0, 160));
                            break;
                        case GREEN:
                            g.setColor(Color.GREEN);
                            break;
                        case DEFAULT:
                            g.setColor(new Color(0, 0, 0));
                            break;
                    }
                    g.fillRect(this.getWidth() - 100 + 25 * m,
                            this.getHeight() - 150 + 25 * i,
                            25,
                            25);
                }
            }
            g.setColor(Color.red);
            g.drawRect(this.getWidth() - 101,
                    this.getHeight() - 151,
                    101,
                    101);
            g.setColor(Color.BLACK);
            g.drawString("Score", this.getWidth() - 90, 25);
            g.drawString("" + _controller.getScore(), this.getWidth() - 90, 50);
        } else {
            // GameOver kirajzolasa
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, this.getWidth() - 100, this.getHeight());
            g.setColor(Color.ORANGE);
            g.fillRect(this.getWidth() - 100, 0, 100, this.getHeight());
            g.setColor(Color.BLACK);
            g.setFont(new Font("Times New Roman", Font.BOLD, 16));
            g.drawString("Score", this.getWidth() - 90, 25);
            g.drawString("" + _controller.getScore(), this.getWidth() - 90, 50);
            g.setColor(Color.ORANGE);
            g.drawString("Game Over", (this.getWidth() - 100) / 2, this.getHeight() / 2);
            _return.setBounds(this.getWidth() - 100, this.getHeight() - 20, 100, 20);

        }
    }

    /**
     * Ezzel fogjuk elkezdeni a játékot, illetve újrakezdeni, ha már befejeztünk
     * egyet
     */
    public void startGame() {
        _gameOver = false;
        _controller.startGame();
    }

}
