import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;


import java.io.FileInputStream;

public class ImageServer {
    public static void main(String[] args) throws IOException {
        // Create a new HTTP server that listens on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        
        // Register handlers for the solved.png and blank.png image URLs
        server.createContext("/solved.png", new ImageHandler("solved.png"));
        server.createContext("/blank.png", new ImageHandler("blank.png"));
        
        // Set the server's executor to null to use the default executor
        server.setExecutor(null);
        
        // Start the server
        server.start();
    }
}

class ImageHandler implements HttpHandler {
    private String filename;

    public ImageHandler(String filename) {
        this.filename = filename;
    }

    public void handle(HttpExchange t) throws IOException {
        // Retrieve the image file as a byte array
        byte[] response = getBytesFromFile(filename);
        
        // Send the HTTP response headers, indicating that the response code is 200 and the response length
        t.sendResponseHeaders(200, response.length);
        
        // Retrieve the output stream for the HTTP response
        OutputStream os = t.getResponseBody();
        
        // Write the response byte array to the output stream
        os.write(response);
        
        // Close the output stream
        os.close();
    }

    /**

    Retrieve the image file as a byte array.

    @param filename The name of the image file to retrieve.

    @return The contents of the image file as a byte array.

    @throws IOException If an error occurs while reading the image file.
    */
    private byte[] getBytesFromFile(String filename) throws IOException {
        // Read the contents of the image file into a byte array
        File file = new File(filename);
        byte[] bytes = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(bytes);
        fis.close();
    
        // Return the byte array
        return bytes;
        }/**
    Test suite:
    - Partitions: filenames of "solved.png" and "blank.png"
    - Subdomains: image files that exist and image files that don't exist
    - Coverage: 100% coverage of the getBytesFromFile method with the given filenames
    */
}
