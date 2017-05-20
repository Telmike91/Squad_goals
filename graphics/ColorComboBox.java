package graphics;

import javax.swing.JComboBox;
import model.*;

/**
 * Egyéni combobox amiben tároljuk hogy melyik PieceType-hoz melyik szín tartozik.
 */
public class ColorComboBox extends JComboBox  {
    private final String[] colors = {"Blue", "Green", "Red", "Yellow", "Orange", "Cyan", "Purple", "Default"};
    public PieceColor _color = PieceColor.DEFAULT;
    public PieceType _type;
    public ColorComboBox(PieceType type) {
        _type = type;
        for(String s : colors) {
            super.addItem(s);
        }
        super.setSelectedItem("Default");
    }

}
