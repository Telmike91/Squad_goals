package model;
//a nagy tábla építőkövei, jelenleg ezek a mezők figyelik a játék állapotát

public class Field {

    private FieldState fieldState;
    private PieceColor color;
    private int x;
    private int y;
    private boolean isMoveable;

    public Field(int x, int y) {
        this.fieldState = FieldState.EMPTY;
        this.color = PieceColor.DEFAULT;
        this.x = x;
        this.y = y;
        this.isMoveable = false;
    }

    public Field getField(int x, int y) {
        if (this.x == x && this.y == y) {
            return this;
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return this.fieldState.equals(FieldState.EMPTY);
    }

    public boolean isMoveable() {
        return this.isMoveable;
    }

    public FieldState getFieldState() {
        return this.fieldState;
    }

    public PieceColor getColor() {
        return this.color;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setFieldState(FieldState fieldState) {
        this.fieldState = fieldState;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }

    public void stop() {
        this.isMoveable = false;
    }

    public void start() {
        this.isMoveable = true;
    }
}
