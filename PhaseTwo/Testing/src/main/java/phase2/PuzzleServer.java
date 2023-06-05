package phase2;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
// import java.util.Arrays;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class PuzzleServer {
    // Method Signature:
    /**
     * Method name: main
     * Starts the puzzle server on a specific port.
     *
     * @param args command line arguments
     * @throws IOException if an I/O error occurs while starting the server
     */
	
	/*
	 * Partition: Valid port number
Test Case 1: Start the server on port 8000
Partition: Invalid port number (e.g., negative, out of range)
Test Case 2: Attempt to start the server on an invalid port number and expect an IOException to be thrown
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

        /**
         * Method name: PuzzleHandler
         * Constructs a PuzzleHandler with the specified puzzle file.
         *
         * @param puzzleFile the path of the file containing the puzzle data
         */
        public PuzzleHandler(String puzzleFile) {
            this.puzzleFile = puzzleFile;
        }

        /**
         * Method name: handle
         * Handles an HTTP request and sends the puzzle data as the response.
         *
         * @param exchange the HTTP exchange object representing the request and response
         * @throws IOException if an I/O error occurs while handling the request
         */
        
        /*
         * Partition: Valid HTTP exchange
Test Case 1: Handle a valid HTTP request and verify that the puzzle data is correctly sent as the response
Partition: Invalid HTTP exchange (e.g., null exchange)
Test Case 2: Handle an invalid HTTP request and expect an IOException to be thrown
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

        /**
         * Method name: encodePuzzleAsString
         * Encodes the puzzle data as a string with a custom delimiter.
         *
         * @param puzzle    the puzzle data as a 2D array of strings
         * @param delimiter the delimiter string used to separate puzzle elements
         * @return the encoded puzzle data as a string
         */
        
        /*
         * Partition: Valid puzzle array and delimiter
Test Case 1: Encode a valid puzzle array using a valid delimiter and verify the encoded puzzle string
Partition: Empty puzzle array
Test Case 2: Encode an empty puzzle array and expect an empty string as the result
Partition: Null puzzle array or delimiter
Test Case 3: Encode a null puzzle array or delimiter and expect an empty string as the result
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
