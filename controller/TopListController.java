/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;
import java.util.ArrayList;
import model.Player;
public class TopListController {
    private Connection c;
    private Statement stmt;
    public TopListController() {        
        c = null;        
        stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:toplist.db");
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
    
    public ArrayList<Player> getScores() {
        ArrayList<Player> players = new ArrayList<>();
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM toplist ORDER BY score DESC");            

            while(rs.next() != false) {
                players.add(new Player(rs.getString(1),
                                        rs.getInt(2)));
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return players;
    }
}
