package phase2;

import java.io.FileWriter;
import java.io.IOException;

public class CreateTestFiles {

    public static void main(String[] args) {
        
        String emptyFilePath = "C:\\Users\\rasha\\Desktop\\empty_file.txt";
        String validFilePath =  "C:\\Users\\rasha\\Desktop\\valid_file.txt";
        String oneRowFiveColumns = "C:\\Users\\rasha\\Desktop\\oneRowFiveColumns.txt";
        String multipleRowsFilePath = "C:\\Users\\rasha\\Desktop\\multiple_rows_file.txt";
        String oneColumnSevenRows = "C:\\Users\\rasha\\Desktop\\oneColumnSevenRows.txt";
        
       
        

        try {
        	createFile(validFilePath,   "5 3 . . 7 . . . .\n" +
						                "6 . . 1 9 5 . . .\n" +
						                ". 9 8 . . . . 6 .\n" +
						                "8 . . . 6 . . . 3\n" +
						                "4 . . 8 . 3 . . 1\n" +
						                "7 . . . 2 . . . 6\n" +
						                ". 6 . . . . 2 8 .\n" +
						                ". . . 4 1 9 . . 5\n" +
						                ". . . . 8 . . 7 9  ");
            createFile(emptyFilePath, "");
            // Invalid file path, no need to create
            createFile(oneRowFiveColumns, "1 2 3 4 5");
            createFile(oneColumnSevenRows, "1\n2\n3\n4\n5\n6\n7");
            
           
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFile(String filePath, String content) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(content);
        writer.close();
    }
}
