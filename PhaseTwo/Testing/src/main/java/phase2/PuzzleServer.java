package phase2;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
// import java.util.Arrays;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class PuzzleServer {

	/*
	// Partition: Valid port number
    // 1.   Subdomain: a correct port (cover Subdomain 1 )
    // Test Cases: 
    // 1.   Test Case: Start the server on port 8000 (cover Subdomain 1 )
    // Partition: Invalid port number (e.g., negative, out of range)
    // 2.   Subdomain: an negative number
    // Test Cases:
    // 2.   Test Case: Attempt to start the server on an invalid port number and expect an IOException to be thrown (cover Subdomain 2S )
	 */
    public static void main(String[] args) throws IOException {
        // Create an HTTP server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // Register handlers for each puzzle type
        server.createContext("/SudokuBlankPuzzle", new PuzzleHandler("SudokuBlankPuzzle.txt"));
        server.createContext("/SudokuSolvedPuzzle", new PuzzleHandler("SudokuSolvedPuzzle.txt"));
        server.createContext("/HexSudokuBlankPuzzle", new PuzzleHandler("HexSudokuBlankPuzzle.txt"));
        server.createContext("/HexSudokuSolvePuzzle", new PuzzleHandler("HexSudokuSolvePuzzle.txt"));

        // Start the server
        server.start();
        System.out.println("Server started on port 8000");
    }

    static class PuzzleHandler implements HttpHandler {
        String puzzleFile;
        public PuzzleHandler(String puzzleFile) {
            this.puzzleFile = puzzleFile;
        }

        /*
        // Partition: Valid HTTP exchange
        // 1.   Subdomain: a valid HTTP
        // Test Cases: S
        // 1.   Test Case : Handle a valid HTTP request and verify that the puzzle data is correctly sent as the response (cover Subdomain 1 )
        // Partition: Invalid HTTP exchange (e.g., null exchange)
        // 2.   Subdomain: an invalid HTTP
        // Test Cases: 
        // 2.   Test Case 2: Handle an invalid HTTP request and expect an IOException to be thrown (cover Subdomain 2 )
         */
        public void handle(HttpExchange exchange) throws IOException {
            // Parse the puzzle data from the file
            String[][] puzzle = PuzzleParser.parsePuzzleData(puzzleFile);

            // Encode the puzzle data as a string with a custom delimiter
            String puzzleString = encodePuzzleAsString(puzzle, "; ");

            // Set the response headers
            exchange.sendResponseHeaders(200, puzzleString.length());

            // Get the output stream of the response
            OutputStream responseStream = exchange.getResponseBody();

            // Write the puzzle data to the output stream
            responseStream.write(puzzleString.getBytes());

            // Close the output stream
            responseStream.close();
        }

        
        /*
        // Partition: Valid puzzle array and delimiter
        // Subdomain: 
	    // 1. Encode a valid puzzle array using a valid delimiter and verify the encoded puzzle string
        // Test Cases:
        // 1. Test Case: Encode a valid puzzle array using a valid delimiter and verify the encoded puzzle string (cover Subdomain 1 )
        // Partition: Empty puzzle array
        // 2. Subdomain: an empty puzzle
        // Test Cases:
        // 2.  Test Case: Encode an empty puzzle array and expect an empty string as the result (cover Subdomain 2 )
        // Partition: Null puzzle array or delimiter
        // 3. Subdomain: a null puzzle
        // Test Cases:
        // 3. Test Case 3: Encode a null puzzle array or delimiter and expect an empty string as the result (cover Subdomain 3 )
         */
        private String encodePuzzleAsString(String[][] puzzle, String delimiter) {
            StringBuilder encodedPuzzle = new StringBuilder();
            for (int i = 0; i < puzzle.length; i++) {
                String[] row = puzzle[i];

                // Join the elements of the row with the delimiter
                encodedPuzzle.append(String.join(delimiter, row));

                // Add a newline character after each row except the last one
                if (i != puzzle.length - 1) {
                    encodedPuzzle.append(delimiter);
                }
                encodedPuzzle.append("\n");
            }
            return encodedPuzzle.toString();
        }
    }
}
