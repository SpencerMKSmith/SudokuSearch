package com.smks.personal.sudoku.init.unit;

/*
 * Interface which will be used to determine the index of the cells which correspond
 * to a given unit (row, column, block).
 */
public interface CellNumberFunction {

	Integer cellNumberToExtract(Integer gridSize, Integer unitNumber, Integer itemNumber);
}
