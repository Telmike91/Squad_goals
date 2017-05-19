package model;
//ez a class lesz felelős a játéktábla létrehozásáért és a controllnak az adatok továbbadásával
import java.util.*;

public class Model{
	public static void main(String[] args){
		Table table = new Table(20, 10);
		ArrayList<Coordinate> pieceCoord= new ArrayList<Coordinate>();
		
		pieceCoord.add(new Coordinate(0, 0));
		pieceCoord.add(new Coordinate(0, 1));
		pieceCoord.add(new Coordinate(1, 0));
		pieceCoord.add(new Coordinate(1, 1));
		
		ArrayList<Coordinate> pieceCoord2 = new ArrayList<Coordinate>();
		
		pieceCoord2.add(new Coordinate(0, 0));
		pieceCoord2.add(new Coordinate(0, 1));
		pieceCoord2.add(new Coordinate(0, 2));
		pieceCoord2.add(new Coordinate(0, 3));
		
		Piece piece = new Piece(pieceCoord, Color.BLUE);
		Piece piece2 = new Piece(pieceCoord2, Color.YELLOW);
		
		table.enterPiece(piece);
		
		System.out.println("******************kocka betesz************************");
		
		for(int i = 19; i >= 0; --i){
			for(int j = 9; j >= 0; --j){
				if(table.fields[i][j].isMoveable()){
					System.out.print('1');
				}else{
					if(table.fields[i][j].isEmpty()){
						System.out.print('0');
					}else{
						System.out.print('2');
					}
					
				}
			}
			
			System.out.println(' ');
		}
		
		System.out.println("*******************kocka lemegy 1-el***********************");
		table.move(Direction.DOWN);
		
		for(int i = 19; i >= 0; --i){
			for(int j = 9; j >= 0; --j){
				if(table.fields[i][j].isMoveable()){
					System.out.print('1');
				}else{
					if(table.fields[i][j].isEmpty()){
						System.out.print('0');
					}else{
						System.out.print('2');
					}
					
				}
			}
			
			System.out.println(' ');
		}
		
		System.out.println("**************kocka lemegy az aljara*********************");
		
		for(int i = 0; i<20; ++i){
			table.move(Direction.DOWN);
		}
		
		for(int i = 19; i >= 0; --i){
			for(int j = 9; j >= 0; --j){
				if(table.fields[i][j].isMoveable()){
					System.out.print('1');
				}else{
					if(table.fields[i][j].isEmpty()){
						System.out.print('0');
					}else{
						System.out.print('2');
					}
					
				}
			}
			
			System.out.println(' ');
		}
		
		System.out.println("************uj kocka*********************");
		
		table.enterPiece(piece);
		
		for(int i = 19; i >= 0; --i){
			for(int j = 9; j >= 0; --j){
				if(table.fields[i][j].isMoveable()){
					System.out.print('1');
				}else{
					if(table.fields[i][j].isEmpty()){
						System.out.print('0');
					}else{
						System.out.print('2');
					}
					
				}
			}
			
			System.out.println(' ');
		}
		
		System.out.println("******************kocka lemegy a masikra********************");
		
		for(int i = 0; i<20; ++i){
			table.move(Direction.DOWN);
		}
		
		for(int i = 19; i >= 0; --i){
			for(int j = 9; j >= 0; --j){
				if(table.fields[i][j].isMoveable()){
					System.out.print('1');
				}else{
					if(table.fields[i][j].isEmpty()){
						System.out.print('0');
					}else{
						System.out.print('2');
					}
					
				}
			}
			
			System.out.println(' ');
		}
		
		System.out.println("******************egyenes bejon*******************");
		
		table.enterPiece(piece2);
		
		for(int i = 19; i >= 0; --i){
			for(int j = 9; j >= 0; --j){
				if(table.fields[i][j].isMoveable()){
					System.out.print('1');
				}else{
					if(table.fields[i][j].isEmpty()){
						System.out.print('0');
					}else{
						System.out.print('2');
					}
					
				}
			}
			
			System.out.println(' ');
		}
		
		System.out.println("*************egyenes lemegy a tobbire******************");
		
		for(int i = 0; i<20; ++i){
			table.move(Direction.DOWN);
		}
		
		for(int i = 19; i >= 0; --i){
			for(int j = 9; j >= 0; --j){
				if(table.fields[i][j].isMoveable()){
					System.out.print('1');
				}else{
					if(table.fields[i][j].isEmpty()){
						System.out.print('0');
					}else{
						System.out.print('2');
					}
					
				}
			}
			
			System.out.println(' ');
		}
		
		System.out.println("*************************************************");
	}
}