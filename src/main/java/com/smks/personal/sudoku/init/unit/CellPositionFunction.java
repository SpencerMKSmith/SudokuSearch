package com.smks.personal.sudoku.init.unit;

import com.smks.personal.sudoku.data.Position;

/*
 * Interface which will be used to determine the index of the cells which correspond
 * to a given unit (row, column, block).
 */
public interface CellPositionFunction {

	Integer cellNumberToExtract(Integer gridSize, Integer unitNumber, Integer itemNumber);
	Position getCellPositionForUnitAndItem(Integer gridSize, Integer unitIndex, Integer itemIndex);
}
