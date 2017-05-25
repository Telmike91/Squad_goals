package model;

/**
 * A nagy tábla építőkövei, jelenleg ezek a mezők figyelik a játék állapotát
 */
public class Field {

    private FieldState fieldState;
    private PieceColor color;
    private int x;
    private int y;
    private boolean isMoveable;

    /**
     * Létrehozza a tábla alapegységét az egyik mezőt
     * @param x Az x koordináta
     * @param y Az y koordináta
     */
    public Field(int x, int y) {
        this.fieldState = FieldState.EMPTY;
        this.color = PieceColor.DEFAULT;
        this.x = x;
        this.y = y;
        this.isMoveable = false;
    }

    /**
     * A megadott paraméterekből választja ki a mezőt
     * @param x A sor
     * @param y Az oszlop
     * @return Visszaadja a mezőt ha létezik, különben null
     */
    public Field getField(int x, int y) {
        if (this.x == x && this.y == y) {
            return this;
        } else {
            return null;
        }
    }

    /**     
     * @return Eldönti hogy üres-e a mező vagy sem
     */
    public boolean isEmpty() {
        return this.fieldState.equals(FieldState.EMPTY);
    }

    /**     
     * @return visszaadja hogy a mező-n mozgatható elem van vagy nem
     */
    public boolean isMoveable() {
        return this.isMoveable;
    }

    /**
     * Egyszerű getter
     * @return a Fieldnek az állapota
     */
    public FieldState getFieldState() {
        return this.fieldState;
    }

    /**
     * Egyszerű getter
     * @return A field-nek a színe
     */
    public PieceColor getColor() {
        return this.color;
    }

    /**
     * Egyszerű getter
     * @return Az X koordináta azaz hanyadik sor
     */
    public int getX() {
        return this.x;
    }

    /**
     * Egyszerű getter
     * @return Az Y koordináta azaz hanyadik oszlop
     */
    public int getY() {
        return this.y;
    }

    /**
     * Egyszerű setter
     * @param fieldState milyen legyen a mező állapota
     */
    public void setFieldState(FieldState fieldState) {
        this.fieldState = fieldState;
    }

    /**
     * Egyszerű setter
     * @param color milyen színű legyen
     */
    public void setColor(PieceColor color) {
        this.color = color;
    }

    /**
     * Egy setter, ami a mező mozdíthatatlanná teszi, azaz vagy nincs ezen Piece
     * vagy a Piece leért az aljára
     */
    public void stop() {
        this.isMoveable = false;
    }

    /**
     * Egy setter, ami a mezőt mozdíthatóvá teszi, azaz egy Piece lett itt
     */
    public void start() {
        this.isMoveable = true;
    }
}
