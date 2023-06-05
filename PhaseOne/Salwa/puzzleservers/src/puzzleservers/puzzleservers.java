// auther: salwa shamma
//id:4010405

package puzzleservers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class puzzleservers {

    private static final int DEFAULT_PORT = 8000;
    private static final String IMAGE_DIRECTORY = "C:\\Users\\lenovo\\Downloads\\images\\";
    private static final String IMAGE_EXTENSION = ".png";
 
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(DEFAULT_PORT), 0);
        server.createContext("/puzzle", new PuzzleHandler());
        server.setExecutor(null);
        System.out.println("Server started on port " + DEFAULT_PORT);
        server.start();
    }

    static class PuzzleHandler implements HttpHandler {

        private final Map<String, String> queryParameters = new HashMap<>();

        @Override
        /**
         * Handles an HTTP request to the "/puzzle" endpoint. Parses the query parameters
         * to determine the image file to be sent in the response. Sends an error response
         * if the required parameters are missing or if the requested image file does not exist.
         * 
         * @param exchange An instance of HttpExchange that represents the HTTP request and response.
         * @return There is no return value for this method.
         * @throws IOException if there is an error during the transmission of the response
         * such as losing a network connection.
         */
        public void handle(HttpExchange exchange) throws IOException {
            parseQueryParameters(exchange.getRequestURI().getQuery());
            if (!queryParameters.containsKey("type") || !queryParameters.containsKey("number")) {
                sendError(exchange, 400, "Bad Request: Missing type or number parameter");
                return;
            }
            String filename = IMAGE_DIRECTORY + queryParameters.get("type") + queryParameters.get("number") + IMAGE_EXTENSION;
            Path imagePath = Paths.get(filename);
            if (!Files.exists(imagePath)) {
                sendError(exchange, 404, "Not Found: Image file not found: " + filename);
                return;
            }
            byte[] imageData = Files.readAllBytes(imagePath);
            exchange.sendResponseHeaders(200, imageData.length);
            try (OutputStream outputStream = exchange.getResponseBody()) {
                outputStream.write(imageData);
            }
            
//            Partition:
//            1. HttpExchange object
//            Subdomains:
//            1.1 HttpExchange object with valid query parameters and a valid image file
//            1.2 HttpExchange object with valid query parameters and a non-existent image file
//            1.3 HttpExchange object with missing "type" query parameter
//            1.4 HttpExchange object with missing "number" query parameter
//            1.5 HttpExchange object with empty query parameters
//            Test cases:
//            1. Given an HttpExchange object with query parameters "type=blank&number=1", which correspond to an existing image file, should return a 200 status code and the image data (covers subdomain 1.1)
//            2. Given an HttpExchange object with query parameters "type=sudoku&number=3", which correspond to a non-existent image file, should return a 404 status code and an error message (covers subdomain 1.2)
//            3. Given an HttpExchange object with query parameters "number=1", which are missing the "type" parameter, should return a 400 status code and an error message (covers subdomain 1.3)
//            4. Given an HttpExchange object with query parameters "type=hexadecimal", which are missing the "number" parameter, should return a 400 status code and an error message (covers subdomain 1.4)
//            5. Given an HttpExchange object with empty query parameters, should return a 400 status code and an error message (covers subdomain 1.5)
            
        }

        /**
         * Parses the query parameters from the HTTP request URI's query string and stores
         * them in a Map object.
         * 
         * @param query A String representing the query string of the HTTP request.
         * @return There is no return value for this method.
         * @throws IllegalArgumentException If the query string is malformed and cannot be parsed.
         */
        private void parseQueryParameters(String query) throws IllegalArgumentException {
            if (query == null) {
                return;
            }
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                if (idx > 0) {
                    String key = pair.substring(0, idx);
                    String value = pair.substring(idx + 1);
                    queryParameters.put(key, value);
                } else {
                    throw new IllegalArgumentException("Malformed query string: " + query);
                }
            }
            
//            Partition:
//            1. Query string
//            Subdomains:
//            1.1 Query string with two parameters
//            1.2 Query string with three or more parameters
//            1.3 Query string with empty values
//            1.4 Query string with special characters in values
//            1.5 Malformed query string with missing "=" character
//            1.6 Malformed query string with missing key
//            1.7 Malformed query string with missing value
//            Test cases:
//            1. Given a query string "type=blank&number=1", should add the "type" key with value "blank" and the "number" key with value "1" to the queryParameters map (covers subdomain 1.1)
//            2. Given a query string "type=hexadecimal&number=2&difficulty=hard", should throw an IllegalArgumentException (covers subdomain 1.2)
//            3. Given a query string "type=&number=", should throw an IllegalArgumentException (covers subdomain 13)
//            4. Given a query string "type=hexadecimal&number=%21%40%23%24", should throw an IllegalArgumentException (covers subdomain 1.4)
//            5. Given a query string "type=hexadecimal&number2", should throw an IllegalArgumentException (covers subdomain 1.5)
//            6. Given a query string "hexadecimal=2", should throw an IllegalArgumentException (covers subdomain 1.6)
//            7. Given a query string "type=", should throw an IllegalArgumentException (covers subdomain 1.7)

        }

        /**
         * Sends an error response to the HTTP client.
         * 
         * @param exchange An instance of HttpExchange that represents the HTTP request and response.
         * @param code An integer representing the HTTP status code to be sent in the response.
         * @param message A String representing the error message to be sent in the response.
         * @return There is no return value for this method.
         * @throws IOException if there is an error during the transmission of the response
         * such as losing a network connection.
         */
        private void sendError(HttpExchange exchange, int code, String message) throws IOException {
            exchange.sendResponseHeaders(code, message.length());
            try (OutputStream outputStream = exchange.getResponseBody()) {
                outputStream.write(message.getBytes());
            }
            
//        Partition:
//        1. HttpExchange
//        2. HTTP status code
//        3. Error message
//        Subdomains:
//        1.1 Valid HttpExchange instance
//        1.2 HttpExchange with missing or invalid fields (e.g., missing request method)
//        2.1 Valid HTTP status code (400)
//        2.2 Invalid HTTP status code (0) 
//        2.3 HTTP status code with special values (e.g., 200, 201, 204)
//        3.1 Valid error message (e.g., 200, 201, 204)
//        3.2 Empty error message
//        3.3 Error message with special characters or formatting (e.g., HTML tags, JSON format)
//        Test cases:
//        1. Given a valid HttpExchange instance, HTTP status code 400, and error message "Bad Request", should send an HTTP response with status code 400 and message "Bad Request" (covers subdomains 1.1, 2.1, and 3.1)
//        2. Given a HttpExchange instance with missing request method (no GET), HTTP status code 400, and error message "Bad Request", should throw an IllegalArgumentException (covers subdomain 1.2)
//        3. Given a valid HttpExchange instance, HTTP status code 0, and error message "Error", should throw an IllegalArgumentException (covers subdomain 2.2)
//        4. Given a valid HttpExchange instance, HTTP status code 200, and error message "OK", should send an HTTP response with status code 200 and message "OK" (covers subdomains 2.3 and 3.1)
//        5. Given a valid HttpExchange instance, HTTP status code 201, and error message "Created", should send an HTTP response with status code 201 and message "Created" (covers subdomains 2.3 and 3.1)
//        6. Given a valid HttpExchange instance, HTTP status code 204, and error message "No Content", should send an HTTP response with status code 204 and message "No Content" (covers subdomains 2.3 and 3.1)
//        7. Given a valid HttpExchange instance, HTTP status code 400, and an empty error message, should send an HTTP response with status code 400 and an empty message (covers subdomains 2.1 and 3.2)
//        8. Given a valid HttpExchange instance, HTTP status code 400, and error message "<html><body><h1>Bad Request</h1></body></html>", should send an HTTP response with status code 400 and the HTML-formatted message "<html><body><h1>Bad Request</h1></body></html>" (covers subdomains 3.3)
//        9. Given a valid HttpExchange instance, HTTP status code 500, and error message "{"error":"Internal Server Error"}", should send an HTTP response with status code 500 and the JSON-formatted message "{"error":"Internal Server Error"}" (covers subdomains 3.3)
        }
    }
}