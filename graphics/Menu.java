package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Menu extends JPanel {
    private final JLabel _menuText;
    private final JButton _newGame;
    private final JButton _settings;
    private final JButton _topList;
    private final JButton _exit;

    private int test;
    
    @Override
    public void repaint() {
        super.repaint();
        System.out.println("Testing" + test++);
    }
    public Menu(MainWindow w) {      
        test = 0;
        _menuText = new JLabel("Tetris");
        _newGame = new JButton("New Game");
        _settings = new JButton("Settings");
        _topList = new JButton("Top list");
        _exit = new JButton("Exit");
        
        _menuText.setAlignmentX(Component.CENTER_ALIGNMENT);
        _newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        _settings.setAlignmentX(Component.CENTER_ALIGNMENT);
        _topList.setAlignmentX(Component.CENTER_ALIGNMENT);
        _exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        _newGame.addActionListener((ActionEvent e) -> {
            w.changePanel("newGame");
        });
        
        _settings.addActionListener((ActionEvent e) -> {
            w.changePanel("settings");
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
        super.add(_settings);
        super.add(Box.createRigidArea(d));
        super.add(_topList);
        super.add(Box.createRigidArea(d));
        super.add(_exit);
    }    
}
