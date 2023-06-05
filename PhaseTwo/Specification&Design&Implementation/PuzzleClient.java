import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PuzzleClient {
    private static final String SUDOKU_PUZZLE_URL = "http://localhost:8000/SudokuBlankPuzzle";
    private static final String HEXSUDOKU_PUZZLE_URL = "http://localhost:8000/HexSudokuBlankPuzzle";

    private JFrame frame;
    private JPanel buttonPanel;
    private JPanel finishPanel;
    private JButton sudokuButton;
    private JButton hexSudokuButton;
    private JPanel puzzlePanel;
    private JTextField[][] puzzleFields;
    JFrame solutionFrame = new JFrame("Puzzle Solution");
    StringBuilder puzzleData = new StringBuilder();


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PuzzleClient().createAndShowGUI());
    }

    void createAndShowGUI() {
        frame = new JFrame("Puzzle Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        // Create the buttons
        sudokuButton = new JButton("Sudoku");
        hexSudokuButton = new JButton("HexSudoku");
    
        // Add action listeners to the buttons
        sudokuButton.addActionListener(e -> fetchAndDisplayPuzzle(SUDOKU_PUZZLE_URL, 9));
        hexSudokuButton.addActionListener(e -> fetchAndDisplayPuzzle(HEXSUDOKU_PUZZLE_URL, 16));
    
        // Create the button panel and add the buttons
        buttonPanel = new JPanel();
        buttonPanel.add(sudokuButton);
        buttonPanel.add(hexSudokuButton);
    
        // Create the puzzle panel and puzzle fields
        puzzlePanel = new JPanel();
        puzzleFields = new JTextField[0][0];
    
        // Create the submit button and add an action listener
        JButton submitButton = new JButton("Submit");
        JButton showSolutionButton = new JButton("Show Solution");
        finishPanel = new JPanel();
        finishPanel.add(submitButton);
        finishPanel.add(showSolutionButton);
        finishPanel.setVisible(false);
    
        submitButton.addActionListener(e -> saveUserInput());
        showSolutionButton.addActionListener(e -> showSolution());
    
        // Create the main content pane and add the panels and submit button
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(buttonPanel, BorderLayout.NORTH);
        contentPane.add(puzzlePanel, BorderLayout.CENTER);
        contentPane.add(finishPanel, BorderLayout.SOUTH);
    
        // Set the frame size and make it visible
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
    
    private void saveUserInput() {
        String[][] userInput = new String[puzzleFields.length][puzzleFields[0].length];
    
        for (int i = 0; i < puzzleFields.length; i++) {
            for (int j = 0; j < puzzleFields[0].length; j++) {
                String input = puzzleFields[i][j].getText();
    
                boolean isValidInput = isValidInput(input);
                if (!isValidInput) {
                    String errorMessage = getErrorMessage(input);
                    JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
    
                userInput[i][j] = input;
            }
        }
    
        String gameType = (puzzleFields.length == 9) ? "Sudoku" : "HexSudoku";
        String outputFilename = gameType + "Output.txt";
        File outputFile = new File(outputFilename);
    
        if (outputFile.exists()) {
            outputFile.delete();
        }
    
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            StringBuilder puzzleData = new StringBuilder();
            for (String[] row : userInput) {
                for (String value : row) {
                    puzzleData.append(value).append(" ");
                }
                puzzleData.append("\n");
            }
            writer.write(puzzleData.toString());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Error saving puzzle: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        String solvedPuzzleFilename = (puzzleFields.length == 9) ? "SudokuSolvedPuzzle.txt" : "HexSudokuSolvePuzzle.txt";
    
        try {
            boolean isEqual = areFilesEqual(outputFilename, solvedPuzzleFilename);
            if (isEqual) {
                JOptionPane.showMessageDialog(frame, "Congrats!!! You Won.", "Output Check", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Your solution is not correct, please try again.", "Output Check", JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error checking output: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean isValidInput(String input) {
        if (puzzleFields.length == 9) {
            return input.matches("[1-9]");
        } else {
            return input.matches("[0-9a-fA-F]");
        }
    }
    
    private String getErrorMessage(String input) {
        if (puzzleFields.length == 9) {
            return "Invalid input: " + input + ". Please enter a number from 1 to 9.";
        } else {
            return "Invalid input: " + input + ". Please enter a number or letter from 0 to 9, or a to f.";
        }
    }
    

    void showSolution() {
        solutionFrame.getContentPane().removeAll();
        try {
            URL url;
            if (puzzleFields.length == 9) {
                url = new URL("http://localhost:8000/SudokuSolvedPuzzle");
            } else {
                url = new URL("http://localhost:8000/HexSudokuSolvePuzzle");
            }
    
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "text/plain");
    
            try (OutputStream outputStream = con.getOutputStream()) {
                outputStream.write(puzzleData.toString().getBytes());
                outputStream.flush();
            }
    
            StringBuilder solutionData = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String response;
                while ((response = in.readLine()) != null) {
                    solutionData.append(response);
                }
            }
    
            JPanel solutionPanel = createSolutionPanel(solutionData.toString());
            displaySolutionPanel(solutionPanel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error sending puzzle: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private JPanel createSolutionPanel(String solutionData) {
        JPanel solutionPanel = new JPanel(new GridLayout(puzzleFields.length, puzzleFields[0].length));
        solutionPanel.setPreferredSize(puzzlePanel.getPreferredSize());
    
        String[] solutionValues = solutionData.split(";");
        for (int i = 0; i < puzzleFields.length; i++) {
            for (int j = 0; j < puzzleFields[0].length; j++) {
                JTextField solutionField = new JTextField(solutionValues[i * puzzleFields.length + j], 1);
                solutionField.setEditable(false);
                solutionField.setHorizontalAlignment(JTextField.CENTER);
                solutionField.setFont(new Font("Dialog", Font.PLAIN, 18));
                solutionField.setBackground(puzzleFields[i][j].getBackground());
                solutionField.setBorder(puzzleFields[i][j].getBorder());
                solutionPanel.add(solutionField);
            }
        }
    
        return solutionPanel;
    }
    
    private void displaySolutionPanel(JPanel solutionPanel) {
        solutionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        solutionFrame.getContentPane().add(solutionPanel);
        solutionFrame.pack();
        solutionFrame.setVisible(true);
    }
    

    private void fetchAndDisplayPuzzle(String puzzleUrl, int size) {
        try {
            String puzzleString = sendHttpGetRequest(puzzleUrl);
            String[][] puzzle = decodePuzzleFromString(puzzleString, "; ");
            displayPuzzle(puzzle, size);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error fetching puzzle: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finishPanel.setVisible(true);
    }

    private String sendHttpGetRequest(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    private String[][] decodePuzzleFromString(String puzzleString, String delimiter) {
        String[] rows = puzzleString.split("\n");
        String[][] puzzle = new String[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            puzzle[i] = rows[i].split(delimiter);
        }
        return puzzle;
    }

    private void displayPuzzle(String[][] puzzle, int size) {
        // Create the puzzle panel and puzzle fields
        puzzlePanel.removeAll();
        puzzlePanel.setLayout(new GridLayout(size, size));
        puzzleFields = new JTextField[size][size];
    
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JTextField field = new JTextField(2);
                field.setHorizontalAlignment(JTextField.CENTER);
                field.setEditable(false);
                puzzleFields[i][j] = field;
                puzzlePanel.add(field);
    
                // Add action listener to the puzzle field
                int finalI = i;
                int finalJ = j;
                field.addActionListener(e -> {
                    // Update the corresponding value in the puzzle array
                    puzzle[finalI][finalJ] = field.getText();
                });
            }
        }
    
        // Display the puzzle values in the puzzle fields
        int row = 0;
        int column = 0;
        for (String[] rowValues : puzzle) {
            for (String value : rowValues) {
                JTextField field = puzzleFields[row][column];
                field.setText(value);
                field.setEditable(value.equals("-")); // Set editability to true for dash values only
                field.setText(value.equals("-") ? "" : value); // Display an empty string instead of dash
                column++;
                if (column == size) {
                    // Move to the next row after displaying size elements
                    column = 0;
                    row++;
                }
            }
        }
    
        // Redraw the puzzle panel
        puzzlePanel.revalidate();
        puzzlePanel.repaint();
    }
    

    private boolean areFilesEqual(String file1, String file2) throws IOException {
        BufferedReader reader1 = new BufferedReader(new FileReader(file1));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2));
    
        String line1, line2;
        boolean isFile1End = false;
        boolean isFile2End = false;
    
        while (!isFile1End && !isFile2End) {
            line1 = reader1.readLine();
            line2 = reader2.readLine();
    
            if (line1 == null) {
                isFile1End = true;
            }
            if (line2 == null) {
                isFile2End = true;
            }
    
            if (line1 != null && line2 != null && !line1.equals(line2)) {
                reader1.close();
                reader2.close();
                return false;
            }
        }
    
        reader1.close();
        reader2.close();

        return isFile1End && isFile2End;
    }

}