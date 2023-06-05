package phase2;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import com.sun.net.httpserver.HttpExchange;
import static org.junit.jupiter.api.Assertions.*;

class PuzzleServerTest {

   

    @Test
    void testMain_StartServer_InvalidPort() {
        assertThrows(IOException.class, () -> PuzzleServer.main(new String[]{}));
        // Verify that an IOException is thrown when attempting to start the server on an invalid port
    }

    @Test
    void testPuzzleHandler_ValidPuzzleFile() {
        String validPuzzleFile = "SudokuBlankPuzzle.txt"; // Valid puzzle file for testing

        PuzzleServer.PuzzleHandler puzzleHandler = new PuzzleServer.PuzzleHandler(validPuzzleFile);

        // Verify that the puzzleHandler is constructed successfully with the specified puzzle file
        assertEquals(validPuzzleFile, puzzleHandler.puzzleFile);
    }

    

   

    @Test
    void testHandle_InvalidExchange() {
        PuzzleServer.PuzzleHandler puzzleHandler = new PuzzleServer.PuzzleHandler("ValidPuzzleFile.txt");
        HttpExchange invalidExchange = null; // Invalid exchange for testing

        assertThrows(IOException.class, () -> puzzleHandler.handle(invalidExchange));
        // Verify that an IOException is thrown when handling an invalid exchange
    }

   

   
  

   
    }

