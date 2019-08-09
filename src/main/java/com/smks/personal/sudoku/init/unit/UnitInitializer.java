package com.smks.personal.sudoku.init.unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.smks.personal.sudoku.data.Cell;
import com.smks.personal.sudoku.data.Unit;
import com.smks.personal.sudoku.data.UnitType;

/*
 * Used to initialize a list of units.  A unit is either a Row, Column, or Block
 * of cells.  Each Grid will have a number of units equal to the gridSize and a number
 * of items in that
 */
public class UnitInitializer {

	final static Map<UnitType, CellNumberFunction> CELL_NUMBER_FUNCTIONS = Map.of(
			UnitType.BLOCK, new BlockCellNumberFunction(),
			UnitType.COLUMN, new ColumnCellNumberFunction(),
			UnitType.ROW, new RowCellNumberFunction());
	
	public static List<Unit> initializeUnit(final Integer gridSize, 
			final List<Cell> cells, 
			final UnitType unitType) {
		final CellNumberFunction numberFunction = CELL_NUMBER_FUNCTIONS.get(unitType);
		
		final List<Unit> unitList = new ArrayList<>();
		
		for(int unit = 1; unit <= gridSize; unit++) {
			final List<Cell> cellsInUnit = new ArrayList<>();
			for(int itemNumber = 1; itemNumber <= gridSize; itemNumber++) {
				final Integer nextCellNumber = numberFunction.cellNumberToExtract(gridSize, unit, itemNumber);
				final Integer nextCellIndex = nextCellNumber - 1;
				cellsInUnit.add(cells.get(nextCellIndex));
			}
			unitList.add(new Unit(unitType, cellsInUnit));
		}
		
		return unitList;
	}
}
