import java.io.*;
import java.net.*;

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

    /** 
    * handles an HTTP request sent by a client to the server.
    * @param clientSocket representing the client connection
    * @throws IOException if the clientSocket is not valid or is closed.
    */
    private static void handleRequest(Socket clientSocket) throws IOException {
        //Need to implement this method
        //serveFile();
    }

    /** 
    * Writing the file's contents to an output stream along with an HTTP response 
    * @param filename arepresenting the name of the file to be served 
    * @param contentType representing the content type of the file
    * @param out represents the output stream to which the file contents will be written
    * @throws IOException if the file does not exist 
    */
    private static void serveFile(String filename, String contentType, OutputStream out) throws IOException {
        //Need to implement this method
    }

    /** 
    * Opens a file in the default web browser
    * @param fileName represents the name of the file to be opened in the web browser.
    * @throws IOException if there problem in  while opening the file in the web browser.
    * @return boolean return True if the file was successfully opened
    *         returned False if an IOException occurred while attempting to open the file.
    */
    private static boolean openInBrowser(String fileName) throws IOException {
        //Need to implement this method
    }
}