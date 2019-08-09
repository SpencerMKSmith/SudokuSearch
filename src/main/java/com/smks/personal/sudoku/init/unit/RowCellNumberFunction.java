package com.smks.personal.sudoku.init.unit;

/*
 * Returns the cell number of the requested item in a unit.
 * 
 * Example: gridSize = 9, unitNumber (row) = 2, itemNumber = 3
 * Output = 12; The third item on the second row is the 12th item in the flat input list
 */
public class RowCellNumberFunction implements CellNumberFunction {

	@Override
	public Integer cellNumberToExtract(Integer gridSize, Integer unitNumber, Integer itemNumber) {
		return gridSize * (unitNumber - 1) + itemNumber;
	}

}
