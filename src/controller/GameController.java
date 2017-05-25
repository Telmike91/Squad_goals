package controller;

import graphics.GamePanel;
import graphics.TopListDialogBox;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.Timer;
import model.*;

/**
 * A legfőbb controller. Ezzel tudjuk irányítani a játékmenetet
 */
public class GameController implements ActionListener,
        FocusListener,
        KeyListener {

    private final Model _model;
    private final Timer _timer;
    private final GamePanel _gp;
    private final ArrayList<PieceType> _randomPiece;
    private PieceType _nextPiece;
    private boolean _gameOver = false;
    private final int INIT_SPEED = 600;
    private final int SPEED_UP = 100;
    private final int _rows;
    private final int _cols;
    private int _score;
    private final Model _nextPieceGraphics;
    private final TopListController _controllerConn;
    private final TopListDialogBox _topListDBconn;

    /**
     * Konstruktor. Beállítja a megfelelő mezőket és inicializálja a random
     * Tömböt
     *
     * @param gp a játék grafikus felületének referenciája
     * @param controllerConn A topList kontrollerének referenciája
     * @param topListDBconn A topListDialogBox referenciája
     * @param rows hány sorból áll a játék
     * @param cols Hány oszlopból áll a játék
     */
    public GameController(GamePanel gp, 
            TopListController controllerConn, 
            TopListDialogBox topListDBconn, 
            int rows, 
            int cols) {
        _rows = rows;
        _cols = cols;
        _score = 0;
        _topListDBconn = topListDBconn;
        _controllerConn = controllerConn;
        _nextPieceGraphics = new Model(4, 4);
        _model = new Model(rows, cols);
        _timer = new Timer(INIT_SPEED, this);
        _timer.setActionCommand("Timer");
        _randomPiece = new ArrayList<>();
        _randomPiece.addAll(Arrays.asList(PieceType.values()));
        _gp = gp;
    }

    /**
     * Lekérdezzük a Field-et amit átadunk a game view-nak hogy kitudja rajzolni
     * a pályát
     *
     * @return Field referencia
     */
    public Field[][] getField() {
        return _model.getFields();
    }

    /**
     * A lenyomott gombtól függően mozgatjuk a jelenlegi Piece-t
     *
     * @param e ebből nyerjük ki hogy melyik gomb került lenyomásra
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // DOWN : 40
        // LEFT : 37
        // RIGHT : 39
        // A: 65
        // D: 68
        // S: 83
        // Shift: 16
        // E: 69
        // Q: 81
        // CTRL: 17
        switch (e.getKeyCode()) {
            case 17:
            case 69:
                if (!_gameOver) {
                    _model.flip();
                    _gp.repaint();
                }
                break;
            case 16:
            case 81:
                if (!_gameOver) {
                    _model.flip();
                    _gp.repaint();
                }
                break;
            case 65:
            case 37:
                if (!_gameOver) {
                    _model.moveLeftRight(Direction.LEFT);
                    _gp.repaint();
                }
                break;
            case 68:
            case 39:
                if (!_gameOver) {
                    _model.moveLeftRight(Direction.RIGHT);
                    _gp.repaint();
                }
                break;
            case 83:
            case 40:
                if (!_gameOver) {
                    _timer.setDelay(SPEED_UP);
                }
                break;
        }
    }

    /**
     * Nem csinál semmit csak az implementálásból jön
     *
     * @param e Az implementálásból jövő paraméter
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Egyedül a lefele gomb felengedését figyeljük, és lelassítjuk a Piece
     * mozgását
     *
     * @param e Az implementálásból jövő paraméter
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 83:
            case 40:
                if (!_gameOver) {
                    _timer.setDelay(INIT_SPEED);
                }
                break;
        }
    }

    /**
     * Egy egyszerű Getter
     *
     * @return Visszaadja a pontszámot-t
     */
    public int getScore() {
        return _score;
    }

    /**
     * A timerből jövő action, és a TopListDialogBox-ból jövő gombnyomásra reagál
     * @param e Az actionPerformed implementálásból jövő paraméter
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Timer")) {
            if(!_gameOver) {
                if (!_model.move()) {
                    int temp = _model.clearRows();
                    if (temp > 0) {
                        _score = _score + temp;
                    }
                    if (!_model.enterPiece(_nextPiece)) {
                        _gp.gameOver();
                        _gameOver = true;
                        _timer.stop();
                    } else {
                        Collections.shuffle(_randomPiece);
                        _nextPiece = _randomPiece.get(0);
                        _nextPieceGraphics.resetTable();
                        _nextPieceGraphics.enterPiece(_nextPiece);
                    }
                }
                _gp.repaint();
            }
        } else if(e.getActionCommand().equals("ScoreSave")) {
            _topListDBconn.setVisible(false);
            String temp = _topListDBconn.getPlayerName();
            if(!temp.equals(""))
                _controllerConn.updateScore(temp, _score);
        }
    }

    /**
     * Egyszerű getter
     * @return Visszaadja a nextPieceGraphics referenciáját
     */
    public Field[][] getNextPieceGraphics() {
        return _nextPieceGraphics.getFields();
    }

    /**
     * Elkezdi a játékot. Újrainicializálja a táblát és elkezdi az időzítőt
     */
    public void startGame() {
        _gameOver = false;
        _timer.start();
        _score = 0;
        _model.resetTable();
        _nextPieceGraphics.resetTable();
        Collections.shuffle(_randomPiece);
        _nextPiece = _randomPiece.get(0);
        if (!_model.enterPiece(_nextPiece)) {
            _timer.stop();
            _gp.gameOver();
        }

        _nextPiece = _randomPiece.get(1);
        _nextPieceGraphics.enterPiece(_nextPiece);
        _gp.repaint();
    }

    /**
     * Beállítja a különböző Piece-knek a színeit
     *
     * @param colors egy map amiben a PieceType-hoz hozzárendelt színt
     * tartalmazza.
     */
    public void setColors(HashMap<PieceType, PieceColor> colors) {
        for (PieceType type : colors.keySet()) {
            _model.setColor(colors.get(type), type);
        }
    }

    /**
     * Amikor újra focus-t kap azaz visszalépünk a játékba, elindítjuk a timert
     *
     * @param e a focus implementációjából jövő paraméter
     */
    @Override
    public void focusGained(FocusEvent e) {
        _timer.start();
    }

    /**
     * Amikor return-re kattint vagy a játékos kikattint a képernyőről a játékos
     * akkor elvész a focus azaz kilépünk a jelenlegi játékból, illetve
     * szüneteltetjük
     *
     * @param e A focusLost implementálásából jön
     */
    @Override
    public void focusLost(FocusEvent e) {
        _timer.stop();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
