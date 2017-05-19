package model;
// ez a class a blokkok relatív koordinátáinak tárolására van 
// (azaz, pl. a kocka blokknak (0,0), (0,1), (1,0), (1,1)-ek a koordinátái)

// Square : (0,0), (0,1), (1,0), (1,1)
// I: (0,0), (1,0), (2,0), (3,0)
// T: (0,0), (1,-1), (1,0), (1,1)
public class Coordinate{
	private int x;
	private int y;
	
	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
}