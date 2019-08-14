package com.smks.personal.sudoku.validate;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.smks.personal.sudoku.data.Cell;
import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.data.Unit;
import com.smks.personal.sudoku.util.CellGatherer;

/*
 * Takes a Grid and validates that the board is complete and valid
 */
public class GridValidator {

	public boolean isGridValid(final Grid grid) {
		
		if(!gridIsComplete(grid)) {
			return false;
		}
		
		//areUnitsValid(grid, grid.getColumns());
		return true;
	}
	
	/*
	 * Checks that each Cell has an assigned value
	 */
	private boolean gridIsComplete(final Grid grid) {
		return grid.getPositionToCellMap()
				.values()
				.stream()
				.map(Cell::getValue)
				.allMatch(Optional::isPresent);
	}
	
	private boolean areUnitsValid(final Grid grid, final List<Unit> units) {
		final CellGatherer cellsForUnit = new CellGatherer();
		
		return units.stream()
				.map(unit -> cellsForUnit.getCellsForUnit(grid, unit))
				.allMatch(cells -> isUnitValid(cells, grid.getGridSize()));
				
	}
	
	/*
	 * A unit is valid if it contains all distinct values within the range [1, gridSize]
	 */
	private boolean isUnitValid(final Set<Cell> cells, final Integer gridSize) {
		if(cells.size() != gridSize) return false;
		
		return cells.stream()
				.map(Cell::getValue)
				.map(Optional::get)
				.filter(value -> value >= 1 && value <= gridSize)
				.distinct()
				.count() == gridSize;
	}
}
