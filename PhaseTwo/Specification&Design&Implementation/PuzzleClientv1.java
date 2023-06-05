import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

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

        /**
        Creates and shows the graphical user interface (GUI) for the Puzzle Client.
        Initializes the puzzle state, sets up the frame, buttons, panels, and event listeners.
        @param None
        @return None
        @throws None
        */

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

    /**
    Saves the user input from the puzzle fields to a file.
    Validates the input, updates the puzzle state, and writes the user input and puzzle state to a file.
    @param None
    @return None
    @throws IOException if an error occurs while saving the user input
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

    /**
    Checks if the given input is valid.
    @param input the input string to validate
    @return true if the input is valid, false otherwise
    @throws None
    */
    private boolean isValidInput(String input) {
        return input.matches("[1-9]|1[0-6]|-");
    }

    /**
    Gets the error message for the given input.
    @param input the input string
    @return the error message corresponding to the input
    @throws None
    */
    private String getErrorMessage(String input) {
        if (input.isEmpty()) {
            return "Input cannot be empty.";
        } else {
            return "Invalid input. Please enter a digit from 1 to 9 or A to G, or '-' for empty cells.";
        }
    }

    /**
    Displays an error message dialog box.
    @param errorMessage the error message to display
    @return None
    @throws None
    */
    private void showErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage, "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }

    /**
    Displays a message dialog box.
    @param message the message to display
    @return None
    @throws None
    */
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
    Fetches a puzzle from a specified URL and displays it on the UI.
    @param url the URL to fetch the puzzle from
    @param puzzleSize the size of the puzzle (e.g., 9 for Sudoku, 16 for HexSudoku)
    @return None
    @throws IOException if an error occurs while fetching the puzzle
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

    /**
    Displays the puzzle on the UI, allowing user interaction.
    @param puzzle The puzzle grid represented as a 2D array of strings.
    @param puzzleSize The size of the puzzle grid.
    @return None
    @throws None
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


    /**
    Displays the solution of the puzzle on a separate frame.
    @return None
    @throws None
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
