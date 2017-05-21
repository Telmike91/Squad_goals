package controller;

import java.sql.*;
import java.util.ArrayList;

import model.Player;

/**
 * <p>
 * Ezzel a controllerrel tartjuk majd a kapcsolatot az adatbázissal
 * Ezen keresztül INSERT-elhetünk, UPFATE-elhetünk és SELECT-elhetünk adatokat.
 * </p>
 */
public class TopListController {
    private Connection c;
    private Statement stmt;

    public TopListController() {
        c = null;
        stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:toplist.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    /**
     * Lekérdezzük az adatbázisból a felhasználók adatai
     *
     * @return ArrayList<Player> az adatbázisban szereplő felhasználók adatai
     */
    public ArrayList<Player> getScores() {
        ArrayList<Player> players = new ArrayList<>();
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM toplist ORDER BY score DESC");

            while (rs.next() != false) {
                players.add(new Player(rs.getString(1),
                        rs.getInt(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return players;
    }

    /**
     * Beirhatjuk vagy frissithetjük az adatbázisban szereplő játekos pontszámát
     *
     * @param nev  játékos neve
     * @param pont játékos pontszáma
     */
    public void updateScore(String nev, int pont) {
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM toplist where name=" + nev);
            if (rs.next()) {
                //Benne van az adatbázisban, csak updatelni kell a pontszámat, feltéve ha az nagyobb mint az adatbazisban szereplő
                int result = rs.getInt(2);
                if (result < pont) {
                    stmt.executeUpdate("update toplist set score = " + pont + " where name = " + nev);
                }
            } else {
                //Nincs még ilyen ember az adatbazisban , 1-1 ben beletesszük
                stmt.executeUpdate("insert into toplist values (" + nev + ", " + pont + ")");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
