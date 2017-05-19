/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import graphics.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import model.*;


public class GameController implements KeyListener, MouseListener {
    private Table _table;

    public GameController(GamePanel gp, int rows, int cols) {
        _table = new Table(rows, cols);
        
    }
    
    public void test() {
        ArrayList<Coordinate> cords = new ArrayList<>();
        cords.add(new Coordinate(0,0));
        cords.add(new Coordinate(1,-1));
        cords.add(new Coordinate(1,0));
        cords.add(new Coordinate(1,1));
        Piece testPiece = new Piece(cords, PieceColor.RED);
        _table.enterPiece(testPiece); 
    }
    
    public Field[][] getField() {
        return _table.getField();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        // UP : 38
        // DOWN : 40
        // LEFT : 37
        // RIGHT : 39
        switch(e.getKeyCode()) {
            case 37:
                System.out.println("Left");
                break;
            case 38:
                System.out.println("Up");
                break;
            case 39:
                System.out.println("Right");
                break;
            case 40:
                System.out.println("Down");
                break;                
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}
