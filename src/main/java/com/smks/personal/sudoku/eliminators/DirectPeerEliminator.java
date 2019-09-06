package com.smks.personal.sudoku.eliminators;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;
import com.smks.personal.sudoku.data.Cell;
import com.smks.personal.sudoku.data.CellUpdate;
import com.smks.personal.sudoku.data.Grid;
import com.smks.personal.sudoku.data.UpdatedGrid;
import com.smks.personal.sudoku.error.ErrorResult;
import com.smks.personal.sudoku.util.CellGatherer;
import com.smks.personal.sudoku.util.GridStateManager;

import io.vavr.control.Either;

/*
 * The most commonly used elimination method where the value of each peer
 * is looked at to eliminate possible values in the given cell.
 * 
 */

public class DirectPeerEliminator implements Eliminator {

	@Autowired
	private CellGatherer cellGatherer;
	@Autowired
	private GridStateManager gridStateManager;
	
	@Override
	public Either<ErrorResult, UpdatedGrid> apply(Either<ErrorResult, UpdatedGrid> previousUpdate) {
		return previousUpdate.flatMap(this::performElimination);
	}

	public Either<ErrorResult, UpdatedGrid> performElimination(UpdatedGrid previousGrid) {
		final Grid grid = previousGrid.getGrid();
		
		final List<CellUpdate> cellUpdates = grid.getPositionToCellMap()
				.values()
				.stream()
				.filter(cell -> cell.getValue().isEmpty())
				.map(cell -> reducePossibilities(grid, cell))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
		
		final UpdatedGrid newlyUpdatedGrid = gridStateManager.step(previousGrid, cellUpdates);
		
		return Either.right(newlyUpdatedGrid);
	}

	public Optional<CellUpdate> reducePossibilities(final Grid grid, final Cell cell) {
		final Set<Integer> eliminatedPossibilities = getEliminatedPossibilities(grid, cell);
		
		if(CollectionUtils.isEmpty(eliminatedPossibilities))
			return Optional.empty();

		final Set<Integer> newPossibilities = Sets.difference(cell.getPossibleValues(), eliminatedPossibilities);
		final Cell updatedCell = Cell.of(cell.getValue(), cell.getPosition(), newPossibilities);
		
		return Optional.of(CellUpdate.of(updatedCell, eliminatedPossibilities, this.getClass().getName()));
	}

	/*
	 * Returns a set of integers which represents values no longer
	 * possible for the given cell.
	 */
	private Set<Integer> getEliminatedPossibilities(final Grid grid, final Cell cell) {
		if(cell.getValue().isPresent()) {
			return Collections.emptySet();
		}
				
		final Set<Integer> valuesOfPeers = cellGatherer.getPeerCellsOfPosition(grid, cell.getPosition())
				.stream()
				.map(Cell::getValue)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toSet());
			
		return Sets.intersection(cell.getPossibleValues(), valuesOfPeers);
	}
}
