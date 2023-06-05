class PuzzleState implements IPuzzleState{
    private String[][] puzzle;
    private StringBuilder puzzleData;

    public PuzzleState() {
        puzzle = new String[0][0];
        puzzleData = new StringBuilder();
    }

    public String[][] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(String[][] puzzle) {
        this.puzzle = puzzle;
        updatePuzzleData();
    }

    public String getPuzzleData() {
        return puzzleData.toString();
    }

    public void updateCell(int row, int column, String value) {
        puzzle[row][column] = value;
        updatePuzzleData();
    }

    private void updatePuzzleData() {
        puzzleData.setLength(0);
        for (String[] row : puzzle) {
            for (String value : row) {
                puzzleData.append(value).append(" ");
            }
            puzzleData.append("\n");
        }
    }
}
