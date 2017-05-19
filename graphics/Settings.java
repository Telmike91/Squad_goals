package graphics;

import controller.SettingsController;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Settings extends JPanel {
    private final String[] pieces;
    private final JPanel _colorPanel;
    private final ArrayList<ColorComboBox> _comboBoxes;
    private final SettingsController _settingsController;
    private final JButton _return;
    
    
    /**
     * 
     * @param w - Főablak referenciája, hogy atadjuk a controllernek a hozzáférést
     */
    public Settings(MainWindow w) {
        this.pieces = new String[]{"Straight", "Square", "T", "Right Snake", "Left Snake", "J", "L"};
        _return = new JButton("Return");
        _colorPanel = new JPanel();
        _comboBoxes = new ArrayList(); 
        _settingsController = new SettingsController(w);
                               
        _return.addActionListener(_settingsController);
        _return.setActionCommand("Window");

        // Init ComboBoxes
        _colorPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,0,5,5);   
        c.ipadx = 0;
        for(int i = 0; i < 7; i++) {
            _comboBoxes.add(new ColorComboBox());
        }
        int i = 0;        
        for(ColorComboBox cb : _comboBoxes) { 
            cb.addActionListener(_settingsController);
            cb.setActionCommand("ComboBox");
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.ipady = 0;
            c.gridy = i;
            _colorPanel.add(new JLabel(pieces[i]), c);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.ipady = 5;
            c.gridy = i;
            i++;
            _colorPanel.add(cb, c);
        }
        super.setLayout(new BorderLayout());
        super.add(_return, BorderLayout.PAGE_END); 
        super.add(_colorPanel, BorderLayout.WEST);          
    }
   
    /**
     * 
     * @return visszaadja a PieceColornak megfelelő java.awt.Color-t
     */
    public Color[] getColors() {
        Color[] colors = new Color[7];
        int i = 0;
        for(ColorComboBox ccb : _comboBoxes) {
            switch(ccb._color) {
                case CYAN:
                    colors[i] = Color.CYAN;
                    break;
                case BLUE:
                    colors[i] = Color.BLUE;
                    break;
                case RED:
                    colors[i] = Color.RED;                    
                    break;
                case YELLOW:
                    colors[i] = Color.YELLOW;                    
                    break;
                case ORANGE:
                    colors[i] = Color.ORANGE;                    
                    break;
                case PURPLE:
                    colors[i] = new Color(128, 0, 128);                    
                    break;
                case DEFAULT:
                    colors[i] = Color.BLACK;                    
                    break;

            }
            i++;
        }
        return colors;
    }
}
