package com.smks.personal.sudoku.init.unit;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.smks.personal.sudoku.data.Cell;
import com.smks.personal.sudoku.data.Position;
import com.smks.personal.sudoku.data.Unit;
import com.smks.personal.sudoku.data.UnitType;

/*
 * Used to initialize a list of units.  A unit is either a Row, Column, or Block
 * of cells.  Each Grid will have a number of units equal to the gridSize and a number
 * of items in that
 */
public class UnitInitializer {

	final static Map<UnitType, CellPositionFunction> POSITION_FUNCTIONS = Map.of(
			UnitType.BLOCK, new BlockCellPositionFunction(),
			UnitType.COLUMN, new ColumnCellPositionFunction(),
			UnitType.ROW, new RowCellPositionFunction());
	
	public static Set<Unit> initializeUnit(final Integer gridSize, 
			final List<Cell> cells, 
			final UnitType unitType) {		
		
		final Set<Unit> unitSet = IntStream.range(0, gridSize)
				.mapToObj(unitIndex -> generateUnit(gridSize, unitIndex, unitType))
				.collect(Collectors.toSet());

		return unitSet;
	}
	
	private static Unit generateUnit(final Integer gridSize,
			final Integer unitIndex,
			final UnitType unitType) {
		return Unit.of(unitType, unitIndex, getCellPositionsForUnit(gridSize, unitIndex, unitType));
	}
	
	private static Set<Position> getCellPositionsForUnit(final Integer gridSize, 
			final Integer unitIndex,
			final UnitType unitType) {
		return IntStream.range(0, gridSize)
				.mapToObj(itemIndex -> POSITION_FUNCTIONS.get(unitType)
						.getCellPositionForUnitAndItem(gridSize, unitIndex, itemIndex))
				.collect(Collectors.toSet());
	}
}
