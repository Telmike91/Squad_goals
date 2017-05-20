package model;
//az egész játékmenet központja, ennek a függvényeit hívja meg a control a view műveletei alapján
//egyelőre úgy van megcsinálva, hogy amint egy sablont a táblára másolt a gép az megszűnik koherens egységként működni
//nem tudom, hogy ez mennyire jó irány volt, de egyelőre nem akarom újrakezdeni/kijavítani

public class Table{
	private int height;
	private int width;
	private Field[][] fields;
	
	public Table(int height, int width){
		this.height = height;
		this.width = width;
		this.fields = new Field[this.height][this.width];
		
		for(int i = 0; i < this.height; ++i){
			for(int j = 0; j < this.width; ++j){
				this.fields[i][j] = new Field(i, j);
			}
		}
	}
	
	//bemásolja a blokkot a képernyő legtetejére, próbálva lehető legközépebbre helyezni
	public boolean enterPiece(Piece piece){
		Field field;
		
		for(Coordinate coordinate : piece.getPieceCoordinates()){
			field = this.fields[(this.height-1)+coordinate.getX()-piece.getMaxHeight()][((this.width/2)-1) + coordinate.getY() - (piece.getMaxWidth()/2)];
			
			if(field.isEmpty()){
				field.setFieldState(FieldState.OCCUPIED);
				field.setColor(piece.getColor());
				field.start();
			}else{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean move(Direction direction){
		switch(direction){
			case DOWN:
				for(int i = 1; i < this.height; ++i){
					for(int j = 0; j < this.width; ++j){
						if(this.fields[i][j].isMoveable() && !(this.fields[i-1][j].isEmpty()) && !(this.fields[i-1][j].isMoveable())){
							stopPieces();
							
							return false;
						}
					}
				}
				
				for(int i = 0; i < this.width; ++i){
					if(fields[0][i].isMoveable()){
						stopPieces();
						
						return false;
					}
				}
				
				for(int i = 1; i < this.height; ++i){
					for(int j = 0; j < this.width; ++j){
						if(this.fields[i][j].isMoveable()){
							fields[i-1][j].setFieldState(fields[i][j].getFieldState());
							fields[i-1][j].setColor(fields[i][j].getColor());
							fields[i-1][j].start();
							
							fields[i][j].setFieldState(FieldState.EMPTY);
							fields[i][j].setColor(Color.DEFAULT);
							fields[i][j].stop();
						}
					}
				}
				
				break;
			case LEFT:
				
				break;
			case RIGHT:
				
				break;
		}
		
		return true;
	}
	
	private void stopPieces(){
		for(int i = 0; i < this.height; ++i){
			for(int j = 0; j < this.width; ++j){
				this.fields[i][j].stop();
			}
		}
	}
	
	public boolean flip(){
		return false;
	}
	
	public int clearRows(){
		int n = 0;
		
		for(int i = 0; i < this.height; ++i){
			if(clearRow(i)){
				n += 1;
			}
		}
		
		int s = 0;
		
		for(int i = 0; i < n; ++i){
			s += 100*(2^n);
		}
		
		return s;
	}
	
	private boolean clearRow(int height){
		for(int i = 0; i < this.width; ++i){
			if(this.fields[height][i].isEmpty()){
				return false;
			}
		}
		
		for(int i = 0; i < this.width; ++i){
			this.fields[height][i].setFieldState(FieldState.EMPTY);
			this.fields[height][i].setColor(Color.DEFAULT);
		}
		
		shiftRows(height);
		
		return true;
	}
	
	private void shiftRows(int height){
		for(int i = height; i < this.height; ++i){
			for(int j = 0; j < this.width; ++j){
				fields[i][j].setFieldState(fields[i+1][j].getFieldState());
				fields[i][j].setColor(fields[i+1][j].getColor());
			}
		}
	}
	
	public Field[][] getFields(){
		return fields;
	}
}