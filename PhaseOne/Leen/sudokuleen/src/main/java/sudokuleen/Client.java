/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudokuleen;

import java.awt.Image;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.io.*;
import java.net.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Client {
    public static void main(String[] args) {
        try {
            // Set up the socket connection to the server
            Socket socket = new Socket("localhost", 8000);
            System.out.println("Connected to server");
            
            // Send request for image 1
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("GET /image1 HTTP/1.1");
            System.out.println("Request sent for image 1");
            
            // Read the server's response
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                if (responseLine.equals("")) {
                    break;
                }
            }
            
            // Read the image data from the server
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            InputStream input = socket.getInputStream();
            while ((nRead = input.read(data, 0, data.length)) != -1) {
              buffer.write(data, 0, nRead);
            }
            buffer.flush();
            byte[] imageData = buffer.toByteArray();
            System.out.println("Received image 1 data");
            
            // Display the image
            ImageIcon image = new ImageIcon(imageData);
            JLabel label = new JLabel(image);
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(label);
            frame.pack();
            frame.setVisible(true);
            
            // Send request for image 2
            out.println("GET /image2 HTTP/1.1");
            System.out.println("Request sent for image 2");
            
            // Read the server's response
            while ((responseLine = in.readLine()) != null) {
                if (responseLine.equals("")) {
                    break;
                }
            }
            
            // Read the image data from the server
            buffer.reset();
            while ((nRead = input.read(data, 0, data.length)) != -1) {
              buffer.write(data, 0, nRead);
            }
            buffer.flush();
            imageData = buffer.toByteArray();
            System.out.println("Received image 2 data");
            
            // Display the image
            image = new ImageIcon(imageData);
            label = new JLabel(image);
            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(label);
            frame.pack();
            frame.setVisible(true);
            
            // Close the connection
            socket.close();
            System.out.println("Disconnected from server");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}



// Client Testing Strategy
// Test Suite:
// Partition 1: Socket Connection
// Subdomain 1: Connection establishment
// Subdomain 2: Connection termination
// Coverage: Test that the client can establish a connection to the server and disconnect from it
// Partition 2: Image Request and Display
// Subdomain 1: Request for image 1
// Subdomain 2: Request for image 2
// Subdomain 3: Image display
// Coverage: Test that the client can request images from the server and display them properly
// Check if the socket connection to the server is successful
// Verify that the request for image 1 is successfully sent
// Verify that the server's response for image 1 is successfully read
// Verify that the image data for image 1 is successfully read
// Verify that image 1 is displayed correctly
// Verify that the request for image 2 is successfully sent
// Verify that the server's response for image 2 is successfully read
// Verify that the image data for image 2 is successfully read
// Verify that image 2 is displayed correctly
// Verify that the connection is closed properly