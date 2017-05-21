package graphics;

import controller.SettingsController;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import model.PieceColor;
import model.PieceType;

/**
 * Ezen a grafikai felületen lehet beállítani a színeket.
 */
public class Settings extends JPanel {
    private final String[] pieces;
    private final JPanel _colorPanel;
    private final ArrayList<ColorComboBox> _comboBoxes;
    private final SettingsController _settingsController;
    private final JButton _return;
    
    
    /**
     * Szimpla konstruktor
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
        for(PieceType type : PieceType.values()) {
            _comboBoxes.add(new ColorComboBox(type));
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
     * @return visszaad egy HashMap-et amiben a különböző típusú Pieceknek
     * a színét tárolja el
     */
    public HashMap<PieceType, PieceColor> getColors() {                       
        HashMap<PieceType, PieceColor> colors = new HashMap<>();
        int i = 0;
        for(ColorComboBox ccb : _comboBoxes) {
            switch(ccb._color) {
                case CYAN:
                    colors.put(ccb._type, PieceColor.CYAN);
                    break;
                case BLUE:
                    colors. put(ccb._type, PieceColor.BLUE);
                    break;
                case RED:
                    colors. put(ccb._type, PieceColor.RED);                    
                    break;
                case YELLOW:
                    colors. put(ccb._type, PieceColor.YELLOW);                    
                    break;
                case ORANGE:
                    colors. put(ccb._type, PieceColor.ORANGE);                    
                    break;
                case PURPLE:
                    colors. put(ccb._type, PieceColor.PURPLE);                    
                    break;
                case GREEN:
                    colors.put(ccb._type, PieceColor.GREEN);
                    break;
                case DEFAULT:                   
                    break;
            }
            i++;
        }
        return colors;
    }
}
