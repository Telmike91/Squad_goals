package model;
/**
 * <p>
 * ez a class a blokkok relatív koordinátáinak tárolására van 
 * (azaz, pl. a kocka blokknak (0,0), (0,1), (1,0), (1,1)-ek a koordinátái)
 * </p>
 */
public class Coordinate{
	private int x;
	private int y;
	
        /**
         * Egyszerű konstruktor, amivel beállítjuk a koordinátákat
         * @param x az X koordináta azaz hanyadik sor
         * @param y az Y koordináta azaz hanyadik oszlop
         */
	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	
        /**
         * Egyszerű getter
         * @return az X koordináta azaz hanyadik sor
         */
	public int getX(){
		return this.x;
	}
	
        /**
         * Egyszerű getter
         * @return Az Y koordináta azaz hanyadik oszlop
         */
	public int getY(){
		return this.y;
	}
}