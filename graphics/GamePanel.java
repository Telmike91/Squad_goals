package graphics;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private final JButton _return;
    
    public GamePanel(MainWindow w) {        
        _return = new JButton("Return");
                
        _return.addActionListener((ActionEvent e) -> {
            w.changePanel("menu");
        });
        
        super.setLayout(new FlowLayout());
        super.add(_return); 
    }   
}
