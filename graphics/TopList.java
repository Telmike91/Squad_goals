package graphics;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class TopList extends JPanel {
    private final JButton _return;
    
    public TopList(MainWindow w) {        
        _return = new JButton("Return");
                
        _return.addActionListener((ActionEvent e) -> {
            w.changePanel("menu");
        });
        
        super.setLayout(new FlowLayout());
        super.add(_return); 
    }   
}
