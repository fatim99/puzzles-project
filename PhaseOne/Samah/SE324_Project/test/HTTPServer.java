import java.io.*;
import java.net.*;

import org.junit.jupiter.api.Test;

public class HTTPServer {
    public static void main(String[] args) throws IOException {
        int port = 8070;
        // Create a new ServerSocket object to listen for incoming client connections on the specified port
        try (
        ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);

            while (true) {
                // Wait for a client to connect and accept the connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                //Read the HTTP request from the client
                handleRequest(clientSocket);

                // Close the client socket and print a message indicating that the client has disconnected
                clientSocket.close();
                System.out.println("Client disconnected");
            }
        }
    }
/*---------------------------------------Test strategy------------------------------------------- */
    /**Partition: 
     * 0. Null socket input
     * 
     * 1. Valid socket input
     *    Subdomains:
     *    1.0. Valid GET request with valid resource path
     *    1.1. Valid GET request with invalid resource path
     *    1.2. Invalid HTTP method in request
     *    1.3. Invalid HTTP version in request
     *    1.4. Valid POST request with valid resource path
     *    1.5. Valid POST request with invalid resource path
     *    1.6. Invalid Content-Length header in request
     *
     *    Test cases:
     *    1. Valid GET request with valid resource path Cover Subdomain 1.0
     *    2. Valid GET request with invalid resource path Cover Subdomain 1.1
     *    3. Invalid HTTP method in request Cover Subdomain 1.2
     *    4. Invalid HTTP version in request Cover Subdomain 1.3
     *    5. Valid POST request with valid resource path Cover Subdomain 1.4
     *    6. Valid POST request with invalid resource path Cover Subdomain 1.5
     *    7. Invalid Content-Length header in request Cover Subdomain 1.6
     *  
     * 2. Invalid input
     * 
     *    Subdomains:
     *    2.0. The request is null or does not start with "GET /"
     *    2.1. The requested file name is null or empty
     *    2.2. The requested file name does not match any of the available files
     *    
     *    Test cases:
     *    1. Request = null covers subdomain 2.0
     *    2. Request = "POST /index.html" covers subdomain 2.0
     *    3. Request = "GET /" covers subdomains 2.1 and 2.2
     *    4. Request = "GET /invalid.html" covers subdomain 2.2
     *    5. Request = "GET /QSudoku.php" covers subdomain 2.2
    **/
    private static void handleRequest(Socket clientSocket) throws IOException {
        //Need to implement this method
    }

    /* Partition:
     * 0. Null socket input
     */
    @Test
    public void testNullSocket() {
        //Need to implement this method
    }

    /* Partition:
     * 1. Valid socket input
     * 
     *    Subdomains:
     *    1.0. Valid GET request with valid resource path
     *    1.1. Valid GET request with invalid resource path
     *    1.2. Invalid HTTP method in request
     *    1.3. Invalid HTTP version in request
     *    1.4. Valid POST request with valid resource path
     *    1.5. Valid POST request with invalid resource path
     *    1.6. Invalid Content-Length header in request
     *
     *    Test cases:
     *    1. Valid GET request with valid resource path
     *    2. Valid GET request with invalid resource path
     *    3. Invalid HTTP method in request
     *    4. Invalid HTTP version in request
     *    5. Valid POST request with valid resource path
     *    6. Valid POST request with invalid resource path
     *    7. Invalid Content-Length header in request
     */

    @Test
    public void testValidSocket() throws IOException {
        // Subdomain 1.0

        // Subdomain 1.1

        // Subdomain 1.2

        // Subdomain 1.3

        // Subdomain 1.4

        // Subdomain 1.5

        // Subdomain 1.6

        //Need to implement this method
    }

    /* Partition:
     * 2. Invalid socket input
     * 
     *    Subdomains:
     *    2.0. Socket input stream throws an IOException when read() method is called
     *
     *    Test case:
     *    1. Socket input stream is invalid and throws an IOException
 */
@Test
public void testInvalidSocketInputStream() throws IOException {
    // Subdomain 2.0
    //Need to implement this method
}

/*-------------------------------------------------------------------------------------------------- */

/*---------------------------------------Test strategy------------------------------------------- */
/**Partition: 
     * 0. Null file name input
     * 
     * 1. Valid input
     * 
     *    Subdomains:
     *    1.0. File exists and is a valid HTML page
     *      
     *    Test cases:
     *    1. content = "<html><body><h1>Hello, world!</h1></body></html>" Cover Subdomain 1.0
     *    2. fileName = "index.html" Cover Subdomain 1.1
     * 
     * 2. Invalid file name input
     * 
     *    Subdomains:
     *    2.0. File does not exist
     *    2.1. File exists but is not an HTML page
     *    2.2. File name contains invalid characters (e.g. slashes, colons)
     * 
     *    Test cases:
     *    1. fileName = "non-existent.html" Cover Subdomain 2.0
     *    2. content = "This is not HTML content" 
     *       fileName = "text-file.txt" cover Subdomain 2.1
     *    3. fileName = "folder/index.html" cover Subdomain 2.2
 **/
    private static void serveFile(String filename, String contentType, OutputStream out) throws IOException {
        //Need to implement this method
    }

    /* Partition:
     * 0. Null file name input
    */
    @Test
    public void testNullFileName() throws IOException {
            //Need to implement this method
    }

/* Partition:
 * 1. Valid file name input
 *    Subdomains:
 *    1.0. File exists and is a valid HTML page
 *      
 *    Test cases:
 *    1. content = "<html><body><h1>Hello, world!</h1></body></html>" Cover Subdomain 1.0
 *    2. fileName = "index.html" Cover Subdomain 1.1
 */
@Test
public void testValidFileName() throws IOException {
    // Subdomain 1.0

    // Subdomain 1.1

    //Need to implement this method

}

/* Partition:
 * 2. Invalid file name input
 * 
 *    Subdomains:
 *    2.0. File does not exist
 *    2.1. File exists but is not an HTML page
 *    2.2. File name contains invalid characters (e.g. slashes, colons)
 * 
 *    Test cases:
 *    1. fileName = "non-existent.html" Cover Subdomain 2.0
 *    2. content = "This is not HTML content" 
 *       fileName = "text-file.txt" cover Subdomain 2.1
 *    3. fileName = "folder/index.html" cover Subdomain 2.2
 */
@Test
public void testInvalidFileName() throws IOException {
    // Subdomain 2.0

    // Subdomain 2.1

    // Subdomain 2.2

    //Need to implement this method
}
/*-------------------------------------------------------------------------------------------------- */

/*------------------------------------------Test strategy------------------------------------------- */
/**Partition: 
     * 0. Null output stream input
     * 
     * 1. Valid output stream input
     *    Subdomains:
     *    1.0. Output stream is not closed after method call
     *
     *    Test cases:
     *    1. ByteArrayOutputStream with size > 0 Cover Subdomain 1.0
     *    2. ByteArrayOutputStream with size = 0 Cover Subdomain 1.0
     * 
     * 2. Invalid output stream input
     * 
     *    Subdomains:
     *    2.0: The output stream throws an IOException when the write method is called.
     * 
     *    Test cases:
     *    1. Output stream is invalid and throws an IOException cover Subdomain 2.0
 **/
    private static void serve404(OutputStream out) throws IOException {
       //Need to implement this method
    }

    /* Partition:
     * 0. Null output stream input
     */
    @Test
    public void testNullOutputStream() throws IOException {
        //Need to implement this method
    }

    /* Partition:
     * 1. Valid output stream input
     *    Subdomains:
     *    1.0. Output stream is not closed after method call
     *
     *    Test cases:
     *    1. ByteArrayOutputStream with size > 0 Cover Subdomain 1.0
     *    2. ByteArrayOutputStream with size = 0 Cover Subdomain 1.0
     */

    @Test
    public void testValidOutputStream() throws IOException {
        //Need to implement this method
    }

    /* Partition:
     * 2. Invalid output stream input
     */

    @Test
    public void testInvalidOutputStream() throws IOException {
        //Need to implement this method
    }
}
/*-------------------------------------------------------------------------------------------------- */
