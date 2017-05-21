package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Ezen a grafikai felületen lehet továbbmenni a többi ablakra, illetve kilépni
 * a főprogramból
 */
public class Menu extends JPanel {
    private final JLabel _menuText;
    private final JButton _newGame;
    private final JButton _topList;
    private final JButton _exit;

    
    /**
     * 
     * @param w - a főablak referenciája, hogy a changePanel-t megtudjuk hívni
     */
    public Menu(MainWindow w) {      
        _menuText = new JLabel("Tetris");
        _menuText.setFont(new Font("Times New Roman", Font.BOLD, 20));        
        _newGame = new JButton("New Game");
        _topList = new JButton("Top list");
        _exit = new JButton("Exit");
        
        _menuText.setAlignmentX(Component.CENTER_ALIGNMENT);
        _newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        _topList.setAlignmentX(Component.CENTER_ALIGNMENT);
        _exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        _newGame.addActionListener((ActionEvent e) -> {
            w.changePanel("newGame");
        });
             
        _topList.addActionListener((ActionEvent e) -> {
            w.changePanel("topList");
        });
        
        _exit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        super.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));        
        
        Dimension d = new Dimension(0, 10);
        super.add(_menuText);
        super.add(Box.createRigidArea(d));
        super.add(_newGame);
        super.add(Box.createRigidArea(d));
        super.add(_topList);
        super.add(Box.createRigidArea(d));
        super.add(_exit);
    }    
}
