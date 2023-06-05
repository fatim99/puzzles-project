package phase2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PuzzleParserTest {
	
	
	String oneRowFiveColumns = "C:\\Users\\rasha\\Desktop\\oneRowFiveColumns.txt";
	String validFilePath =  "C:\\Users\\rasha\\Desktop\\valid_file.txt";
    String emptyFilePath = "C:\\Users\\rasha\\Desktop\\empty_file.txt";
    String invalidFilePath = "C:\\Users\\rasha\\Desktop\\invalid_fiile.txt";
    String oneColumnSevenRows = "C:\\Users\\rasha\\Desktop\\oneColumnSevenRows.txt";
    @Test
    void testParsePuzzleData() throws IOException {
        // Partition: Parsing puzzle data from a file
    	
        // Subdomain: 
    	// 1. Valid file path with correct puzzle data
        // 2. Valid file path with empty puzzle data
        // 3. Invalid file path
    	
        // Test Cases:
        // 1. Valid file path with 9rows and 9 columns puzzle data-->return expected puzzle
        // 2. Valid empty file path with 0 rows and 0 columns-->return 0
        // 3. Invalid file path with a spelling mistake--> IOException
    	
    	String[][] expectedPuzzle = {
    		    {"5", "3", ".", ".", "7", ".", ".", ".", "."},
    		    {"6", ".", ".", "1", "9", "5", ".", ".", "."},
    		    {".", "9", "8", ".", ".", ".", ".", "6", "."},
    		    {"8", ".", ".", ".", "6", ".", ".", ".", "3"},
    		    {"4", ".", ".", "8", ".", "3", ".", ".", "1"},
    		    {"7", ".", ".", ".", "2", ".", ".", ".", "6"},
    		    {".", "6", ".", ".", ".", ".", "2", "8", "."},
    		    {".", ".", ".", "4", "1", "9", ".", ".", "5"},
    		    {".", ".", ".", ".", "8", ".", ".", "7", "9"}
    		};
       

        String[][] parsedPuzzle = PuzzleParser.parsePuzzleData(validFilePath);
        Assertions.assertArrayEquals(expectedPuzzle, parsedPuzzle);
        

        parsedPuzzle = PuzzleParser.parsePuzzleData(emptyFilePath);
        Assertions.assertEquals(0, parsedPuzzle.length);

        Assertions.assertThrows(IOException.class, () -> PuzzleParser.parsePuzzleData(invalidFilePath));
    }

    @Test
    void testCountRows() throws IOException {
        // Partition: Counting the number of rows in a file
    	
        // Subdomain: 
    	// 1. Empty file 
        // 2. File with a single row
        // 3. File with multiple rows
    	
        // Test Cases:
       
    	// 1. Empty file (0 rows)-->return 0
        // 2. File with a single row and Five columns-->return 1
        // 3. File with Seven rows and one columns-->return 7
        
        int numRows = PuzzleParser.countRows(emptyFilePath);
        Assertions.assertEquals(0, numRows);

        numRows = PuzzleParser.countRows(oneRowFiveColumns);
        Assertions.assertEquals(1, numRows);

        numRows = PuzzleParser.countRows(oneColumnSevenRows);
        Assertions.assertEquals(7, numRows);
    }

    @Test
    void testCountCols() throws IOException {
        // Partition: Counting the number of columns in a file
    	
        // Subdomain: 
    	// 1. Empty file 
        // 2. File with a single row and multiple columns
        // 3. File with multiple rows and one columns
    	
        // Test Cases:
        // 1. Empty file (0 columns)-->return 0
        // 2. File with a single row and Five columns-->return 5
        // 3. File with Seven rows and one columns-->return 1
        

        int numCols = PuzzleParser.countCols(emptyFilePath);
        Assertions.assertEquals(0, numCols);

        numCols = PuzzleParser.countCols(oneColumnSevenRows);
        Assertions.assertEquals(1, numCols);

        numCols = PuzzleParser.countCols(oneRowFiveColumns);
        Assertions.assertEquals(5, numCols);

        
    }
}
