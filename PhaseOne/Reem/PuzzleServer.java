
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * A class that serves puzzle images via HTTP.
 */
public class PuzzleServer {
    private static final int PORT = 8080;
    private static final String BLANK_PUZZLE_PATH = "puzzle1Unsolved.png";
    private static final String SOLVED_PUZZLE_PATH = "puzzle1Solved.png";

    /*
     * Method Name: main()
     * Effect: Starts the HTTP server on the default port and creates contexts for
     * serving blank and solved puzzles.
     * parameter type: array of strings
     * 
     * @param args command line arguments (not used)
     * 
     * @throws IOException if an error occurs while starting the HTTP server
     * 
     * @return None
     */
    public static void main(String[] args) throws IOException {

        /*
         * Testing Strategy:
         * Test Cases and their coverage:
            * Test that the server starts successfully: Verify that the HTTP server starts
                * and is listening on the specified port.
            * Test that the blank puzzle context is created successfully: Verify that the
                * HTTP context for serving blank puzzles is created and is serving the correct
                * file.
            * Test that the solved puzzle context is created successfully: Verify that the
                * HTTP context for serving solved puzzles is created and is serving the correct
                * file.

            Coverage:
                100% statement coverage 
         */

        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/blank", new PuzzleHandler(new File(BLANK_PUZZLE_PATH)));
        server.createContext("/solved", new PuzzleHandler(new File(SOLVED_PUZZLE_PATH)));
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    /**
     * An HTTP handler that serves a puzzle image file in response to a request.
     */
    static class PuzzleHandler implements HttpHandler {
        private final File puzzleFile;

        /*
         * Method Name: PuzzleHandler
         * Effect: Constructs a new PuzzleHandler object with the specified puzzle file.
         * parameter type: File
         * 
         * @param puzzleFile the file object representing the puzzle image to be served
         * 
         * @throws None
         * 
         * @return None
         */
        public PuzzleHandler(File puzzleFile) {

            /*
             * Partitions:
                * puzzleFile: a valid file object
             * Subdomains:
                * puzzleFile: any valid file object representing an image file
             * Test Cases and their coverage:
                * Test that the PuzzleHandler object is constructed successfully: Verify that
                    * the object is constructed successfully with a valid puzzle file object.
                Coverage:
                    100% statement coverage
             */
            this.puzzleFile = puzzleFile;
        }

        /*
         * Method Name: handle()
         * Effect: Serves the puzzle image file in response to an HTTP request.
         * parameter type: HttpExchange
         * 
         * @param exchange the HttpExchange object representing the request and response
         * 
         * @throws IOException if an error occurs while reading the file or writing the
         * response
         * 
         * @return None
         */
        public void handle(HttpExchange exchange) throws IOException {

            /*
             * Testing Strategy:
             * Partitions:
                * exchange: a valid HttpExchange object
             * Subdomains:
                * exchange: any valid HttpExchange object
             * Test Cases and their coverage:
                * Test that the puzzle image file is served successfully: Verify that the file
                    * is read and sent as a response in the HttpExchange object.
                * Test that an IOException is thrown if there is an error while reading the
                    * file
                * Test that an IOException is thrown if there is an error while writing the
                    * response
                Coverage: 25% statement coverage
                    no test cases for the last three line
             */
            byte[] response = Files.readAllBytes(puzzleFile.toPath());
            exchange.sendResponseHeaders(200, response.length);
            exchange.getResponseBody().write(response);
            exchange.close();
        }
    }
}
