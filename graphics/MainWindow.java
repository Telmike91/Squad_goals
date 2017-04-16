package graphics;

import javax.swing.*;
import java.awt.*;

/**
 * <p>
 * The main window of the program.
 * </p>
 * 
 */
public class MainWindow extends JFrame {
    private JPanel _content;
    
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
        
        this._content = new Menu(this);
        add(_content, BorderLayout.CENTER);     
                
        this.setVisible(true);
    } 
    
    /**
     * 
     * @param which "which" describes which panel should the program change to
     * possible values:
     * <ul>
     *  <li> menu</li>
     *  <li> topList</li>
     *  <li>settings</li>
     *  <li>newGame</li>
     * </ul>
     */
    public void changePanel(String which) {
        _content.removeAll();       
        switch(which) {
            case "menu":
                _content = new Menu(this);
                break;
            case "topList":
                _content = new TopList(this);
                break;
            case "settings":
                _content = new Settings(this);
                break;
            case "newGame":
                _content = new GamePanel(this);
                break;
            default:
                System.exit(1);
        }        
        this.add(_content, BorderLayout.CENTER);
        this.revalidate();
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
