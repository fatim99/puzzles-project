package phase2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import javax.swing.*;
import java.awt.event.ActionListener;

class PuzzleClientTest {

    private PuzzleClient puzzleClient;
    private JButton sudokuButton;
    private JButton hexSudokuButton;

    @BeforeEach
    void setUp() {
        puzzleClient = new PuzzleClient();
        puzzleClient.createAndShowGUI();
        sudokuButton = puzzleClient.sudokuButton;
        hexSudokuButton = puzzleClient.hexSudokuButton;
    }

    @Test
    void testCreateAndShowGUI_GuiCreatedAndVisible() {
        JFrame frame = puzzleClient.frame;
        assertNotNull(frame);
        assertEquals("Puzzle Client", frame.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        assertTrue(frame.isVisible());
    }

    @Test
    void testCreateAndShowGUI_ButtonsCreatedAndActionListenersSet() {
        assertNotNull(sudokuButton);
        assertNotNull(hexSudokuButton);

        ActionListener[] sudokuListeners = sudokuButton.getActionListeners();
        ActionListener[] hexSudokuListeners = hexSudokuButton.getActionListeners();

        assertEquals(1, sudokuListeners.length);
        assertEquals(1, hexSudokuListeners.length);
    }

    
    

    
}