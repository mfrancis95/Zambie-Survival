package com.amf.uitools;

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
     * @param col
     * @param maxcol
     * @return 
     */
    private static int convertMatrixToVectorIndex(int row, int col, int maxcol){
        return ((row-1)*maxcol)+col;
    }
    
    /**
     * Converts a matrix index to a vector index.
     * @param row
     * @param col
     * @param maxcol
     * @return 
     */
    private int convertMatrixToVectorIndex(int row, int col){
        return ((row-1)*this.col)+col;
    }
}
