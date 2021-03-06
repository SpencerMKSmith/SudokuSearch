package com.smks.personal.sudoku.init.unit;

import com.smks.personal.sudoku.data.Position;

public class BlockCellPositionFunction implements CellPositionFunction {

	@Override
	public Integer cellNumberToExtract(Integer gridSize, Integer unitNumber, Integer itemNumber) {
		final Integer unitIndex = unitNumber - 1;
		final Integer itemIndex = itemNumber -1;
		
		// How wide each block is, generally 3
		final Integer blockWidth = (int) Math.sqrt(gridSize);
		
		// The block row and column number.  Value between 0 and 2
		final Integer blockRow = unitIndex / blockWidth;
		final Integer blockColumn = unitIndex % blockWidth;
		
		// Row of the grid that the cell we're looking for is in
		final Integer rowThatTheCellIsIn = blockRow * blockWidth + (itemIndex % blockWidth);
		
		// Column that the cell is in
		final Integer columnThatTheCellIsIn = blockColumn * blockWidth + (itemIndex % blockWidth);
		
		final Integer cellNumber = rowThatTheCellIsIn * gridSize + columnThatTheCellIsIn + 1;

		return cellNumber;
	}

	@Override
	public Position getCellPositionForUnitAndItem(Integer gridSize, Integer blockIndex, Integer itemIndex) {
		// How wide each block is, generally 3
		final Integer blockWidth = (int) Math.sqrt(gridSize);
		
		// The block row and column number.  Value between 0 and 2
		final Integer blockRow = blockIndex / blockWidth;
		final Integer blockColumn = blockIndex % blockWidth;

		// Row of the grid that the cell we're looking for is in
		final Integer rowThatTheCellIsIn = blockRow * blockWidth + (itemIndex / blockWidth);

		// Column that the cell is in
		final Integer columnThatTheCellIsIn = blockColumn * blockWidth + (itemIndex % blockWidth);
		
		return Position.of(columnThatTheCellIsIn, rowThatTheCellIsIn);
	}

}
