package graphics;

import javax.swing.*;
import java.awt.*;

/**
 * <p>
 * A főablaka a programnak
 * </p>
 */
public class MainWindow extends JFrame {
    private Menu _menuPanel;
    private TopList _topListPanel;
    private GamePanel _gamePanel;
    private JPanel _currPanel;
    
    /**
     * Létrehozza a főablakot
     * @param name A főablak neve. Ezesetben "Tetris"    
     */
    public MainWindow(String name) {
        super(name);         
        super.setLayout(new BorderLayout());
    }
    
    /**
     * Inicializálja a főablakot
     */
    public void init() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);       
        this.setMinimumSize(new Dimension(400,400));
               
        this._menuPanel = new Menu(this);        
        this._currPanel = _menuPanel;
        this._topListPanel = new TopList(this);
        this._gamePanel = new GamePanel(this, _topListPanel.getController());        
        _gamePanel.setFocusable(true);  
        add(_menuPanel, BorderLayout.CENTER);     
                
        this.setVisible(true);
    } 
            
    /**
     * Az ablak változtatás metódusa.
     * @param which Ebbe írjuk le melyik ablakra szeretnénk váltani. Lehetséges értékek
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
            case "newGame":
                add(this._gamePanel);
                _currPanel = _gamePanel;
                _gamePanel.requestFocus();
                _gamePanel.startGame();
                break;
            default:
                System.exit(1);
        }     
        pack();
        revalidate();
        repaint();
    }
    
    /**
     * A program fő függvénye. Itt kezdődik minden
     * @param args a program argumentumai. Bármennyi lehet a program nem nézi őket
     */
    public static void main(String[] args) {
        final MainWindow mainWindow = new MainWindow("Tetris");
        mainWindow.init();
    }

}
