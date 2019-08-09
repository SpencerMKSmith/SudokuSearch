package com.smks.personal.sudoku.init.unit;

/*
 * Returns the cell number of the requested item in a unit.
 * 
 * Example: gridSize = 9, unitNumber (row) = 2, itemNumber = 3
 * Output = 20; The third item on the second column is the 20th item in the flat input list
 */
public class ColumnCellNumberFunction implements CellNumberFunction {

	@Override
	public Integer cellNumberToExtract(Integer gridSize, Integer unitNumber, Integer itemNumber) {
		return gridSize * (itemNumber - 1) + unitNumber;
	}

}
