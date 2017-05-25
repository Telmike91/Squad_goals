package model;

/**
 * Ebben az osztályban tároljuk a játékos nevét és pontszámát. Ez az adatbázis
 * nál lesz fontos
 */
public class Player {
    private final String _name;
    private final int _score;
    
    /**
     * Egyszerű konstuktor
     * @param name A játékos neve
     * @param score A játékos pontszáma
     */
    public Player(String name, int score) {
        _name = name;
        _score = score;
    }

    /**
     * Egyszerű getter
     * @return visszaadja a nevet
     */
    public String getName() {
        return _name;
    }

    /**
     * Egyszerű getter
     * @return visszaadja a pontsázmot
     */
    public int getScore() {
        return _score;
    }
}
