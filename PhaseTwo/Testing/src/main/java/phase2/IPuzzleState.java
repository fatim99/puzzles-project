package phase2;
interface IPuzzleState {
    String[][] getPuzzle();
    void setPuzzle(String[][] puzzle);
    String getPuzzleData();
    void updateCell(int row, int column, String value);
}