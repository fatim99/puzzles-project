package sudokuse;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;


public class sukokuServer {
 /**
 * Starts the image server.
 * @throws IOException if there is an error creating the server.
 */
    public static void main(String[] args) throws IOException {
        //initializes an HttpServer instance.
    }
    static class ImageHandler implements HttpHandler {
         /**
     * Handles HTTP requests to the /image endpoint by returning the image specified by the "image" query parameter.
     *
     * @param exchange The HttpExchange object representing the incoming HTTP request and response.
     * 
     * @throws IOException if there is an error reading the image file or writing the response.
     */
        @Override
        //The handle() method is called when a request is received by the server that matches the context of this handler (in this case, requests to the /image endpoint).
        public void handle(HttpExchange exchange) throws IOException {
            // Get requested image name from query parameter
            
            
            // Load image from file
            
            // Convert image to byte array and send response
        }
        }
    static class IndexHandler implements HttpHandler {
/**
 * Handles requests for images.
 * @param exchange the HttpExchange object representing the client's request and the server's response
 * @throws IOException if an I/O error occurs while processing the request
 */
        @Override
        public void handle(HttpExchange exchange) throws IOException {
             // Serve HTML page with links to the two images
           
        }
      }
}

/**
 * 
 Partitions:

1-HTTP requests to /image endpoint with valid image name
2-HTTP requests to /image endpoint with invalid image name
3-HTTP requests to /index endpoint


Subdomains:

1-Valid image names:
a.solved
b. notsolved

2-Invalid image names:
a. Empty string
b. Non-existent image name


Test coverage:
* The test suite cover the  subdomains: valid_image, invalid_image.
* Each subdomain is tested with  test case that verifies the expected behavior
* 
Partition 1: HTTP request to /image endpoint with valid  image name returns expected image content
Partition 2: HTTP request to /image endpoint with invalid image name returns error message
Partition 2 : HTTP request to /image endpoint with empty image name returns error message
Partition 2 : HTTP request to /image endpoint with non-existent image name returns error message
Partition 3 : HTTP request to /index endpoint returns HTML page with links to the two images
Partition 3: HTML page returned by /index endpoint has correct image links and content
 */
