package controller;

import graphics.GamePanel;
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
    private boolean _gameOver = false;
    private final int INIT_SPEED = 1000;
    private final int SPEED_UP = 200;
    private final int _rows;
    private final int _cols;
    private int _score;

    /**
     * Konstruktor. Beállítja a megfelelő mezőket és inicializálja a random Tömböt
     * @param gp a játék grafikus felületének referenciája
     * @param rows hány sorból áll a játék
     * @param cols Hány oszlopból áll a játék
     */
    public GameController(GamePanel gp, int rows, int cols) {
        _rows = rows;
        _cols = cols;
        _score = 0;
        _model = new Model(rows, cols);
        _timer = new Timer(INIT_SPEED, this);
        _randomPiece = new ArrayList<>();
        _randomPiece.addAll(Arrays.asList(PieceType.values()));
        _gp = gp;    
    }  
    
    /**
     * Lekérdezzük a Field-et amit átadunk a game view-nak hogy kitudja rajzolni a pályát
     * @return Field referencia
     */
    public Field[][] getField() {
        return _model.getFields();
    }
    
    /**
     * A lenyomott gombtól függően mozgatjuk a jelenlegi Piece-t
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
        switch(e.getKeyCode()) {
            case 17:
            case 69:
                if(!_gameOver) {
                    _model.flip();
                    _gp.repaint();
                }
                break;
            case 16:
            case 81:
                if(!_gameOver) {
                    _model.flip();
                    _gp.repaint();
                }
                break;
            case 65:
            case 37:
                if(!_gameOver) {
                    _model.moveLeftRight(Direction.LEFT);
                    _gp.repaint();
                }
                break;
            case 68:
            case 39:
                if(!_gameOver) {
                    _model.moveLeftRight(Direction.RIGHT);
                    _gp.repaint();
                }
                break;
            case 83:
            case 40:   
                if(!_gameOver) {
                    _timer.setDelay(SPEED_UP);
                }
                break;                
        }
    }

    /**
     * Nem csinál semmit csak az implementálásból jön
     * @param e Az implementálásból jövő paraméter
     */
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    /**
     * Egyedül a lefele gomb felengedését figyeljük, és lelassítjuk a Piece mozgását
     * @param e Az implementálásból jövő paraméter
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case 83:
            case 40:
                if(!_gameOver) {
                    _timer.setDelay(INIT_SPEED);
                }
                break;                
        }
    }
    /**
     * Egy egyszerű Getter
     * @return Visszaadja a _score
     */
    public int getScore() {
        return _score;
    }

    /**
     * A timerből jövő action
     * @param e Az actionPerformed implementálásból jövő paraméter
     */
    @Override
    public void actionPerformed(ActionEvent e) {                      
        if(!_model.move()) {
            int temp = _model.clearRows();
            if(temp > 0) {
                _score = _score + temp;
            }
            Collections.shuffle(_randomPiece);
            if(!_model.enterPiece(_randomPiece.get(0))) {
                _gp.gameOver();
                _gameOver = true;
                _timer.stop();
            }
        }
        _gp.repaint();        
    }
    
    /**
     * Elkezdi a játékot. Újrainicializálja a táblát és elkezdi az időzítőt
     */
    public void startGame() {
        _gameOver = false;
        _timer.start();
        _score = 0;
        _model.resetTable();
        Collections.shuffle(_randomPiece);
        if(!_model.enterPiece(_randomPiece.get(0))) {
            _timer.stop();
            _gp.gameOver();            
        }
        _gp.repaint();
    }

    /**
     * Beállítja a különböző Piece-knek a színeit
     * @param colors egy map amiben a PieceType-hoz hozzárendelt színt tartalmazza.
     */
    public void setColors(HashMap<PieceType, PieceColor> colors) {
        for(PieceType type : colors.keySet()) {
            _model.setColor(colors.get(type), type);
        }
    }

    /**
     * Amikor újra focus-t kap azaz visszalépünk a játékba, elindítjuk a timert
     * @param e 
     */
    @Override
    public void focusGained(FocusEvent e) {
        _timer.start();
    }

    /**
     * Amikor return-re kattint vagy a játékos kikattint a képernyőről a játékos 
     * akkor elvész a focus azaz kilépünk a jelenlegi játékból, illetve szüneteltetjük
     * @param e A focusLost implementálásából jön
     */
    @Override
    public void focusLost(FocusEvent e) {
        _timer.stop();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
