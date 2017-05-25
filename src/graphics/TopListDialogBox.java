package graphics;

import controller.GameController;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
import javax.swing.JTextField;

/**
 * A játék végén megjelenik ez a DialogBox, ahova beírhatjuk a nevünket 
 */
public class TopListDialogBox extends JDialog {
    private final JTextField _nameInput;
    private final JButton _return;
    
    /**
     * Elhelyezi a Dialog Boxon belül a komponenseket és beállítja a méreteket.
     */
    public TopListDialogBox() {
        super();
        super.pack();
        super.setMinimumSize(new Dimension(200,200));
        _nameInput = new JTextField();        
        
        super.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 10;
        super.add(new JLabel("Name"), c);
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 10;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        super.add(_nameInput, c);
        c.gridx = 0;
        c.gridy = 2;
        _return = new JButton("Return");        
        super.add(_return, c);        
    }
    
    /**
     * Egyszerű getter. Lekérdezi a TextField-en belül lévő szöveget
     * @return Visszaadja a játékos nevét.
     */
    public String getPlayerName() {
        return _nameInput.getText();
    }

    /**
     * Beállítja a gombra az ActionListenert
     * @param controller A GameController referenciája
     */
    void setButtonListener(GameController controller) {
        _return.addActionListener(controller);
        _return.setActionCommand("ScoreSave");
    }
}
