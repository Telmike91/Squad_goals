package controllerTest;

import java.util.ArrayList;
import controller.*;
import model.*;
import static org.junit.Assert.*;
import org.junit.*;

public class TopListControllerTest {
   
    private TopListController topListController;
    
    @Before
    public void initialize(){
        topListController = new TopListController();
    }
    
    @Test
    public void getScoresTest() {
        topListController.updateScore("Anna", 1000);
        topListController.updateScore("Nem Anna", 2000);
        topListController.updateScore("Rih Anna", 7000);
        ArrayList<Player> result = topListController.getScores();
        
        assertEquals("Az enterPiece() tele volt, amikor nem kellett volna." ,"Anna", result.get(0).getName());
        assertEquals("Az enterPiece() tele volt, amikor nem kellett volna." ,2000, result.get(0).getScore());
        
    }

}
