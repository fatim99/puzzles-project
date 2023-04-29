package sudokuleen;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            // Set up the server socket
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server started on port 8000");

            while (true) {
                // Wait for a client to connect
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");

                // Create input and output streams
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read the client's request
                String request = in.readLine();
                System.out.println("Request received: " + request);

                // Handle the request
                sendImageToClient(clientSocket, request, out);

                // Close the connection
                clientSocket.close();
                System.out.println("Client disconnected");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void sendImageToClient(Socket clientSocket, String request, PrintWriter out) throws IOException {
        if (request.equals("GET /image1 HTTP/1.1")) {
            // Send image 1 to the client
            File file = new File("C:\\Users\\Leene\\Downloads\\unsolved.jpeg");
            FileInputStream fileIn = new FileInputStream(file);
            byte[] fileData = new byte[(int) file.length()];
            fileIn.read(fileData);
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: image/jpeg");
            out.println("Content-Length: " + file.length());
            out.println();
            out.flush();
            clientSocket.getOutputStream().write(fileData);
            clientSocket.getOutputStream().flush();
            fileIn.close();
        } else if (request.equals("GET /image2 HTTP/1.1")) {
            // Send image 2 to the client
            File file = new File("C:\\Users\\Leene\\Downloads\\solved.jpeg");
            FileInputStream fileIn = new FileInputStream(file);
            byte[] fileData = new byte[(int) file.length()];
            fileIn.read(fileData);
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: image/jpeg");
            out.println("Content-Length: " + file.length());
            out.println();
            out.flush();
            clientSocket.getOutputStream().write(fileData);
            clientSocket.getOutputStream().flush();
            fileIn.close();
        } else {
            // Invalid request
            out.println("HTTP/1.1 400 Bad Request");
            out.println();
            out.flush();
        }
    }
}
// Server Testing Strategy
// @Test suite:
// The partitions
// Their subdomains
// Their coverage

// This code sets up a server socket and listens for client connections.
// Upon receiving a request, the server sends back an image to the client based on the request.
// To test this code, we can use the following test suite:

// Test suite: Server testing strategy for each method and function available
// Partitions:
// 1. Server socket setup
// 2. Client connection
// 3. Input and output streams
// 4. Client request handling
// 5. Image sending
// Subdomains:
// 1. Server socket setup:
// a. Valid port number
// b. Invalid port number
// 2. Client connection:
// a. Client connects successfully
// b. Client fails to connect
// c. Valid request with HTTP version 1.0
// d. Valid request with HTTP version 1.1
// 3. Input and output streams:
// a. Input and output streams are created successfully
// b. Input and output streams fail to be created
// 4. Client request handling:
// a. Valid GET request for image 1
// b. Valid GET request for image 2
// c. Invalid GET request
// d. Valid request with HTTP method POST
// e. Valid request with HTTP method PUT
// f. Valid request with HTTP method DELETE
// 5. Image sending:
// a. Image 1 sent successfully
// b. Image 2 sent successfully
// c. Error when sending image

// @Coverage:
// Boundary value analysis for the length of the request
// Test for handling multiple clients concurrently
// Test for handling large file sizes

// By testing the partitions, subdomains, and their coverage,
// we can ensure that the server handles all possible scenarios and provides accurate responses to client requests.

