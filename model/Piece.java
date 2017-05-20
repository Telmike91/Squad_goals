package model;
//ezzel a classal lehet megalkotni a sémákat amik a konkrét táblára kerülnek, azonban irányítani függvények megadásával ezt nem lehet!

import java.util.*;

public class Piece{
	private ArrayList<Coordinate> pieceCoordinates;
	private PieceColor color;
	private int maxHeight;
	private int maxWidth;
	
	public Piece(ArrayList<Coordinate> pieceCoordinates, PieceColor color){
		this.pieceCoordinates = pieceCoordinates;
		this.color = color;
		
		this.maxHeight = 0;
		
		for(Coordinate coordinate : pieceCoordinates){
			if(coordinate.getX() > this.maxHeight){
				this.maxHeight = coordinate.getX();
			}
		}
		
		this.maxWidth = 0;
		
		for(Coordinate coordinate : pieceCoordinates){
			if(coordinate.getY() > this.maxWidth){
				this.maxWidth = coordinate.getY();
			}
		}
	}                
	
	public ArrayList<Coordinate> getPieceCoordinates(){
		return pieceCoordinates;
	}
	
	public PieceColor getColor(){
		return this.color;
	}
	
	public int getMaxHeight(){
		return this.maxHeight;
	}
	
	public int getMaxWidth(){
		return this.maxWidth;
	}

    public void setColor(PieceColor color) {
        this.color = color;
    }

}