package graphics;

import java.awt.event.ActionEvent;
import javax.swing.*;
import controller.TopListController;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import model.Player;

/**
 * A toplista grafikus felülete 
 */
public class TopList extends JPanel {
    private final JPanel _scoreList;
    private final JButton _return;
    private final ArrayList<JLabel> _scorers;
    private final TopListController _topListController;
    
    public TopList(MainWindow w) {
        _scoreList = new JPanel();
        _scoreList.setLayout(new GridBagLayout());
        _scorers = new ArrayList<>();
        _return = new JButton("Return");
        _topListController = new TopListController();
                
        _return.addActionListener((ActionEvent e) -> {
            w.changePanel("menu");
        });
        
        super.setLayout(new BorderLayout());
        super.add(_scoreList, BorderLayout.PAGE_START);
        super.add(_return, BorderLayout.PAGE_END);         
    }   
    
    public void refreshTopList() {
        _scoreList.removeAll();   
        _scorers.removeAll(_scorers);
        int i = 1;
        for(Player p : _topListController.getScores()) {
            _scorers.add(new JLabel(i + ". " + p.getName() + " " + p.getScore()));
            i++;
        }
        
        GridBagConstraints c = new GridBagConstraints();        
        c.gridy = 0;
        i = 0;
        for(JLabel l : _scorers) {                        
            c.gridy = i;
            _scoreList.add(l, c);
            i++;
        }
        
    }
}
