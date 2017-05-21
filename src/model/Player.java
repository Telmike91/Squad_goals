package model;

/**
 * Ebben az osztályban tároljuk a játékos nevét és pontszámát.
 * 
 */
public class Player {
    private final String _name;
    private final int _score;
    
    public Player(String name, int score) {
        _name = name;
        _score = score;
    }

    public String getName() {
        return _name;
    }

    public int getScore() {
        return _score;
    }
}
