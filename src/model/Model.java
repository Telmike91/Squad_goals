package model;


/**
 * Ez a class lesz felelős a játéktábla létrehozásáért és a controllnak az adatok továbbadásával 
 */
import java.util.*;

public class Model {

    private Table table;
    private Piece straight;
    private Piece square;
    private Piece triangle;
    private Piece rturn;
    private Piece lturn;
    private Piece leftl;
    private Piece rightl;
    
    /**
     * Egy egyszerű Model konstruktor, ami létrehozza a táblát és a Piece-ket
     * @param x Tábla szélessége
     * @param y Tábla magassága
     */
    public Model(int x, int y) {
        this.table = new Table(x, y);
        
        ArrayList<Coordinate> straightC = new ArrayList<>();
        straightC.add(new Coordinate(0, 0));
        straightC.add(new Coordinate(0, 1));
        straightC.add(new Coordinate(0, 2));
        straightC.add(new Coordinate(0, 3));
        this.straight = new Piece(straightC, PieceColor.CYAN);
        
        ArrayList<Coordinate> squareC = new ArrayList<>();
        squareC.add(new Coordinate(0, 0));
        squareC.add(new Coordinate(0, 1));
        squareC.add(new Coordinate(1, 0));
        squareC.add(new Coordinate(1, 1));
        this.square = new Piece(squareC, PieceColor.YELLOW);
        
        ArrayList<Coordinate> triangleC = new ArrayList<>();
        triangleC.add(new Coordinate(0, 0));
        triangleC.add(new Coordinate(0, 1));
        triangleC.add(new Coordinate(0, 2));
        triangleC.add(new Coordinate(1, 1));
        this.triangle = new Piece(triangleC, PieceColor.PURPLE);
        
        ArrayList<Coordinate> rturnC = new ArrayList<>();
        rturnC.add(new Coordinate(0, 0));
        rturnC.add(new Coordinate(0, 1));
        rturnC.add(new Coordinate(1, 1));
        rturnC.add(new Coordinate(1, 2));
        this.rturn = new Piece(rturnC, PieceColor.GREEN);
        
        ArrayList<Coordinate> lturnC = new ArrayList<>();
        lturnC.add(new Coordinate(1, 0));
        lturnC.add(new Coordinate(1, 1));
        lturnC.add(new Coordinate(0, 1));
        lturnC.add(new Coordinate(0, 2));
        this.lturn = new Piece(lturnC, PieceColor.RED);
        
        ArrayList<Coordinate> leftlC = new ArrayList<>();
        leftlC.add(new Coordinate(0, 0));
        leftlC.add(new Coordinate(1, 0));
        leftlC.add(new Coordinate(0, 1));
        leftlC.add(new Coordinate(0, 2));
        this.leftl = new Piece(leftlC, PieceColor.BLUE);
        
        ArrayList<Coordinate> rightlC = new ArrayList<>();
        rightlC.add(new Coordinate(0, 0));
        rightlC.add(new Coordinate(0, 1));
        rightlC.add(new Coordinate(0, 2));
        rightlC.add(new Coordinate(1, 2));
        this.rightl = new Piece(rightlC, PieceColor.ORANGE);
    }
    
    /**
     * Újraindítja a táblát. A játék újrakezdéséért szükséges ez a metódus
     */
    public void resetTable() {
        table.reset();
    }
    
    /**
     * Egy megadott PieceType-t lerak a tábla tetejének a közepére
     * @param pieceType - Az adott PieceType
     * @return lerakható-e az adott Piece
     */
    public boolean enterPiece(PieceType pieceType) {        
        switch (pieceType) {
            case STRAIGHT:
                return table.enterPiece(straight);
            case SQUARE:
                return table.enterPiece(square);
            case TRIANGLE:
                return table.enterPiece(triangle);
            case RTURN:
                return table.enterPiece(rturn);
            case LTURN:
                return table.enterPiece(lturn);
            case LEFTL:
                return table.enterPiece(leftl);
            case RIGHTL:
                return table.enterPiece(rightl);
        }
        
        return false;
    }
    
    /**
     * A jelenlegi Piece-t mozgatja lefele
     * @return Ha mozgatható igazat ad vissza különben hamis.
     */
    public boolean move() {
        return table.move();
    }
    
    /**
     * Megforgatja a jelenlegi Piece-t
     * @return Ha forgatható igaz, különben hamis
     */
    public boolean flip() {
        return table.flip();
    }
    
    /**
     * Kitörli azokat a sorokat, amik tele vannak.
     * @return Visszaadja a pontszámot. Minél nagyobb annál jobb.
     */
    public int clearRows() {
        return table.clearRows();
    }
    
    /**
     * Beállítja a színeket a különböző Pieceknek, amiket a Settingsből kapunk
     * @param color Milyen szinű legyen
     * @param which Melyik típusnak állítsuk be
     */
    public void setColor(PieceColor color, PieceType which) {
        switch (which) {
            case STRAIGHT:
                if(color == PieceColor.DEFAULT)
                    straight.setColor(PieceColor.CYAN);
                else 
                    straight.setColor(color);
                break;
            case SQUARE:
                if(color == PieceColor.DEFAULT)
                    square.setColor(PieceColor.YELLOW);
                else 
                    square.setColor(color);                
                break;
            case TRIANGLE:
                if(color == PieceColor.DEFAULT)
                    triangle.setColor(PieceColor.PURPLE);
                else {
                    triangle.setColor(color);                
                }
                break;
            case RTURN:
                if(color == PieceColor.DEFAULT)
                    rturn.setColor(PieceColor.GREEN);
                else 
                    rturn.setColor(color);                
                break;
            case LTURN:
                if(color == PieceColor.DEFAULT)
                    lturn.setColor(PieceColor.RED);
                else 
                    lturn.setColor(color);                                
                break;
            case LEFTL:
                if(color == PieceColor.DEFAULT)
                    leftl.setColor(PieceColor.BLUE);
                else 
                    leftl.setColor(color);                
                break;
            case RIGHTL:
                if(color == PieceColor.DEFAULT)
                    rightl.setColor(PieceColor.ORANGE);
                else 
                    rightl.setColor(color);                
                break;
        }
    }
    
    /**
     * Visszaadja a mezők mátrix referenciáját
     * @return Mezők mátrix referenciája
     */
    public Field[][] getFields() {
        return table.getFields();
    }

    /**
     * Az adott Piece-t jobbra vagy balra mozgatja
     * @param direction vagy jobbra vagy balra.
     * @return Ha mozgatható igaz, különben hamis.
     */
    public boolean moveLeftRight(Direction direction) {
        return table.moveLeftRight(direction);
    }
}
