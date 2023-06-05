import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
// import java.util.Arrays;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class PuzzleServer {
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

    private static class PuzzleHandler implements HttpHandler {
        private String puzzleFile;

        public PuzzleHandler(String puzzleFile) {
            this.puzzleFile = puzzleFile;
        }

        public void handle(HttpExchange exchange) throws IOException {
            // Parse the puzzle data from the file
            String[][] puzzle = PuzzleParser.parsePuzzleData(puzzleFile);

            // Encode the puzzle data as a string with a custom delimiter
            String puzzleString = encodePuzzleAsString(puzzle, "; ");

            exchange.sendResponseHeaders(200, puzzleString.length());
            OutputStream os = exchange.getResponseBody();
            os.write(puzzleString.getBytes());
            os.close();
        }

        private String encodePuzzleAsString(String[][] puzzle, String delimiter) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < puzzle.length; i++) {
                String[] row = puzzle[i];
                sb.append(String.join(delimiter, row));
                if (i != puzzle.length - 1) {
                    sb.append(delimiter);
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }
}
