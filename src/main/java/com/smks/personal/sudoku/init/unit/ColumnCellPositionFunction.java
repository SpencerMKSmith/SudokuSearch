package com.smks.personal.sudoku.init.unit;

import com.smks.personal.sudoku.data.Position;

/*
 * Returns the Position of the cell requested
 */
public class ColumnCellPositionFunction implements CellPositionFunction {

	@Override
	public Integer cellNumberToExtract(Integer gridSize, Integer unitNumber, Integer itemNumber) {
		return gridSize * (itemNumber - 1) + unitNumber;
	}

	@Override
	public Position getCellPositionForUnitAndItem(Integer gridSize, Integer columnIndex, Integer itemIndex) {
		return Position.of(columnIndex, itemIndex);
	}

}
