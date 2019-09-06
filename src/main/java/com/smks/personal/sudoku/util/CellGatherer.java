package com.smks.personal.sudoku.util;

import static java.util.stream.Collectors.toSet;

import java.util.Set;

import com.smks.personal.sudoku.data.Cell;
import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.data.Position;
import com.smks.personal.sudoku.data.Unit;


public class CellGatherer {

	/*
	 * Return the cells that correspond to a given Unit
	 */
	public Set<Cell> getCellsForUnit(final Grid grid, final Unit unit) {
		
		return unit.getCellPositions()
				.stream()
				.map(position -> grid.getPositionToCellMap().get(position))
				.collect(toSet());
	}
	
	public Set<Cell> getPeerCellsOfPosition(final Grid grid, final Position position) {
		final Set<Unit> unitsThePositionBelongsTo = UnitUtils.getUnitsForPosition(position, grid);

		return unitsThePositionBelongsTo.stream()
				.map(unit -> getCellsForUnit(grid, unit))
				.flatMap(Set::stream)
				.filter(cell -> !cell.getPosition().equals(position))
				.collect(toSet());
	}
}
