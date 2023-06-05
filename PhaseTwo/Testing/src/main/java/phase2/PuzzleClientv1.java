package phase2;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class PuzzleClientv1 {
    private static final String SUDOKU_PUZZLE_URL = "http://localhost:8000/SudokuBlankPuzzle";
    private static final String HEXSUDOKU_PUZZLE_URL = "http://localhost:8000/HexSudokuBlankPuzzle";

    private JFrame frame;
    private JPanel buttonPanel;
    private JPanel finishPanel;
    private JButton sudokuButton;
    private JButton hexSudokuButton;
    private JPanel puzzlePanel;
    private JTextField[][] puzzleFields;
    private PuzzleState puzzleState;
    private JFrame solutionFrame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PuzzleClient().createAndShowGUI());
    }		

    private void createAndShowGUI() {
        puzzleState = new PuzzleState();
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
/*Test Cases:

Verify that the user input is correctly saved to the puzzle state.
Verify that an output file is created and written with the correct user input and puzzle state.
Verify that an error message is shown when there is an error while saving the user input.
Testing Plan:
  Partition the user input:
Test with valid user input (digits from 1 to 9 or A to G, or '-' for empty cells).
Test with invalid user input (empty input or invalid characters).
Partition the output file:
Test with an existing output file.
Test without an existing output file.
Subdomain: File I/O
Verify the creation and writing of the output file.
Verify the handling of errors during file I/O operations.
 */
    private void saveUserInput() {
        String[][] userInput = new String[puzzleFields.length][puzzleFields[0].length];

        for (int i = 0; i < puzzleState.getPuzzle().length; i++) {
            for (int j = 0; j < puzzleState.getPuzzle()[0].length; j++) {
                String input = puzzleFields[i][j].getText();

                boolean isValidInput = isValidInput(input);
                if (!isValidInput) {
                    String errorMessage = getErrorMessage(input);
                    showErrorMessage(errorMessage);
                    return;
                }

                userInput[i][j] = input;
                puzzleState.updateCell(i, j, input); // Update the puzzle state
            }
        }

        String gameType = (puzzleFields.length == 9) ? "Sudoku" : "HexSudoku";
        String outputFilename = gameType + "Output.txt";
        File outputFile = new File(outputFilename);

        if (outputFile.exists()) {
            outputFile.delete();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            for (String[] row : userInput) {
                writer.write(String.join(" ", row));
                writer.newLine();
            }

            writer.write("-----");
            writer.newLine();

            for (String[] row : puzzleState.getPuzzle()) {
                writer.write(String.join(" ", row));
                writer.newLine();
            }

            showMessage("User input saved successfully!");
        } catch (IOException e) {
            showErrorMessage("An error occurred while saving the user input.");
        }
    }

    /*
     * 
Verify that a valid input returns true.
Verify that an invalid input returns false.
Testing Plan:

Partition the input:
Test with valid inputs (digits from 1 to 9 or A to G, or '-').
Test with invalid inputs (empty input or invalid characters).
Subdomain: Input Validation
Verify the correctness of the regular expression used for input validation.
     * 
     */
    private boolean isValidInput(String input) {
        return input.matches("[1-9]|1[0-6]|-");
    }
/*
 * Test Cases:

Verify that an error message is returned for an empty input.
Verify that an error message is returned for an invalid input.
Testing Plan:

Partition the input:
Test with an empty input.
Test with an invalid input.
Subdomain: Error Handling
Verify the correctness and clarity of the error messages returned.*/
    private String getErrorMessage(String input) {
        if (input.isEmpty()) {
            return "Input cannot be empty.";
        } else {
            return "Invalid input. Please enter a digit from 1 to 9 or A to G, or '-' for empty cells.";
        }
    }
    /*
     * Test Cases:

    Verify that the error message dialog is displayed correctly with the given error message.
    Testing Plan:

    Partition the error messages:
    Test with a general error message.
    Test with specific error messages related to input validation.
    Subdomain: Error Handling
    Verify the correct display of error messages in the error message dialog.
     */
    private void showErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage, "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }
/*
 * Test Cases:

Verify that the message dialog is displayed correctly with the given message.
Testing Plan:

Partition the messages:
Test with a general message.
Test with specific messages related to user input saving.
Subdomain: User Interaction
Verify the correct display of messages in the message dialog.
 */
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
/*
 * Test Cases:

Verify that the puzzle is fetched correctly from the given URL.
Verify that an error message is shown when there is an error while fetching the puzzle.
Testing Plan:

Partition the URL and response code:
Test with a valid URL and successful response.
Test with an invalid URL and unsuccessful response.
Subdomain: Network Communication
Verify the correct fetching of the puzzle from the URL.
Verify the handling of errors during network communication.
 */
    private void fetchAndDisplayPuzzle(String url, int puzzleSize) {
        try {
            URL puzzleUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) puzzleUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;

                String[][] puzzle = new String[puzzleSize][puzzleSize];
                int row = 0;

                while ((line = bufferedReader.readLine()) != null) {
                    String[] values = line.split(" ");
                    puzzle[row] = values;
                    row++;
                }

                bufferedReader.close();
                inputStreamReader.close();

                // Display the puzzle on the UI
                displayPuzzle(puzzle, puzzleSize);
            } else {
                showErrorMessage("Error fetching puzzle: " + responseCode);
            }
        } catch (IOException e) {
            showErrorMessage("An error occurred while fetching the puzzle.");
        }
    }
/*
 * Test Cases:

Verify that the puzzle is displayed correctly in the puzzle panel.
Verify that the finish panel becomes visible after displaying the puzzle.
Testing Plan:

Partition the puzzle:
Test with different puzzle sizes (e.g., 9x9, 16x16).
Test with different puzzle values (digits from 1 to 9 or A to G).
Subdomain: GUI
Verify the correct display of the puzzle in the puzzle panel.
Verify the visibility of the finish panel after displaying the puzzle.
 */
    private void displayPuzzle(String[][] puzzle, int puzzleSize) {
        puzzlePanel.removeAll();
        puzzlePanel.setLayout(new GridLayout(puzzleSize, puzzleSize));

        puzzleFields = new JTextField[puzzleSize][puzzleSize];
        for (int i = 0; i < puzzleSize; i++) {
            for (int j = 0; j < puzzleSize; j++) {
                String cellValue = puzzle[i][j];

                JTextField textField = new JTextField(cellValue, 2);
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setEditable(true);

                puzzleFields[i][j] = textField;
                puzzlePanel.add(textField);
            }
        }

        finishPanel.setVisible(true);

        frame.revalidate();
        frame.repaint();
    }
/*
 * Test Cases:

Verify that the solution frame is created and displayed correctly with the given solution.
Testing Plan:

Partition the solution:
Test with different puzzle sizes (e.g., 9x9, 16x16).
Test with different solution values (digits from 1 to 9 or A to G).
Subdomain: GUI
Verify the creation and display of the solution frame.
Verify the correctness of the solution displayed in the solution frame.
 */
    private void showSolution() {
        String[][] solution = puzzleState.getPuzzle();
        int puzzleSize = solution.length;

        solutionFrame = new JFrame("Solution");
        solutionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        solutionFrame.setLayout(new GridLayout(puzzleSize, puzzleSize));

        for (int i = 0; i < puzzleSize; i++) {
            for (int j = 0; j < puzzleSize; j++) {
                String cellValue = solution[i][j];

                JTextField textField = new JTextField(cellValue, 2);
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setEditable(false);

                solutionFrame.add(textField);
            }
        }

        solutionFrame.setSize(400, 400);
        solutionFrame.setVisible(true);
    }
}
