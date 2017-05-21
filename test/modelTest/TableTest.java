package modelTest;

import java.util.ArrayList;
import model.*;
import static org.junit.Assert.*;
import org.junit.*;

public class TableTest {
    
    private Table table;
    private Piece piece;
    private int height =22;
    private int width=10;
    private Field[][] fields;
    
    @Before
    public void initialize(){
        table = new Table(height,width);
        
        ArrayList<Coordinate> straightC = new ArrayList<>();
        straightC.add(new Coordinate(0, 0));
        straightC.add(new Coordinate(0, 1));
        straightC.add(new Coordinate(0, 2));
        straightC.add(new Coordinate(0, 3));
        piece = new Piece(straightC, PieceColor.CYAN);
        
        fields = table.getFields();
    }
    
    @Test
    public void resetTest() {
        table.reset();
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                assertEquals("A reset() nem allitja vissza a color-t DEFAULT-ra.", PieceColor.DEFAULT,fields[i][j].getColor());
                assertEquals("A reset() nem allitja vissza a state-t EMPTY-re.", FieldState.EMPTY,fields[i][j].getFieldState());
                assertEquals("A reset() nem allitja vissza a movable-t FALSE-ra.", false, fields[i][j].isMoveable());
            }
        }        
    }
    
    //assertEquals EXPECTED aztan a VALOS
    
    @Test
    public void enterPieceTest() {
        table.reset();
        assertEquals("Az enterPiece() tele volt, amikor nem kellett volna." ,true,table.enterPiece(piece));
        for(Coordinate c :  piece.getPieceCoordinates()){
            assertEquals("Nem allitottuk at a FieldState-t 'OCCUPIED'-re" , FieldState.OCCUPIED, fields[(this.height - 1) + c.getX() - piece.getMaxHeight()][((this.width / 2) - 1) + c.getY() - (piece.getMaxWidth() / 2)].getFieldState());
            assertEquals("Nem allitottuk at a Color-t 'CYAN'-re" , PieceColor.CYAN, fields[(this.height - 1) + c.getX() - piece.getMaxHeight()][((this.width / 2) - 1) + c.getY() - (piece.getMaxWidth() / 2)].getColor());
            assertEquals("Nem allitottuk at a Movablet-t 'TRUE'-ra" , true, fields[(this.height - 1) + c.getX() - piece.getMaxHeight()][((this.width / 2) - 1) + c.getY() - (piece.getMaxWidth() / 2)].isMoveable());
            
        }
        table.reset();
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                fields[i][j].setFieldState(FieldState.OCCUPIED);
            }
        }
        for(Coordinate c :  piece.getPieceCoordinates()){
            assertEquals("Az enterPiece() nem volt tele, amikor tele kellett volna legyen.", false,table.enterPiece(piece));
        }
    }
    
    @Test
    public void moveLeftRightTestFalse(){
        //RIGHT - FALSE 1
        table.reset();
        table.enterPiece(piece);
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
               if(fields[i][j].isMoveable()){
                    fields[i][j - 1].setFieldState(FieldState.OCCUPIED);
                    fields[i][j - 1].stop();
               }
            }
        }
        assertEquals("Mozgathato olyan iranyba, ahova nem szabadna: RIGHT", false, table.moveLeftRight(Direction.RIGHT));
        //RIGHT - FALSE 2
        table.reset();
        table.enterPiece(piece);
        for (int i = 0; i < this.height; ++i) {
            fields[i][0].start();
        }
        assertEquals("Mozgathato olyan iranyba, ahova nem szabadna: RIGHT", false, table.moveLeftRight(Direction.RIGHT));
        //LEFT - FALSE 1
        table.reset();
        table.enterPiece(piece);
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
               if(fields[i][j].isMoveable()){
                    fields[i][j + 1].setFieldState(FieldState.OCCUPIED);
                    fields[i][j + 1].stop();
               }
            }
        }
        assertEquals("Mozgathato olyan iranyba, ahova nem szabadna: LEFT", false, table.moveLeftRight(Direction.LEFT));
        //LEFT - FALSE 2
        table.reset();
        table.enterPiece(piece);
        for (int i = 0; i < this.height; ++i) {
            fields[i][width-1].start();
        }
        assertEquals("Mozgathato olyan iranyba, ahova nem szabadna: LEFT", false, table.moveLeftRight(Direction.LEFT));
    }
    
    @Test
    public void moveLeftTestTrue(){
        //RIGHT - TRUE
        table.reset();
        table.enterPiece(piece);
        ArrayList<Coordinate> pieceOriginalCoordinates = new ArrayList<>();
        for (int i = 0; i < this.height; ++i) {
            for (int j = 1; j < this.width; ++j) {
                if(fields[i][j].isMoveable()){
                    pieceOriginalCoordinates.add(new Coordinate(i,j));
                } 
            }
        }
        assertEquals("Nem mozgathato olyan iranyba, ahova szabadna: LEFT", true, table.moveLeftRight(Direction.LEFT));
        //RIGHT - JO HELYRE MOZGATTUK?
        ArrayList<Coordinate> pieceChangedCoordinates = new ArrayList<>();
        for (int i = 0; i < this.height; ++i) {
            for (int j = 1; j < this.width; ++j) {
                if(fields[i][j].isMoveable()){
                    pieceChangedCoordinates.add(new Coordinate(i,j));
                } 
            }
        }
        int shouldEqual = 0;
        for(int i = 0; i<pieceChangedCoordinates.size();i++){
            Coordinate c = new Coordinate(pieceOriginalCoordinates.get(i).getX(), pieceOriginalCoordinates.get(i).getY()+1);
            for(int j = 0; j<pieceChangedCoordinates.size();j++){
                if(pieceChangedCoordinates.get(i).getX() == c.getX() && pieceChangedCoordinates.get(i).getY() == c.getY()){
                    shouldEqual++;
                    break;
                }
            }
        }
        assertEquals("Nem mozgathato olyan iranyba, ahova szabadna: LEFT", shouldEqual, pieceChangedCoordinates.size());
    }
    
    @Test
    public void moveRightTestTrue(){
        //Right - TRUE
        table.reset();
        table.enterPiece(piece);
        ArrayList<Coordinate> pieceOriginalCoordinates = new ArrayList<>();
        for (int i = 0; i < this.height; ++i) {
            for (int j = 1; j < this.width; ++j) {
                if(fields[i][j].isMoveable()){
                    pieceOriginalCoordinates.add(new Coordinate(i,j));
                } 
            }
        }
        assertEquals("Nem mozgathato olyan iranyba, ahova szabadna: RIGHT", true, table.moveLeftRight(Direction.RIGHT));
        //Right - JO HELYRE MOZGATTUK?
        ArrayList<Coordinate> pieceChangedCoordinates = new ArrayList<>();
        for (int i = 0; i < this.height; ++i) {
            for (int j = 1; j < this.width; ++j) {
                if(fields[i][j].isMoveable()){
                    pieceChangedCoordinates.add(new Coordinate(i,j));
                } 
            }
        }
        int shouldEqual = 0;
        for(int i = 0; i<pieceChangedCoordinates.size();i++){
            Coordinate c = new Coordinate(pieceOriginalCoordinates.get(i).getX(), pieceOriginalCoordinates.get(i).getY()-1);
            for(int j = 0; j<pieceChangedCoordinates.size();j++){
                if(pieceChangedCoordinates.get(i).getX() == c.getX() && pieceChangedCoordinates.get(i).getY() == c.getY()){
                    shouldEqual++;
                    break;
                }
            }
        }
        assertEquals("Nem mozgathato olyan iranyba, ahova szabadna: RIGHT", shouldEqual, pieceChangedCoordinates.size());
    }
    
    @Test
    public void moveDownTestFalse(){
        //DOWN - FALSE 1
        table.reset();
        table.enterPiece(piece);
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
               if(fields[i][j].isMoveable()){
                    fields[i - 1][j].setFieldState(FieldState.OCCUPIED);
                    fields[i - 1][j].stop();
               }
            }
        }
        assertEquals("Mozgathato olyan iranyba, ahova nem szabadna: DOWN", false, table.move());
        //DOWN - FALSE 2
        table.reset();
        table.enterPiece(piece);
        for (int j = 0; j < this.width; ++j) {
            fields[0][j].start();
        }
        assertEquals("Mozgathato olyan iranyba, ahova nem szabadna: DOWN", false, table.move());
    }
        
    @Test
    public void moveDownTestTrue(){
        //DOWN - TRUE
        table.reset();
        table.enterPiece(piece);
        ArrayList<Coordinate> pieceOriginalCoordinates = new ArrayList<>();
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                if(fields[i][j].isMoveable()){
                    pieceOriginalCoordinates.add(new Coordinate(i,j));
                } 
            }
        }
        assertEquals("Nem mozgathato olyan iranyba, ahova szabadna: DOWN", true, table.move());
        //DOWN - JO HELYRE MOZGATTUK?
        ArrayList<Coordinate> pieceChangedCoordinates = new ArrayList<>();
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                if(fields[i][j].isMoveable()){
                    pieceChangedCoordinates.add(new Coordinate(i,j));
                } 
            }
        }
        int shouldEqual = 0;
        for(int i = 0; i<pieceChangedCoordinates.size();i++){
            Coordinate c = new Coordinate(pieceOriginalCoordinates.get(i).getX()-1, pieceOriginalCoordinates.get(i).getY());
            for(int j = 0; j<pieceChangedCoordinates.size();j++){
                if(pieceChangedCoordinates.get(i).getX() == c.getX() && pieceChangedCoordinates.get(i).getY() == c.getY()){
                    shouldEqual++;
                    break;
                }
            }
        }
        assertEquals("Nem mozgathato olyan iranyba, ahova szabadna: DOWN", pieceChangedCoordinates.size(), shouldEqual);
    } 
    
    @Test
    public void clearRowsTest(){
        //4 sor kigyulik egyszerre.
        table.reset();
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < this.width; ++j) {
                fields[i][j].setFieldState(FieldState.OCCUPIED);
                fields[i][j].setColor(PieceColor.GREEN);
            }
        }
        assertEquals("Nem jol szamolja ki a pontszamot.",6400, table.clearRows());
    }
}