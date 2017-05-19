package graphics;

import javax.swing.JComboBox;
import model.*;

public class ColorComboBox extends JComboBox  {
    private final String[] colors = {"Blue", "Green", "Red", "Yellow", "Orange", "Cyan", "Purple", "Default"};
    public PieceColor _color = PieceColor.DEFAULT;
    public ColorComboBox() {
        for(String s : colors) {
            super.addItem(s);
        }
        super.setSelectedItem("Default");
    }

}
