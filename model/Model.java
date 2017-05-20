package model;
//ez a class lesz felelős a játéktábla létrehozásáért és a controllnak az adatok továbbadásával
import java.util.*;

public class Model{
	private Table table;
	private Piece straight;
	private Piece square;
	private Piece triangle;
	private Piece rturn;
	private Piece lturn;
	private Piece leftl;
	private Piece rightl;
	
	public Model(int x, int y){
		this.table = new Table(x, y);
		
		
		ArrayList<Coordinate> straightC = new ArrayList<Coordinate>();
		straightC.add(new Coordinate(0, 0));
		straightC.add(new Coordinate(0, 1));
		straightC.add(new Coordinate(0, 2));
		straightC.add(new Coordinate(0, 3));
		this.straight = new Piece(straightC, Color.CYAN);
		
		ArrayList<Coordinate> squareC = new ArrayList<Coordinate>();
		squareC.add(new Coordinate(0, 0));
		squareC.add(new Coordinate(0, 1));
		squareC.add(new Coordinate(1, 0));
		squareC.add(new Coordinate(1, 1));
		this.square = new Piece(squareC, Color.YELLOW);
		
		ArrayList<Coordinate> triangleC = new ArrayList<Coordinate>();
		triangleC.add(new Coordinate(0,0));
		triangleC.add(new Coordinate(0,1));
		triangleC.add(new Coordinate(0,2));
		triangleC.add(new Coordinate(1,1));
		this.triangle = new Piece(triangleC, Color.PURPLE);
		
		ArrayList<Coordinate> rturnC = new ArrayList<Coordinate>();
		rturnC.add(new Coordinate(0,0));
		rturnC.add(new Coordinate(0,1));
		rturnC.add(new Coordinate(1,1));
		rturnC.add(new Coordinate(1,2));
		this.rturn = new Piece(rturnC, Color.GREEN);
		
		ArrayList<Coordinate> lturnC = new ArrayList<Coordinate>();
		lturnC.add(new Coordinate(1,0));
		lturnC.add(new Coordinate(1,1));
		lturnC.add(new Coordinate(0,1));
		lturnC.add(new Coordinate(0,2));
		this.lturn = new Piece(lturnC, Color.RED);
		
		ArrayList<Coordinate> leftlC = new ArrayList<Coordinate>();
		leftlC.add(new Coordinate(0,0));
		leftlC.add(new Coordinate(1,0));
		leftlC.add(new Coordinate(0,1));
		leftlC.add(new Coordinate(0,2));
		this.leftl = new Piece(leftlC, Color.BLUE);
		
		ArrayList<Coordinate> rightlC = new ArrayList<Coordinate>();
		rightlC.add(new Coordinate(0,0));
		rightlC.add(new Coordinate(0,1));
		rightlC.add(new Coordinate(0,2));
		rightlC.add(new Coordinate(1,2));
		this.rightl = new Piece(rightlC, Color.ORANGE);
	}
	
	public boolean enterPiece(PieceType pieceType){
		switch(pieceType){
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
	
	public boolean move(Direction direction){
		return table.move(direction);
	}
	
	public boolean flip(){
		return table.flip();
	}
	
	public int clearRows(){
		return table.clearRows();
	}
	
	public Field[][] getFields(){
		return table.getFields();
	}
}