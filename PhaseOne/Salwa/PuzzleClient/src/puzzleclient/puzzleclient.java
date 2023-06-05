// auther: salwa shamma
//id:4010405

package puzzleclient;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class puzzleclient {
        private static final int port = 8000;
        private static final String[] puzzleTypes = {"blank", "solved"};
        private static final int[][] puzzleNumbers = {{1, 2}, {1, 2}};
        private static final String[] puzzleNames = {"Sudoku", "Hexadecimal"};
        
    public static void main(String[] args) throws Exception {

        // Get user input for puzzle type and number
        int typeIndex = getPuzzleTypeIndex(puzzleTypes);
        int numberIndex = getPuzzleNumberIndex(puzzleNumbers[typeIndex], puzzleNames);

        // Send request to server and open image in browser
        String type = puzzleTypes[typeIndex];
        int number = puzzleNumbers[typeIndex][numberIndex];
        String puzzleUrl = "http://localhost:" + port + "/puzzle?type=" + type + "&number=" + number;
        System.out.println("Sending request to server: " + puzzleUrl);
        openImageInBrowser(puzzleUrl);
    }

    /**
    *Gets the index of the selected puzzle type from the user.
    * 
    *@param puzzleTypes an array of Strings representing the available puzzle types
    *@return an integer representing the index of the selected puzzle type in the puzzleTypes array
    *@throws InputMismatchException if the user enters an invalid input
    */
    private static int getPuzzleTypeIndex(String[] puzzleTypes) throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select puzzle type:");
        for (int i = 0; i < puzzleTypes.length; i++) {
            System.out.println((i + 1) + ": " + puzzleTypes[i]);
        }
        int typeIndex = scanner.nextInt() - 1;
        return typeIndex;
        
//        Partition:
//        1. puzzleTypes
//        Subdomains:
//        1.1 Valid puzzle type input
//        1.2 Invalid puzzle type input
//        1.3 Non-existent puzzle type input
//        Test cases:
//        1. Given puzzle types {"Sudoku", "Hexadecimal"}, input 1 should (cover subdomain 1.1) 
//        2. Given puzzle types {"Sudoku", "Hexadecimal"}, input 3 should throw an InputMismatchException (covers subdomain 1.2)
//        3. Given puzzle types {"Sudoku", "Hexadecimal"}, input "ABC" should throw an InputMismatchException (covers subdomain 1.3)
    }

    /**
    *Gets the index of the selected puzzle number from the user.
    * 
    *@param puzzleNames an array of String representing the name of each puzzle
    *@param puzzleNumbers an array of integers representing the available puzzle numbers(puzzle names) for the selected puzzle type
    *@return an integer representing the index of the selected puzzle number(puzzle names) in the puzzleNumbers array
    *@throws InputMismatchException if the user enters an invalid input
    */
    private static int getPuzzleNumberIndex(int[] puzzleNumbers,String[] puzzleNames) throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter puzzle number:");
        for (int i = 0; i < puzzleNumbers.length; i++) {
            System.out.println((i + 1) + ": " + puzzleNames[i]);
        }
        int numberIndex = scanner.nextInt() - 1;
        return numberIndex;
        
//        Partition:
//        1. puzzleNumbers
//        2. puzzleNames
//        Subdomains:
//        1.1 Valid puzzle type input
//        1.2 Invalid puzzle type input
//        1.3 Non-existent puzzle type input
//        2.1 Valid puzzle number input for the selected puzzle type
//        2.2 Invalid puzzle number input for the selected puzzle type
//        2.3 Non-integer puzzle number input
//        Test cases:
//        1. Given puzzle types {"Sudoku", "Hexadecimal"}, input 1 should cover (subdomain 1.1)
//        2. Given puzzle types {"Sudoku", "Hexadecimal"}, input 3 should throw an InputMismatchException (covers subdomain 1.2)
//        3. Given puzzle types {"Sudoku", "Hexadecimal"}, input "ABC" should throw an InputMismatchException (covers subdomain 1.3)
//        4. Given puzzle type "Sudoku" and available puzzle numbers {1, 2}, input {1, 2} should (cover subdomain 2.1)
//        5. Given puzzle type "Sudoku" and available puzzle numbers {1, 2}, input {3, 4} should throw an InputMismatchException (covers subdomain 2.2)
//        6. Input {"ABC", "DEF"} should throw an InputMismatchException (covers subdomain 2.3)

    }

    /**
    *Opens the puzzle image in a browser.
    * 
    *@param puzzleUrl a String representing the URL of the puzzle image to open in the browser
    *@return There is no return value for this method.
    *@throws IOException if there is an error opening the puzzle image in the browser
    *@throws URISyntaxException if the puzzleUrl parameter is not a valid URI
    */
    private static void openImageInBrowser(String puzzleUrl) throws IOException, URISyntaxException {
        Desktop desktop = Desktop.getDesktop();
        URI uri = new URI(puzzleUrl);
        desktop.browse(uri);
        
//       Partition:
//       1. puzzleUrl
//       Subdomains:
//       1.1 Valid URLs
//       1.2 Invalid URL
//       Test cases:
//       1. url= http://localhost:8000/puzzle?type=blank&number=2 (covers Subdomain 1.1)
//       2. url= http://localhost:8000/puzzle?type=blank&number=3 (covers Subdomain 1.2) 

    }
}
