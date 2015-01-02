package main.java.org.zambiesurvival.gui;

/**
 *
 * @author Nelnel33
 */
public class MatrixDimension {
    public final int row, col;
    
    public MatrixDimension(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    /**
     * Converts a matrix index to a vector index.
     * @param row
     * Can be from 0 to n-1 inclusive.
     * @param col
     * Can be from 0 to n-1 inclusive.
     * @param maxcol
     * @return 
     */
    private static int convertMatrixToVectorIndex(int row, int col, int maxcol){
        return ((row)*maxcol)+col;
    }
    
    /**
     * Converts a matrix index to a vector index.
     * @param row
     * Can be from 0 to n-1 inclusive.
     * @param col
     * Can be from 0 to n-1 inclusive.
     * @param maxcol
     * @return 
     */
    private int convertMatrixToVectorIndex(int row, int col){
        return ((row)*this.col)+col;
    }
}
