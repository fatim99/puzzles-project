package phase2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import com.sun.net.httpserver.HttpExchange;
import static org.junit.jupiter.api.Assertions.*;

class PuzzleClientTest {

    @Test
    void testPuzzleHandler_EmptyPuzzleFile() {
        String emptyPuzzleFile = ""; // Empty puzzle file for testing

        PuzzleServer.PuzzleHandler puzzleHandler = new PuzzleServer.PuzzleHandler(emptyPuzzleFile);

        // Verify that the puzzleHandler is constructed successfully with the specified
        // puzzle file
        assertEquals(emptyPuzzleFile, puzzleHandler.puzzleFile);
    }

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
    public void testGetErrorMessageForEmptyInput() {
        PuzzleClientv1 puzzleClient = new PuzzleClientv1();
        String errorMessage = puzzleClient.getErrorMessage("");
        String expectedErrorMessage = "Input cannot be empty.";
        Assertions.assertEquals(expectedErrorMessage, errorMessage);
    }

    @Test
    public void testGetErrorMessageForInvalidInput() {
        PuzzleClientv1 puzzleClient = new PuzzleClientv1();
        String errorMessage = puzzleClient.getErrorMessage("10");
        String expectedErrorMessage = "Invalid input. Please enter a digit from 1 to 9 or A to G, or '-' for empty cells.";
        Assertions.assertEquals(expectedErrorMessage, errorMessage);
    }

}