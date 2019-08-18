package com.smks.personal.sudoku.solver;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.smks.personal.sudoku.data.Cell;
import com.smks.personal.sudoku.data.CellUpdate;
import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.data.UpdatedGrid;

/*
 * A Single is the only candidate value for a given cell.  This will find cells
 * which contain singles and set their values (future: update peers).
 */
public class SingleSetter {

	public UpdatedGrid solveSingles(final Grid grid) {
	
		final Set<Cell> loneSingles = grid.getPositionToCellMap()
				.values()
				.stream()
				.filter(cell -> cell.getValue().isEmpty())
				.filter(cell -> cell.getPossibleValues().size() == 1)
				.map(this::getNewCellWithSetValue)
				.collect(Collectors.toSet());

		loneSingles.stream().forEach(loneSingle -> grid.putUpdatedCell(loneSingle));
		
		final Set<CellUpdate> cellUpdates = loneSingles.stream()
				.map(cell -> CellUpdate.of(cell, Collections.emptySet(), this.getClass().getName()))
				.collect(Collectors.toSet())
				;
		return UpdatedGrid.of(grid, cellUpdates);
	}

	private Cell getNewCellWithSetValue(final Cell cell) {
		//System.out.println("Setting single: " + cell);
		final Optional<Integer> newValue = cell.getPossibleValues().stream().findFirst();
		return Cell.of(newValue, cell.getPosition(), Collections.emptySet());
	}
}
