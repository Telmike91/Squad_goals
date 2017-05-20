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
        KeyListener, 
        MouseListener {
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
                }
                break;
            case 16:
            case 81:
                if(!_gameOver) {
                    _model.flip();
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

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

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

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("#X# " + e.getX() + " #Y# " + e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getScore() {
        return _score;
    }

    @Override
    public void actionPerformed(ActionEvent e) {              
        if(!_model.move(Direction.DOWN)) {
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
    
    public void startGame() {
        _gameOver = false;
        _timer.start();
        _model.resetTable();
        Collections.shuffle(_randomPiece);
        if(!_model.enterPiece(_randomPiece.get(0))) {
            _timer.stop();
            _gp.gameOver();            
        }
        _gp.repaint();
    }

    public void setColors(HashMap<PieceType, PieceColor> colors) {
        System.out.println(colors.size());
        for(PieceType type : colors.keySet()) {
            _model.setColor(colors.get(type), type);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        _timer.start();
    }

    /**
     * Amikor return-re kattint a játékos akkor elvész a focus azaz befejezte a játékot.
     * @param e
     */
    @Override
    public void focusLost(FocusEvent e) {
        _timer.stop();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
