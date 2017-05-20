package graphics;

import javax.swing.*;
import java.awt.*;

/**
 * <p>
 * The main window of the program.
 * </p>
 */
public class MainWindow extends JFrame {
    private Menu _menuPanel;
    private Settings _settingsPanel;
    private TopList _topListPanel;
    private GamePanel _gamePanel;
    private JPanel _currPanel;
    
    /**
     * Creates the main window. 
     * @param name name of the window. In this case it should be "Tetris"     
     */
    public MainWindow(String name) {
        super(name);         
        super.setLayout(new BorderLayout());
    }
    
    /**
     * No parameters required. Initializes the mainWindow
     */
    public void init() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);       
        this.setMinimumSize(new Dimension(400,400));
        
        
        this._menuPanel = new Menu(this);        
        this._currPanel = _menuPanel;
        this._settingsPanel = new Settings(this);
        this._topListPanel = new TopList(this);
        this._gamePanel = new GamePanel(this);        
        _gamePanel.setFocusable(true);  
        add(_menuPanel, BorderLayout.CENTER);     
                
        this.setVisible(true);
    } 
            
    /**
     * 
     * @param which "which" describes which panel should the program change to
     * possible values:
     * <ul>
     *  <li>menu</li>
     *  <li>topList</li>
     *  <li>settings</li>
     *  <li>newGame</li>
     * </ul>
     */
    public void changePanel(String which) {
        remove(_currPanel);
        switch(which) {
            case "menu":
                add(_menuPanel);
                _currPanel = _menuPanel;
                break;
            case "topList":
                add(_topListPanel);
                _currPanel = _topListPanel;
                _topListPanel.refreshTopList();
                break;
            case "settings":
                add(_settingsPanel);
                _currPanel = _settingsPanel;
                break;
            case "newGame":
                add(this._gamePanel);
                _currPanel = _gamePanel;
                _gamePanel.requestFocus();
                _gamePanel.startGame(_settingsPanel.getColors());
                break;
            default:
                System.exit(1);
        }     
        pack();
        revalidate();
        repaint();
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        final MainWindow mainWindow = new MainWindow("Tetris");
        mainWindow.init();
    }

}
