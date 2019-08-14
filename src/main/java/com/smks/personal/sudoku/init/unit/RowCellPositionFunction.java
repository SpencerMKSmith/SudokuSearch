package com.smks.personal.sudoku.init.unit;

import com.smks.personal.sudoku.data.Position;

/*
 * Returns the Position of the cell requested
 */
public class RowCellPositionFunction implements CellPositionFunction {

	@Override
	public Integer cellNumberToExtract(Integer gridSize, Integer unitNumber, Integer itemNumber) {
		return gridSize * (unitNumber - 1) + itemNumber;
	}

	@Override
	public Position getCellPositionForUnitAndItem(Integer gridSize, Integer rowIndex, Integer itemIndex) {
		return Position.of(itemIndex, rowIndex);
	}

}
