package com.smks.personal.sudoku.validate;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.smks.personal.sudoku.data.Cell;
import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.data.Unit;
import com.smks.personal.sudoku.util.CellGatherer;

/*
 * Takes a Grid and validates that the board is complete and valid
 */
@Component
@ComponentScan("com.smks.personal.sudoku")
public class GridValidator {

	@Autowired
	private CellGatherer cellGatherer;
	
	public boolean isGridValid(final Grid grid) {
		
		if(!gridIsComplete(grid)) {
			return false;
		}
		
		return areUnitsValid(grid, grid.getColumns())
				&& areUnitsValid(grid, grid.getRows())
				&& areUnitsValid(grid, grid.getBlocks());

	}
	
	/*
	 * Checks that each Cell has an assigned value
	 */
	private boolean gridIsComplete(final Grid grid) {
		boolean hasCorrectCount = grid.getPositionToCellMap().size() == Math.pow(grid.getGridSize(), 2);
		
		if(!hasCorrectCount) return false;
		
		return grid.getPositionToCellMap()
				.values()
				.stream()
				.map(Cell::getValue)
				.allMatch(Optional::isPresent);
	}

	private boolean areUnitsValid(final Grid grid, final Set<Unit> units) {
		
		return units.stream()
				.map(unit -> cellGatherer.getCellsForUnit(grid, unit))
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
