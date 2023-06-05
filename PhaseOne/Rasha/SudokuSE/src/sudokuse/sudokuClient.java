
package sudokuse;

import java.awt.image.BufferedImage;
import java.io.IOException;

  
public class sudokuClient {

    /**
     * Prompts the user for input(solved or notsolved) and returns the chosen image type.
     * 
     * @return The chosen image type.
     */
     public static String getImageChoice() {
  
        return imageChoice;
    }

    /**
     * Sends an HTTP request for the specified image type and returns the response as a BufferedImage.
     * 
     * @param imageChoice The chosen image type.
     * @return The response as a BufferedImage.
     * @throws IOException If an I/O error occurs while connecting to the server or reading the response.
     */
    public static BufferedImage getImageFromServer(String imageChoice) throws IOException {
        
        return image;
    }

    /**
     * Displays the specified image in a JFrame.
     * 
     * @param image The image to display.
     */
    public static void displayImage(BufferedImage image) {
       
    }

    /**
     * Runs the client.
     * 
     * @param args An array of command-line arguments.
     * @throws IOException If an I/O error occurs while connecting to the server or reading the response.
     */
    public static void main(String[] args) throws IOException {
        
    }

}


/**
 * Partition:

User input for image choice:
a. Valid input(solved or notsolved) .
b. Invalid input (a string outside the range of available images).


HTTP request for selected image:
a. Successful HTTP request.
b. Unsuccessful HTTP request due to server errors.
c. Unsuccessful HTTP request due to network errors.

Response as image:
a. Valid response (an image is returned).
b. Invalid response (an error message is returned).
c. Empty response (nothing is returned).

Subdomains:

User input for image choice:
a. Partition 1a: Enter a valid name(solved or not solved) within the range of available image names.
b. Partition 1b: Enter a string outside the range of available image names.


HTTP request for selected image:
a. Partition 2a: Send a valid HTTP request to the server.
b. Partition 2b: Send an HTTP request that results in server errors (e.g., 404 Not Found).
c. Partition 2c: Send an HTTP request that results in network errors (e.g., connection timeout).

Response as image:
a. Partition 3a: Receive a valid image as the response.
b. Partition 3b: Receive an error message as the response.
c. Partition 3c: Empty response (nothing is returned).

Test coverage:
* The test suite cover all the subdomains

Test for partition 1a: Enter a valid string within the range of available images.
Expected result: The program should proceed to send an HTTP request for the selected image.
* 
Test for partition 1b: Enter a string outside the range of available 
* Expected result: The program should display an error message indicating that the input is invalid.
* 
* 
Test for partition 2a: Send a valid HTTP request to the server.

Expected result: The program should receive a valid response containing the selected image.
Test for partition 2b: Send an HTTP request that results in server errors.

Expected result: The program should display an error message indicating that the server could not process the request.
Test for partition 2c: Send an HTTP request that results in network errors.

Expected result: The program should display an error message indicating that there is a problem with the network connection.
Test for partition 3a: Receive a valid image as the response.

Expected result: The program should display the selected image in a JFrame.
Test for partition 3b: Receive an error message as the response.

Expected result: The program should display an error message indicating that there is a problem with the server.
* 

 */