package com.smks.personal.sudoku.solver;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.smks.personal.sudoku.data.Cell;
import com.smks.personal.sudoku.data.CellUpdate;
import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.data.UpdatedGrid;
import com.smks.personal.sudoku.util.GridStateManager;

/*
 * A Single is the only candidate value for a given cell.  This will find cells
 * which contain singles and set their values (future: update peers).
 */
public class SingleSetter {

	@Autowired
	private GridStateManager gridStateManager;
	
	public UpdatedGrid solveSingles(final UpdatedGrid previous) {
		final Grid grid = previous.getGrid();
		
		final List<CellUpdate> cellUpdates = grid.getPositionToCellMap()
				.values()
				.stream()
				.filter(cell -> cell.getValue().isEmpty())
				.filter(cell -> cell.getPossibleValues().size() == 1)
				.map(this::getNewCellWithSetValue)
				.map(cell -> CellUpdate.of(cell, Collections.emptySet(), this.getClass().getName()))
				.collect(toList());

		return gridStateManager.step(previous, cellUpdates);
	}

	private Cell getNewCellWithSetValue(final Cell cell) {
		//System.out.println("Setting single: " + cell);
		final Optional<Integer> newValue = cell.getPossibleValues().stream().findFirst();
		return Cell.of(newValue, cell.getPosition(), Collections.emptySet());
	}
}
