package phase2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PuzzleParser {

    // Private constructor to prevent instantiation
    private PuzzleParser() {
    }

    // Partition: Parsing puzzle data from a file
    // Subdomain: 
	// 1. Valid file path with correct puzzle data
    // 2. Valid file path with empty puzzle data
    // 3. Invalid file path
    // Test Cases:
    // 1. Valid file path with 9rows and 9 columns puzzle data-->return expected puzzle (cover Subdomain 1 )
    // 2. Valid empty file path with 0 rows and 0 columns-->return 0 (cover Subdomain 2 )
    // 3. Invalid file path with a spelling mistake--> IOException (cover Subdomain 3 )
    public static String[][] parsePuzzleData(String filePath) throws IOException {
        // Create a BufferedReader to read the puzzle data from the file
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        // Determine the number of rows and columns in the file
        int numRows = countRows(filePath);
        int numCols = countCols(filePath);

        // Create a 2D array to store the puzzle data
        String[][] puzzle = new String[numRows][numCols];

        // Reset the reader to the beginning of the file
        reader.close();
        reader = new BufferedReader(new FileReader(filePath));

        // Loop through each line in the file and parse the puzzle data
        int row = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            String[] cells = line.split(" ");
            for (int col = 0; col < cells.length; col++) {
                puzzle[row][col] = cells[col];
            }
            row++;
        }

        // Close the reader and return the parsed puzzle data
        reader.close();
        return puzzle;
    }

    // Partition: Counting the number of rows in a file
    // Subdomain: 
	// 1. Empty file 
    // 2. File with a single row
    // 3. File with multiple rows
    // Test Cases:
	// 1. Empty file (0 rows)-->return 0 (cover Subdomain 1 )
    // 2. File with a single row and Five columns-->return 1 (cover Subdomain 2 )
    // 3. File with Seven rows and one columns-->return 7 (cover Subdomain 3 )
    static int countRows(String filePath) throws IOException {
        // Create a BufferedReader to read the puzzle data from the file
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        // Count the number of rows in the file
        int numRows = 0;
        while (reader.readLine() != null) {
            numRows++;
        }

        // Close the reader and return the number of rows
        reader.close();
        return numRows;
    }

    // Partition: Counting the number of columns in a file
    // Subdomain: 
	// 1. Empty file 
    // 2. File with a single row and multiple columns
    // 3. File with multiple rows and one columns
    // Test Cases:
    // 1. Empty file (0 columns)-->return 0 (cover Subdomain 1 )
    // 2. File with a single row and Five columns-->return 5 (cover Subdomain 2 )
    // 3. File with Seven rows and one columns-->return 1 (cover Subdomain 3 )
    static int countCols(String filePath) throws IOException {
        // Create a BufferedReader to read the puzzle data from the file
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        // Determine the number of columns in the file
        int numCols = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            int count = line.split(" ").length;
            if (count > numCols) {
                numCols = count;
            }
        }

        // Close the reader and return the number of columns
        reader.close();
        return numCols;
    }

}
