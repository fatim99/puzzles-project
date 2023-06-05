
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PuzzleParser {

    // Private constructor to prevent instantiation
    private PuzzleParser() {
    }

    /*
      * @param filePath the path of the file containing the puzzle data
      * @return a 2D array of strings representing the parsed puzzle data
      * @throws IOException if an I/O error occurs while reading the file
     */
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

    /**
     * @param filePath the path of the file to count the rows in
     * @return the number of rows in the file
     * @throws IOException if an I/O error occurs while reading the file
     */
    private static int countRows(String filePath) throws IOException {
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

    /**
     * @param filePath the path of the file to count the columns in
     * @return the number of columns in the file
     * @throws IOException if an I/O error occurs while reading the file
     */
    private static int countCols(String filePath) throws IOException {
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
