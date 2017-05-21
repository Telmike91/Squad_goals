package model;
//ez a class a blokkok relatív koordinátáinak tárolására van (azaz, pl. a kocka blokknak (0,0), (0,1), (1,0), (1,1)-ek a koordinátái)

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