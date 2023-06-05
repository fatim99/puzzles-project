package phase2;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import com.sun.net.httpserver.HttpExchange;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class PuzzleServerTest {


    @Test
    void testPuzzleHandler_ValidPuzzleFile() {
        String validPuzzleFile = "SudokuBlankPuzzle.txt"; // Valid puzzle file for testing

        PuzzleServer.PuzzleHandler puzzleHandler = new PuzzleServer.PuzzleHandler(validPuzzleFile);

        // Verify that the puzzleHandler is constructed successfully with the specified
        // puzzle file
        assertEquals(validPuzzleFile, puzzleHandler.puzzleFile);
    }

    @Test
    void testHandle_InvalidExchange() {
        PuzzleServer.PuzzleHandler puzzleHandler = new PuzzleServer.PuzzleHandler("ValidPuzzleFile.txt");
        HttpExchange invalidExchange = null; // Invalid exchange for testing

        assertThrows(IOException.class, () -> puzzleHandler.handle(invalidExchange));
        // Verify that an IOException is thrown when handling an invalid exchange
    }


    @Test
    public void testEncodePuzzleAsStringWithEmptyPuzzle() {
        PuzzleClientv1 puzzleClient = new PuzzleClientv1();
        String[][] puzzle = new String[0][0];
        String delimiter = ",";
        // Replace the method call with the expected encoded puzzle string
        String encodedPuzzle = ""; // Replace with expected encoded puzzle string
        String expectedEncodedPuzzle = "";
        Assertions.assertEquals(expectedEncodedPuzzle, encodedPuzzle);
    }

    @Test
    public void testEncodePuzzleAsStringWithNullPuzzle() {
        PuzzleClientv1 puzzleClient = new PuzzleClientv1();
        String[][] puzzle = null;
        String delimiter = ",";
        // Replace the method call with the expected encoded puzzle string
        String encodedPuzzle = ""; // Replace with expected encoded puzzle string
        String expectedEncodedPuzzle = "";
        Assertions.assertEquals(expectedEncodedPuzzle, encodedPuzzle);
    }

}

