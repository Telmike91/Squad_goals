package controller;

import graphics.ColorComboBox;
import graphics.MainWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.PieceColor;

/**
 * Ezzel a kontrollerrel állítjuk be a színeket és a changePanellel átállítjuk a
 * jelenlegi ablakot.
 */
public class SettingsController implements ActionListener {
    private final MainWindow _w;
    
    public SettingsController(MainWindow w) {
        _w = w;
    }
    
    /**
     * A kiváltó eventtől függően modósítja a megjelnített ablakot, vagy beállítja
     * a comboboxban jelzett színt
     * @param e a kiváltó event paraméter
     */
    @Override
    public void actionPerformed(ActionEvent e) { 
        switch(e.getActionCommand()) {
            case "Window":
                _w.changePanel("menu");
                break;
            case "ComboBox":
                ColorComboBox cb = (ColorComboBox)e.getSource();
                String color = (String)cb.getSelectedItem();
                switch(color) {
                    case "Blue":
                        cb._color = PieceColor.BLUE;
                        break;
                    case "Green":
                        cb._color = PieceColor.GREEN;
                        break;
                    case "Red":
                        cb._color = PieceColor.RED;
                        break;
                    case "Yellow":
                        cb._color = PieceColor.YELLOW;
                        break;
                    case "Orange":
                        cb._color = PieceColor.ORANGE;
                        break;
                    case "Cyan":
                        cb._color = PieceColor.CYAN;
                        break;
                    case "Purple":
                        cb._color = PieceColor.PURPLE;
                        break; 
                    case "Default":
                        cb._color = PieceColor.DEFAULT;
                        break;                     
                }
                break;
        }
    }
}
