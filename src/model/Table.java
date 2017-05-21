package model;
//az egész játékmenet központja, ennek a függvényeit hívja meg a control a view műveletei alapján
//egyelőre úgy van megcsinálva, hogy amint egy sablont a táblára másolt a gép az megszűnik koherens egységként működni
//nem tudom, hogy ez mennyire jó irány volt, de egyelőre nem akarom újrakezdeni/kijavítani

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Table {

    private int height;
    private int width;
    private Field[][] fields;
    private int rotation;

    public Table(int height, int width) {
        this.height = height;
        this.width = width;
        this.fields = new Field[this.height][this.width];

        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                this.fields[i][j] = new Field(i, j);
            }
        }

        this.rotation = 0;
    }

    public void reset() {
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                this.fields[i][j].setColor(PieceColor.DEFAULT);
                this.fields[i][j].setFieldState(FieldState.EMPTY);
                this.fields[i][j].stop();
            }
        }
    }

    /**
     * Bemásolja a blokkot a képernyő legtetejére, próbálva lehető legközépebbre
     * helyezni
     *
     * @return Igaz, ha behelyezhető különben hamis
     */
    public boolean enterPiece(Piece piece) {
        Field field;

        for (Coordinate coordinate : piece.getPieceCoordinates()) {
            field = this.fields[(this.height - 1) + coordinate.getX() - piece.getMaxHeight()][((this.width / 2) - 1) + coordinate.getY() - (piece.getMaxWidth() / 2)];
            if (field.isEmpty()) {
                field.setFieldState(FieldState.OCCUPIED);
                field.setColor(piece.getColor());
                field.start();
            } else {
                System.out.println("FALSE");
                return false;
            }
        }
        System.out.println("testing");
        this.rotation = 0;
        return true;
    }

    /**
     * Mozgatja a jelenlegi Piece-t jobbra vagy balra
     *
     * @param direction Az adott irány
     * @return Igaz, ha mozgatható különben hamis
     */
    public boolean moveLeftRight(Direction direction) {
        switch (direction) {
            case RIGHT:
                for (int i = 0; i < this.height; ++i) {
                    for (int j = 1; j < this.width; ++j) {
                        if (fields[i][j].isMoveable() && !(fields[i][j - 1].isEmpty()) && !(fields[i][j - 1].isMoveable())) {
                            return false;
                        }
                    }
                }

                for (int i = 0; i < this.height; ++i) {
                    if (fields[i][0].isMoveable()) {
                        return false;
                    }
                }

                for (int i = 0; i < this.height; ++i) {
                    for (int j = 1; j < this.width; ++j) {
                        if (this.fields[i][j].isMoveable()) {
                            fields[i][j - 1].setFieldState(fields[i][j].getFieldState());
                            fields[i][j - 1].setColor(fields[i][j].getColor());
                            fields[i][j - 1].start();

                            fields[i][j].setFieldState(FieldState.EMPTY);
                            fields[i][j].setColor(PieceColor.DEFAULT);
                            fields[i][j].stop();
                        }
                    }
                }
                break;
            case LEFT:
                for (int i = 0; i < this.height; ++i) {
                    for (int j = this.width - 2; j >= 0; --j) {
                        if (fields[i][j].isMoveable() && !(fields[i][j + 1].isEmpty()) && !(fields[i][j + 1].isMoveable())) {

                            return false;
                        }
                    }
                }

                for (int i = 0; i < this.height; ++i) {
                    if (fields[i][width - 1].isMoveable()) {
                        return false;
                    }
                }

                for (int i = 0; i < this.height; ++i) {
                    for (int j = this.width - 2; j >= 0; --j) {
                        if (this.fields[i][j].isMoveable()) {
                            fields[i][j + 1].setFieldState(fields[i][j].getFieldState());
                            fields[i][j + 1].setColor(fields[i][j].getColor());
                            fields[i][j + 1].start();

                            fields[i][j].setFieldState(FieldState.EMPTY);
                            fields[i][j].setColor(PieceColor.DEFAULT);
                            fields[i][j].stop();
                        }
                    }
                }
                break;
        }
        return true;
    }

    /**
     * Mozgatja az adott Piece-t lefele
     * @return Igaz, ha lehet mozgatni, különben hamis
     */
    public boolean move() {
        for (int i = 1; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                if (this.fields[i][j].isMoveable() && !(this.fields[i - 1][j].isEmpty()) && !(this.fields[i - 1][j].isMoveable())) {
                    stopPieces();
                    return false;
                }
            }
        }

        for (int i = 0; i < this.width; ++i) {
            if (fields[0][i].isMoveable()) {
                stopPieces();
                return false;
            }
        }

        for (int i = 1; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                if (this.fields[i][j].isMoveable()) {
                    fields[i - 1][j].setFieldState(fields[i][j].getFieldState());
                    fields[i - 1][j].setColor(fields[i][j].getColor());
                    fields[i - 1][j].start();

                    fields[i][j].setFieldState(FieldState.EMPTY);
                    fields[i][j].setColor(PieceColor.DEFAULT);
                    fields[i][j].stop();
                }
            }
        }

        return true;
    }

    /**
     * A jelenlegi Piece-t megállítjuk, hogy nem lehet ezt már mozgatni.
     */
    private void stopPieces() {
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                this.fields[i][j].stop();
            }
        }
    }

    /**
     * Forgatjuk a jelenlegi Piece-t
     * @return Igaz, ha lehetett mozgatni, különben hamis
     */
    public boolean flip() {
        ArrayList<Coordinate> pieceCoords = new ArrayList<Coordinate>();
        ArrayList<Coordinate> newPieceCoords = new ArrayList<Coordinate>();
        PieceColor pieceColor = PieceColor.DEFAULT;

        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                if (fields[i][j].isMoveable()) {
                    pieceCoords.add(new Coordinate(fields[i][j].getX(), fields[i][j].getY()));
                    pieceColor = fields[i][j].getColor();
                }
            }
        }

        switch (pieceColor) {
            case CYAN:
                switch (rotation) {
                    case 0:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(2));
                        break;
                    case 1:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(1));
                        break;
                    case 2:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(1));
                        break;
                    case 3:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(2));
                        break;
                }
                break;
            case BLUE:
                switch (rotation) {
                    case 0:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(1));
                        break;
                    case 1:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(1));
                        break;
                    case 2:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(2));
                        break;
                    case 3:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(2));
                        break;
                }
                break;
            case ORANGE:
                switch (rotation) {
                    case 0:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(1));
                        break;
                    case 1:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(2));
                        break;
                    case 2:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(2));
                        break;
                    case 3:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(1));
                        break;
                }
                break;
            case GREEN:
                switch (rotation) {
                    case 0:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(2));
                        break;
                    case 1:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(1));
                        break;
                    case 2:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(1));
                        break;
                    case 3:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(1));
                        break;
                }
                break;
            case PURPLE:
                switch (rotation) {
                    case 0:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(1));
                        break;
                    case 1:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(1));
                        break;
                    case 2:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(2));
                        break;
                    case 3:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(2));
                        break;
                }
                break;
            case RED:
                switch (rotation) {
                    case 0:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(3));
                        break;
                    case 1:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(2));
                        break;
                    case 2:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(0));
                        break;
                    case 3:
                        newPieceCoords = rotateArray(pieceCoords, pieceCoords.get(1));
                        break;
                }
                break;
            case YELLOW:
                return true;
        }

        try {
            for (Coordinate coordinate : newPieceCoords) {
                if (!(fields[coordinate.getX()][coordinate.getY()].isEmpty()) && !(fields[coordinate.getX()][coordinate.getY()].isMoveable())) {
                    return false;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

        for (Coordinate coordinate : pieceCoords) {
            fields[coordinate.getX()][coordinate.getY()].setFieldState(FieldState.EMPTY);
            fields[coordinate.getX()][coordinate.getY()].setColor(PieceColor.DEFAULT);
            fields[coordinate.getX()][coordinate.getY()].stop();
        }

        for (Coordinate coordinate : newPieceCoords) {
            fields[coordinate.getX()][coordinate.getY()].setFieldState(FieldState.OCCUPIED);
            fields[coordinate.getX()][coordinate.getY()].setColor(pieceColor);
            fields[coordinate.getX()][coordinate.getY()].start();
        }

        if (rotation == 3) {
            rotation = 0;
        } else {
            rotation += 1;
        }

        return true;
    }

    private ArrayList<Coordinate> rotateArray(ArrayList<Coordinate> pieceCoords, Coordinate around) {
        ArrayList<Coordinate> newPieceCoords = new ArrayList<Coordinate>();

        for (Coordinate coordinate : pieceCoords) {
            newPieceCoords.add(rotate(coordinate, around));
        }

        return newPieceCoords;
    }

    private Coordinate rotate(Coordinate what, Coordinate around) {
        Point center = new Point(around.getX(), around.getY());

        double[] pt = {(double) (what.getX()), (double) (what.getY())};
        AffineTransform.getRotateInstance(Math.toRadians(90), center.x, center.y)
                .transform(pt, 0, pt, 0, 1); // specifying to use this double[] to hold coords
        double newX = pt[0];
        double newY = pt[1];

        return new Coordinate((int) (newX), (int) (newY));
    }

    public int clearRows() {
        int n = 0;

        for (int i = 0; i < this.height; i++) {
            if (clearRow(i)) {
                n += 1;
                i -= 1;
            }
        }

        int s = 0;

        for (int i = 0; i < n; ++i) {
            s += 100 * (Math.pow(2, n));
        }

        return s;
    }

    private boolean clearRow(int height) {
        for (int i = 0; i < this.width; ++i) {
            if (this.fields[height][i].isEmpty()) {
                return false;
            }
        }

        for (int i = 0; i < this.width; ++i) {
            this.fields[height][i].setFieldState(FieldState.EMPTY);
            this.fields[height][i].setColor(PieceColor.DEFAULT);
        }

        shiftRows(height);

        return true;
    }

    private void shiftRows(int height) {
        System.out.println("MAGASSAG: " + height);
        for (int i = height; i < this.height - 1; i++) {
            for (int j = 0; j < this.width; j++) {
                fields[i][j].setFieldState(fields[i + 1][j].getFieldState());
                fields[i][j].setColor(fields[i + 1][j].getColor());
            }
        }
    }

    public Field[][] getFields() {
        return fields;
    }
}
